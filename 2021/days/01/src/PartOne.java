/**
 * --- Day 1: Sonar Sweep ---
 *
 * https://adventofcode.com/2021/day/1
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) throws Exception {
        int counter = -1;
        int first = 0;
        int second = 0;

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                first = Integer.parseInt(line);

                if (first > second) {
                    counter += 1;
                }
                second = Integer.parseInt(line);

            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(counter);
    }
}
