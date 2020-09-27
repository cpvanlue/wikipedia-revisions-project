package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MenuTestClass {

    @Test
    public void testUserInputReturnSearchTerm(){
        UserInterface userInterface = new UserInterface();
        String searchTerm = userInterface.collectSearchTerm();
        Assertions.assertEquals("zappa", searchTerm);
    }
}
