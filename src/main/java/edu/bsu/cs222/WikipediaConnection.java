package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class WikipediaConnection {

    public static void main(String[] args) throws IOException {
        URLConnection connection = connectToWikipedia();
        String jsonData = readJsonDataFrom(connection);
        System.out.println(jsonData);
    }

    private static URLConnection connectToWikipedia() throws IOException {
        URL url = new URL(
                "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=json&rvprop=timestamp%7Cuser&rvlimit=20&titles=zappa&redirects=");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (cpvanlue@bsu.edu)");
        connection.connect();
        return connection;
    }

    private static String readJsonDataFrom(URLConnection connection) throws IOException {
        StringBuilder jsonStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonData;
        while ((jsonData = bufferedReader.readLine()) != null) {
            jsonStringBuilder.append(jsonData + "\n");
        }
        return jsonStringBuilder.toString().trim();
    }
}