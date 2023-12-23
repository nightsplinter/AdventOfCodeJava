import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * --- Day 7: Camel Cards --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/7
 **/

public class PartTwo {

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
        String strengths = "J23456789TQKA";
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
        if (isFiveOfAKind(card)) {
            return 6;
        } else if (isFourOfAKind(card)) {
            return 5;
        } else if (isFullHouse(card)) {
            return 4;
        } else if (isThreeOfAKind(card)) {
            return 3;
        } else if (isTwoPair(card)) {
            return 2;
        } else if (isOnePair(card)) {
            return 1;
        } else if (isHighCard(card)) {
            return 0;
        }
        return 0;
    }

    /**
     * Five of a kind, where all cards have the same label
     *
     * @param value String of cards
     * @return true if all cards have the same label
     */
    public static boolean isFiveOfAKind(String value) {
        value = value.replace("J", "");
        return value.chars().distinct().count() == 1 || value.isEmpty();
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
        boolean isJoker = value.contains("J");

        if (co == 2 || (co == 3 && isJoker)) {
            Map<Character, Integer> charCountMap = new HashMap<>();
            char[] cArray = value.toCharArray();
            for (char c : cArray) {
                charCountMap.put(c, charCountMap
                        .getOrDefault(c, 0) + 1);
            }

            if (isJoker) {
                return (charCountMap.get('J') == 3
                        && charCountMap.values().stream().anyMatch(x -> x == 1))
                        || (charCountMap.get('J') == 2
                                && charCountMap.values().stream().anyMatch(x -> x == 2)
                                || (charCountMap.get('J') == 1
                                        && charCountMap.values()
                                                .stream().anyMatch(x -> x == 3)));
            }

            return charCountMap.values().stream().anyMatch(x -> x == 4);
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
            charCountMap.put(c, charCountMap
                    .getOrDefault(c, 0) + 1);
        }

        if (charCountMap.containsKey('J')) {
            int jokerCount = charCountMap.get('J');

            return (jokerCount <= 2 && charCountMap.size() == 3);

        }

        return (charCountMap.values().stream().anyMatch(x -> x == 2)
                && charCountMap.values().stream().anyMatch(x -> x == 3));
    }

    /**
     * Three of a kind, where three cards have the same label
     *
     * @param value String of cards
     * @return true if three cards have the same label
     */
    public static boolean isThreeOfAKind(String value) {
        boolean isJoker = value.contains("J");

        if (value.chars().distinct().count() == 3 || isJoker) {
            Map<Character, Integer> charCountMap = new HashMap<>();
            for (char c : value.toCharArray()) {
                charCountMap.put(c,
                        charCountMap.getOrDefault(c, 0) + 1);
            }
            if (isJoker) {
                return (charCountMap.get('J') == 2
                        && charCountMap.values().stream().anyMatch(i -> i == 1)
                        && charCountMap.size() > 3)
                        || (charCountMap.get('J') == 1
                                && charCountMap.values()
                                        .stream().anyMatch(i -> i == 2)
                                && charCountMap.size() > 3);
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
        if (value.contains("J") && value.chars().distinct().count() == 2) {
            return true;
        }
        return value.chars().distinct().count() == 3;
    }

    /**
     * One pair, where two cards have the same label
     *
     * @param value String of cards
     * @return true if two cards have the same label
     */
    public static boolean isOnePair(String value) {
        if (value.contains("J")
                && value.replace("J", "").length() == 4) {
            return true;
        }
        return value.chars().distinct().count() == 4;
    }

    /**
     * High card, where all cards' labels are distinct
     *
     * @param value String of cards
     * @return true if all cards are distinct
     */
    public static boolean isHighCard(String value) {
        value = value.replace("J", "");
        return value.chars().distinct().count() == 1;
    }
}
