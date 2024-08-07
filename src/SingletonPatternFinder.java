public class SingletonPatternFinder {
    public static String singletonMiner(String randomString, int maxLength) {
        // Iterate from maxLength down to 1
        for (int i = maxLength; i >= 1; i--) {
            // Check all possible substrings of length i in the randomString
            for (int j = 0; j <= randomString.length() - i; j++) {
                boolean isSingleton = true;
                // Get the first character of the current substring
                char firstChar = randomString.charAt(j);
                // Compare all other characters in the substring to the first character
                for (int k = j + 1; k < j + i; k++) {
                    // If any character doesn't match, it's not a singleton
                    if (randomString.charAt(k) != firstChar) {
                        isSingleton = false;
                        break;
                    }
                }
                // If all characters match, we've found singleton
                if (isSingleton) {
                    // Return the singleton substring
                    return randomString.substring(j, j + i);
                }
            }
        }
        // If no singleton is found, return null
        return null;
    }
}