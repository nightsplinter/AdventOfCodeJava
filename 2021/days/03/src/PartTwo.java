
/**
 * --- Day 2: Dive! --- --- Part Two ---
 *
 * https://adventofcode.com/2021/day/3#part2
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> binaryList = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("src/input.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            binaryList.add(line);
        }
        scanner.close();

        String oxygenBinary = getBinary(binaryList, true);
        String co2Binary = getBinary(binaryList, false);

        int oxygenRate = Integer.parseInt(oxygenBinary, 2);
        int co2Rate = Integer.parseInt(co2Binary, 2);
        System.out.println(oxygenRate * co2Rate);
    }

    private static String getBinary(List<String> input, Boolean highest) {
        List<String> mainList = input;
        char value;
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < input.get(0).length(); i++) {

            int[] countZero = new int[12];
            int[] countOne = new int[12];
            List<String> newList = new ArrayList<String>();

            if (list.size() == 0 || list.size() > 1) {

                for (int j = 0; j < mainList.size(); j++) {

                    if (mainList.get(j).charAt(i) == '1') {
                        countOne[i] += 1;
                    } else {
                        countZero[i] += 1;
                    }
                }

                if (countZero[i] == countOne[i] || countZero[i] < countOne[i]) {
                    value = highest ? '1' : '0';
                } else {
                    value = highest ? '0' : '1';
                }

                for (String binaryString : mainList) {
                    if (binaryString.charAt(i) == value) {
                        newList.add(binaryString);
                    }
                }
                list = newList;
                mainList = list;
            }
        }
        return mainList.get(0);
    }
}