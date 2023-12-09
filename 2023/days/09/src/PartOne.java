import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * --- Day 9: Mirage Maintenance ---
 *
 *
 * https://adventofcode.com/2023/day/9
 **/

public class PartOne {

    /**
     * Save the history of numbers
     */
    public static TreeMap<Integer, List<Integer>> map = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        int total = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                total += getHistoryNumber(input);
                map.clear();
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(total);

    }

    /**
     * Get the history number
     *
     * @param input String
     * @return the number of the history line
     */
    private static int getHistoryNumber(String input) {
        String[] numbers = input.split(" ");
        List<Integer> history = getNumbers(numbers);
        map.put(0, history);
        boolean justZeros = false;
        do {
            justZeros = getDifference();
        } while (!justZeros);
        return getNumber();
    }

    /**
     * Get the number of the history map
     *
     * @return the number of the history map
     */
    private static int getNumber() {
        map.get(map.size() - 1).add(0);
        int lastNumber = map.get(map.size() - 1)
                .get(map.get(map.size() - 1).size() - 1);

        for (int i = map.size() - 2; i >= 0; i--) {
            int currentLastNumber = map.get(i)
                    .get(map.get(i).size() - 1);
            map.get(i).add(currentLastNumber + lastNumber);
            lastNumber = currentLastNumber + lastNumber;
        }
        return map.get(0).get(map.get(0).size() - 1);
    }

    /**
     * Get the difference between the numbers
     *
     * @return true if the all numbers are zeros
     */
    private static boolean getDifference() {
        int lastIndex = map.size() - 1;
        map.put(lastIndex + 1, new ArrayList<>());
        for (int i = 0; i < map.get(lastIndex).size() - 1; i++) {
            int number = map.get(lastIndex).get(i);
            int nextNumber = map.get(lastIndex).get(i + 1);
            int difference = nextNumber - number;
            map.get(lastIndex + 1).add(difference);
        }
        int sum = map.get(lastIndex + 1).stream()
                .mapToInt(Integer::intValue).sum();
        return sum == 0;
    }

    /**
     * Get the numbers from the string array and convert to integer
     *
     * @param numbers
     * @return the numbers
     */
    public static List<Integer> getNumbers(String[] numbers) {
        List<Integer> history = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            int number = Integer.parseInt(numbers[i]);
            history.add(number);
        }
        return history;
    }
}