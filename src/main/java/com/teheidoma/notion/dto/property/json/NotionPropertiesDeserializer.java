package com.teheidoma.notion.dto.property.json;


import com.google.gson.*;
import com.teheidoma.notion.dto.property.NotionProperties;
import com.teheidoma.notion.dto.property.NotionProperty;

import java.lang.reflect.Type;
import java.util.Map;

public class NotionPropertiesDeserializer implements JsonDeserializer<NotionProperties> {
    @Override
    public NotionProperties deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        NotionProperties properties = new NotionProperties();
        json.getAsJsonObject().entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), context.<NotionProperty>deserialize(entry.getValue(), NotionProperty.class)))
                .forEach(entry -> {
                    entry.getValue().setName(entry.getKey());
                    properties.put(entry.getKey(), entry.getValue());
                });
        return properties;
    }
}
