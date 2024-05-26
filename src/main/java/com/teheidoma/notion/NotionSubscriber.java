package com.teheidoma.notion;

import com.google.gson.JsonElement;
import com.teheidoma.notion.dto.page.NotionPage;
import com.teheidoma.notion.dto.property.NotionProperty;
import com.teheidoma.notion.dto.query.NotionQueryResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class NotionSubscriber {
    private static final Logger log = LoggerFactory.getLogger(NotionSubscriber.class);
    private final NotionClient notionClient;

    public Flux<NotionPage> subscribeNewPage(String databaseId) {
        Set<String> ids = new HashSet<>(notionClient.query(databaseId, null).getResults().stream().map(NotionPage::getId).toList());

        return Flux.interval(Duration.ZERO, Duration.ofSeconds(5))
                .flatMap(secs -> Flux.fromIterable(notionClient.query(databaseId, null).getResults()))
                .filter(page -> !ids.contains(page.getId()))
                .log()
                .doOnNext(page -> ids.add(page.getId()));
    }

    public Flux<NotionUpdatedProperty> subscribeUpdatedProperty(String databaseId, String... properties) {
        Map<String, List<NotionUpdatedProperty>> savedProperties = new HashMap<>();
        NotionQueryResult init = notionClient.query(databaseId, null);
        init.getResults().forEach(page -> {
            getProperties(page, properties).forEach(property -> savedProperties.computeIfAbsent(property.getPageId(), k -> new ArrayList<>()).add(property));
        });
        log.info("registered properties {}", savedProperties);
        return Flux.interval(Duration.ZERO, Duration.ofSeconds(5))
                .flatMap(secs -> Flux.fromIterable(notionClient.query(databaseId, null).getResults()))
                .flatMap(page -> Flux.fromIterable(Stream.of(properties)
                        .map(property -> new NotionUpdatedProperty(
                                page.getId(),
                                property,
                                page.getProperties().get(property),
                                null
                        ))
                        .toList()))
                .mapNotNull(property -> {
                    Optional<NotionUpdatedProperty> propertyOptional = savedProperties.computeIfAbsent(property.getPageId(), this::getNewPage)
                            .stream()
                            .filter(savedProperty -> savedProperty.getName().equals(property.getName()))
                            .filter(savedProperty -> !savedProperty.getValue().equals(property.getValue()))
                            .findFirst();

                    if (propertyOptional.isPresent()) {
                        propertyOptional.ifPresent(p -> {
                            var oldValue = p.getValue();
                            p.setValue(property.getValue());
                            p.setOldValue(oldValue);
                        });
                        log.info("changed property {}", property.getName());
                        return propertyOptional.get();
                    }
                    return null;
                });
    }

    private List<NotionUpdatedProperty> getNewPage(String pageId) {
        return notionClient.getPage(pageId)
                .getProperties().entrySet().stream()
                .map(property -> new NotionUpdatedProperty(
                        pageId,
                        property.getKey(),
                        property.getValue(),
                        null
                ))
                .toList();
    }

    private List<NotionUpdatedProperty> getProperties(NotionPage page, String... properties) {
        List<NotionUpdatedProperty> result = new ArrayList<>();
        for (var property : properties) {
            result.add(new NotionUpdatedProperty(
                    page.getId(),
                    property,
                    page.getProperties().get(property),
                    null
            ));
        }
        return result;
    }


    @Data
    @AllArgsConstructor
    public static class NotionUpdatedProperty {
        private String pageId;
        private String name;
        private NotionProperty value;
        private NotionProperty oldValue;

        public <T> T getValueAs(Class<T> clazz) {
            return clazz.cast(value);
        }

        public <T> T getOldValueAs(Class<T> clazz) {
            return clazz.cast(value);
        }
    }
}
