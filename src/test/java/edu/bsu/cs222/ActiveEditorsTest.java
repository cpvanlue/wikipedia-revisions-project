package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.bsu.cs222.RevisionParserTest.collectSampleDataAsJsonObject;

public class ActiveEditorsTest {

    @Test
    public void canCountNumberOfEditsFromMostRecentEditor() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> revisionsList = RevisionParser.parseRevisionsToList(query);
        int numberOfEdits = ActiveEditors.collectNumberOfEdits(revisionsList, 0);
        Assertions.assertEquals(1, numberOfEdits);
    }
}
