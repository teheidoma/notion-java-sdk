package com.teheidoma.notion.dto.property;

import lombok.Singular;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionRichTextProperty extends NotionProperty {
    @Singular("richText")
    private List<RichText> richText;

    public static NotionRichTextProperty fromString(String text) {
        return builder()
                .richText(RichText.fromString(text))
                .build();
    }
}
