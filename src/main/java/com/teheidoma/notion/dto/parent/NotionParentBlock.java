package com.teheidoma.notion.dto.parent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionParentBlock extends NotionParent {
    private String blockId;
}
