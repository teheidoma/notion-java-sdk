package com.teheidoma.notion.dto;

import com.teheidoma.notion.NotionClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NotionBaseDTO implements Serializable {
    private String id;
    private NotionObjectType object;
    private transient NotionClient notionClient;
}
