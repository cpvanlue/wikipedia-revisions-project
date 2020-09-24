package edu.bsu.cs222;

public class SearchTermToUrl {

    public String convert(String input) {
        String inputReplaceSpace = input.replaceAll(" ", "+");
        return inputReplaceSpace;
    }
}
