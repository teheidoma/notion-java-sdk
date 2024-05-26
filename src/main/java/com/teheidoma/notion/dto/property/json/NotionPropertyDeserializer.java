package com.teheidoma.notion.dto.property.json;

import com.google.gson.*;
import com.teheidoma.notion.dto.property.*;

import java.lang.reflect.Type;

public class NotionPropertyDeserializer implements JsonDeserializer<NotionProperty> {
    @Override
    public NotionProperty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        NotionPropertyType type = NotionPropertyType.findByValue(jsonObject.get("type").getAsString());
        if (type == null) {
            return NotionUnknownProperty.builder()
                    .data(json)
                    .build();
        }
        Class<?> targetClass = type.getPropertyClass();
        if (type == NotionPropertyType.RICH_TEXT && jsonObject.get("rich_text").isJsonObject()) {
            return NotionRichTextProperty.builder()
                    .id(jsonObject.get("id").getAsString())
                    .name(jsonObject.get("name").getAsString())
                    .type(type)
                    .richText(getFromSingular(jsonObject.get("rich_text"), context, RichText.class))
                    .build();
        } else if (type == NotionPropertyType.MULTI_SELECT && jsonObject.get("multi_select").isJsonObject()) {
            return NotionMultiselectProperty.builder()
                    .id(jsonObject.get("id").getAsString())
                    .name(jsonObject.get("name").getAsString())
                    .type(type)
                    .multiSelect(getFromSingular(jsonObject.get("multi_select"), context, Option.class))
                    .build();
        } else if (type == NotionPropertyType.TITLE && jsonObject.get("title").isJsonObject()) {
            return NotionTitleProperty.builder()
                    .id(jsonObject.get("id").getAsString())
                    .name(jsonObject.get("name").getAsString())
                    .type(type)
                    .title(getFromSingular(jsonObject.get("title"), context, RichText.class))
                    .build();
        }
        NotionProperty property = context.deserialize(json, targetClass);
        property.setType(type);
        return property;
    }

    private <T> T getFromSingular(JsonElement json, JsonDeserializationContext context, Class<T> type) {
        return context.deserialize(json.getAsJsonObject(), type);
    }

}
