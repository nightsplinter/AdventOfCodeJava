import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 2: Cube Conundrum ---
 *
 *
 * https://adventofcode.com/2023/day/2
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {

        int possibleGames = 0;

        int redCubes = 12;
        int greenCubes = 13;
        int blueCubes = 14;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                int getIndexOf = input.indexOf(":");
                int gameNumber = Integer.parseInt(
                        input.substring("Game".length() + 1, getIndexOf));
                input = input.substring(getIndexOf + 1);

                String[] splitBySet = input.split(";");
                boolean canPlay = true;

                for (int i = 0; i < splitBySet.length; i++) {
                    int countRed = 0;
                    int countGreen = 0;
                    int countBlue = 0;
                    if (!canPlay) {
                        break;
                    }

                    String[] splitByComma = splitBySet[i].split(",");

                    for (int j = 0; j < splitByComma.length; j++) {
                        String current = splitByComma[j].trim();
                        String numOfCubes = current.substring(0,
                                current.indexOf(" "));
                        int num = Integer.parseInt(numOfCubes);

                        if (current.contains("red")) {
                            countRed += num;
                        } else if (current.contains("green")) {
                            countGreen += num;
                        } else if (current.contains("blue")) {
                            countBlue += num;
                        }

                        if (canPlay && countRed > redCubes
                                || countGreen > greenCubes
                                || countBlue > blueCubes) {
                            canPlay = false;
                        }
                    }
                }

                if (canPlay) {
                    possibleGames += gameNumber;
                }

            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(possibleGames);
    }
}
