import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 11: Cosmic Expansion ---
 *
 *
 * https://adventofcode.com/2023/day/11
 **/

public class PartOne {

    /**
     * This class is used to represent a galaxy.
     */
    public record Galaxy(int x, int y) {

        /**
         * This method is used to calculate the Manhattan distance
         * between two galaxies.
         *
         * @param other The other galaxy.
         * @return The Manhattan distance between the two galaxies.
         */
        public long manhattanDistance(Galaxy other) {
            return Math.abs(other.x - x) + Math.abs(other.y - y);
        }
    }

    /**
     * This list is used to store the grid.
     */
    static List<String> grid = new ArrayList<>();

    /**
     * This list is used to store the columns that contain only dots.
     */
    public static List<Integer> cols = new ArrayList<>();

    /**
     * This list is used to store the galaxies.
     */
    public static List<Galaxy> galaxies = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.contains("#")) {
                    grid.add(line);
                }
                grid.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        setCols();
        expandGrid();
        setGalaxies();
        System.out.println(totalSum());
    }

    /**
     * This method is used to calculate the total sum of the Manhattan
     * distances between all the galaxies.
     *
     * @return The total sum of the Manhattan distances between all
     *         the galaxies.
     */
    public static long totalSum() {
        long sum = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            var galaxy = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                var otherGalaxy = galaxies.get(j);
                sum += otherGalaxy.manhattanDistance(galaxy);
            }
        }
        return sum;
    }

    /**
     * This method is used to set the galaxies list.
     */
    private static void setGalaxies() {
        for (int x = 0; x < grid.size(); x++) {
            for (int y = 0; y < grid.get(0).length(); y++) {
                if (grid.get(x).charAt(y) == '#') {
                    galaxies.add(new Galaxy(x, y));
                }
            }
        }
    }

    /**
     * This method is used to expand the grid.
     * It adds a dot to each column that contains only dots.
     */
    private static void expandGrid() {
        int cIndex = 0;
        for (int column : cols) {
            for (int listIndex = 0; listIndex < grid.size(); listIndex++) {
                grid.set(
                        listIndex,
                        grid.get(listIndex).substring(0, column + cIndex)
                                + "."
                                + grid.get(listIndex).substring(column + cIndex));
            }
            cIndex++;
        }
    }

    /**
     * This method is used to set the cols list.
     * It checks if there is a column that contains only dots.
     */
    public static void setCols() {
        boolean isDot;
        for (int i = 0; i < grid.get(0).length() - 1; i++) {
            isDot = true;
            for (String line : grid) {
                if (line.charAt(i) == '#') {
                    isDot = false;
                    break;
                }
            }
            if (isDot) {
                cols.add(i);
            }
        }
    }

}
