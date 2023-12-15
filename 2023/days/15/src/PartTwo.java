import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 15: Lens Library --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/15
 **/

public class PartTwo {

    /**
     * The boxes
     */
    public static Map<Integer, List<String>> boxes = new HashMap<>();

    public static void main(String[] args) throws Exception {
        int total = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                for (int i = 0; i < split.length; i++) {
                    addToBoxes(split[i]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        total = getFocusPower();
        System.out.println(total);
    }

    /**
     * Get the total focus power of all the boxes
     *
     * @return the total focus power of all the boxes
     */
    private static int getFocusPower() {
        int total = 0;
        for (Map.Entry<Integer, List<String>> entry : boxes.entrySet()) {
            int boxNumber = entry.getKey();
            List<String> list = entry.getValue();
            for (int pos = 0; pos < list.size(); pos++) {
                String label = list.get(pos);
                int focalLength = Integer.parseInt(label.split(" ")[1]);
                total += (boxNumber + 1) * (pos + 1) * focalLength;
            }
        }
        return total;
    }

    /**
     * Remove a label from the boxes
     *
     * @param label the label to remove
     */
    private static void dash(String[] split) {
        String name = split[0];
        int hashValue = getAsciiCode(name);
        if (boxes.containsKey(hashValue)) {
            List<String> list = boxes.get(hashValue);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(name)) {
                    boxes.get(hashValue).remove(i);
                    break;
                }
            }
        }
    }

    /**
     * Add a label to the boxes
     *
     * @param label the label to add
     */
    private static void equals(String[] split) {
        String name = split[0];
        int number = Integer.parseInt(split[1]);
        int hashValue = getAsciiCode(name);
        String labelName = name + " " + number;

        if (boxes.containsKey(hashValue)) {
            List<String> list = boxes.get(hashValue);
            boolean replaced = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(name)) {
                    boxes.get(hashValue).set(i, labelName);
                    replaced = true;
                    break;
                }
            }
            if (!replaced) {
                boxes.get(hashValue).add(labelName);
            }
        } else {
            List<String> list = new ArrayList<>();
            list.add(labelName);
            boxes.put(hashValue, list);
        }
    }

    /**
     * Add a label to the boxes
     *
     * @param label the label to add
     */
    private static void addToBoxes(String label) {
        String[] split = label.split("-|=");

        if (label.contains("-")) {
            dash(split);
        } else if (label.contains("=")) {
            equals(split);
        }
    }

    /**
     * Get the ascii code of a string
     *
     * @param string the string to get the ascii code of
     * @return the ascii code of the string
     */
    private static int getAsciiCode(String string) {
        int current = 0;
        for (int i = 0; i < string.length(); i++) {
            int ascii = (int) string.charAt(i);
            current += ascii;
            current *= 17;
            current %= 256;
        }
        return current;
    }
}