import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 1: Historian Hysteria ---
 *
 *
 * https://adventofcode.com/2024/day/1
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("2024/days/01/src/input.txt");
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        int difference = 0;

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

        int listSize = leftList.size();

        for (int index = 0; index < listSize; index++) {
            int leftNumber = leftList.get(index);
            int rightNumber = rightList.get(index);
            difference += Math.abs(leftNumber - rightNumber);
        }
        System.out.println(difference);
    }
}
