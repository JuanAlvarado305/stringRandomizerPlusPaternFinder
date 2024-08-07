public class ArithmeticPatternFinder {
    public static String arithmeticMiner(String randomString, int maxLength, int order) {
        // StringBuilder to store the pattern
        StringBuilder pattern = new StringBuilder();

        // Iterate from maxLength down to 2 (minimum arithmetic sequence length)
        for (int i = maxLength; i >= 2; i--) {
            // Check all possible substrings of length i in the randomString
            for (int j = 0; j <= randomString.length() - i; j++) {
                boolean isArithmetic = true;
                // Set the step size based on the order
                int step = order;

                // Check if the difference between consecutive characters is the same
                for (int k = 1; k < i; k++) {
                    // Compare the absolute difference between consecutive characters
                    if (Math.abs(randomString.charAt(j + k) - randomString.charAt(j + k - 1)) != step) {
                        isArithmetic = false;
                        break;
                    }
                }

                // If we've found an arithmetic sequence, return it
                if (isArithmetic) {
                    return randomString.substring(j, j + i);
                }
            }
        }

        // If no arithmetic sequence is found, return null
        return null;
    }
}