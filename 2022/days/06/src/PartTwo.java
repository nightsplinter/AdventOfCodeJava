import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

/**
 * --- Day 6: Tuning Trouble --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2022/day/6#part2
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        String input = Files.readAllLines(Path.of("./src/input.txt"))
                .get(0);
        int marker = 14;

        marker += IntStream.range(0, input.length() - 15)
                .filter(i -> input.substring(i, i + 14)
                        .chars().distinct().count() == 14)
                .findFirst()
                .orElse(0);
        System.out.println(marker);
    }
}
