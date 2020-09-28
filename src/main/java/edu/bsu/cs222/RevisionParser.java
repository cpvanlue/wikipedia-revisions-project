package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {
    public List<JsonObject> parse(JsonObject object){
        JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        List<JsonObject> revisionsList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        for (int i=0; i<array.size(); i++){
            revisionsList.add(array.get(i).getAsJsonObject());
        }
        return revisionsList;
    }

    public static String createCleanEntry(List<JsonObject> revisionsList, int i) {
        JsonObject firstObject = revisionsList.get(i);
        String firstUsername = firstObject.get("user").getAsString().replaceAll("\"", "");
        String firstTimestamp = firstObject.get("timestamp").getAsString();
        return "Username: " + firstUsername + ", Timestamp: " + firstTimestamp + "\n";
    }
}
