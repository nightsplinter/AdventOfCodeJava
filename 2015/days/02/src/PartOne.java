import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 2: I Was Told There Would Be No Math ---
 *
 * https://adventofcode.com/2015/day/2
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {

        int paper = 0;
        try {

            Scanner scanner = new Scanner(new File("02/src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] result = input.split("x");

                int l = Integer.parseInt(result[0]);
                int w = Integer.parseInt(result[1]);
                int h = Integer.parseInt(result[2]);

                int lw = l * w;
                int wh = w * h;
                int hl = h * l;

                int min = Math.min(Math.min(lw, wh), hl);
                int box = 2 * lw + 2 * wh + 2 * hl + min;
                paper = paper + box;
            }

            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(paper);
    }
}
