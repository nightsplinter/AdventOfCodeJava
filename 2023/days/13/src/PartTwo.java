import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 13: Cosmic Expansion --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/13
 **/

public class PartTwo {

    public static void main(String[] args) throws Exception {

        List<String> grid = new ArrayList<>();
        List<String> gridTranspose = new ArrayList<>();
        int total = 0;
        int lineMultiplicationFactor = 100;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains("#")) {
                    grid.add(line);
                    continue;
                }

                gridTranspose = getTransposeGrid(grid);
                total += (getReflectionIndex(grid) * lineMultiplicationFactor
                        + getReflectionIndex(gridTranspose));

                grid.clear();
                gridTranspose.clear();
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(total);
    }

    /**
     * This method is used to transpose the grid.
     *
     * @param originalGrid The original grid
     * @return The transposed grid
     */
    private static List<String> getTransposeGrid(List<String> originalGrid) {
        List<String> grid = new ArrayList<>();
        int size = originalGrid.size();
        int lengthOfLine = originalGrid.get(0).length();

        for (int index = 0; index < lengthOfLine; index++) {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < size; row++) {
                sb.append(originalGrid.get(row).charAt(index));
            }
            grid.add(sb.toString());
        }
        return grid;
    }

    /**
     * This method is used to get the reflection index.
     *
     * @param grid The grid
     * @return The reflection index
     */
    private static int getReflectionIndex(List<String> grid) {
        final int amountOfSmudges = 1;
        for (var rowIndex = 1; rowIndex < grid.size(); rowIndex++) {
            int countSmudges = 0;
            for (var colIndex = 0; colIndex < Math.min(rowIndex, grid.size() - rowIndex); colIndex++) {
                String firstLine = grid.get(rowIndex - colIndex - 1);
                String secondLine = grid.get(rowIndex + colIndex);
                countSmudges += getAmountOfSmudges(firstLine, secondLine);
            }
            if (countSmudges == amountOfSmudges) {
                return rowIndex;
            }
        }
        return 0;
    }

    /**
     * This method is used to get the amount of smudges.
     *
     * @param firstLine  the first line
     * @param secondLine the second line
     * @return the amount of smudges between the two lines
     */
    private static int getAmountOfSmudges(String firstLine, String secondLine) {
        if (firstLine.equals(secondLine)) {
            return 0;
        }
        int countSmudges = 0;
        for (int cIndex = 0; cIndex < firstLine.length(); cIndex++) {
            if (firstLine.charAt(cIndex) != secondLine.charAt(cIndex)) {
                countSmudges++;
            }
        }
        return countSmudges;
    }

}