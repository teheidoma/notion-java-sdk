package com.teheidoma.notion.dto.block.json;

import com.google.gson.*;
import com.teheidoma.notion.dto.block.NotionBlock;
import com.teheidoma.notion.dto.block.NotionBlockType;
import com.teheidoma.notion.dto.parent.NotionParent;
import com.teheidoma.notion.dto.parent.NotionParentType;

import java.lang.reflect.Type;

public class NotionBlockDeserializer implements JsonDeserializer<NotionBlock> {

    @Override
    public NotionBlock deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject asJsonObject = json.getAsJsonObject();
        NotionBlockType type = NotionBlockType.findByValue(asJsonObject.get("type").getAsString());

        return context.deserialize(json, type.getPropertyClass());
    }
}
