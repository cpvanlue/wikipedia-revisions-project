package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public static String parseAndReturnCleanResultsString(JsonObject wikiDataObject) {
        List<JsonObject> revisionsList = parseRevisionsToList(wikiDataObject);
        List<JsonObject> redirectsList = parseRedirectsToList(wikiDataObject);
        return "\n\n" + createCleanListOfRevisions(redirectsList) + createListOfCleanRevisions(revisionsList);
    }

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
        } else {
            //violates model-view, FIX THIS NEXT LINE
            System.out.println("There is no Wikipedia entry for this query.");
        }
        return revisionsList;
    }

    public static String createCleanRevision(List<JsonObject> revisionsList, int i) {
        JsonObject revision = revisionsList.get(i);
        String username = revision.get("user").getAsString().replaceAll("\"", "");
        String timestamp = revision.get("timestamp").getAsString();
        return "Username: " + username + ", Timestamp: " + timestamp + "\n";
    }

    public static String createListOfCleanRevisions(List<JsonObject> revisionsList) {
        StringBuilder prettyRevisionsList = new StringBuilder();
        if (revisionsList != null){
            for (int i = 0; i < revisionsList.size(); i++) {
                String cleanRevision = createCleanRevision(revisionsList, i);
                prettyRevisionsList.append(cleanRevision);
            }
        } else {
            return null;
        }
        return prettyRevisionsList.toString();
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

    public static String createCleanListOfRevisions(List<JsonObject> redirectsList) {
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
