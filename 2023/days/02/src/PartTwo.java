import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 2: Cube Conundrum --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/2
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {

        int totalMinCubes = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                int getIndexOf = input.indexOf(":");
                input = input.substring(getIndexOf + 1);

                String[] splitBySet = input.split(";");

                int minRed = 0;
                int minGreen = 0;
                int minBlue = 0;

                for (int i = 0; i < splitBySet.length; i++) {

                    String[] splitByComma = splitBySet[i].split(",");

                    for (int j = 0; j < splitByComma.length; j++) {
                        String current = splitByComma[j].trim();
                        String numOfCubes = current.substring(0,
                                current.indexOf(" "));
                        int num = Integer.parseInt(numOfCubes);

                        if (current.contains("red") && num > minRed) {
                            minRed = num;
                        } else if (current.contains("green") && num > minGreen) {
                            minGreen = num;
                        } else if (current.contains("blue") && num > minBlue) {
                            minBlue = num;
                        }
                    }
                }

                int powerOfSet = minRed * minGreen * minBlue;
                totalMinCubes += powerOfSet;
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(totalMinCubes);
    }
}
