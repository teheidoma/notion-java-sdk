package com.teheidoma.notion.dto.query;

import com.teheidoma.notion.dto.NotionBaseDTO;
import com.teheidoma.notion.dto.page.NotionPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotionQueryResult extends NotionBaseDTO {
    private List<NotionPage> results;
}
