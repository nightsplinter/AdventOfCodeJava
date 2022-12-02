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
 * https://adventofcode.com/2022/day/2
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        int totalScore = 0;

        LinkedHashMap<String, String> youList = new LinkedHashMap<>();
        youList.put("X", "Rock");
        youList.put("Y", "Paper");
        youList.put("Z", "Scissors");

        LinkedHashMap<String, String> opponentList = new LinkedHashMap<>();
        opponentList.put("A", "Rock");
        opponentList.put("B", "Paper");
        opponentList.put("C", "Scissors");

        List<String> keys = new ArrayList<String>(youList.keySet());

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] move = input.split(" ");
                String opponent = move[0];
                String you = move[1];

                totalScore += keys.indexOf(you) + 1;

                if (youList.get(you).equals(opponentList.get(opponent))) {
                    totalScore += 3;
                } else if ((you.equals("X")
                        && opponent.equals("C"))
                        || (you.equals("Z")
                                && opponent.equals("B"))
                        || (you.equals("Y")
                                && opponent.equals("A"))) {
                    totalScore += 6;
                }
            }
            scanner.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(totalScore);
    }
}
