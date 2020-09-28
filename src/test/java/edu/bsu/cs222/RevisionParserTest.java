package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class RevisionParserTest {

    @Test
    public void testCountRevisions() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String,JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        JsonElement firstRevision = array.get(0);
        JsonObject firstObject = firstRevision.getAsJsonObject();
        String name = firstObject.get("user").toString();
        Assertions.assertEquals("\"DVdm\"", name);
    }
    @Test
    public void testParseReturnsJsonObjectList() throws IOException {
        RevisionParser parser = new RevisionParser();
        WikipediaConnection wikipediaConnection = new WikipediaConnection();
        URLConnection connection = wikipediaConnection.connectToWikipedia("zappa");
        JsonObject query = wikipediaConnection.readJsonDataFrom(connection);
        List<JsonObject> revisionsList = parser.parse(query);
        Assertions.assertEquals("[{\"user\":\"Davenold\",\"timestamp\":\"2020-09-27T14:29:15Z\"}, {\"user\":\"DVdm\",\"timestamp\":\"2020-09-21T11:32:46Z\"}, {\"user\":\"179.53.16.150\",\"anon\":\"\",\"timestamp\":\"2020-09-21T09:22:33Z\"}, {\"user\":\"Strudjum\",\"timestamp\":\"2020-09-20T03:13:40Z\"}, {\"user\":\"ClueBot NG\",\"timestamp\":\"2020-09-17T20:57:00Z\"}, {\"user\":\"176.58.214.213\",\"anon\":\"\",\"timestamp\":\"2020-09-17T20:56:41Z\"}, {\"user\":\"Binksternet\",\"timestamp\":\"2020-09-10T22:30:02Z\"}, {\"user\":\"2601:5C2:300:43D0:182E:95C0:F1C:8ADD\",\"anon\":\"\",\"timestamp\":\"2020-09-10T22:01:36Z\"}, {\"user\":\"ClueBot NG\",\"timestamp\":\"2020-09-10T21:58:57Z\"}, {\"user\":\"2601:5C2:300:43D0:182E:95C0:F1C:8ADD\",\"anon\":\"\",\"timestamp\":\"2020-09-10T21:58:41Z\"}, {\"user\":\"Materialscientist\",\"timestamp\":\"2020-09-02T17:21:41Z\"}, {\"user\":\"2600:8800:2900:507:71BC:81A0:B42B:8E80\",\"anon\":\"\",\"timestamp\":\"2020-09-02T17:20:55Z\"}, {\"user\":\"Ser Amantio di Nicolao\",\"timestamp\":\"2020-08-25T01:34:59Z\"}, {\"user\":\"Blablubbs\",\"timestamp\":\"2020-08-24T20:03:45Z\"}, {\"user\":\"86.172.94.248\",\"anon\":\"\",\"timestamp\":\"2020-08-24T20:02:44Z\"}, {\"user\":\"Neveselbert\",\"timestamp\":\"2020-08-17T21:54:47Z\"}, {\"user\":\"100.34.139.116\",\"anon\":\"\",\"timestamp\":\"2020-08-13T06:22:45Z\"}, {\"user\":\"100.34.139.116\",\"anon\":\"\",\"timestamp\":\"2020-08-13T06:21:06Z\"}, {\"user\":\"2600:8801:1A83:5E00:4DC7:98DF:8B05:4A54\",\"anon\":\"\",\"timestamp\":\"2020-08-10T01:02:02Z\"}, {\"user\":\"2600:8801:1A83:5E00:4DC7:98DF:8B05:4A54\",\"anon\":\"\",\"timestamp\":\"2020-08-10T00:58:46Z\"}]", revisionsList.toString());
    }

    @Test
    public void testCanReturnCleanFirstEntry() throws IOException {
        RevisionParser parser = new RevisionParser();
        WikipediaConnection wikipediaConnection = new WikipediaConnection();
        URLConnection connection = wikipediaConnection.connectToWikipedia("zappa");
        JsonObject query = wikipediaConnection.readJsonDataFrom(connection);
        List<JsonObject> revisionsList = parser.parse(query);
        String cleanFirstEntry = parser.createCleanEntry(revisionsList, 0);
        Assertions.assertEquals("Username: Davenold, Timestamp: 2020-09-27T14:29:15Z\n", cleanFirstEntry);
    }
}
