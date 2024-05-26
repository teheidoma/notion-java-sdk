package com.teheidoma.notion.dto.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionCreatedByProperty extends NotionProperty{
    private CreatedBy createdBy;

    @Data
    public static class CreatedBy {
        private String object;
        private String id;
    }
}
