package com.teheidoma.notion.dto.property;

import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionCheckboxProperty extends NotionProperty{
    private boolean checkbox;

    public static NotionCheckboxProperty fromBoolean(boolean checkbox) {
        return builder()
                .checkbox(checkbox)
                .build();
    }
}
