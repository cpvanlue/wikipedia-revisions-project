package edu.bsu.cs222;

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

import static edu.bsu.cs222.RevisionParser.createListOfCleanRevisions;
import static edu.bsu.cs222.RevisionParser.parseRevisionsToList;
import static edu.bsu.cs222.RevisionParserTest.collectSampleDataAsJsonObject;
import static edu.bsu.cs222.UserInterface.collectSearchOption;
import static edu.bsu.cs222.WikipediaConnection.*;

public class MenuTestClass {

    @Test
    public void testUserInputReturnsCleanList() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> revisionsList = parseRevisionsToList(query);
        String cleanList = createListOfCleanRevisions(revisionsList);
        Assertions.assertEquals("Username: DVdm, Timestamp: 2020-09-21T11:32:46Z" +
                        "\nUsername: 179.53.16.150, Timestamp: 2020-09-21T09:22:33Z" +
                        "\nUsername: Strudjum, Timestamp: 2020-09-20T03:13:40Z" +
                        "\nUsername: ClueBot NG, Timestamp: 2020-09-17T20:57:00Z\n", cleanList);
    }
}
