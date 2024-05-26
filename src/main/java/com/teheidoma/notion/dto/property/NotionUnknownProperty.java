package com.teheidoma.notion.dto.property;

import com.google.gson.JsonElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionUnknownProperty extends NotionProperty {
    private JsonElement data;
}
