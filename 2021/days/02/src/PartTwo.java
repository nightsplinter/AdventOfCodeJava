
/**
 * --- Day 2: Dive! ---
 *
 * https://adventofcode.com/2021/day/2#part2
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartTwo {
    public static void main(String[] args) throws Exception {
        Pattern forwardPattern = Pattern.compile("forward\\s(\\d+)");
        Pattern downPattern = Pattern.compile("down\\s(\\d+)");
        Pattern upPattern = Pattern.compile("up\\s(\\d+)");
        boolean f = false;
        boolean u = false;
        Matcher find;
        int depth = 0;
        int horizontal = 0;
        int aim = 0;

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher forwardMatch = forwardPattern.matcher(line);
                boolean forward = forwardMatch.matches();

                Matcher downMatch = downPattern.matcher(line);
                boolean down = downMatch.matches();

                Matcher upMatch = upPattern.matcher(line);
                boolean up = upMatch.matches();

                if (forward) {
                    find = forwardMatch;
                    f = true;
                } else if (down) {
                    find = downMatch;
                } else {
                    find = upMatch;
                    u = true;
                }
                int number = Integer.parseInt(find.group(1));

                if (u) {
                    u = false;
                    aim -= number;

                } else if (f) {
                    horizontal += number;
                    if (aim > 0) {
                        depth += number * aim;
                    }

                    f = false;
                } else {
                    aim += number;
                }
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        System.out.println(depth * horizontal);
    }
}
