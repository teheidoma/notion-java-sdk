package com.teheidoma.notion.dto.parent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotionParentType {
    PAGE("page_id", NotionParentPage.class),
    DATABASE("database_id", NotionParentDatabase.class),
    WORKSPACE("workspace_id", NotionParentWorkspace.class),
    BLOCK("block_id", NotionParentBlock.class);

    private final String type;
    private final Class<? extends NotionParent> parentClass;


    public static NotionParentType findByValue(String value) {
        for (NotionParentType notionParentType : NotionParentType.values()) {
            if (notionParentType.type.equals(value)) {
                return notionParentType;
            }
        }
        throw new IllegalArgumentException("No NotionParentType with value: " + value);
    }
}
