import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

/**
 * --- Day 4: Camp Cleanup ---
 *
 *
 *
 * https://adventofcode.com/2022/day/4
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        int pairs = 0;
        List<List<Integer>> list = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String pair[] = input.split(",");

                for (String section : pair) {
                    int index = section.indexOf("-");

                    int start = parseInt(
                            section.substring(0, index));

                    int end = parseInt(section.substring(index + 1));

                    List<Integer> s = new ArrayList<Integer>();

                    for (int i = start; i <= end; i++) {
                        s.add(i);
                    }
                    list.add(s);

                    if (list.size() == 2) {
                        if (list.get(0).containsAll(list.get(1))
                                || list.get(1)
                                        .containsAll(list.get(0))) {
                            pairs = pairs + 1;
                        }
                        list.clear();
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(pairs);
    }
}