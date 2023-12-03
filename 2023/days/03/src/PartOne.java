import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * --- Day 3: Gear Ratios ---
 *
 *
 * https://adventofcode.com/2023/day/3
 **/

public class PartOne {
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

                    if (!Character.isDigit(c) && !c.equals('.')) {
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
                        }
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        for (int[] pair : numbersStartAt) {
            int h = pair[0];
            int r = pair[1];

            String[] row = grid[h];
            String value = "";
            int index = r;
            while (index < row.length
                    && Character.isDigit(row[index].charAt(0))) {
                value += row[index];
                index += 1;
            }
            total += Integer.parseInt(value);
        }
        System.out.println(total);
    }
}