import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * --- Day 3: Mull It Over --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2024/day/3
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/03/src/input.txt");
        long multiplications = 0;
        ArrayList<String> operations = new ArrayList<>();
        Pattern pattern = Pattern
                .compile(
                        "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        Pattern mul_pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        try (var lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                var matcher = pattern.matcher(line);
                while (matcher.find()) {
                    operations.add(matcher.group());
                }
            }
        }
        boolean firstDoOrDont = false;
        boolean mul_enabled = false;

        for (int i = 0; i < operations.size(); i++) {
            boolean isDo = operations.get(i).equals("do()");
            boolean isDont = operations.get(i).equals("don't()");
            if ((isDo || isDont) && !firstDoOrDont) {
                firstDoOrDont = !firstDoOrDont;
            }
            if (!firstDoOrDont || isDo) {
                mul_enabled = true;
            }
            if (isDont) {
                mul_enabled = false;
            }
            if (mul_enabled) {
                var matcher = mul_pattern.matcher(operations.get(i));
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
