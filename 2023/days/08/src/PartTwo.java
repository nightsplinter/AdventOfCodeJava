import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 8: Haunted Wasteland --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/8
 **/

public class PartTwo {

    /**
     * The instructions (L and R)
     */
    public static String order = "";

    /**
     * The input map
     */
    public static Map<String, Map<String, String>> inputMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Map<Integer, String> startA = new HashMap<>();

        int aIndex = 0;
        Pattern pattern = Pattern.compile("(.*)(?:\\s\\=\\s\\()(.*)(?:\\,\\s)(.*)(?:\\))");
        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if ((line.startsWith("L") || line.startsWith("R")) && order.isEmpty()) {
                    order = line;
                }

                Matcher m = pattern.matcher(line);

                if (m.find()) {
                    MatchResult result = m.toMatchResult();
                    String key = result.group(1);
                    String left = result.group(2);
                    String right = result.group(3);
                    Map<String, String> value = new HashMap<>();
                    value.put(left, right);
                    inputMap.put(key, value);

                    if (key.charAt(key.length() - 1) == 'A') {
                        startA.put(aIndex++, key);
                    }
                }

            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        BigInteger steps[] = new BigInteger[startA.size()];

        for (int i = 0; i < startA.size(); i++) {
            String start = startA.get(i);
            int amountOfSteps = getAmountOfSteps(start);
            steps[i] = BigInteger.valueOf(amountOfSteps);
        }
        BigInteger lcm = calculateLCM(steps);
        System.out.println(lcm);
    }

    /**
     * Calculates the least common multiple of the given numbers
     *
     * @param numbers The numbers
     * @return The least common multiple
     */
    public static BigInteger calculateLCM(BigInteger[] numbers) {
        return Arrays.stream(numbers)
                .reduce(BigInteger.ONE, PartTwo::calculateLCM);
    }

    /**
     * Calculates the least common multiple of the given numbers
     *
     * @param a The first number
     * @param b The second number
     * @return The least common multiple
     */
    private static BigInteger calculateLCM(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(a.gcd(b));
    }

    /**
     * Gets the amount of steps to get to the end
     *
     * @param start The start
     * @return The amount of steps
     */
    public static int getAmountOfSteps(String start) {
        int count = 1;
        for (int i = 0;; i = (i + 1) % order.length(), count++) {
            char instruction = order.charAt(i);
            Map<String, String> value = inputMap.get(start);
            if (instruction == 'L') {
                String leftValue = value.keySet().toArray()[0].toString();
                start = leftValue;
            } else if (instruction == 'R') {
                String rightValue = value.values().toArray()[0].toString();
                start = rightValue;
            }
            if (start.charAt(start.length() - 1) == 'Z') {
                return count;
            }
        }
    }
}
