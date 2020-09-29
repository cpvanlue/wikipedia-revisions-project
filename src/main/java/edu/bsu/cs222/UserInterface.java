package edu.bsu.cs222;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Scanner;

import static edu.bsu.cs222.WikipediaConnection.collectObjectFromWikipedia;

public class UserInterface {

    public static String collectSearchTerm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a search term: ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String searchTerm = collectSearchTerm();
        JsonObject jsonData = null;
        try {
            jsonData = collectObjectFromWikipedia(searchTerm);
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
