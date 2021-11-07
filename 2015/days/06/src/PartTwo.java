import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 6: Probably a Fire Hazard --- --- Part Two ---
 *
 * https://adventofcode.com/2015/day/6
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {

        int startX, startY, endX, endY;
        Matcher find;
        Boolean on, off;

        int[][] lightGrid = new int[1000][1000];
        int count = 0;

        Pattern turnOnPattern = Pattern.compile("turn\\son\\s(\\d+),(\\d+)\\sthrough\\s(\\d+),(\\d+)");
        Pattern togglePattern = Pattern.compile("toggle\\s(\\d+),(\\d+)\\sthrough\\s(\\d+),(\\d+)");
        Pattern turnOffPattern = Pattern.compile("turn\\soff\\s(\\d+),(\\d+)\\sthrough\\s(\\d+),(\\d+)");

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                Matcher turnOnMatch = turnOnPattern.matcher(input);
                boolean turnOn = turnOnMatch.matches();
                Matcher toggleMatch = togglePattern.matcher(input);
                boolean toggle = toggleMatch.matches();
                Matcher turnOffMatch = turnOffPattern.matcher(input);
                boolean turnOff = turnOffMatch.matches();

                on = false;
                off = false;

                if (turnOn) {
                    find = turnOnMatch;
                    on = true;
                } else if (toggle) {
                    find = toggleMatch;
                } else {
                    find = turnOffMatch;
                    off = true;
                }

                startX = Integer.parseInt(find.group(1));
                startY = Integer.parseInt(find.group(2));
                endX = Integer.parseInt(find.group(3));
                endY = Integer.parseInt(find.group(4));

                for (int i = startX; i <= endX; i++) {
                    for (int j = startY; j <= endY; j++) {

                        if (off) {
                            lightGrid[i][j] = Math.max(0, lightGrid[i][j] -= 1);

                        } else if (on) {
                            lightGrid[i][j] += 1;
                        } else {
                            lightGrid[i][j] += 2;
                        }
                    }
                }

            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < lightGrid.length; i++) {
            for (int j = 0; j < lightGrid.length; j++) {
                if (lightGrid[i][j] != 0) {
                    count += lightGrid[i][j];
                }
            }
        }
        System.out.println(count);
    }
}
