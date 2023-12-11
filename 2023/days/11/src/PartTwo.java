import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

/**
 * --- Day 10: Pipe Maze --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/11
 **/

public class PartTwo {

    /**
     * This class is used to represent a galaxy.
     */
    public record Galaxy(BigInteger x, BigInteger y) {

        /**
         * This method is used to calculate the Manhattan distance
         * between two galaxies.
         *
         * @param other The other galaxy.
         * @return The Manhattan distance between the two galaxies.
         */
        public BigInteger manhattanDistance(Galaxy other) {
            return other.x.subtract(x).abs().add(other.y.subtract(y).abs());
        }
    }

    /**
     * This list is used to store the grid.
     */
    static List<String> grid = new ArrayList<>();

    /**
     * This list is used to store the galaxies.
     */
    public static List<Galaxy> galaxies = new ArrayList<>();

    /**
     * This constant is used to store the size of the grid.
     */
    public final static int SIZE = 1_000_000 - 1;

    public static void main(String[] args) throws Exception {
        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                grid.add(line);
                if (!line.contains("#")) {
                    for (int i = 0; i < SIZE; i++) {
                        grid.add(line);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
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
    public static BigInteger totalSum() {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < galaxies.size(); i++) {
            var galaxy = galaxies.get(i);
            for (int j = i + 1; j < galaxies.size(); j++) {
                var otherGalaxy = galaxies.get(j);
                sum = sum.add(galaxy.manhattanDistance(otherGalaxy));
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
                    BigInteger yBig = getShiftedY(y);
                    BigInteger xBig = BigInteger.valueOf(x);
                    galaxies.add(new Galaxy(xBig, yBig));
                }
            }
        }
    }

    /**
     * This method is used to get the shifted y coordinate.
     *
     * @param y The y coordinate.
     * @return The shifted y coordinate.
     */
    private static BigInteger getShiftedY(int y) {
        String firstLine = grid.get(0);
        int count = 0;
        for (int i = 0; i < y; i++) {
            if (firstLine.charAt(i) == '.') {
                int column = i;
                boolean isDotColumn = checkIfDotColumn(column);
                if (isDotColumn) {
                    count++;
                }
            }
        }
        BigInteger shift = BigInteger.valueOf(SIZE).multiply(BigInteger.valueOf(count));
        return BigInteger.valueOf(y).add(shift);
    }

    /**
     * This method is used to check if a column contains only dots.
     *
     * @param column The column to check.
     * @return True if the column contains only dots, false otherwise.
     */
    private static boolean checkIfDotColumn(int column) {
        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i).charAt(column) == '#') {
                return false;
            }
        }
        return true;
    }
}
