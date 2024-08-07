public class PalindromePatternFinder {
    public static String palindromeMiner(String randomString, int maxLength) {
        // Iterate from maxLength down to 2 (minimum palindrome length)
        for (int i = maxLength; i >= 2; i--) {
            // Check all possible substrings of length i in the randomString
            for (int j = 0; j <= randomString.length() - i; j++) {
                boolean isPalindrome = true;
                // Compare characters from both ends, moving towards the center
                for (int k = 0; k < i / 2; k++) {
                    // If characters don't match, it's not a palindrome
                    if (randomString.charAt(j + k) != randomString.charAt(j + i - 1 - k)) {
                        isPalindrome = false;
                        break;
                    }
                }
                // If we've found a palindrome, return it
                if (isPalindrome) {
                    return randomString.substring(j, j + i);
                }
            }
        }
        // If no palindrome is found, return null
        return null;
    }
}