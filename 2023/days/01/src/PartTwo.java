import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * --- Day 1: Trebuchet?! --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/1
 **/

public class PartTwo {

    public static void main(String[] args) {

        int total = 0;
        String[] numWords = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        String[] numReplace = { "o1e", "t2o", "t3e", "f4r", "f5e", "s6x", "s7n", "e8t", "n9e" };

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                for (int index = 0; index < numWords.length; index++) {
                    input = input.replace(numWords[index], numReplace[index]);
                }

                input = input.replaceAll("\\D", "");
                String firstInt = input.substring(0, 1);
                String lastInt = input.substring(input.length() - 1);
                total += Integer.parseInt(firstInt + lastInt);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(total);
    }
}
