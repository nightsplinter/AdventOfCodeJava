/**
 * --- Day 1: Sonar Sweep --- --- Part Two ---
 *
 * https://adventofcode.com/2021/day/1#part2
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) throws Exception {
        int counter = 0;
        ArrayList<Integer> input = new ArrayList<Integer>();

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                input.add(Integer.parseInt(line));
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        for (int i = 3; i < input.size(); i += 1) {

            int firstWindow = input.get(i - 1) + input.get(i - 2) + input.get(i - 3);
            int secondWindow = input.get(i - 2) + input.get(i - 1) + input.get(i);
            if (secondWindow > firstWindow) {
                counter += 1;
            }
        }
        System.out.println(counter);
    }
}