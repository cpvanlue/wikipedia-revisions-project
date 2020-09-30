package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.bsu.cs222.RedirectParser.parseRedirectsToList;
import static edu.bsu.cs222.RevisionParserTest.collectSampleDataAsJsonObject;

public class RedirectParserTest {

    @Test
    public void testCanReturnRedirectsList() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> redirectsList = parseRedirectsToList(query);
        assert redirectsList != null;
        Assertions.assertEquals("[{\"from\":\"Zappa\",\"to\":\"Frank Zappa\"}]", redirectsList.toString());
    }
}
