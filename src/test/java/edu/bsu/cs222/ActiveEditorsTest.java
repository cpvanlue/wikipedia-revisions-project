package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

public class ActiveEditorsTest {

    @Test
    public void canCountNumberOfEditsFromMostRecentEditor() throws IOException {
        URLConnection connection = WikipediaConnection.connectToWikipedia("zappa");
        JsonObject query = WikipediaConnection.readJsonDataFrom(connection);
        List<JsonObject> revisionsList = RevisionParser.parseRevisionsToList(query);
        ActiveEditors activeEditors = new ActiveEditors();
        int numberOfEdits = activeEditors.collectNumberOfEdits(revisionsList, 0);
        Assertions.assertEquals(2, numberOfEdits);
    }

    @Test
    public void canReturnMostRecentEditTimestamp() throws IOException {

    }
}
