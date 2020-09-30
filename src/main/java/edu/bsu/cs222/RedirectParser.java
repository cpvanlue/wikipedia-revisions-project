package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class RedirectParser {

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
