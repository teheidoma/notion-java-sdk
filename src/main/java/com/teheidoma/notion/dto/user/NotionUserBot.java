package com.teheidoma.notion.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotionUserBot extends NotionUser {
    private Bot bot;

    @Data
    private static class Bot {
        private Owner owner;
        private String workspaceName;
    }

    private static class Owner {
        private String ownerType;//TODO
    }
}
