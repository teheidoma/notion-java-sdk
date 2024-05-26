package com.teheidoma.notion.dto.property;

import lombok.experimental.SuperBuilder;
import lombok.Data;

@Data
@SuperBuilder
public class RichText {
    private String type;
    private Text text;

    public static RichText fromString(String text) {
        return RichText.builder()
                .text(Text.fromString(text))
                .build();
    }

    @Data
    @SuperBuilder
    public static class Text {
        private String content;
        private String link;
        private String plaintText;
        private String href;
        private Annotations annotations;

        public static Text fromString(String text) {
            return Text.builder()
                    .content(text)
                    .build();
        }
    }

    @Data
    public static class Annotations {
        private boolean bold;
        private boolean italic;
        private boolean strikethrough;
        private boolean underline;
        private boolean code;
        private String color;
    }
}
