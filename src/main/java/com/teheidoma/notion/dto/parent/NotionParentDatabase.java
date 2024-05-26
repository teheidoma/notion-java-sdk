package com.teheidoma.notion.dto.parent;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionParentDatabase extends NotionParent {
    private String databaseId;

    public static NotionParentDatabase fromString(String id) {
        return builder()
                .databaseId(id)
                .build();
    }
}
