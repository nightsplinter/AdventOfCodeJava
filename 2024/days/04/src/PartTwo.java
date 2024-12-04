import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 4: Ceres Search --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2024/day/4
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/04/src/input.txt");
        String searchWords = "XMAS";
        int count = 0;

        try {
            List<String> lines = Files.readAllLines(path);
            char[][] grid = lines.stream()
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
            count += countWordInGrid(grid, searchWords);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e);
        }
        System.out.println(count);
    }

    public static int countWordInGrid(char[][] grid, String searchWord) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        List<Character[][]> patterns = List.of(
                new Character[][] {
                        { 'M', '.', 'S' },
                        { '.', 'A', '.' },
                        { 'M', '.', 'S' },
                },
                new Character[][] {
                        { 'S', '.', 'M' },
                        { '.', 'A', '.' },
                        { 'S', '.', 'M' },
                },
                new Character[][] {
                        { 'S', '.', 'S' },
                        { '.', 'A', '.' },
                        { 'M', '.', 'M' },
                },
                new Character[][] {
                        { 'S', '.', 'S' },
                        { '.', 'A', '.' },
                        { 'M', '.', 'M' },
                },
                new Character[][] {
                        { 'M', '.', 'M' },
                        { '.', 'A', '.' },
                        { 'S', '.', 'S' },
                });

        for (int rIndex = 0; rIndex < rows - 2; rIndex++) {
            for (int cIndex = 0; cIndex < cols - 2; cIndex++) {
                Character[][] xmasGrid = {
                        { grid[rIndex][cIndex], '.', grid[rIndex][cIndex + 2] },
                        { '.', grid[rIndex + 1][cIndex + 1], '.' },
                        { grid[rIndex + 2][cIndex], '.', grid[rIndex + 2][cIndex + 2] }
                };
                for (Character[][] pattern : patterns) {
                    boolean isMatch = true;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (pattern[i][j] != '.'
                                    && pattern[i][j] != xmasGrid[i][j]) {
                                isMatch = false;
                                break;
                            }
                        }
                    }
                    if (isMatch) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
}
