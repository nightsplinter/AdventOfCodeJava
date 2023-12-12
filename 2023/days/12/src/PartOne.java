import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * --- Day 12: Hot Springs ---
 *
 *
 * https://adventofcode.com/2023/day/12
 **/

public class PartOne {

    public static void main(String[] args) throws Exception {
        int sum = 0;
        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");
                String hotSprings = split[0];
                String[] groups = split[1].split(",");
                List<Integer> groupList = Arrays.stream(groups)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                sum += getAmountOfPossibleHotSprings(hotSprings, groupList);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(sum);
    }

    /**
     * Get the each group size of the hot springs
     *
     * @param hotSprings the hot springs
     * @return the list of group size
     */
    public static List<Integer> getGroup(String hotSprings) {
        int groupSize = 0;
        List<Integer> groups = new ArrayList<>();

        for (int index = 0; index < hotSprings.length(); index++) {
            Character c = hotSprings.charAt(index);
            if (c.equals('#')) {
                groupSize++;
            } else if (c.equals('.') && groupSize > 0) {
                groups.add(groupSize);
                groupSize = 0;
            }
        }

        if (groupSize > 0) {
            groups.add(groupSize);
        }
        return groups;
    }

    /**
     * Get the amount of possible valid hot springs
     *
     * @param hotspring the hot springs
     * @param groups    the list of group size
     * @return the amount of possible valid hot springs
     */
    public static int getAmountOfPossibleHotSprings(String hotspring, List<Integer> groups) {
        int sum = 0;
        int charIndex;
        String possibleHotSpring = "";
        String[] possibleChars = { ".", "#" };
        int possibleCharsLength = possibleChars.length;
        int numberOfReplaceable = getNumberOfQuestionMarks(hotspring);
        int totalPermutations = (int) Math.pow(
                possibleCharsLength, numberOfReplaceable);

        for (int permNum = 0; permNum < totalPermutations; permNum++) {
            possibleHotSpring = hotspring;

            for (int n = 0; n < numberOfReplaceable; n++) {
                // Calculate the character to swap the nth replacement char to
                charIndex = permNum / (int) (Math.pow(possibleCharsLength, n))
                        % possibleCharsLength;
                possibleHotSpring = possibleHotSpring
                        .replaceFirst("\\?", possibleChars[charIndex]);
            }
            if (isValid(possibleHotSpring, groups)) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Get the number of replaceable character
     *
     * @param hotspring the hot springs
     * @return the number of replaceable character
     */
    private static int getNumberOfQuestionMarks(String hotspring) {
        int numberOfReplaceable = 0;
        for (char c : hotspring.toCharArray())
            if (c == '?')
                numberOfReplaceable++;
        return numberOfReplaceable;
    }

    /**
     * Check if the new hot springs is valid
     *
     * @param newHotSprings the new hot springs
     * @param groups        the list of group size
     * @return true if the new hot springs is valid, false otherwise
     */
    private static boolean isValid(String newHotSprings, List<Integer> groups) {
        return getGroup(newHotSprings).equals(groups);
    }
}
