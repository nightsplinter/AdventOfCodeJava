import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 14: Parabolic Reflector Dish ---
 *
 *
 * https://adventofcode.com/2023/day/14
 **/

public class PartTwo {

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
        List<String> newGrid = new ArrayList<>();

        for (int i = 0; i < 1000000000; i++) {

            if (newGrid.isEmpty()) {
                newGrid = grid;
            }

            // north
            List<String> northGrid = getNorthGrid(newGrid);

            // west
            List<String> westGrid = getWestGrid(northGrid);

            // south
            Collections.reverse(westGrid);
            List<String> southGrid = getNorthGrid(westGrid);
            Collections.reverse(southGrid);

            // east
            List<String> eastGrid = getEastGrid(southGrid);
            newGrid = eastGrid;
        }
        System.out.println(getTotalLoad(newGrid));
    }

    /**
     * Get the grid after all O's move east
     *
     * @param grid original grid
     * @return grid after all O's move east
     */
    private static List<String> getEastGrid(List<String> grid) {
        List<String> eastGrid = new ArrayList<>();
        for (int row = 0; row < grid.size(); ++row) {
            String platformRow = grid.get(row);
            for (int column = platformRow.length() - 1; column >= 0; --column) {
                char space = platformRow.charAt(column);
                if (space == 'O') {
                    int newColumn = column;
                    while (newColumn < platformRow.length() - 1 && platformRow.charAt(newColumn + 1) == '.') {
                        ++newColumn;
                    }
                    platformRow = platformRow.substring(0, column) + '.' + platformRow.substring(column + 1);
                    platformRow = platformRow.substring(0, newColumn) + 'O' + platformRow.substring(newColumn + 1);
                }
            }
            eastGrid.add(platformRow);
        }
        return eastGrid;
    }

    // Left
    private static List<String> getWestGrid(List<String> grid) {
        List<String> westGrid = new ArrayList<>();
        for (int row = 0; row < grid.size(); ++row) {
            String platformRow = grid.get(row);
            for (int column = 0; column < platformRow.length(); ++column) {
                char space = platformRow.charAt(column);
                if (space == 'O') {
                    int newColumn = column;
                    while (newColumn > 0 && platformRow.charAt(newColumn - 1) == '.') {
                        --newColumn;
                    }
                    platformRow = platformRow.substring(0, column) + '.' + platformRow.substring(column + 1);
                    platformRow = platformRow.substring(0, newColumn) + 'O' + platformRow.substring(newColumn + 1);
                }
            }
            westGrid.add(platformRow);
        }
        return westGrid;
    }

    /**
     * Get the total load of the rounded rocks.
     *
     * @param grid grid of rounded rocks
     * @return total number of rounded rocks
     */
    private static int getTotalLoad(List<String> grid) {
        int sum = 0;
        int len = grid.size();
        for (String line : grid) {
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
