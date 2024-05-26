package com.teheidoma.notion.dto;

import com.teheidoma.notion.dto.page.NotionPage;
import com.teheidoma.notion.dto.parent.NotionParent;
import com.teheidoma.notion.dto.parent.NotionParentDatabase;
import com.teheidoma.notion.dto.property.NotionProperty;
import com.teheidoma.notion.dto.query.NotionQueryResult;
import com.teheidoma.notion.dto.user.NotionUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionDatabase extends NotionBaseDTO {
    private String cover;
    private String icon;
    private Timestamp createdTime;
    private NotionUser createdBy;
    private Map<String, NotionProperty> properties;
    private NotionParent parent;


    public <T extends NotionProperty> T getPropertyByName(String name, Class<T> propertyClass) {
        return propertyClass.cast(properties.get(name));
    }

    public <T extends NotionProperty> T getPropertyById(String id, Class<T> propertyClass) {
        return properties.values().stream()
                .findFirst()
                .map(propertyClass::cast)
                .orElseThrow();
    }

    public NotionQueryResult query(String query) {
        NotionQueryResult response = getNotionClient().query(getId(), query);
        response.getResults().forEach(page -> page.setNotionClient(getNotionClient()));
        return response;
    }

    public NotionQueryResult query() {
        return query(null);
    }

    public NotionPage createPage(Consumer<NotionPage.NotionPageBuilder> builder) {
        Consumer<NotionPage.NotionPageBuilder> parentBuilder = b -> {
            b.parent(NotionParentDatabase.fromString(getId()));
            builder.accept(b);
        };
        return getNotionClient().createPage(parentBuilder);
    }
}
