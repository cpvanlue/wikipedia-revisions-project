package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RefactorSearchTermTest {

    @Test
    public void testStringToSearchTerm() {
        SearchTermToUrl translator = new SearchTermToUrl();
        String result = translator.convert("frank zappa");
        Assertions.assertEquals("frank+zappa", result);
    }
}
