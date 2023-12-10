import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 10: Pipe Maze --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/10
 **/

public class PartTwo {

    /**
     * Class that represents the coordinates
     */
    public record Coordinates(int x, int y) {
        /**
         * Gets the x coordinate
         *
         * @return the x coordinate
         */
        public int getX() {
            return x;
        }

        /**
         * Gets the y coordinate
         *
         * @return the y coordinate
         */
        public int getY() {
            return y;
        }
    }

    /**
     * Queue that stores the coordinates
     */
    public static LinkedList<Coordinates> queue = new LinkedList<Coordinates>();

    /**
     * The input
     */
    public static char[][] input = new char[140][140];

    /**
     * Map that stores the visited coordinates
     */
    public static Map<Coordinates, Boolean> visited = new HashMap<Coordinates, Boolean>();

    public static void main(String[] args) throws Exception {
        int startX = 0;
        int startY = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split("");
                for (int i = 0; i < split.length; i++) {
                    input[index][i] = split[i].charAt(0);
                    visited.put(new Coordinates(index, i), false);
                    if (input[index][i] == 'S') {
                        startX = index;
                        startY = i;
                    }
                }
                index++;
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        bfs(startX, startY);

        int inside = 0;
        int count = 0;
        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < input[x].length; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (visited.get(coordinates) == false) {
                    inside = getCountInside(x, y);
                    if (inside > 0) {
                        count += inside % 2 == 1 ? 1 : 0;
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static int getCountInside(int x, int yIndexOfTheCharacter) {
        char[] row = input[x];
        int count = 0;
        int y = 0;
        while (y < yIndexOfTheCharacter) {
            y++;
            Coordinates coordinates = new Coordinates(x, y);
            if (visited.get(coordinates) == false) {
                continue;
            }
            count += "JL|".contains(row[y] + "") ? 1 : 0;
        }
        return count;
    }

    public static void bfs(int x, int y) {
        Coordinates startCoordinates = new Coordinates(x, y);
        visited.put(startCoordinates, true);
        queue.add(startCoordinates);

        int inputHeight = input.length - 1;
        int inputWidth = input[0].length - 1;

        while (queue.size() != 0) {
            Coordinates coordinates = queue.poll();
            x = coordinates.getX();
            y = coordinates.getY();
            char character = input[x][y];

            if (x > 0 && canGoUp(character)
                    && nextCharacterCanReceiveGoingUp(x, y)) {
                ifNotVisitedAddToQueue(new Coordinates(x - 1, y));
            }

            if (x < inputHeight && canGoDown(character)
                    && nextCharacterCanReceiveGoingDown(x, y)) {
                ifNotVisitedAddToQueue(new Coordinates(x + 1, y));
            }

            if (y > 0 && canGoLeft(character)
                    && nextCharacterCanReceiveGoingLeft(x, y)) {
                ifNotVisitedAddToQueue(new Coordinates(x, y - 1));
            }

            if (y < inputWidth && canGoRight(character)
                    && nextCharacterCanReceiveGoingRight(x, y)) {
                ifNotVisitedAddToQueue(new Coordinates(x, y + 1));
            }
        }
    }

    /**
     * If the coordinates are not visited, add them to the queue
     * and mark them as visited
     *
     * @param next the coordinates
     */
    private static void ifNotVisitedAddToQueue(Coordinates next) {
        if (!visited.get(next)) {
            visited.put(next, true);
            queue.add(next);
        }
    }

    /**
     * Checks if the next character can receive the character going right
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if it can receive, false otherwise
     */
    private static boolean nextCharacterCanReceiveGoingRight(int x, int y) {
        char nextCharacter = input[x][y + 1];
        return "-J7".contains(nextCharacter + "");
    }

    /**
     * Checks if the next character can receive the character going left
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if it can receive, false otherwise
     */
    private static boolean nextCharacterCanReceiveGoingLeft(int x, int y) {
        char nextCharacter = input[x][y - 1];
        return "-LF".contains(nextCharacter + "");
    }

    /**
     * Checks if the next character can receive the character going down
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if it can receive, false otherwise
     */
    private static boolean nextCharacterCanReceiveGoingDown(int x, int y) {
        char nextCharacter = input[x + 1][y];
        return "|JL".contains(nextCharacter + "");
    }

    /**
     * Checks if the next character can receive the character going up
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return true if it can receive, false otherwise
     */
    private static boolean nextCharacterCanReceiveGoingUp(int x, int y) {
        char nextCharacter = input[x - 1][y];
        return "|7F".contains(nextCharacter + "");
    }

    /**
     * Checks if the character can go up
     *
     * @param character the character
     * @return true if it can go up, false otherwise
     */
    private static boolean canGoUp(char character) {
        return "S|JL".contains(character + "");
    }

    /**
     * Checks if the character can go down
     *
     * @param character the character
     * @return true if it can go down, false otherwise
     */
    private static boolean canGoDown(char character) {
        return "S|7F".contains(character + "");
    }

    /**
     * Checks if the character can go left
     *
     * @param character the character
     * @return true if it can go left, false otherwise
     */
    private static boolean canGoLeft(char character) {
        return "S-J7".contains(character + "");
    }

    /**
     * Checks if the character can go right
     *
     * @param character the character
     * @return true if it can go right, false otherwise
     */
    private static boolean canGoRight(char character) {
        return "S-LF".contains(character + "");
    }
}
