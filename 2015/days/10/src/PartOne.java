import java.util.regex.*;

/**
 * --- Day 10: Elves Look, Elves Say ---
 *
 * https://adventofcode.com/2015/day/9
 *
 */

public class PartOne {
    public static void main(String[] args) {
        String input = "3113322113";
        String newInput = "";
        int count = 1;

        for (int i = 0; i < input.length(); i++) {

            Pattern pattern = Pattern.compile(input.charAt(i) + "+");
            Matcher matcher = pattern.matcher(input.substring(i));

            if (matcher.find()) {
                String group = matcher.group(0);
                i += matcher.group(0).length() - 1;
                newInput += matcher.group(0).length() + "" + group.charAt(0);
            }

            if (i == input.length() - 1 && count != 40) {
                count++;
                input = newInput;
                newInput = "";
                i = -1;
            }
        }
        System.out.println(newInput.length());
    }
}