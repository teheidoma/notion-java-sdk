package com.teheidoma.notion.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotionUserPerson extends NotionUser {
    private Person person;
}
