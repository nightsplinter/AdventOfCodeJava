import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 1: Historian Hysteria --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2024/day/1
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/01/src/input.txt");
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        int similarityScore = 0;

        try (var lines = Files.lines(path)) {
            lines.forEach(line -> {
                String[] parts = line.split("\\s+");
                int leftNumber = Integer.parseInt(parts[0]);
                int rightNumber = Integer.parseInt(parts[1]);
                leftList.add(leftNumber);
                rightList.add(rightNumber);
            });
        }
        leftList.sort(Integer::compareTo);
        rightList.sort(Integer::compareTo);

        Map<Integer, Integer> countMap = new HashMap<>();
        for (Integer item : rightList) {
            countMap.put(item,
                    countMap.getOrDefault(item, 0) + 1);
        }

        int listSize = leftList.size();

        for (int index = 0; index < listSize; index++) {
            int leftNumber = leftList.get(index);
            int amountOfOccurences = countMap.getOrDefault(
                    leftNumber, 0);
            similarityScore += Math.abs(leftNumber * amountOfOccurences);
        }
        System.out.println(similarityScore);

    }
}
