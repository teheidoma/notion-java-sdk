package com.teheidoma.notion.dto.property;

import lombok.Singular;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotionTitleProperty extends NotionProperty {
    @Singular("title")
    private List<RichText> title;

    public static NotionTitleProperty fromString(String text) {
        return builder()
                .title(List.of(
                        RichText.fromString(text)
                ))
                .build();
    }

    public String getAsText() {
        return title.stream()
                .map(text -> text.getText().getContent())
                .collect(Collectors.joining(""));
    }
}
