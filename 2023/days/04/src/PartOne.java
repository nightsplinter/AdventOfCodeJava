import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * --- Day 4: Scratchcards ---
 *
 *
 * https://adventofcode.com/2023/day/4
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        int total = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] cardSplit = input.split(":");
                input = cardSplit[1];
                String[] ownAndwinningCards = input.split(" \\| ");

                String[] ownCards = ownAndwinningCards[0].split(" ");
                String[] winningCards = ownAndwinningCards[1].split(" ");
                int count = 0;

                for (int i = 0; i < ownCards.length; i++) {

                    for (int j = 0; j < winningCards.length; j++) {
                        if (ownCards[i].isEmpty() || winningCards[j].isEmpty()) {
                            continue;
                        }

                        int ownCard = Integer.parseInt(ownCards[i]);
                        int winningCard = Integer.parseInt(winningCards[j]);

                        if (ownCard == winningCard) {
                            if (count == 0) {
                                count = 1;
                            } else {
                                count += count;
                            }
                        }
                    }
                }

                if (count != 0) {

                    total += count;
                }

            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        System.out.println(total);
    }
}
