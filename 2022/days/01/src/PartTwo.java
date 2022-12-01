import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * --- Day 1: Calorie Counting --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2022/day/1
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        int totalCalories = 0;
        ArrayList<Integer> calorieList = new ArrayList<Integer>();
        int calorie = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.isBlank()) {
                    calorieList.add(calorie);
                    calorie = 0;
                } else {
                    calorie += Integer.parseInt(input);
                }
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        Collections.sort(calorieList, Collections.reverseOrder());
        totalCalories = calorieList.get(0)
                + calorieList.get(1) + calorieList.get(2);
        System.out.println(totalCalories);
    }
}
