package com.teheidoma.notion;

import com.google.gson.JsonElement;
import com.teheidoma.notion.dto.NotionDatabase;
import com.teheidoma.notion.dto.block.NotionBlockResponse;
import com.teheidoma.notion.dto.page.NotionPage;
import com.teheidoma.notion.dto.property.NotionProperty;
import com.teheidoma.notion.dto.query.NotionQueryResult;
import com.teheidoma.notion.http.JavaNetNotionHttpClient;
import com.teheidoma.notion.http.NotionHttpClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class NotionClient {
    private final String apiKey;
    private final NotionVersion notionVersion;
    @Setter
    private NotionHttpClient notionHttpClient = new JavaNetNotionHttpClient(this);

    public NotionClient(String apiKey) {
        this(apiKey, NotionVersion.LATEST);
    }

    public NotionDatabase getDatabase(String id) {
        return notionHttpClient.get("/databases/" + id, NotionDatabase.class);
    }

    public NotionQueryResult query(String databaseId, @Nullable String query) {
        return notionHttpClient.post("/databases/" + databaseId + "/query", query, NotionQueryResult.class);
    }

    public JsonElement queryForJson(String databaseId, @Nullable String query) {
        return notionHttpClient.post("/databases/" + databaseId + "/query", query, JsonElement.class);
    }

    public NotionPage createPage(Consumer<NotionPage.NotionPageBuilder> builderSupplier) {
        var builder = NotionPage.builder();
        builderSupplier.accept(builder);
        var page = builder.build();
        return notionHttpClient.post("/pages", page, NotionPage.class);
    }

    public NotionPage getPage(String id) {
        return notionHttpClient.get("/pages/" + id, NotionPage.class);
    }

    public NotionPage updatePageProperty(String id, Map<String, NotionProperty> properties) {
        return notionHttpClient.patch("/pages/" + id, Map.of("properties", properties), NotionPage.class);
    }

    public NotionProperty getPageProperty(String pageId, String propertyId) {
        return notionHttpClient.get(String.format("/pages/%s/properties/%s", pageId, propertyId), NotionProperty.class);
    }

    public NotionBlockResponse getBlocks(String parentId) {
        return notionHttpClient.get("/blocks/"+parentId+"/children?page_size=100", NotionBlockResponse.class);
    }
}