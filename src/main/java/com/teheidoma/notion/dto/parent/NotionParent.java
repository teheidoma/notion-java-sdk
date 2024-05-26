package com.teheidoma.notion.dto.parent;

import com.teheidoma.notion.dto.NotionBaseDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class NotionParent extends NotionBaseDTO {
    private NotionParentType type;
}
