
package com.teheidoma.notion.http;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teheidoma.notion.NotionClient;
import com.teheidoma.notion.dto.NotionBaseDTO;
import com.teheidoma.notion.dto.block.NotionBlock;
import com.teheidoma.notion.dto.block.json.NotionBlockDeserializer;
import com.teheidoma.notion.dto.parent.NotionParent;
import com.teheidoma.notion.dto.parent.json.NotionParentDeserializer;
import com.teheidoma.notion.dto.property.NotionProperty;
import com.teheidoma.notion.dto.property.json.NotionPropertyDeserializer;
import com.teheidoma.notion.dto.user.NotionUser;
import com.teheidoma.notion.dto.user.json.NotionUserDeserializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Slf4j
public class JavaNetNotionHttpClient implements NotionHttpClient{
    private final String BASE_URL = "https://api.notion.com/v1";
    @Setter
    private HttpClient httpClient = HttpClient.newHttpClient();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(NotionParent.class, new NotionParentDeserializer())
            .registerTypeAdapter(NotionUser.class, new NotionUserDeserializer())
            .registerTypeAdapter(NotionProperty.class, new NotionPropertyDeserializer())
            .registerTypeAdapter(NotionBlock.class, new NotionBlockDeserializer())
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    @Getter
    private final NotionClient notionClient;


    @SneakyThrows
    public <T extends NotionBaseDTO> T get(String url, Class<T> targetClass) {
        return execute(url, null, "GET", targetClass);
    }

    private void populateHeaders(HttpRequest.Builder builder) {
        builder.header("Accept", "application/json");
        builder.header("Content-Type", "application/json");
        builder.header("Authorization", "Bearer " + notionClient.getApiKey());
        builder.header("Notion-Version", notionClient.getNotionVersion().getVersion());
        builder.header("User-Agent", "Apidog/1.0.0 (https://apidog.com)");
    }

    public <T> T post(String url, Object payload, Class<T> targetClass) {
        return execute(url, payload, "POST", targetClass);
    }

    public <T extends NotionBaseDTO> T patch(String url, Object payload, Class<T> targetClass) {
        return execute(url, payload, "PATCH", targetClass);
    }

    @SneakyThrows

    private <T> T execute(String url, Object payload, String method, Class<T> targetClass) {
        HttpRequest.BodyPublisher body;
        String json = gson.toJson(payload);
        if (payload != null) {
            body = HttpRequest.BodyPublishers.ofString(json);
        } else {
            body = HttpRequest.BodyPublishers.noBody();
        }
        HttpRequest.Builder builder = HttpRequest.newBuilder(URI.create(BASE_URL + url)).method(method, body);
        log.debug("{} {} \n {}", method, url, json);
        populateHeaders(builder);
        HttpRequest request = builder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.debug("response {} \n {}", url, response.body());
        return gson.fromJson(response.body(), targetClass);
    }
}
