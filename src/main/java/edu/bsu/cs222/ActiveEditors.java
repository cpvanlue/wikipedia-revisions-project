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

    public static String createNumberOfEditsString(List<JsonObject> revisionsList) {
        StringBuilder numberOfEditsString = new StringBuilder();
        if (revisionsList.isEmpty()) {
            return "There is no Wikipedia entry for this query.\n\n";
        }
        for (int i = 0; i < revisionsList.size(); i++) {
            if (i != 0) {
                int numberOfEdits = collectNumberOfEdits(revisionsList, i);
                String editorAndEdits = "Username: " + revisionsList.get(i).get("user").getAsString() + " Number of Edits: " + numberOfEdits + "\n";
                if (!numberOfEditsString.toString().contains(editorAndEdits)) {
                    numberOfEditsString.append(editorAndEdits);
                }
            }
        } return numberOfEditsString.toString();
    }

    public static List<Editor> createNumberOfEditsArray(List<JsonObject> revisionsList) {
        List<Editor> editorsList = new ArrayList<>();
        for (int i = 0; i < revisionsList.size(); i++) {
            Editor editor = Editor.collectEditorAsObject(revisionsList, i);
            editorsList.add(editor);
            Collections.sort(editorsList, Comparator.comparing(Editor::getNumberOfEdits));
            Collections.reverse(editorsList);
        }
        return editorsList;
    }

    public static String createSortedEditsString(List<Editor> editorsList) {
        StringBuilder numberOfEditsString = new StringBuilder();
        for (int i = 0; i < editorsList.size(); i++) {
            String editorAndEdits = editorsList.get(i).getNumberOfEdits() + " edits made by user: " + editorsList.get(i).getUser() + "\n";
            if (!numberOfEditsString.toString().contains(editorAndEdits)) {
                numberOfEditsString.append(editorAndEdits);
            }
        } return numberOfEditsString.toString();
    }
}
