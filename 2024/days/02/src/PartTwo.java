import java.nio.file.Files;
import java.nio.file.Path;

/**
 * --- Day 2: Red-Nosed Reports --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2024/day/2
 **/

public class PartTwo {
    static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/02/src/input.txt");
        int amountOfSafeValues = 0;
        try (var lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                String[] numbers = line.split("\\s+");
                if (isSafe(numbers)) {
                    amountOfSafeValues++;
                }
            }
        }
        System.out.println(amountOfSafeValues);
    }

    private static boolean isSafe(String[] numbers) {
        int size = numbers.length;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = Integer.parseInt(numbers[i]);
        }

        for (int removeIndex = 0; removeIndex < nums.length; removeIndex++) {
            if (isValidSequence(nums, removeIndex)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidSequence(int[] nums, int removeIndex) {
        boolean isAscending = true, isDescending = true;
        int size = nums.length;

        int[] newNums = new int[size - 1];
        for (int index = 0, newIndex = 0; index < size; index++) {
            if (index != removeIndex) {
                newNums[newIndex++] = nums[index];
            }
        }

        nums = newNums;

        for (int index = 0; index < nums.length - 1; index++) {

            int currentNumber = nums[index];
            int nextNumber = nums[index + 1];

            int difference = Math.abs(currentNumber - nextNumber);

            if (currentNumber >= nextNumber) {
                isAscending = false;
            }

            if (currentNumber <= nextNumber) {
                isDescending = false;
            }

            if (difference > 3) {
                return false;
            }
        }
        return isAscending || isDescending;
    }
}
