package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static edu.bsu.cs222.RevisionParserTest.collectSampleDataAsJsonObject;

public class EditorTest {

    @Test
    public void testCanReturnEditorAsObject() {
        JsonObject query = collectSampleDataAsJsonObject();
        List<JsonObject> revisionsList = RevisionParser.parseRevisionsToList(query);
        Editor editor = Editor.collectEditorAsObject(revisionsList, 0);
        Assertions.assertEquals("Editor{user='DVdm', numberOfEdits=1}", editor.toString());
    }
}
