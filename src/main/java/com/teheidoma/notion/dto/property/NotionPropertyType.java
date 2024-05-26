package com.teheidoma.notion.dto.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotionPropertyType {
    MULTI_SELECT("multi_select", NotionMultiselectProperty.class),
    STATUS("status", NotionStatusProperty.class),
    TITLE("title", NotionTitleProperty.class),
    RICH_TEXT("rich_text", NotionRichTextProperty.class);

    private final String type;
    private final Class<? extends NotionProperty> propertyClass;


    public static NotionPropertyType findByValue(String value) {
        for (NotionPropertyType notionParentType : NotionPropertyType.values()) {
            if (notionParentType.type.equals(value)) {
                return notionParentType;
            }
        }
        return RICH_TEXT;
    }
}