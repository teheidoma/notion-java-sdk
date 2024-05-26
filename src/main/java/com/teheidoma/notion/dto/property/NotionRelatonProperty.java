package com.teheidoma.notion.dto.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionRelatonProperty extends NotionProperty {
    private List<Relation> relation;

    @Data
    public static class Relation {
        private String id;
    }
}
