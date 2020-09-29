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

import static edu.bsu.cs222.RevisionParser.*;
import static edu.bsu.cs222.WikipediaConnection.connectToWikipedia;
import static edu.bsu.cs222.WikipediaConnection.readJsonDataFrom;

public class RevisionParserTest {

    @Test
    public void testCountRevisions() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        assert inputStream != null;
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String,JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        assert array != null;
        JsonElement firstRevision = array.get(0);
        JsonObject firstObject = firstRevision.getAsJsonObject();
        String name = firstObject.get("user").toString();
        Assertions.assertEquals("\"DVdm\"", name);
    }
    @Test
    //this tests with live data, will likely fail after 9/29/20
    public void testParseReturnsJsonObjectList() throws IOException {
        URLConnection connection = connectToWikipedia("zappa");
        JsonObject query = readJsonDataFrom(connection);
        List<JsonObject> revisionsList = parseRevisionsToList(query);
        Assertions.assertEquals("[{\"user\":\"Davenold\",\"timestamp\":\"2020-09-27T14:29:15Z\"}, {\"user\":\"DVdm\",\"timestamp\":\"2020-09-21T11:32:46Z\"}, {\"user\":\"179.53.16.150\",\"anon\":\"\",\"timestamp\":\"2020-09-21T09:22:33Z\"}, {\"user\":\"Strudjum\",\"timestamp\":\"2020-09-20T03:13:40Z\"}, {\"user\":\"ClueBot NG\",\"timestamp\":\"2020-09-17T20:57:00Z\"}, {\"user\":\"176.58.214.213\",\"anon\":\"\",\"timestamp\":\"2020-09-17T20:56:41Z\"}, {\"user\":\"Binksternet\",\"timestamp\":\"2020-09-10T22:30:02Z\"}, {\"user\":\"2601:5C2:300:43D0:182E:95C0:F1C:8ADD\",\"anon\":\"\",\"timestamp\":\"2020-09-10T22:01:36Z\"}, {\"user\":\"ClueBot NG\",\"timestamp\":\"2020-09-10T21:58:57Z\"}, {\"user\":\"2601:5C2:300:43D0:182E:95C0:F1C:8ADD\",\"anon\":\"\",\"timestamp\":\"2020-09-10T21:58:41Z\"}, {\"user\":\"Materialscientist\",\"timestamp\":\"2020-09-02T17:21:41Z\"}, {\"user\":\"2600:8800:2900:507:71BC:81A0:B42B:8E80\",\"anon\":\"\",\"timestamp\":\"2020-09-02T17:20:55Z\"}, {\"user\":\"Ser Amantio di Nicolao\",\"timestamp\":\"2020-08-25T01:34:59Z\"}, {\"user\":\"Blablubbs\",\"timestamp\":\"2020-08-24T20:03:45Z\"}, {\"user\":\"86.172.94.248\",\"anon\":\"\",\"timestamp\":\"2020-08-24T20:02:44Z\"}, {\"user\":\"Neveselbert\",\"timestamp\":\"2020-08-17T21:54:47Z\"}, {\"user\":\"100.34.139.116\",\"anon\":\"\",\"timestamp\":\"2020-08-13T06:22:45Z\"}, {\"user\":\"100.34.139.116\",\"anon\":\"\",\"timestamp\":\"2020-08-13T06:21:06Z\"}, {\"user\":\"2600:8801:1A83:5E00:4DC7:98DF:8B05:4A54\",\"anon\":\"\",\"timestamp\":\"2020-08-10T01:02:02Z\"}, {\"user\":\"2600:8801:1A83:5E00:4DC7:98DF:8B05:4A54\",\"anon\":\"\",\"timestamp\":\"2020-08-10T00:58:46Z\"}]", revisionsList.toString());
    }

    @Test
    //this tests with live data, will likely fail after 9/29/20
    public void testCanReturnCleanFirstEntry() throws IOException {
        URLConnection connection = connectToWikipedia("zappa");
        JsonObject query = readJsonDataFrom(connection);
        List<JsonObject> revisionsList = parseRevisionsToList(query);
        String cleanFirstEntry = createCleanRevision(revisionsList, 0);
        Assertions.assertEquals("Username: Davenold, Timestamp: 2020-09-27T14:29:15Z\n", cleanFirstEntry);
    }

    @Test
    public void testCanReturnRedirectsList() throws IOException {
        URLConnection connection = connectToWikipedia("zappa");
        JsonObject query = readJsonDataFrom(connection);
        List<JsonObject> redirectsList = parseRedirectsToList(query);
        assert redirectsList != null;
        Assertions.assertEquals("[{\"from\":\"Zappa\",\"to\":\"Frank Zappa\"}]", redirectsList.toString());
    }

    @Test
    public void canReturnCleanFirstRedirect() throws IOException {
        URLConnection connection = connectToWikipedia("zappa");
        JsonObject query = readJsonDataFrom(connection);
        List<JsonObject> redirectsList = parseRedirectsToList(query);
        String cleanFirstRevision = createCleanRedirect(redirectsList, 0);
        System.out.println(cleanFirstRevision);
        Assertions.assertEquals("Redirects: 1) Zappa -> Frank Zappa\n", cleanFirstRevision);
    }

}
