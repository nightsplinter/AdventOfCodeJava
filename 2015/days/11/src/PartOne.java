/**
 * --- Day 11: Corporate Policy ---
 *
 * https://adventofcode.com/2015/day/11
 *
 */

public class PartOne {
    public static void main(String[] args) {
        String input = "vzbxkghb";
        do {
            input = getNextPassword(input);
        } while (!isValid(input));

        System.out.println(input);
    }

    public static String getNextPassword(String password) {
        char[] chars = password.toCharArray();
        int i = chars.length - 1;

        while (i >= 0 && chars[i] == 'z') {
            chars[i] = 'a';
            i--;
        }

        if (i < 0) {
            return new String(chars) + 'a';
        } else {
            chars[i]++;
            return new String(chars);
        }
    }

    private static boolean isValid(String password) {
        if (password.contains("i")
                || password.contains("o")
                || password.contains("l")) {
            return false;
        }

        if (password.contains("i") || password.contains("o") || password.contains("l")) {
            return false;
        }
        int numPairs = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                numPairs++;
                i++;
            }
        }
        if (numPairs < 2) {
            return false;
        }

        boolean threeLetters = false;
        for (int i = 0; i < password.length() - 2; i++) {
            char charAtIndex = password.charAt(i);

            if (charAtIndex + 1 == password.charAt(i + 1)
                    && charAtIndex + 2 == password.charAt(i + 2)) {
                threeLetters = true;
                break;
            }
        }
        return threeLetters;
    }
}
