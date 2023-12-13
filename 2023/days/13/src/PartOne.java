import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 13: Cosmic Expansion ---
 *
 *
 * https://adventofcode.com/2023/day/13
 **/

public class PartOne {

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
     * @param grid
     * @return
     */
    static private int getReflectionIndex(List<String> grid) {
        int sizeOfGrid = grid.size() - 1;

        for (int rowIndex = 0; rowIndex < sizeOfGrid; rowIndex++) {
            if (hasReflection(grid, rowIndex)) {
                return rowIndex + 1;
            }
        }
        return 0;
    }

    /**
     * This method is used to check if the grid has reflection at the given row
     * index.
     *
     * @param grid     The grid
     * @param rowIndex The row index
     * @return True if the grid has reflection at the given row index, false
     *         otherwise
     */
    static private boolean hasReflection(List<String> grid, int rowIndex) {
        int sizeOfGrid = grid.size();

        for (var index = 0; index < sizeOfGrid; index++) {
            int mirrorIndexOfRow = rowIndex * 2 + 1 - index;

            if (mirrorIndexOfRow >= sizeOfGrid || mirrorIndexOfRow < 0) {
                continue;
            }

            if (!grid.get(index).equals(grid.get(mirrorIndexOfRow))) {
                return false;
            }
        }
        return true;
    }

}