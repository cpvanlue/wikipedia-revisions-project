package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public static List<JsonObject> parseRevisionsToList(JsonObject wikiDataObject){
        JsonObject pages = wikiDataObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray revisionsArray = null;
        List<JsonObject> revisionsList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisionsArray = entryObject.getAsJsonArray("revisions");
        }
        if (revisionsArray != null) {
            for (int i = 0; i < revisionsArray.size(); i++) {
                revisionsList.add(revisionsArray.get(i).getAsJsonObject());
            }
        }
        return revisionsList;
    }

    public static List<JsonObject> parseRedirectsToList(JsonObject wikiDataObject) {
        JsonArray redirectsArray = wikiDataObject.getAsJsonObject("query").getAsJsonArray("redirects");
        List<JsonObject> redirectsList = new ArrayList<>();
        if (redirectsArray != null) {
            for (JsonElement redirect : redirectsArray) {
                redirectsList.add((JsonObject) redirect);
            }
        } else { return null; }
        return redirectsList;
    }
}
