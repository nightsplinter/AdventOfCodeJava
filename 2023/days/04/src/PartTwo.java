import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * --- Day 4: Scratchcards --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/4
 **/
public class PartTwo {

    /**
     * The ownCardsMap contains the own cards for each cardId
     */
    public static TreeMap<Integer, String[]> ownCardsMap = new TreeMap<>();

    /**
     * The winningCardsMap contains the winning cards for each cardId
     */
    public static TreeMap<Integer, String[]> winningCardsMap = new TreeMap<>();

    public static void main(String[] args) {
        long totalCards = 0;

        try (Scanner scanner = new Scanner(new File("./input.txt"))) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] cardSplit = input.split(":\\s+");
                int cardNumber = Integer.parseInt(cardSplit[0]
                        .replaceAll("[^0-9]", ""));
                String[] ownAndWinningCards = cardSplit[1].split(" \\| ");
                String[] ownCards = ownAndWinningCards[0].split("\\s+");
                String[] winningCards = ownAndWinningCards[1].split("\\s+");

                ownCardsMap.put(cardNumber - 1, ownCards);
                winningCardsMap.put(cardNumber - 1, winningCards);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        for (int cardId = 0; cardId < ownCardsMap.size(); cardId++) {
            totalCards += getWinningCards(cardId);
        }
        System.out.println(totalCards);
    }

    /**
     * Returns the amount of winning cards for a given cardId including the
     * Scratchcards
     *
     * @param cardId the cardId
     * @return the amount of winning cards
     */
    public static long getWinningCards(int cardId) {
        long winningCardCount = getAmountOfWinningCards(cardId);
        long result = 1;
        for (int i = 1; i <= winningCardCount; i++) {
            result += getWinningCards(cardId + i);
        }
        return result;
    }

    /**
     * Returns the amount of winning cards for a given cardId
     *
     * @param cardId the cardId
     * @return the amount of winning cards
     */
    private static long getAmountOfWinningCards(int cardId) {
        return Arrays.stream(ownCardsMap.get(cardId))
                .filter(Arrays.asList(winningCardsMap.get(cardId))::contains)
                .count();
    }
}