import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * --- Day 12: Hot Springs --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/12
 **/

public class PartTwo {

    /**
     * The amount of times the string should be repeated
     */
    public final static int REPEAT = 5;

    public static void main(String[] args) throws Exception {
        long sum = 0;
        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");
                String[] groups = split[1].split(",");
                String hotSprings = expand(split[0]);
                List<Integer> groupList = Collections.nCopies(5, Arrays.stream(groups)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                        .stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());
                sum += getAmountOfPossibleHotSprings(
                        hotSprings, groupList, 0, 0, 0,
                        new HashMap<>());
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(sum);
    }

    /**
     * Expand the string to the required length (REPEAT) and remove unnecessary
     * dots
     *
     * @param string
     * @return
     */
    private static String expand(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < REPEAT; i++) {
            sb.append(string);
            if (i != REPEAT - 1) {
                sb.append("?");
            }
        }

        // Replace multiple dots with one dot
        sb = new StringBuilder(sb.toString().replaceAll("\\.+", "."));
        // If the last character is a dot, remove it
        if (sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        // If the first character is a dot, remove it
        if (sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * Check if the index is the end of the hot spring string
     *
     * @param hotspring the hot spring string
     * @param index     the index to check
     * @return true if the index is the end of the hot spring string
     */
    public static boolean isEndOfHotSpringStr(String hotspring, int index) {
        return index == hotspring.length();
    }

    /**
     * Check if the index is the end of the group list
     *
     * @param groups the list of group size
     * @param index  the index to check
     * @return true if the index is the end of the group list
     */
    public static boolean isEndOfGroup(List<Integer> groups, int index) {
        return index == groups.size();
    }

    /**
     * Check if the index is the second to last group
     *
     * @param groups the list of group size
     * @param index  the index to check
     * @return true if the index is the second to last group
     */
    public static boolean isSecondToLastGroup(List<Integer> groups, int index) {
        return index == groups.size() - 1;
    }

    /**
     * Check if the amount of hashtags is equal to the group value
     *
     * @param groups        the list of group size
     * @param gIndex        the index of the group
     * @param countHashtags the amount of hashtags
     * @return true if the amount of hashtags is equal to the group value
     */
    public static boolean checkIfAmountOfHashtagsIsEqualToGroupValue(List<Integer> groups,
            int gIndex, int countHashtags) {
        return groups.get(gIndex) == countHashtags;
    }

    /**
     * Get the amount of possible valid hot springs
     *
     * @param hotspring     the hot springs
     * @param groups        the list of group size
     * @param hIndex        the index of the hot spring
     * @param gIndex        the index of the group
     * @param countHashtags the amount of hashtags
     * @param cache         the cache
     * @return the amount of possible valid hot springs
     */
    public static Long getAmountOfPossibleHotSprings(String hotspring,
            List<Integer> groups, int hIndex,
            int gIndex, int countHashtags, Map<String, Long> cache) {

        String cKey = hIndex + "-" + gIndex + "-" + countHashtags;
        if (cache.containsKey(cKey)) {
            return cache.get(cKey);
        }

        if (isEndOfHotSpringStr(hotspring, hIndex)
                && ((isEndOfGroup(groups, gIndex) && countHashtags == 0)
                        || (isSecondToLastGroup(groups, gIndex)
                                && checkIfAmountOfHashtagsIsEqualToGroupValue(groups, gIndex, countHashtags)))) {
            return 1L;
        } else if (isEndOfHotSpringStr(hotspring, hIndex)) {
            return 0L;
        }

        Long amount = 0L;
        final String[] possibleChars = { ".", "#" };
        Character currentChar = hotspring.charAt(hIndex);

        for (String pChar : possibleChars) {

            if (currentChar == pChar.charAt(0) || currentChar == '?') {

                if (pChar.equals(".")) {

                    if (countHashtags == 0) {
                        amount += getAmountOfPossibleHotSprings(
                                hotspring,
                                groups, hIndex + 1, gIndex, 0, cache);
                    } else if (countHashtags > 0 && gIndex < groups.size()
                            && checkIfAmountOfHashtagsIsEqualToGroupValue(
                                    groups, gIndex, countHashtags)) {
                        amount += getAmountOfPossibleHotSprings(
                                hotspring, groups, hIndex + 1,
                                gIndex + 1, 0, cache);
                    }
                } else if (pChar.equals("#")) {
                    amount += getAmountOfPossibleHotSprings(hotspring,
                            groups, hIndex + 1,
                            gIndex, countHashtags + 1,
                            cache);
                }
            }
        }
        cache.put(cKey, amount);
        return amount;
    }

}
