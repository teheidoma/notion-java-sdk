package com.teheidoma.notion.http;

import com.teheidoma.notion.NotionClient;
import com.teheidoma.notion.dto.NotionBaseDTO;



public interface NotionHttpClient {
    NotionClient getNotionClient();

    <T extends NotionBaseDTO> T get(String url, Class<T> targetClass);

    <T> T post(String url, Object payload, Class<T> targetClass);

    <T extends NotionBaseDTO> T patch(String url, Object payload, Class<T> targetClass);
}
