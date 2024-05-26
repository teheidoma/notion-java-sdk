
package com.teheidoma.notion.dto.property;

import lombok.Singular;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionStatusProperty extends NotionProperty {
    private Status status;

    public static NotionStatusProperty name(String status) {
        return builder()
                .status(Status.name(status))
                .build();
    }

    @Data
    @SuperBuilder
    public static class Status {
        private String id;
        private String name;
        private List<Option> options;

        public static Status fromOption(Option... option) {
            return Status.builder()
                    .options(List.of(option))
                    .build();
        }

        public static Status name(String name) {
            return Status.builder()
                    .name(name)
                    .build();
        }
    }

    @Data
    @SuperBuilder
    public static class Option {
        private String id;
        private String name;
        private String color;
        private String description;

        public static Option fromString(String option) {
            return Option.builder()
                    .name(option)
                    .build();
        }
    }
}
