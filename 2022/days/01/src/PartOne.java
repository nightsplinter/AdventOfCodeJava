import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 1: Calorie Counting ---
 *
 *
 * https://adventofcode.com/2022/day/1
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        int maxCalories = 0;
        int calorie = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.isBlank()) {
                    if (calorie > maxCalories) {
                        maxCalories = calorie;
                    }
                    calorie = 0;
                } else {
                    calorie += Integer.parseInt(input);
                }
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(maxCalories);
    }
}
