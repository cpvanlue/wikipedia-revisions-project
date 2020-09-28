package edu.bsu.cs222;

import java.util.Scanner;

public class UserInterface {
    public String collectSearchTerm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a search term: ");
        return scanner.nextLine();
    }
}
