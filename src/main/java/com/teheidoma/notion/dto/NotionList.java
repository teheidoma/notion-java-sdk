package com.teheidoma.notion.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotionList<T extends NotionBaseDTO> {
    private List<T> results;
}
