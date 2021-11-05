import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 *
 * https://adventofcode.com/2015/day/5
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {

        int nice = 0;

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                Pattern duplicateLetter = Pattern.compile("(.)\\1+");
                Matcher match = duplicateLetter.matcher(input);

                if (input.matches("(\\w*[aeuio]\\w*){3,}") && input.matches("(?:([A-Za-z0-9])(?!2{2}))+.")
                        && match.find() && !input.contains("ab") && !input.contains("cd") && !input.contains("pq")
                        && !input.contains("xy")) {
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
