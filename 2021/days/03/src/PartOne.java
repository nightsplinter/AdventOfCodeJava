
/**
 * --- Day 2: Dive! ---
 *
 * https://adventofcode.com/2021/day/3
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) throws FileNotFoundException {
        int[] countZero = new int[12];
        int[] countOne = new int[12];

        String gammaRateBinary = "";
        String epsilonRateBinary = "";
        Scanner scanner = new Scanner(new File("src/input.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '1') {
                    countOne[i] += 1;
                } else {
                    countZero[i] += 1;
                }
            }
        }
        scanner.close();

        for (int i = 0; i < countOne.length; i++) {

            if (countZero[i] < countOne[i]) {
                gammaRateBinary += '1';
                epsilonRateBinary += '0';
            } else {
                gammaRateBinary += '0';
                epsilonRateBinary += '1';
            }
        }
        int gammaRate = Integer.parseInt(gammaRateBinary, 2);
        int epsilonRate = Integer.parseInt(epsilonRateBinary, 2);
        System.out.println(gammaRate * epsilonRate);
    }
}
