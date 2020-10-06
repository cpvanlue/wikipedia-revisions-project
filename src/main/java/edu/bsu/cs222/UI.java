package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.IOException;

import static edu.bsu.cs222.StringFormat.parseAndReturnCleanResultsString;
import static edu.bsu.cs222.WikipediaConnection.collectJsonObjectFromWikipedia;

public class UI {

    public static String collectRecentRevisions(String searchTerm) {
        JsonObject wikiData;
        try {
            wikiData = collectJsonObjectFromWikipedia(searchTerm);
        } catch (IOException e) {
            return "No connection available.";
        }
        return parseAndReturnCleanResultsString(wikiData);
    }

    public static String collectRecentEditors(String searchTerm) {
        JsonObject wikiData;
        try {
            wikiData = collectJsonObjectFromWikipedia(searchTerm);
        } catch (IOException e) {
            return "No connection available.";
        }
        return ActiveEditors.createNumberOfEditsString(RevisionParser.parseRevisionsToList(wikiData));
    }



}
