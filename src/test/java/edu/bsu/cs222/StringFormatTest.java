package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.bsu.cs222.RevisionParser.*;
import static edu.bsu.cs222.RevisionParserTest.collectSampleDataAsJsonObject;
import static edu.bsu.cs222.StringFormat.createCleanRedirect;
import static edu.bsu.cs222.StringFormat.createCleanRevision;

public class StringFormatTest {

    @Test
    public void testCanReturnCleanFirstEntry() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> revisionsList = parseRevisionsToList(query);
        String cleanFirstEntry = createCleanRevision(revisionsList, 0);
        Assertions.assertEquals("Username: DVdm, Timestamp: 2020-09-21T11:32:46Z\n", cleanFirstEntry);
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
