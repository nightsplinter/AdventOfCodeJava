import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

/**
 * --- Day 6: Tuning Trouble ---
 *
 *
 * https://adventofcode.com/2022/day/6
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        String input = Files.readAllLines(Path.of("./src/input.txt"))
                .get(0);
        int marker = 4;

        marker += IntStream.range(0, input.length() - 5)
                .filter(i -> input.substring(i, i + 4)
                        .chars().distinct().count() == 4)
                .findFirst()
                .orElse(0);
        System.out.println(marker);
    }
}
