package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.util.List;

public class Editor {

    private String user;
    private int numberOfEdits;

    public Editor(String user, int numberOfEdits) {
        this.user = user;
        this.numberOfEdits = numberOfEdits;
    }

    public int getNumberOfEdits() {
        return numberOfEdits;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "user='" + user + '\'' +
                ", numberOfEdits=" + numberOfEdits +
                '}';
    }

    public static Editor collectEditorAsObject(List<JsonObject> revisionsList, int i) {
        String username = revisionsList.get(i).get("user").getAsString();
        int numberOfEdits = ActiveEditors.collectNumberOfEdits(revisionsList, i);
        Editor editor = new Editor(username, numberOfEdits);
        return editor;
    }
}
