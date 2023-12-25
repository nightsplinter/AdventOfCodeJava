import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 14: Parabolic Reflector Dish ---
 *
 *
 * https://adventofcode.com/2023/day/14
 **/

public class PartOne {

    public static void main(String[] args) throws Exception {
        List<String> grid = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                grid.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        List<String> northGrid = getNorthGrid(grid);
        System.out.println(getTotalLoad(northGrid));
    }

    /**
     * Get the total load of the rounded rocks.
     *
     * @param northGrid grid after all O's move up
     * @return total number of rounded rocks
     */
    private static int getTotalLoad(List<String> northGrid) {
        int sum = 0;
        int len = northGrid.size();
        for (String line : northGrid) {
            int count = line.length() - line.replace("O", "").length();
            sum += count * len;
            len--;
        }
        return sum;
    }

    /**
     * Get the grid after all O's move up
     *
     * @param grid original grid
     * @return grid after all O's move up
     */
    private static List<String> getNorthGrid(List<String> grid) {
        List<String> northGrid = new ArrayList<>();

        // Skip first line because it's the header and no O can move up
        for (int row = 0; row < grid.size(); row++) {
            String line = grid.get(row);
            String currentLine = "";

            if (row == 0) {
                northGrid.add(line);
                continue;
            }

            if (!line.contains("O")) {
                northGrid.add(line);
                continue;
            }

            String temp = line;
            // Check all columns in this row
            for (int col = 0; col < line.length(); col++) {

                if (line.charAt(col) != 'O') {
                    continue;
                }

                int rowNumber = -1;
                // Check all above rows at same column
                for (int aboveRow = row - 1; aboveRow >= 0; aboveRow--) {
                    String aboveLine = northGrid.get(aboveRow);
                    if (aboveLine.charAt(col) == '.') {
                        rowNumber = aboveRow;
                    } else {
                        break;
                    }
                }

                if (rowNumber != -1) {
                    String newLine = northGrid.get(rowNumber);
                    newLine = newLine.substring(0, col) + "O" + newLine.substring(col + 1);
                    currentLine = temp.substring(0, col) + "." + temp.substring(col + 1);
                    grid.set(row, currentLine);
                    northGrid.set(rowNumber, newLine);
                    temp = currentLine;
                    rowNumber = -1;
                }
            }
            String newLine = grid.get(row);
            northGrid.add(newLine);
        }
        return northGrid;
    }
}
