package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static edu.bsu.cs222.RevisionParser.parseRevisionsToList;
import static edu.bsu.cs222.RevisionParserTest.collectSampleDataAsJsonObject;
import static edu.bsu.cs222.StringFormat.createListOfCleanRevisions;

public class ControllerTestClass {

    @Test
    public void testUserInputReturnsCleanList() throws ParseException {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> revisionsList = parseRevisionsToList(query);
        String cleanList = createListOfCleanRevisions(revisionsList);
        Assertions.assertEquals("Username: DVdm, Timestamp: Mon Sep 21 11:32:46 EDT 2020\n" +
                "Username: 179.53.16.150, Timestamp: Mon Sep 21 09:22:33 EDT 2020\n" +
                "Username: Strudjum, Timestamp: Sun Sep 20 03:13:40 EDT 2020\n" +
                "Username: ClueBot NG, Timestamp: Thu Sep 17 20:57:00 EDT 2020\n", cleanList);
    }
}
