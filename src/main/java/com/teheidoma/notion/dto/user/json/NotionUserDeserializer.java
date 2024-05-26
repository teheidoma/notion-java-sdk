package com.teheidoma.notion.dto.user.json;

import com.google.gson.*;
import com.teheidoma.notion.dto.user.NotionUser;
import com.teheidoma.notion.dto.user.NotionUserPerson;
import com.teheidoma.notion.dto.user.NotionUserType;

import java.lang.reflect.Type;

public class NotionUserDeserializer implements JsonDeserializer<NotionUser> {

    @Override
    public NotionUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject asJsonObject = json.getAsJsonObject();
        if (asJsonObject.has("type")) {
            NotionUserType type = NotionUserType.findByValue(asJsonObject.get("type").getAsString());
            if (type == null) return context.deserialize(json, NotionUser.class);
            return context.deserialize(json, type.getParentClass());
        } else {
            return context.deserialize(json, NotionUserPerson.class);
        }
    }
}
