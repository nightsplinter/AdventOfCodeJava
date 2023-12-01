import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 1: Trebuchet?! ---
 *
 *
 * https://adventofcode.com/2023/day/1
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        int total = 0;
        String firstInt = "";
        String lastInt = "";

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                input = input.replaceAll("[^0-9]", "");
                firstInt = input.substring(0, 1);
                lastInt = input.substring(input.length() - 1);
                total += Integer.parseInt(firstInt + lastInt);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(total);
    }
}
