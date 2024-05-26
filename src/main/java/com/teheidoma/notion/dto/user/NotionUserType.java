package com.teheidoma.notion.dto.user;

import com.teheidoma.notion.dto.parent.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
@Getter
public enum NotionUserType {
    PERSON("page_id", NotionParentPage.class),
    BOT("database_id", NotionParentDatabase.class);

    private final String type;
    private final Class<? extends NotionParent> parentClass;


    @Nullable
    public static NotionUserType findByValue(String value) {
        for (NotionUserType notionParentType : NotionUserType.values()) {
            if (notionParentType.getType().equals(value)) {
                return notionParentType;
            }
        }
        return null;
    }
}
