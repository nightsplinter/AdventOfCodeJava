import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * --- Day 3: Gear Ratios --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/3
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        int total = 0;
        int height = 140;
        int width = 140;
        String grid[][] = new String[height][width];
        HashSet<int[]> numbersStartAt = new HashSet<>();

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            int hIndex = 0;

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                grid[hIndex] = input.split("");
                hIndex++;
            }
            scanner.close();

            for (int h = 0; h < grid.length; h++) {
                String[] row = grid[h];

                for (int j = 0; j < row.length; j++) {
                    String value = row[j];
                    Character c = value.charAt(0);
                    numbersStartAt = new HashSet<>();
                    if (c.equals('*')) {
                        // Lines below and above
                        int hAbove = h - 1;
                        int hBelow = h + 1;
                        for (int line = hAbove; line <= hBelow; line++) {

                            // Row to the left and right
                            int rLeft = j - 1;
                            int rRight = j + 1;

                            for (int index = rLeft; index <= rRight; index++) {

                                if (index < 0 || index >= row.length
                                        || line < 0 || line >= grid.length
                                        || !Character.isDigit(
                                                grid[line][index]
                                                        .charAt(0))) {
                                    continue;
                                }

                                int column = index;
                                while (column >= 0
                                        && Character.isDigit(
                                                grid[line][column]
                                                        .charAt(0))) {
                                    column -= 1;
                                }

                                int[] pair = new int[] { line, column + 1 };
                                boolean alreadyExists = false;
                                for (int[] p : numbersStartAt) {
                                    if (p[0] == pair[0] && p[1] == pair[1]) {
                                        alreadyExists = true;
                                        continue;
                                    }
                                }

                                if (!alreadyExists) {
                                    numbersStartAt.add(pair);
                                }
                            }

                            if (numbersStartAt.size() != 2) {
                                continue;
                            }

                            String[] gearPair = new String[2];

                            for (int[] pair : numbersStartAt) {
                                int pH = pair[0];
                                int pR = pair[1];

                                String[] pRow = grid[pH];
                                String pValue = "";
                                int index = pR;
                                while (index < pRow.length
                                        && Character.isDigit(
                                                pRow[index].charAt(0))) {
                                    pValue += pRow[index];
                                    index += 1;
                                }
                                if (gearPair[0] == null) {
                                    gearPair[0] = pValue;
                                } else {
                                    gearPair[1] = pValue;
                                    total += Integer.parseInt(
                                            gearPair[0]) *
                                            Integer.parseInt(gearPair[1]);
                                    gearPair = new String[2];
                                    numbersStartAt = new HashSet<>();
                                }
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(total);
    }
}