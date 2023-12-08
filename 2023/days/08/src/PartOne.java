import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 8: Haunted Wasteland ---
 *
 *
 * https://adventofcode.com/2023/day/8
 **/

public class PartOne {

    public static void main(String[] args) throws Exception {
        Map<String, Map<String, String>> inputMap = new HashMap<>();
        String order = "";
        Pattern pattern = Pattern.compile("([A-Z]{3})(?:\\s\\=\\s\\()([A-Z]{3})(?:\\,\\s)([A-Z]{3})(?:\\))");
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
                }

            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        String start = "AAA";
        String end = "ZZZ";
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
            if (start.equals(end)) {
                break;
            }
        }
        System.out.println(count);
    }
}