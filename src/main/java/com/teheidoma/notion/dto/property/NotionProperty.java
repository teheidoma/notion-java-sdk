package com.teheidoma.notion.dto.property;

import com.teheidoma.notion.dto.NotionBaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionProperty extends NotionBaseDTO {
    private String name;
    private String description;
    private NotionPropertyType type;
}
