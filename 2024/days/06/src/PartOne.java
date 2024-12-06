import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 6: Guard Gallivant ---
 *
 *
 * https://adventofcode.com/2024/day/6
 **/

public class PartOne {

    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/06/src/input.txt");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e);
            return;
        }
        int amountOfLines = lines.size();
        int amountOfColumns = lines.get(0).length();

        int currentLine = 0;
        int currentColumn = 0;

        for (int lineIndex = 0; lineIndex < amountOfLines; lineIndex++) {
            for (int colIndex = 0; colIndex < amountOfColumns; colIndex++) {
                if (lines.get(lineIndex).charAt(colIndex) == '^') {
                    currentColumn = colIndex;
                    currentLine = lineIndex;
                    break;
                }
            }
        }

        int[][] directions = {
                { -1, 0 }, // up ^
                { 0, 1 }, // right >
                { 1, 0 }, // down v
                { 0, -1 } // left <
        };

        int directionIndex = 0;
        Set<String> visted = new HashSet<>();

        while (true) {
            visted.add(currentLine + "," + currentColumn);
            int nextLine = currentLine + directions[directionIndex][0];
            int nextColumn = currentColumn + directions[directionIndex][1];

            if (nextLine < 0 || nextLine >= amountOfLines
                    || nextColumn < 0 || nextColumn >= amountOfColumns) {
                break;
            }

            char nextChar = lines.get(nextLine).charAt(nextColumn);
            if (nextChar == '#') {
                directionIndex = (directionIndex + 1) % 4;
            } else {
                currentLine = nextLine;
                currentColumn = nextColumn;
            }
        }
        System.out.println(visted.size());
    }
}
