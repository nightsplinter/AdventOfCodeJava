import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 3: Rucksack Reorganization ---
 *
 *
 * https://adventofcode.com/2022/day/3
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        int prioritySum = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                int middle = input.length() / 2;
                String firstCompartment = input.substring(0, middle);
                String secondCompartment = input.substring(middle);

                for (int i = 0; i < firstCompartment.length(); i++) {
                    char c = firstCompartment.charAt(i);
                    int num = Character.isLowerCase(c) ? 96 : 38;

                    if (secondCompartment.contains(c + "")) {
                        prioritySum += ((int) firstCompartment.charAt(i)) - num;
                        break;
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(prioritySum);
    }
}
