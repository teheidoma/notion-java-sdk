package com.teheidoma.notion.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotionObjectType {
    @SerializedName("user")
    USER,
    @SerializedName("database")
    DATABASE,
    @SerializedName("list")
    LIST;
}
