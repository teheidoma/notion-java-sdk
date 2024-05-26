package com.teheidoma.notion.dto.parent;

import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionParentPage extends NotionParent {
    private String pageId;
}
