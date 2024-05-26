package com.teheidoma.notion.dto.block;

import com.teheidoma.notion.dto.NotionBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NotionBlock extends NotionBaseDTO {
    private NotionBlockType type;
}
