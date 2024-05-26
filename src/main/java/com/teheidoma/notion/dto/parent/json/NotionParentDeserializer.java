package com.teheidoma.notion.dto.parent.json;

import com.google.gson.*;
import com.teheidoma.notion.dto.parent.NotionParent;
import com.teheidoma.notion.dto.parent.NotionParentType;

import java.lang.reflect.Type;

public class NotionParentDeserializer implements JsonDeserializer<NotionParent> {

    @Override
    public NotionParent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject asJsonObject = json.getAsJsonObject();
        NotionParentType type = NotionParentType.findByValue(asJsonObject.get("type").getAsString());

        return context.deserialize(json, type.getParentClass());
    }
}
