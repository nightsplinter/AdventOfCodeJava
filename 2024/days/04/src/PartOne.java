import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 4: Ceres Search ---
 *
 *
 * https://adventofcode.com/2024/day/4
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/04/src/input.txt");
        String searchWord = "XMAS";
        int count = 0;

        try {
            List<String> lines = Files.readAllLines(path);
            char[][] grid = lines.stream()
                    .map(String::toCharArray)
                    .toArray(char[][]::new);

            count = countWordInGrid(grid, searchWord);

        } catch (Exception e) {
            System.out.println("Error reading file: " + e);
        }
        System.out.println(count);
    }

    public static int countWordInGrid(char[][] grid, String searchWord) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int searchWordLength = searchWord.length();
        int[][] directions = {
                { 0, 1 }, // Horizontal (right)
                { 0, -1 }, // Horizontal (left)

                { 1, 0 }, // Vertical (down)
                { -1, 0 }, // Vertical (up)

                { 1, 1 }, // Diagonal (down-right)
                { 1, -1 }, // Diagonal (down-left)

                { -1, 1 }, // Diagonal (up-right)
                { -1, -1 }, // Diagonal (up-left)
        };

        for (int rIndex = 0; rIndex < rows; rIndex++) {
            for (int cIndex = 0; cIndex < cols; cIndex++) {
                for (int[] direction : directions) {
                    int tempRowIndex = rIndex;
                    int tempColIndex = cIndex;
                    int wordIndex = 0;

                    while (wordIndex < searchWordLength) {

                        if ((tempRowIndex < 0 || tempRowIndex >= rows
                                || tempColIndex < 0 || tempColIndex >= cols)
                                || grid[tempRowIndex][tempColIndex] != searchWord.charAt(wordIndex)) {
                            break;
                        }
                        tempRowIndex += direction[0];
                        tempColIndex += direction[1];
                        wordIndex++;
                    }
                    if (wordIndex == searchWordLength) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
