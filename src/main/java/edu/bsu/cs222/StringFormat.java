package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.util.List;

import static edu.bsu.cs222.RevisionParser.parseRedirectsToList;
import static edu.bsu.cs222.RevisionParser.parseRevisionsToList;

public class StringFormat {

    public static String parseAndReturnCleanResultsString(JsonObject wikiDataObject) {
        List<JsonObject> revisionsList = parseRevisionsToList(wikiDataObject);
        List<JsonObject> redirectsList = parseRedirectsToList(wikiDataObject);
        return "\n\n" + createCleanListOfRedirects(redirectsList) + createListOfCleanRevisions(revisionsList);
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

    public static String createCleanListOfRedirects(List<JsonObject> redirectsList) {
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
