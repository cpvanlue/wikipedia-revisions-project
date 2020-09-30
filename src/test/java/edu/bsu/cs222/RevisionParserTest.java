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

    public static JsonObject collectSampleDataAsJsonObject() {
        InputStream inputStream = RevisionParserTest.class.getClassLoader().getResourceAsStream("sample.json");
        assert inputStream != null;
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = JsonParser.parseReader(reader);
        return rootElement.getAsJsonObject();
    }

    @Test
    public void testCountRevisions() {
        JsonObject rootObject = collectSampleDataAsJsonObject();
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
    public void testParseReturnsJsonObjectList() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> revisionsList = parseRevisionsToList(query);
        Assertions.assertEquals("[{\"user\":\"DVdm\",\"timestamp\":\"2020-09-21T11:32:46Z\"}, " +
                "{\"user\":\"179.53.16.150\",\"anon\":\"\",\"timestamp\":\"2020-09-21T09:22:33Z\"}, " +
                "{\"user\":\"Strudjum\",\"timestamp\":\"2020-09-20T03:13:40Z\"}, " +
                "{\"user\":\"ClueBot NG\",\"timestamp\":\"2020-09-17T20:57:00Z\"}]", revisionsList.toString());
    }

    @Test
    public void testCanReturnCleanFirstEntry() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> revisionsList = parseRevisionsToList(query);
        String cleanFirstEntry = createCleanRevision(revisionsList, 0);
        Assertions.assertEquals("Username: DVdm, Timestamp: 2020-09-21T11:32:46Z\n", cleanFirstEntry);
    }

    @Test
    public void testCanReturnRedirectsList() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> redirectsList = parseRedirectsToList(query);
        assert redirectsList != null;
        Assertions.assertEquals("[{\"from\":\"Zappa\",\"to\":\"Frank Zappa\"}]", redirectsList.toString());
    }

    @Test
    public void canReturnCleanFirstRedirect() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> redirectsList = parseRedirectsToList(query);
        String cleanFirstRevision = createCleanRedirect(redirectsList, 0);
        System.out.println(cleanFirstRevision);
        Assertions.assertEquals("Redirects: 1) Zappa -> Frank Zappa\n", cleanFirstRevision);
    }

}
