import com.teheidoma.notion.NotionClient;
import com.teheidoma.notion.http.JavaNetNotionHttpClient;
import com.teheidoma.notion.http.NotionHttpClient;
import com.teheidoma.notion.dto.NotionDatabase;
import com.teheidoma.notion.dto.NotionObjectType;
import com.teheidoma.notion.dto.property.NotionMultiselectProperty;
import com.teheidoma.notion.dto.property.NotionRichTextProperty;
import com.teheidoma.notion.dto.property.NotionStatusProperty;
import com.teheidoma.notion.dto.property.NotionTitleProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {
    private final NotionClient client = new NotionClient("");
    private final JavaNetNotionHttpClient httpClient = new JavaNetNotionHttpClient(client);
    @Mock
    private HttpClient http;
    @Mock
    private HttpResponse<Object> response;

    @BeforeEach
    public void setup() throws IOException, InterruptedException {
        client.setNotionHttpClient(httpClient);
        httpClient.setHttpClient(http);
        when(http.send(any(), any())).thenReturn(response);
    }

    @Test
    void testTitle() throws IOException, InterruptedException {
        when(response.body()).thenReturn("{\"object\":\"database\",\"id\":\"4322598d-7b9b-45ac-bbed-5b82b0661dd7\",\"cover\":null,\"icon\":null,\"created_time\":\"2024-05-09T17:45:00.000Z\",\"created_by\":{\"object\":\"user\",\"id\":\"dbbc0335-85a1-4f29-bb44-a9f776bcebcb\"},\"last_edited_by\":{\"object\":\"user\",\"id\":\"dbbc0335-85a1-4f29-bb44-a9f776bcebcb\"},\"last_edited_time\":\"2024-05-14T23:52:00.000Z\",\"title\":[],\"description\":[],\"is_inline\":false,\"properties\":{\"Answered\":{\"id\":\"I%3FxM\",\"name\":\"Answered\",\"type\":\"rich_text\",\"rich_text\":{}},\"Multi-select\":{\"id\":\"S_Q_\",\"name\":\"Multi-select\",\"type\":\"multi_select\",\"multi_select\":{\"options\":[{\"id\":\"pxEQ\",\"name\":\"3\",\"color\":\"purple\",\"description\":null},{\"id\":\"@M<A\",\"name\":\"2\",\"color\":\"pink\",\"description\":null},{\"id\":\"lC]L\",\"name\":\"1\",\"color\":\"default\",\"description\":null}]}},\"Status\":{\"id\":\"_RfF\",\"name\":\"Status\",\"type\":\"status\",\"status\":{\"options\":[{\"id\":\"5f653804-1d38-44cb-a688-6813e85455dd\",\"name\":\"Not started\",\"color\":\"default\",\"description\":null},{\"id\":\"909c9a08-1c72-47cb-9c42-b14529bf96b3\",\"name\":\"In progress\",\"color\":\"blue\",\"description\":null},{\"id\":\"ead6b28e-992f-47b5-9f77-faa5954d8c6b\",\"name\":\"Done\",\"color\":\"green\",\"description\":null}],\"groups\":[{\"id\":\"6596f10a-9ffe-43ca-8f4d-95707d760b05\",\"name\":\"To-do\",\"color\":\"gray\",\"option_ids\":[\"5f653804-1d38-44cb-a688-6813e85455dd\"]},{\"id\":\"f8123c7c-bedd-4d05-bc81-565f571cfaa2\",\"name\":\"In progress\",\"color\":\"blue\",\"option_ids\":[\"909c9a08-1c72-47cb-9c42-b14529bf96b3\"]},{\"id\":\"6f311b9d-352c-43cf-ab4f-68f8804b46a1\",\"name\":\"Complete\",\"color\":\"green\",\"option_ids\":[\"ead6b28e-992f-47b5-9f77-faa5954d8c6b\"]}]}},\"Tags\":{\"id\":\"%7Bx%60%5D\",\"name\":\"Tags\",\"type\":\"multi_select\",\"multi_select\":{\"options\":[]}},\"Name\":{\"id\":\"title\",\"name\":\"Name\",\"type\":\"title\",\"title\":{}}},\"parent\":{\"type\":\"page_id\",\"page_id\":\"f096f824-0c23-4ba9-9d9e-38eb8610aebd\"},\"url\":\"https://www.notion.so/4322598d7b9b45acbbed5b82b0661dd7\",\"public_url\":null,\"archived\":false,\"in_trash\":false,\"request_id\":\"31068455-5832-4176-923b-859b2aecbce5\"}");
        NotionDatabase database = client.getDatabase("");
        System.out.println(database);
        assertEquals("4322598d-7b9b-45ac-bbed-5b82b0661dd7", database.getId());
        assertEquals(NotionObjectType.DATABASE, database.getObject());
        assertEquals(5, database.getProperties().size());
        assertEquals(NotionTitleProperty.class, database.getProperties().get("Name").getClass());
        assertEquals(NotionRichTextProperty.class, database.getProperties().get("Answered").getClass());
        assertEquals(NotionMultiselectProperty.class, database.getProperties().get("Multi-select").getClass());
        assertEquals(NotionStatusProperty.class, database.getProperties().get("Status").getClass());
    }
}
