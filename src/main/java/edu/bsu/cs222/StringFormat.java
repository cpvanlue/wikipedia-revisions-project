package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static edu.bsu.cs222.RedirectParser.parseRedirectsToList;
import static edu.bsu.cs222.RevisionParser.parseRevisionsToList;

public class StringFormat {

    public static String parseAndReturnCleanResultsString(JsonObject wikiDataObject) throws ParseException {
        List<JsonObject> revisionsList = parseRevisionsToList(wikiDataObject);
        List<JsonObject> redirectsList = parseRedirectsToList(wikiDataObject);
         if (revisionsList.isEmpty()) {
             return "There is no Wikipedia entry for this query.\n";
         }
         return createCleanListOfRedirects(redirectsList) + createListOfCleanRevisions(revisionsList);
    }

    public static String createCleanRevision(List<JsonObject> revisionsList, int i) throws ParseException {
        JsonObject revision = revisionsList.get(i);
        String username = revision.get("user").getAsString().replaceAll("\"", "");
        String timestamp = revision.get("timestamp").getAsString();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date result1 = df1.parse(timestamp);
        return "Username: " + username + ", Timestamp: " + result1 + "\n";
    }

    public static String createListOfCleanRevisions(List<JsonObject> revisionsList) throws ParseException {
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
