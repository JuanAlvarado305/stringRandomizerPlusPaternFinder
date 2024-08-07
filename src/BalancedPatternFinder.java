public class BalancedPatternFinder {
    // Method to find the longest tripartite pattern in the given string
    public static String tripartiteMiner(String randomString, int maxLength) {
        int length = randomString.length();
        // Iterate from maxLength down to 3, looking for tripartite patterns
        for (int i = maxLength; i >= 3; i--) {
            // Check if the string can be evenly divided into i parts
            if (length % i == 0) {
                int partLength = length / i;
                boolean isTripartite = true;
                String part = randomString.substring(0, partLength);
                for (int j = 1; j < i; j++) {
                    // If any part doesn't match, it's not tripartite
                    if (!randomString.substring(j * partLength, (j + 1) * partLength).equals(part)) {
                        isTripartite = false;
                        break;
                    }
                }
                if (isTripartite) {
                    return part.repeat(i);
                }
            }
        }
        return null;
    }

    public static String bipartiteMiner(String randomString, int maxLength) {
        // Iterate over even lengths from maxLength down to 6
        for (int i = maxLength; i >= 6; i -= 2) {
            // Check all possible substrings of length i in the randomString
            for (int j = 0; j <= randomString.length() - i; j++) {
                // Extract the first half of the current substring
                String firstHalf = randomString.substring(j, j + i / 2);
                // Extract the second half of the current substring
                String secondHalf = randomString.substring(j + i / 2, j + i);
                // Compare the two halves
                if (firstHalf.equals(secondHalf)) {
                    // If the halves are identical, we've found a bipartite pattern
                    // Return the complete bipartite string
                    return firstHalf + secondHalf;
                }
            }
        }
        // If no bipartite pattern is found, return null
        return null;
    }
}