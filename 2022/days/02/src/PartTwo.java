import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

/**
 * --- Day 2: Rock Paper Scissors ---
 *
 *
 * https://adventofcode.com/2022/day/2#part2
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        int totalScore = 0;
        String endTheRoundInADraw = "Y";
        String endTheRoundWin = "Z";

        LinkedHashMap<String, String> shapeList = new LinkedHashMap<>();
        shapeList.put("A", "Rock");
        shapeList.put("B", "Paper");
        shapeList.put("C", "Scissors");

        List<String> keys = new ArrayList<String>(shapeList.keySet());

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] move = input.split(" ");

                String opponent = move[0];
                String you = move[1];

                if (you.equals(endTheRoundInADraw)) {
                    totalScore += 3;
                    you = opponent;
                } else if (you.equals(endTheRoundWin)) {
                    totalScore += 6;

                    switch (opponent) {
                        case "A":
                            you = "B";
                            break;
                        case "C":
                            you = "A";
                            break;
                        default:
                            you = "C";
                            break;
                    }
                } else {
                    switch (opponent) {
                        case "A":
                            you = "C";
                            break;
                        case "B":
                            you = "A";
                            break;
                        default:
                            you = "B";
                            break;
                    }
                }
                totalScore += keys.indexOf(you) + 1;
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(totalScore);
    }
}
