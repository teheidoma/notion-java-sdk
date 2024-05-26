package com.teheidoma.notion.dto.block;

import com.teheidoma.notion.dto.NotionBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NotionBlockResponse extends NotionBaseDTO {
    private List<NotionBlock> results;
}
