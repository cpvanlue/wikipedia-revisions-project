package edu.bsu.cs222;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

import static edu.bsu.cs222.RevisionParser.parse;
import static edu.bsu.cs222.UserInterface.collectSearchTerm;
import static edu.bsu.cs222.WikipediaConnection.*;

public class MenuTestClass {

    @Test
    public void testUserInputReturnSearchTerm(){
        String searchTerm = collectSearchTerm();
        Assertions.assertEquals("zappa", searchTerm);
    }

    @Test
    public void testUserInputReturnsCleanList() throws IOException {
        String searchTerm = "zappa";
        URLConnection connection = connectToWikipedia(searchTerm);
        JsonObject query = readJsonDataFrom(connection);
        RevisionParser parser = new RevisionParser();
        List<JsonObject> revisionsList = parse(query);
        String cleanList = parser.createListOfCleanEntries(revisionsList);
        Assertions.assertEquals("Username: Davenold, Timestamp: 2020-09-27T14:29:15Z\nUsername: DVdm, Timestamp: 2020-09-21T11:32:46Z" +
                        "\nUsername: 179.53.16.150, Timestamp: 2020-09-21T09:22:33Z" +
                        "\nUsername: Strudjum, Timestamp: 2020-09-20T03:13:40Z" +
                        "\nUsername: ClueBot NG, Timestamp: 2020-09-17T20:57:00Z" +
                        "\nUsername: 176.58.214.213, Timestamp: 2020-09-17T20:56:41Z" +
                        "\nUsername: Binksternet, Timestamp: 2020-09-10T22:30:02Z" +
                        "\nUsername: 2601:5C2:300:43D0:182E:95C0:F1C:8ADD, Timestamp: 2020-09-10T22:01:36Z" +
                        "\nUsername: ClueBot NG, Timestamp: 2020-09-10T21:58:57Z" +
                        "\nUsername: 2601:5C2:300:43D0:182E:95C0:F1C:8ADD, Timestamp: 2020-09-10T21:58:41Z" +
                        "\nUsername: Materialscientist, Timestamp: 2020-09-02T17:21:41Z" +
                        "\nUsername: 2600:8800:2900:507:71BC:81A0:B42B:8E80, Timestamp: 2020-09-02T17:20:55Z" +
                        "\nUsername: Ser Amantio di Nicolao, Timestamp: 2020-08-25T01:34:59Z" +
                        "\nUsername: Blablubbs, Timestamp: 2020-08-24T20:03:45Z" +
                        "\nUsername: 86.172.94.248, Timestamp: 2020-08-24T20:02:44Z" +
                        "\nUsername: Neveselbert, Timestamp: 2020-08-17T21:54:47Z" +
                        "\nUsername: 100.34.139.116, Timestamp: 2020-08-13T06:22:45Z" +
                        "\nUsername: 100.34.139.116, Timestamp: 2020-08-13T06:21:06Z" +
                        "\nUsername: 2600:8801:1A83:5E00:4DC7:98DF:8B05:4A54, Timestamp: 2020-08-10T01:02:02Z" +
                "\nUsername: 2600:8801:1A83:5E00:4DC7:98DF:8B05:4A54, Timestamp: 2020-08-10T00:58:46Z\n", cleanList);
    }
}
