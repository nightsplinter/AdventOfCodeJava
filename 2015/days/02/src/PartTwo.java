import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * --- Day 2: I Was Told There Would Be No Math --- --- Part Two ---
 *
 * https://adventofcode.com/2015/day/2#part2
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {

        int ribbons = 0;

        try {

            Scanner scanner = new Scanner(new File("02/src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] result = input.split("x");

                int l = Integer.parseInt(result[0]);
                int w = Integer.parseInt(result[1]);
                int h = Integer.parseInt(result[2]);

                int[] numbers = { l, w, h };
                Arrays.sort(numbers);

                ribbons += numbers[0] * 2 + numbers[1] * 2 + (l * w * h);
            }

            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(ribbons);
    }
}
