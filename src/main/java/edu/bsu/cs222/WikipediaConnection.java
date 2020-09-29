package edu.bsu.cs222;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class WikipediaConnection {

    public static JsonObject collectJsonObjectFromWikipedia(String searchTerm) throws IOException {
        URLConnection connection = connectToWikipedia(searchTerm);
        return readJsonDataFrom(connection);
    }

    public static URLConnection connectToWikipedia(String searchTerm) throws IOException {
        searchTerm = keywordToURL(searchTerm);
        URL url;
        url = new URL(
                "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=json&rvprop=timestamp%7Cuser&rvlimit=20&titles="+searchTerm+"&redirects=");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (cpvanlue@bsu.edu)");
        connection.connect();
        return connection;
    }

    public static JsonObject readJsonDataFrom(URLConnection connection) throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonData;
        while ((jsonData = bufferedReader.readLine()) != null) {
            jsonStringBuilder.append(jsonData).append("\n");
        }
        String websiteInfo = jsonStringBuilder.toString().trim();
        return (JsonObject) JsonParser.parseString(websiteInfo);
    }

    public static String keywordToURL (String input){
        return input.replaceAll(" ", "+");
    }
}