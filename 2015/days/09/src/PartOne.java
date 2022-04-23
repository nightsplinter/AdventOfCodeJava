import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * --- Day 9: All in a Single Night ---
 *
 * https://adventofcode.com/2015/day/9
 *
 */

public class PartOne<T> {
    public static void main(String[] args) throws Exception {
        Set<String> locations = new HashSet<String>();
        Map<String, Integer> distances = new HashMap<String, Integer>();
        int route = 0;
        int min = 0;

        try {
            Scanner scanner = new Scanner(new File("src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String split[] = line.split(" ");
                String start = split[0];
                String destination = split[2];

                locations.add(start);
                locations.add(destination);
                distances.put(start + destination, Integer.parseInt(split[4]));
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        List<List<String>> flightList = flightPossibilities(locations);
        for (List<String> flight : flightList) {
            route = 0;
            for (int i = 0; i < flight.size() - 1; i++) {
                String start = flight.get(i);
                String destination = flight.get(i + 1);

                if (distances.containsKey(start + destination)) {
                    route += distances.get(start + destination);
                } else {
                    route += distances.get(destination + start);
                }
            }
            if (route != 0 && (min == 0 || route < min)) {
                min = route;
            }
        }
        System.out.println(min);
    }

    public static List<List<String>> flightPossibilities(Set<String> locations) {
        List<List<String>> possibilities = new ArrayList<>();
        String[] locationsStrings = locations.toArray(new String[0]);
        permute(possibilities, new ArrayList<>(), locationsStrings);
        return possibilities;
    }

    public static void permute(List<List<String>> possibilities, List<String> possibility, String[] locations) {
        if (possibility.size() == locations.length) {
            possibilities.add(new ArrayList<>(possibility));
            return;
        }
        for (int i = 0; i < locations.length; i++) {
            if (!possibility.contains(locations[i])) {
                possibility.add(locations[i]);
                permute(possibilities, possibility, locations);
                possibility.remove(possibility.size() - 1);
            }
        }
    }
}
