package edu.bsu.cs222;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Scanner;

import static edu.bsu.cs222.StringFormat.parseAndReturnCleanResultsString;
import static edu.bsu.cs222.WikipediaConnection.collectJsonObjectFromWikipedia;

public class UserInterface {

    public static String collectSearchOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Enter 1 to search for revisions or 2 to search for top edits.\nType exit to quit.");
        return scanner.nextLine();
    }

    public static String collectSearchKeyword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a search term or type exit to return to previous menu.");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String option = collectSearchOption();
        while (!option.equals("exit")) {
            if (option.equals("1")) {
                String searchKeyword = collectSearchKeyword();
                while (!searchKeyword.equals("exit")) {
                    JsonObject wikiData;
                    try {
                        wikiData = collectJsonObjectFromWikipedia(searchKeyword);
                    } catch (IOException e) {
                        System.out.println("No connection available.");
                        return;
                    }
                    String cleanList = parseAndReturnCleanResultsString(wikiData);
                    System.out.println(cleanList);
                    searchKeyword = collectSearchKeyword();
                } option = collectSearchOption();
            }
            if (option.equals("2")) {
                String searchKeyword = collectSearchKeyword();
                while (!searchKeyword.equals("exit")) {
                    JsonObject wikiData;
                    try {
                        wikiData = collectJsonObjectFromWikipedia(searchKeyword);
                    } catch (IOException e) {
                        System.out.println("No connection available.");
                        return;
                    }
                    String editorsList = ActiveEditors.createNumberOfEditsString(RevisionParser.parseRevisionsToList(wikiData));
                    System.out.println(editorsList);
                    searchKeyword = collectSearchKeyword();
                }
            }
            option = collectSearchOption();
        }
    }
}
