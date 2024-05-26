package com.teheidoma.notion.dto.parent;

import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionParentWorkspace extends NotionParent {
    private String workspaceId;
}
