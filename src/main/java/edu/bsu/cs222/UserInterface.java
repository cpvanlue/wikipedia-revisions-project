package edu.bsu.cs222;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static String collectSearchTerm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a search term: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String searchTerm = collectSearchTerm();
        WikipediaConnection wikipediaConnection = new WikipediaConnection();
        JsonObject jsonData = null;
        try {
            jsonData = wikipediaConnection.collectObjectFromWikipedia(searchTerm);
        } catch (IOException e) {
            System.out.println("No connection available.");
            return;
        } catch (JsonSyntaxException e) {
            System.out.println("There is no Wikipedia entry under that name.");
        }
        RevisionParser parser = new RevisionParser();
        String cleanList = parser.parseAndReturnCleanString(jsonData);
        System.out.println(cleanList);
    }
}
