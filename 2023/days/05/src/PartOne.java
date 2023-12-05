import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * --- Day 5: If You Give A Seed A Fertilizer ---
 *
 *
 * https://adventofcode.com/2023/day/5
 **/

// 462648396
public class PartOne {

    record Destination(long start, long increment) {
    }

    private static ArrayList<Long> seeds = new ArrayList<>();

    private static Map<Long, Destination> seedToSoilMap = new HashMap<>();

    private static Map<Long, Destination> seedToFertilizerMap = new HashMap<>();

    private static Map<Long, Destination> seedToWaterMap = new HashMap<>();

    private static Map<Long, Destination> seedToLightMap = new HashMap<>();

    private static Map<Long, Destination> seedToTemperatureMap = new HashMap<>();

    private static Map<Long, Destination> seedToHumidityMap = new HashMap<>();

    private static Map<Long, Destination> seedToLocationMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Map<Long, String> inputMap = new HashMap<>();
        long index = 0;
        try {
            Scanner scanner = new Scanner(new File("./src/input.txt"));

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.startsWith("seeds: ")) {
                    addSeeds(input);
                } else if ((index == 0 && input.isEmpty()) || input.contains("map")) {
                    continue;
                } else {
                    inputMap.put(index, input);
                    index++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        convertSeedToMap(inputMap);
        System.out.println(findMinLocation());
    }

    /**
     * Find the minimum location for the seeds
     * @return the minimum location for the seeds
     */
    private static long findMinLocation() {
        long minMax = Long.MAX_VALUE;

        for (long seed : seeds) {
            long soil = getMapValue(seedToSoilMap, seed);
            long fertilizer = getMapValue(seedToFertilizerMap, soil);
            long water = getMapValue(seedToWaterMap, fertilizer);
            long light = getMapValue(seedToLightMap, water);
            long temperature = getMapValue(seedToTemperatureMap, light);
            long humidity = getMapValue(seedToHumidityMap, temperature);
            long location = getMapValue(seedToLocationMap, humidity);
            minMax = Math.min(minMax, location);
        }
        return minMax;
    }

    /**
     * Get the value from the map
     * @param map the map to get the value from
     * @param seed the seed to get the value for
     * @return the value from the map
     */
    private static long getMapValue(Map<Long, PartOne.Destination> map, long seed) {
        long result = seed;
        Map.Entry<Long, Destination> entry = findClosestEntry(map, seed);

        if (entry == null) {
            return result;
        }
        long seedKey = entry.getKey();
        Destination destination = entry.getValue();

        if (seed == seedKey) {
            result = destination.start;
        } else if (seed <= seedKey + destination.increment) {
            long difference = seed - seedKey;
            result = destination.start + difference;
        }
        return result;
    }

    /**
     * Find the closest entry in the map
     * @param map the map to search
     * @param key the key to search for
     * @return the closest entry in the map
     */
    private static Map.Entry<Long, Destination> findClosestEntry(Map<Long, Destination> map, long key) {
        Map.Entry<Long, Destination> closestEntry = null;

        for (Map.Entry<Long, Destination> entry : map.entrySet()) {
            if (entry.getKey() <= key && (closestEntry == null || entry.getKey() > closestEntry.getKey())) {
                closestEntry = entry;
            }
        }
        return closestEntry;
    }

    /**
     * Convert the seed to map
     * @param inputList the input list to convert
     */
    private static void convertSeedToMap(Map<Long, String> inputList) {
        List<Map<Long, Destination>> maps = List.of(
                seedToSoilMap,
                seedToFertilizerMap,
                seedToWaterMap,
                seedToLightMap,
                seedToTemperatureMap,
                seedToHumidityMap,
                seedToLocationMap);

        long index = 0;

        for (Map<Long, Destination> specificMap : maps) {

            String line = inputList.get(index);

            while (!line.isEmpty()) {
                addRangeToMap(line, specificMap);

                index++;
                if (index >= inputList.size()) {
                    break;
                }
                line = inputList.get(index);
            }
            index++;

        }
    }

    /**
     * Add the range to the map
     * @param line the line to add
     * @param map the map to add to
     */
    private static void addRangeToMap(String line, Map<Long, PartOne.Destination> map) {
        String[] split = line.split(" ");
        long destinationRangeStart = Long.parseLong(split[0]);
        long sourceRangeStart = Long.parseLong(split[1]);
        long rangeLength = Long.parseLong(split[2]);

        Destination destination = new Destination(destinationRangeStart, rangeLength);
        map.put(sourceRangeStart, destination);
    }

    /**
     * Add the seeds to the list
     * @param input the input to add
     */
    private static void addSeeds(String input) {
        input = input.replace("seeds:", "");
        String[] split = input.split(" ");
        for (String s : split) {
            if (s.equals("")) {
                continue;
            }
            seeds.add(Long.parseLong(s));
        }
    }
}
