package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public static String parseAndReturnCleanRevisionString(JsonObject object) {
        List<JsonObject> revisionsList = parseRevisionsToList(object);
        List<JsonObject> redirectsList = parseRedirectsToList(object);
        return "\n\n" + createCleanRedirectsList(redirectsList) + createListOfCleanEntries(revisionsList);
    }

    public static List<JsonObject> parseRevisionsToList(JsonObject object){
        JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray jsonArray = null;
        List<JsonObject> revisionsList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jsonArray = entryObject.getAsJsonArray("revisions");
        }
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                revisionsList.add(jsonArray.get(i).getAsJsonObject());
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

    public static String createListOfCleanEntries(List<JsonObject> revisionsList) {
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

    public static List<JsonObject> parseRedirectsToList(JsonObject object) {
        JsonArray redirects = object.getAsJsonObject("query").getAsJsonArray("redirects");
        List<JsonObject> redirectsList = new ArrayList<>();
        if (redirects != null) {
            for (JsonElement redirect : redirects) {
                redirectsList.add((JsonObject) redirect);
            }
        } else { return null; }
        return redirectsList;
    }

    public static String createCleanRedirect(List<JsonObject> redirectsList, int i) {
        if (redirectsList != null) {
            JsonObject redirect = redirectsList.get(i);
            String from = redirect.get("from").getAsString().replaceAll("\"", "");
            String to = redirect.get("to").getAsString().replaceAll("\"", "");
            return "Redirects: "+ (i+1) + ") " + from + " -> " + to + "\n";
        } else {
            return null;
        }
    }

    public static String createCleanRedirectsList(List<JsonObject> redirectsList) {
        StringBuilder prettyRedirectsList = new StringBuilder();
        if (redirectsList != null) {
            for (int i = 0; i < redirectsList.size(); i++) {
                String cleanRedirect = createCleanRedirect(redirectsList, i);
                prettyRedirectsList.append(cleanRedirect);
            }
            } else {
            return "No redirects used.\n";
        } prettyRedirectsList.append("\n");
        return prettyRedirectsList.toString();
    }
}
