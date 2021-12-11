
/**
 * --- Day 4: Giant Squid --- --- Part Two ---
 *
 * https://adventofcode.com/2021/day/4#part2
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) throws FileNotFoundException {
        int[] numbers;
        int unmarked = 0;
        List<int[][]> boards = new ArrayList<int[][]>();

        Scanner scanner = new Scanner(new File("src/input.txt"));

        numbers = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        while (scanner.hasNextLine()) {
            int board[][] = new int[5][5];

            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    board[y][x] = scanner.nextInt();
                }
            }
            boards.add(board);
        }
        scanner.close();

        List<int[][]> markedboards = boards;
        Integer[] winner = new Integer[boards.size()];
        int counter = 0;

        for (int number : numbers) {
            for (int[][] board : markedboards) {
                int index = markedboards.indexOf(board);

                for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {

                        if (board[y][x] == number) {
                            board[y][x] = -1;

                            String row = board[0][x] + " " + board[1][x] + " " + board[2][x] + " " + board[3][x] + " "
                                    + board[4][x];

                            if (Arrays.deepToString(board).contains("[-1, -1, -1, -1, -1]") ||
                                    row.contains("-1 -1 -1 -1 -1")) {
                                for (int i = 0; i < 5; i++) {
                                    for (int j = 0; j < 5; j++) {

                                        unmarked += boards.get(index)[i][j] == -1 ? 0
                                                : boards.get(index)[i][j]; // 148

                                    }
                                }

                                if (unmarked > 0 && counter < boards.size()) {
                                    if (winner[index] == null) {
                                        counter++;
                                    }
                                    winner[index] = unmarked * number;
                                    if (counter == boards.size()) {
                                        System.out.println(winner[index]);
                                    }
                                }
                                unmarked = 0;
                            }
                        }
                    }
                }
            }
        }
    }
}
