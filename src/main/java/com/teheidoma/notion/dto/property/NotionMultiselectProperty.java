package com.teheidoma.notion.dto.property;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
public class NotionMultiselectProperty extends NotionProperty {
    @Singular("multiSelect")
    private List<MultiSelect> multiSelect;
}
