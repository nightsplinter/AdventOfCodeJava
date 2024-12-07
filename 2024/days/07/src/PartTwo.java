import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 7: Bridge Repair --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2024/day/7
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/07/src/input.txt");
        long total = 0;

        try (var lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                String[] split = line.split("\\:");
                long sum = Long.parseLong(split[0]);
                String[] splitByWhiteSpace = split[1].split("\\s+");
                long[] numbers = new long[splitByWhiteSpace.length - 1];
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = Long.parseLong(splitByWhiteSpace[i + 1]);
                }
                if (isValid(sum, numbers)) {
                    total += sum;
                }
            }
        }
        System.out.println(total);
    }

    private static boolean isValid(long sum, long[] numbers) {
        String[] operators = new String[] { "+", "*", "||" };
        List<String> tempList = new ArrayList<>();
        List<List<String>> permutations = new ArrayList<>();
        int amount = numbers.length == 2 ? 1 : numbers.length - 1;

        getPermutations(permutations, operators, amount, tempList);

        for (List<String> permutation : permutations) {
            for (int i = 0; i < permutation.size(); i++) {
                long result = numbers[0];
                for (int j = 1; j < numbers.length; j++) {
                    if (permutation.get(j - 1).equals("+")) {
                        result += numbers[j];
                    } else if (permutation.get(j - 1).equals("*")) {
                        result *= numbers[j];
                    } else {
                        String combined = String.valueOf(result)
                                + String.valueOf(numbers[j]);
                        result = Long.parseLong(combined);
                    }
                }
                if (result == sum) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void getPermutations(List<List<String>> result,
            String[] operators, int size,
            List<String> tempList) {
        if (tempList.size() == size) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (String operator : operators) {
            tempList.add(operator);
            getPermutations(result, operators, size, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}
