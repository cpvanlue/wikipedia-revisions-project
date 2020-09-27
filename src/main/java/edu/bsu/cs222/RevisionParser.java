package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class RevisionParser {
    public String parse(JsonObject object){
        JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        JsonElement firstRevision = array.get(0);
        JsonObject firstObject = firstRevision.getAsJsonObject();
        String name = firstObject.get("user").toString();
        return name;
    }
}
