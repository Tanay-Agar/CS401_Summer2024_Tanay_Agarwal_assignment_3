package com.cs401.assignment3;

public class StringUtils {
    private StringUtils() {
    }

    public static String combine(String input1, String input2) {
    	String comString = "";
        for (int i = 0; i < input1.length(); i++) {
        	comString += input1.charAt(i);
        }
        for (int i = 0; i < input2.length(); i++) {
        	comString += input2.charAt(i);
        }
        return comString;
    }

    public static String reverse(String input) {
        String newString = "";
        int endString = input.length() - 1;
        for (int i = 0; i <= endString; i++) {
            newString += input.charAt(endString - i);
        }
        return newString;
    }
    
    public static String upperCase(String input) {
        return input.toUpperCase();
    }

    public static String trimWhitespace(String input) {
        int startString = 0;
        int endString = input.length() - 1;
        while (startString < input.length() && Character.isWhitespace(input.charAt(startString))) {
            startString++;
        }
        while (endString > startString && Character.isWhitespace(input.charAt(endString))) {
            endString--;
        }
        return input.substring(startString, endString + 1);
    }
}