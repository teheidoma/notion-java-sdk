package com.teheidoma.notion.dto.block;

import com.teheidoma.notion.dto.NotionBaseDTO;
import com.teheidoma.notion.dto.property.RichText;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NotionBlockCode extends NotionBlock {
    private Code code;

    @Data
    public static class Code {
        private List<RichText> caption;
        private List<RichText> richText;
        private String language;
    }
}
