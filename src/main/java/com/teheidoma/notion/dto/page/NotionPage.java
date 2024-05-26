package com.teheidoma.notion.dto.page;

import com.teheidoma.notion.dto.NotionBaseDTO;
import com.teheidoma.notion.dto.block.NotionBlock;
import com.teheidoma.notion.dto.parent.NotionParent;
import com.teheidoma.notion.dto.property.NotionProperty;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NotionPage extends NotionBaseDTO {
    private Timestamp createdTime;
    private Timestamp lastEditedTime;
    private NotionParent parent;
    @Singular("property")
    private Map<String, NotionProperty> properties;

    public NotionPage updateProperties(Map<String, NotionProperty> properties) {
        return getNotionClient().updatePageProperty(getId(), properties);
    }

    public List<NotionBlock> getBlocks() {
        return getNotionClient().getBlocks(getId()).getResults();
    }


    public <T extends NotionProperty> T getPropertyByName(String name, Class<T> propertyClass) {
        return propertyClass.cast(properties.get(name));
    }

    public <T extends NotionProperty> T getPropertyById(String id, Class<T> propertyClass) {
        return properties.values().stream()
                .findFirst()
                .map(propertyClass::cast)
                .orElseThrow();
    }
}
