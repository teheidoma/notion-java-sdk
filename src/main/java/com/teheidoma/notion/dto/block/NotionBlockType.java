package com.teheidoma.notion.dto.block;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotionBlockType {
    PARAGRAPH("paragraph", NotionBlockParagraph.class),
    CODE("code", NotionBlockCode.class);


    private final String type;
    private final Class<? extends NotionBlock> propertyClass;


    public static NotionBlockType findByValue(String value) {
        for (NotionBlockType type : NotionBlockType.values()) {
            if (type.type.equals(value)) {
                return type;
            }
        }
        return PARAGRAPH;
    }
}
