package edu.bsu.cs222;

import com.google.gson.JsonObject;

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
}
