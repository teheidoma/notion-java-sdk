package com.teheidoma.notion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotionVersion {
    V220628("2022-06-28"),
    LATEST("2022-06-28");

    private final String version;
}
