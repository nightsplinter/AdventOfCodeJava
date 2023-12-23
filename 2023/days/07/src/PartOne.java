import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * --- Day 7: Camel Cards ---
 *
 *
 * https://adventofcode.com/2023/day/7
 **/

public class PartOne {

    /**
     * Hand
     */
    public record Hand(int bid, String card) {
    }

    public static void main(String[] args) throws Exception {
        int total = 0;
        List<Hand> hands = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] splitInput = input.split(" ");
                hands.add(new Hand(Integer.parseInt(splitInput[1]), splitInput[0]));
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        // Sort the hands order by type and if the type is the same, sort by bid
        hands.sort((firstHand, secondHand) -> {
            int firstHandType = getHandType(firstHand.card);
            int secondHandType = getHandType(secondHand.card);

            if (firstHandType == secondHandType) {
                return getBid(firstHand.card).compareTo(getBid(secondHand.card));
            }
            return Integer.compare(firstHandType, secondHandType);
        });
        total = hands.stream().mapToInt(hand -> hand.bid * (hands.indexOf(hand) + 1)).sum();
        System.out.println(total);
    }

    /**
     * Get the bid of each card
     *
     * @param value String of cards
     * @return bid of each card
     */
    public static String getBid(String value) {
        StringBuilder strengthStr = new StringBuilder();

        for (char c : value.toCharArray()) {
            strengthStr.append(getStrengths().get(c));
        }
        return strengthStr.toString();
    }

    /**
     * Get the strength of each card
     *
     * @return HashMap of card strength
     */
    private static HashMap<Character, Character> getStrengths() {
        HashMap<Character, Character> strengthChars = new HashMap<>();
        String strengths = "23456789TJQKA";
        for (int i = 0; i < strengths.length(); i++) {
            strengthChars.put(strengths.charAt(i), (char) (i + 65));
        }
        return strengthChars;
    }

    /**
     * Get the type of hand
     *
     * @param card String of cards
     * @return type of hand
     */
    private static int getHandType(String card) {
        int number = 0;
        if (isFiveOfAKind(card)) {
            number = 6;
        } else if (isFourOfAKind(card)) {
            number = 5;
        } else if (isFullHouse(card)) {
            number = 4;
        } else if (isThreeOfAKind(card)) {
            number = 3;
        } else if (isTwoPair(card)) {
            number = 2;
        } else if (isOnePair(card)) {
            number = 1;
        } else if (isHighCard(card)) {
            number = 0;
        }
        return number;
    }

    /**
     * Five of a kind, where all cards have the same label
     *
     * @param value String of cards
     * @return true if all cards have the same label
     */
    public static boolean isFiveOfAKind(String value) {
        return value.chars().distinct().count() == 1;
    }

    /**
     * Four of a kind, where four cards have the same label
     *
     * @param value String of cards
     * @return true if four cards have the same label
     */
    public static boolean isFourOfAKind(String value) {
        IntStream cs = value.chars();
        IntStream dis = cs.distinct();
        int co = (int) dis.count();

        if (co == 2) {
            Map<Character, Integer> charCountMap = new HashMap<>();
            char[] cArray = value.toCharArray();
            for (char c : cArray) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }

            if (charCountMap.values().stream().anyMatch(x -> x == 4)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Full house, where three cards have the same label
     * and the remaining two cards have the same label
     *
     * @param value String of cards
     * @return true if three cards have the same label
     *         and the remaining two cards have the same label
     */
    public static boolean isFullHouse(String value) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        char[] cArray = value.toCharArray();
        for (char c : cArray) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        if (charCountMap.values().stream().anyMatch(x -> x == 2)
                && charCountMap.values().stream().anyMatch(x -> x == 3)) {
            return true;
        }
        return false;
    }

    /**
     * Three of a kind, where three cards have the same label
     *
     * @param value String of cards
     * @return true if three cards have the same label
     */
    public static boolean isThreeOfAKind(String value) {
        if (value.chars().distinct().count() == 3) {
            Map<Character, Integer> charCountMap = new HashMap<>();
            for (char c : value.toCharArray()) {
                charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            }
            return charCountMap.values().stream().anyMatch(i -> i == 3);
        }
        return false;
    }

    /**
     * Two pair, where two cards have the same label
     *
     * @param value String of cards
     * @return true if two cards have the same label
     */
    public static boolean isTwoPair(String value) {
        return value.chars().distinct().count() == 3;
    }

    /**
     * One pair, where two cards have the same label
     *
     * @param value String of cards
     * @return true if two cards have the same label
     */
    public static boolean isOnePair(String value) {
        return value.chars().distinct().count() == 4;
    }

    /**
     * High card, where all cards' labels are distinct
     *
     * @param value String of cards
     * @return true if all cards are distinct
     */
    public static boolean isHighCard(String value) {
        return value.chars().distinct().count() == 1;
    }
}
