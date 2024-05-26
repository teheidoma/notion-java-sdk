package com.teheidoma.notion.dto.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionIdProperty extends NotionProperty {
    private UniqueId uniqueId;

    @Data
    public static class UniqueId {
        private Integer number;
        private String prefix;

        @Override
        public String toString() {
            return prefix + "-" + number;
        }
    }
}
