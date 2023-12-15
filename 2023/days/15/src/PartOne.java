import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 15: Lens Library ---
 *
 *
 * https://adventofcode.com/2023/day/15
 **/

public class PartOne {

    public static void main(String[] args) throws Exception {
        int total = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                for (int i = 0; i < split.length; i++) {
                    total += getAsciiCode(split[i]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(total);
    }

    /**
     * Get the ascii code of a string
     *
     * @param string the string to get the ascii code of
     * @return the ascii code of the string
     */
    private static int getAsciiCode(String string) {
        int current = 0;
        for (int i = 0; i < string.length(); i++) {
            int ascii = (int) string.charAt(i);
            current += ascii;
            current *= 17;
            current %= 256;
        }
        return current;
    }
}