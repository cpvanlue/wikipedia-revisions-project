package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import static edu.bsu.cs222.RevisionParser.*;

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
    public void testCanReturnRedirectsList() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> redirectsList = parseRedirectsToList(query);
        assert redirectsList != null;
        Assertions.assertEquals("[{\"from\":\"Zappa\",\"to\":\"Frank Zappa\"}]", redirectsList.toString());
    }
}
