package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public String parseAndReturnCleanString(JsonObject object) {
        List<JsonObject> revisionsList = parse(object);
        return createListOfCleanEntries(revisionsList);
    }

    public static List<JsonObject> parse(JsonObject object){
        JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        List<JsonObject> revisionsList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        if (array != null) {
            for (int i = 0; i < array.size(); i++) {
                revisionsList.add(array.get(i).getAsJsonObject());
            }
        } else {
            System.out.println("There is no Wikipedia entry for this query.");
            return null;
        }
        return revisionsList;
    }

    public static String createCleanEntry(List<JsonObject> revisionsList, int i) {
        JsonObject firstObject = revisionsList.get(i);
        String firstUsername = firstObject.get("user").getAsString().replaceAll("\"", "");
        String firstTimestamp = firstObject.get("timestamp").getAsString();
        return "Username: " + firstUsername + ", Timestamp: " + firstTimestamp + "\n";
    }

    public String createListOfCleanEntries(List<JsonObject> revisionsList) {
        StringBuilder prettyRevisionsList = new StringBuilder();
        if (revisionsList != null){
            for (int i = 0; i < revisionsList.size(); i++) {
                String cleanEntry = createCleanEntry(revisionsList, i);
                prettyRevisionsList.append(cleanEntry);
            }
        } else {
            return null;
        }
        return prettyRevisionsList.toString();
    }
}
