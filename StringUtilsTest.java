package com.cs401.assignment3;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringUtilsTest {
    private static int totalTestCases = 0;
    private static int failedCaseNumber = 0;
    private static int passedCaseNumber = 0;
    private String testCase;
    private String methodName;
    private String input1;
    private String input2;
    private String expectedResult;
    private String actualResult;

    public StringUtilsTest(String testCase, String methodName, String input1, String input2, String expectedResult) {
        this.testCase = testCase;
        this.methodName = methodName;
        this.input1 = input1;
        this.input2 = input2;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // Combine method test cases
            {"Test case 1: Combine two words", "combine", "Hello", "World", "HelloWorld" },
            {"Test case 2: Combine two empty strings", "combine", "", "", "" },
            {"Test case 3: Combine word with empty string", "combine", "Test", "", "Test" },
            {"Test case 4: Combine empty string with word", "combine", "", "Case", "Case" },
            {"Test case 5: Combine two numbers as strings", "combine", "123", "456", "123456" },
            {"Test case 6: Combine long strings", "combine", "a".repeat(1000), "b".repeat(1000), "a".repeat(1000) + "b".repeat(1000) },
            {"Test case 7: Combine strings with special characters", "combine", "!@#", "$%^", "!@#$%^" },
            {"Test case 8: Combine strings with spaces", "combine", "Hello ", " World", "Hello  World" },
            
            // Reverse method test cases
            {"Test case 9: Reverse a word", "reverse", "Hello", null, "olleH" },
            {"Test case 10: Reverse an empty string", "reverse", "", null, "" },
            {"Test case 11: Reverse a single character", "reverse", "a", null, "a" },
            {"Test case 12: Reverse a string of numbers", "reverse", "12345", null, "54321" },
            {"Test case 13: Reverse a phrase with space", "reverse", "Test Case", null, "esaC tseT" },
            {"Test case 14: Reverse a long string", "reverse", "a".repeat(1000), null, "a".repeat(1000) },
            {"Test case 15: Reverse a string with special characters", "reverse", "!@# $%^", null, "^%$ #@!" },
            {"Test case 16: Reverse a palindrome", "reverse", "madam", null, "madam" },
            
            // UpperCase method test cases
            {"Test case 17: Convert lowercase to uppercase", "upperCase", "hello", null, "HELLO" },
            {"Test case 18: Convert empty string to uppercase", "upperCase", "", null, "" },
            {"Test case 19: Convert already uppercase string", "upperCase", "ALREADY UPPER", null, "ALREADY UPPER" },
            {"Test case 20: Convert mixed case to uppercase", "upperCase", "MixEd CaSe", null, "MIXED CASE" },
            {"Test case 21: Convert alphanumeric to uppercase", "upperCase", "123abc", null, "123ABC" },
            {"Test case 22: Convert string with special characters to uppercase", "upperCase", "!@# abc", null, "!@# ABC" },
            {"Test case 23: Convert long lowercase string to uppercase", "upperCase", "a".repeat(1000), null, "A".repeat(1000) },
            {"Test case 24: Convert mixed case with spaces to uppercase", "upperCase", "Mix Ed CaSe", null, "MIX ED CASE" },
            
            // TrimWhitespace method test cases
            {"Test case 25: Trim whitespace from both ends", "trimWhitespace", "  Hello World!  ", null, "Hello World!" },
            {"Test case 26: Trim whitespace from both ends of a word", "trimWhitespace", " Spaces ", null, "Spaces" },
            {"Test case 27: Trim string with no whitespace", "trimWhitespace", "NoSpaces", null, "NoSpaces" },
            {"Test case 28: Trim string with only whitespaces", "trimWhitespace", "    ", null, "" },
            {"Test case 29: Trim string with multiple internal spaces", "trimWhitespace", "  Multiple   Spaces  ", null, "Multiple   Spaces" },
            {"Test case 30: Trim long string with whitespaces", "trimWhitespace", " ".repeat(1000) + "Text" + " ".repeat(1000), null, "Text" },
            {"Test case 31: Trim string with newline characters", "trimWhitespace", "\n\t Hello \t\n", null, "Hello" },
            {"Test case 32: Trim string with mixed whitespace characters", "trimWhitespace", " \t  Mixed \n  ", null, "Mixed" },
            
            // Combination method test cases
            {"Test case 33: Combine and reverse", "combineReverse", "Hello", "World", "dlroWolleH" },
            {"Test case 34: Combine, reverse, and uppercase", "combineReverseUpperCase", "Hello", "World", "DLROWOLLEH" },
            {"Test case 35: Combine, trim, and uppercase", "combineTrimUpperCase", "  Hello", "World  ", "HELLOWORLD" },
            {"Test case 36: Combine, reverse, and trim", "combineReverseTrim", " Hello", "World ", "dlroWolleH"}
        });
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting all test cases");
        System.out.println("----------------------- \n");
        totalTestCases = 0;
        failedCaseNumber = 0;
        passedCaseNumber = 0;
    }

    @Before
    public void setUp() {
        totalTestCases++;
        System.out.println(testCase);
    }

    @Test
    public void testStringUtils() {
        actualResult = executeMethod();
        assertEquals(expectedResult, actualResult);
    }

    private String executeMethod() {
        switch (methodName) {
            case "combine": return StringUtils.combine(input1, input2);
            case "reverse": return StringUtils.reverse(input1);
            case "upperCase": return StringUtils.upperCase(input1);
            case "trimWhitespace": return StringUtils.trimWhitespace(input1);
            case "combineReverse": return StringUtils.reverse(StringUtils.combine(input1, input2));
            case "combineReverseUpperCase": return StringUtils.upperCase(StringUtils.reverse(StringUtils.combine(input1, input2)));
            case "combineTrimUpperCase": return StringUtils.upperCase(StringUtils.trimWhitespace(StringUtils.combine(input1, input2)));
            case "combineReverseTrim": return StringUtils.trimWhitespace(StringUtils.reverse(StringUtils.combine(input1, input2)));
            default: throw new IllegalArgumentException("Unknown method: " + methodName);
        }
    }

    @After
    public void tearDown() {
        System.out.println("Inputs: input1 = \"" + input1 + "\", input2 = \"" + (input2 != null ? input2 : "null") + "\"");
        System.out.println("Expected Output: \"" + expectedResult + "\"");
        System.out.println("Actual Output: \"" + actualResult + "\"");
        System.out.println("Result: Test " + (expectedResult.equals(actualResult) ? "PASSED" : "FAILED"));
        System.out.println("--------------------");
        if (expectedResult.equals(actualResult)) {
            passedCaseNumber++;
        } else {
            failedCaseNumber++;
        }
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Total test cases run: " + totalTestCases);
        System.out.println("Number of cases passed: " + passedCaseNumber);
        System.out.println("Number of cases failed: " + failedCaseNumber);
    }
}
