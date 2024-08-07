/*

             Program written by: Juan Alvarado (https://github.com/JuanAlvarado305)
             Purpose: Java program that generates a random string of a given length and finds different special patterns. It will search
             if the random string contains either a singleton, palindrome, balanced or arithmetic pattern. The user is instructed to input
             a string length between the values of 100,000 to 100,000,000 The user is then instructed to input the max length of special
             characters between 3 and 15. The program then generates the string based on the length inputted by the user and afterward
             outputs the special patterns it detected.
*/

// import all the necessary classes that we will need for this program, I know I could use java.util.*

import java.util.Scanner; import java.util.List ; import java.util.Random; import java.util.Comparator;
import java.util.ArrayList; import java.util.InputMismatchException;

public class SpecialPatternFinder {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int randomStringLength;
        int patternMaxiumLength;

        while (true) {
            try {
                System.out.println("Enter the length of the randomized string, input a value between (100,000 to 1,000,000,000): ");
                randomStringLength = scnr.nextInt();

                // Check if the randomStringLength is outside the correct range
                // Which is from 100,000 to 1,000,000,000 (inclusive)
                // Basically any value outside of that range throws an exception
                // I love the logical OR operator, it has saved me :)!
                if (randomStringLength < 100000 || randomStringLength > 1000000000) {
                    throw new NumberFormatException("The length of the random string is out of the legal range.");
                }
                System.out.println("Enter the maximum length of the special patterns (3 to 15): ");
                patternMaxiumLength = scnr.nextInt();
                if (patternMaxiumLength < 3 || patternMaxiumLength > 15) {
                    throw new NumberFormatException("The maximum length of the special patterns is out of the legal range.");
                }
                break;
                // This block catches exceptions related to invalid user input such as InputMismatchException and NumberFormatException
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers only.");
                scnr.nextLine(); // Clear the invalid input
            }
        }

        // Generate the random string
        String randomString = randomStringGenerator(randomStringLength);
        System.out.println("Generated Random String: " + randomString);

        // Find and rank special patterns in the random string
        List<String> patternsFound = findAndRankSpecialPatterns(randomString, patternMaxiumLength);

        // Print the results
        if (patternsFound.isEmpty()) {
            System.out.println("No special patterns found in the random string.");
        } else {
            System.out.println("Special patterns found:");
            for (String pattern : patternsFound) {
                System.out.println(pattern);
            }
        }

        scnr.close();
    }

    private static String randomStringGenerator(int length) {
        Random random = new Random(System.nanoTime());
        //The size of the array is determined by the length parameter passed to the method.
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            //This line generates a random lowercase letter and assigns it to a position in the array
            array[i] = (char) ('a' + random.nextInt(26));//26 letters in the alphabet

        }
        return new String(array);
    }

    private static List<String> findAndRankSpecialPatterns(String randomString, int patternMaxLength) {
        List<String> patternsFound = new ArrayList<>();

        // Check for each type of special pattern and add it to the list if it's found
        try {
            // Check for Singleton String
            String singleton = SingletonPatternFinder.singletonMiner(randomString, patternMaxLength);
            if (singleton != null) {
                patternsFound.add("Singleton String: " + singleton);
            }

            // Check for Arithmetic String of Order 1
            String arithmetic1 = ArithmeticPatternFinder.arithmeticMiner(randomString, patternMaxLength, 1);
            if (arithmetic1 != null) {
                patternsFound.add("Arithmetic String of Order 1: " + arithmetic1);
            }

            // Check for Arithmetic String of Order -1
            String arithmeticMinus1 = ArithmeticPatternFinder.arithmeticMiner(randomString, patternMaxLength, -1);
            if (arithmeticMinus1 != null) {
                patternsFound.add("Arithmetic String of Order -1: " + arithmeticMinus1);
            }

            // Check for Balanced Tripartite String
            String tripartite = BalancedPatternFinder.tripartiteMiner(randomString, patternMaxLength);
            if (tripartite != null) {
                patternsFound.add("Balanced Tripartite String: " + tripartite);
            }

            // Check for Balanced Bipartite String
            String bipartite = BalancedPatternFinder.bipartiteMiner(randomString, patternMaxLength);
            if (bipartite != null) {
                patternsFound.add("Balanced Bipartite String: " + bipartite);
            }

            // Check for Palindrome
            String palindrome = PalindromePatternFinder.palindromeMiner(randomString, patternMaxLength);
            if (palindrome != null) {
                patternsFound.add("Palindrome: " + palindrome);
            }
            //Catch-all statement to catch any exception for the try block above
        } catch (Exception e) {
            System.out.println("Error while finding patterns: " + e.getMessage());
        }

        // Sort the patterns by rarity (descending order of length)
        patternsFound.sort(Comparator.comparingInt(String::length).reversed());

        return patternsFound;
    }
}

