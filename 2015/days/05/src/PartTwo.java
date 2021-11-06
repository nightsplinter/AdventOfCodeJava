import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? --- --- Part Two ---
 *
 * https://adventofcode.com/2015/day/5#part2
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {

        int nice = 0;

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                Pattern duplicateLetters = Pattern.compile("\\b\\w*(\\w{2})\\w*\\1");
                Matcher matchDuplicate = duplicateLetters.matcher(input);

                Pattern repeats = Pattern.compile("(.)(.)\\1");
                Matcher matchRepeats = repeats.matcher(input);

                if (matchDuplicate.find() && matchRepeats.find()) {
                    nice++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(nice);
    }
}
