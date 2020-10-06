package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActiveEditors {

    public static int collectNumberOfEdits(List<JsonObject> revisionsList, int i) {
        JsonObject revision = revisionsList.get(i);
        String username = revision.get("user").getAsString();
        int counter = 0;
        for (JsonObject jsonObject : revisionsList) {
            if (jsonObject.get("user").getAsString().equals(username)) {
                counter += 1;
            }
        }
        return counter;
    }

    public static List<Editor> createNumberOfEditsArray(List<JsonObject> revisionsList) {
        List<Editor> editorsList = new ArrayList<>();
        if (revisionsList != null) {
            for (int i = 0; i < revisionsList.size(); i++) {
                Editor editor = Editor.collectEditorAsObject(revisionsList, i);
                editorsList.add(editor);
                editorsList.sort(Comparator.comparing(Editor::getNumberOfEdits));
                Collections.reverse(editorsList);

            }
            return editorsList;
        } else {
            return null;
        }
    }

    public static String createSortedEditsString(List<Editor> editorsList) {
        if (editorsList.isEmpty()) {
            return "There is no Wikipedia entry for this query.";
        } else {
            StringBuilder numberOfEditsString = new StringBuilder();
            for (Editor editor : editorsList) {
                String editorAndEdits = editor.getNumberOfEdits() + " edits made by user: " + editor.getUser() + "\n";
                if (!numberOfEditsString.toString().contains(editorAndEdits)) {
                    numberOfEditsString.append(editorAndEdits);
                }
            }
            String mostActiveEditor = "Top editor for this page: " + editorsList.get(0).getUser() + " with " + editorsList.get(0).getNumberOfEdits() + " edits.\n\n";
            return mostActiveEditor + numberOfEditsString.toString();
        }
    }
}
