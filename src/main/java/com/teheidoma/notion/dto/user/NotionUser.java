package com.teheidoma.notion.dto.user;

import com.teheidoma.notion.dto.NotionBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotionUser extends NotionBaseDTO {
    private String name;
    private NotionUserType type;
}
