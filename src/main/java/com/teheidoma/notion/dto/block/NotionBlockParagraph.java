package com.teheidoma.notion.dto.block;

import com.teheidoma.notion.dto.property.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NotionBlockParagraph extends NotionBlock {
    private Paragraph paragraph;

    @Data
    public static class Paragraph {
        private List<RichText> richText;
        private String color;
    }
}
