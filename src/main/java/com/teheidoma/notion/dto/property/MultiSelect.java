package com.teheidoma.notion.dto.property;

import lombok.Data;

import java.util.List;

@Data
public class MultiSelect {
    private List<Option> options;

    @Data
    public static class Option {
        private String id;
        private String name;
        private String color;
        private String description;
    }
}
