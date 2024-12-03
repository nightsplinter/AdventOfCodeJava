import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * --- Day 3: Mull It Over ---
 *
 *
 * https://adventofcode.com/2024/day/3
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/03/src/input.txt");
        long multiplications = 0;
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        try (var lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                var matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String[] numbers = {
                            matcher.group(1), matcher.group(2)
                    };
                    long a = Long.parseLong(numbers[0]);
                    long b = Long.parseLong(numbers[1]);
                    multiplications += a * b;
                }
            }
        }
        System.out.println(multiplications);
    }
}
