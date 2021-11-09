import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 7: Some Assembly Required ---
 *
 * https://adventofcode.com/2015/day/7
 */

public class PartOne {
    public static void main(String[] args) throws Exception {
        HashMap<String, String> wires = new HashMap<String, String>();
        ArrayList<String> input = new ArrayList<String>();

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                input.add(line);
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        int i = 0;
        do {
            String instruction = input.get(i).trim();
            Pattern wirePattern = Pattern.compile(".*\\-\\>\\s(\\w+)");
            Matcher wireMatcher = wirePattern.matcher(instruction);
            boolean isWire = wireMatcher.matches();
            String wire = wireMatcher.group(1);

            instruction = instruction.split("->")[0].trim();

            if (instruction.contains("AND") || instruction.contains("OR")) {
                Pattern andOrPattern = Pattern.compile("(\\w+)\\s(AND|OR)\\s(\\w+)");
                Matcher andOrMatcher = andOrPattern.matcher(instruction);
                boolean isand = andOrMatcher.matches();

                boolean firstIsNumeric = andOrMatcher.group(1).chars().allMatch(Character::isDigit);
                boolean secondIsNumeric = andOrMatcher.group(3).chars().allMatch(Character::isDigit);

                String first = !firstIsNumeric ? wires.get(andOrMatcher.group(1))
                        : String.valueOf(andOrMatcher.group(1));
                String second = !secondIsNumeric ? wires.get(andOrMatcher.group(3))
                        : String.valueOf(andOrMatcher.group(3));

                if (first != null && second != null) {
                    Integer firstWire = Integer.parseInt(first);
                    Integer secondWire = Integer.parseInt(second);
                    wires.put(wire, String
                            .valueOf(instruction.contains("AND") ? firstWire & secondWire : firstWire | secondWire));
                    input.remove(i);
                    i = 0;
                } else {
                    i += 1;
                }
            } else if (instruction.contains("NOT")) {
                Pattern notPattern = Pattern.compile("NOT\\s(\\w+)");
                Matcher notMatcher = notPattern.matcher(instruction);
                boolean isand = notMatcher.matches();
                String first = wires.get(notMatcher.group(1));

                if (first != null) {
                    Integer firstWire = Integer.parseInt(first);
                    String binary = Integer.toBinaryString(0x10000 | firstWire).substring(1);
                    String inverse = binary.replace('0', '2').replace('1', '0').replace('2', '1');
                    wires.put(wire, String.valueOf(Integer.parseInt(inverse, 2)));
                    input.remove(i);
                    i = 0;
                } else {
                    i += 1;
                }
            } else if (instruction.contains("RSHIFT") || instruction.contains("LSHIFT")) {

                Pattern shiftPattern = Pattern.compile("(\\w+)\\s(RSHIFT|LSHIFT)\\s(\\d+)");
                Matcher shiftMatcher = shiftPattern.matcher(instruction);
                boolean isShift = shiftMatcher.matches();
                String first = wires.get(shiftMatcher.group(1));
                Integer number = Integer.parseInt(shiftMatcher.group(3));

                if (first != null) {
                    Integer getWireValue = Integer.parseInt(first);
                    wires.put(wire, String
                            .valueOf(instruction.contains("RSHIFT") ? getWireValue >> number : getWireValue << number));
                    input.remove(i);
                    i = 0;
                } else {
                    i += 1;
                }
            } else {
                Pattern providePattern = Pattern.compile("(\\w+)");
                Matcher provideMatcher = providePattern.matcher(instruction);
                boolean isProvide = provideMatcher.matches();

                boolean signalIsNumeric = provideMatcher.group(1).chars().allMatch(Character::isDigit);

                String first = !signalIsNumeric ? wires.get(provideMatcher.group(1))
                        : String.valueOf(provideMatcher.group(1));
                if (first != null) {
                    Integer getWireValue = Integer.parseInt(first);
                    wires.put(wire, String.valueOf(getWireValue));
                    input.remove(i);
                    i = 0;
                } else {
                    i += 1;
                }
            }
        } while (input.size() != 0);
        System.out.println(wires.get("a"));
    }
}
