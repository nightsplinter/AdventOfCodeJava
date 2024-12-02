import java.nio.file.Files;
import java.nio.file.Path;

/**
 * --- Day 2: Red-Nosed Reports ---
 *
 *
 * https://adventofcode.com/2024/day/2
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/02/src/input.txt");
        int amountOfSafeValues = 0;

        try (var lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                boolean isSafe = true;
                boolean isDecreasing = false;
                String[] numbers = line.split("\\s+");
                int size = numbers.length - 1;

                for (int index = 0; index < size; index++) {

                    int currentNumber = Integer.parseInt(numbers[index]);
                    int nextNumber = Integer.parseInt(numbers[index + 1]);
                    int difference = Math.abs(currentNumber - nextNumber);

                    if (index == 0) {
                        isDecreasing = currentNumber > nextNumber;
                    }

                    if (difference < 1 || difference > 3
                            || isDecreasing != (currentNumber > nextNumber)) {
                        isSafe = false;
                        break;
                    }
                }
                if (isSafe)
                    amountOfSafeValues++;
            }
        }
        System.out.println(amountOfSafeValues);
    }
}
