import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 8: Matchsticks ---
 * --- Part Two ---
 *
 * https://adventofcode.com/2015/day/8#part2
 */

public class PartTwo {
    public static void main(String[] args) throws Exception {
        int length = 0;
        int dataLength = 0;
        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                length += line.length();
                dataLength += encodedLength(line);
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(dataLength - length);
    }

    private static int encodedLength(String line) {
        String encoded = "\\\"";
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == '\\' || line.charAt(i) == '"') {
                encoded += "\\";
            }
            encoded += line.charAt(i);
        }
        encoded += "\\\"";
        return encoded.length();
    }
}
