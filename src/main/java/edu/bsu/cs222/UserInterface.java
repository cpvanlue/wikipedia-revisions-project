package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Scanner;

import static edu.bsu.cs222.RevisionParser.parseAndReturnCleanResultsString;
import static edu.bsu.cs222.WikipediaConnection.collectJsonObjectFromWikipedia;

public class UserInterface {

    public static String collectSearchTerm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a search term or type exit to quit: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String searchTerm = collectSearchTerm();
        while (!searchTerm.equals("exit")) {
            JsonObject wikiData;
            try {
                wikiData = collectJsonObjectFromWikipedia(searchTerm);
            } catch (IOException e) {
                System.out.println("No connection available.");
                return;
            }
            String cleanList = parseAndReturnCleanResultsString(wikiData);
            System.out.println(cleanList);
            searchTerm = collectSearchTerm();
        }
    }
}
