import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 8: Matchsticks ---
 *
 * https://adventofcode.com/2015/day/8
 */

public class PartOne {
    public static void main(String[] args) throws Exception {
        int length = 0;
        int dataLength = 0;
        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                length += line.length();
                dataLength += inMemoryLength(line);
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(length - dataLength);
    }

    private static int inMemoryLength(String line) {

        String insideString = line.substring(1, line.length() - 1);
        int length = insideString.length();

        int count = insideString.length();
        if (count == 0) {
            return count;
        }
        int i = 0;

        do {
            if (insideString.charAt(i) == '\\'
                    && (insideString.charAt(i + 1) == '\\'
                            || insideString.charAt(i + 1) == '"')) {
                count -= 1;
                i += 2;
            } else if (insideString.charAt(i) == '\\'
                    && insideString.charAt(i + 1) == 'x') {
                i += 4;
                count -= 3;
            } else {
                i += 1;
            }
        } while (i < length);
        return count;
    }
}
