import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * --- Day 5: If You Give A Seed A Fertilizer --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/5
 **/

public class PartTwo {

    record Destination(long start, long increment) {
    }

    private static List<Destination> seeds = new ArrayList<>();

    private static TreeMap<Long, Destination> seedToSoilMap = new TreeMap<>();

    private static TreeMap<Long, Destination> seedToFertilizerMap = new TreeMap<>();

    private static TreeMap<Long, Destination> seedToWaterMap = new TreeMap<>();

    private static TreeMap<Long, Destination> seedToLightMap = new TreeMap<>();

    private static TreeMap<Long, Destination> seedToTemperatureMap = new TreeMap<>();

    private static TreeMap<Long, Destination> seedToHumidityMap = new TreeMap<>();

    private static TreeMap<Long, Destination> seedToLocationMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        TreeMap<Long, String> inputMap = new TreeMap<>();
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
     *
     * @return the minimum location for the seeds
     */
    private static long findMinLocation() {
        long minMax = Long.MAX_VALUE;

        for (Destination seed : seeds) {
            for (long add = 0; add < seed.increment; add++) {

                long soil = getMapValue(seedToSoilMap, (seed.start + add));
                long fertilizer = getMapValue(seedToFertilizerMap, soil);
                long water = getMapValue(seedToWaterMap, fertilizer);
                long light = getMapValue(seedToLightMap, water);
                long temperature = getMapValue(seedToTemperatureMap, light);
                long humidity = getMapValue(seedToHumidityMap, temperature);
                long location = getMapValue(seedToLocationMap, humidity);
                minMax = Math.min(minMax, location);
            }
        }
        return minMax;
    }

    /**
     * Get the value from the map
     *
     * @param map  the map to get the value from
     * @param seed the seed to get the value for
     * @return the value from the map
     */
    private static long getMapValue(TreeMap<Long, PartTwo.Destination> map, long seed) {
        long result = seed;
        Map.Entry<Long, Destination> entry = map.floorEntry(seed);

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
     * Convert the seed to map
     *
     * @param inputList the input list to convert
     */
    private static void convertSeedToMap(TreeMap<Long, String> inputList) {
        List<TreeMap<Long, Destination>> maps = List.of(
                seedToSoilMap,
                seedToFertilizerMap,
                seedToWaterMap,
                seedToLightMap,
                seedToTemperatureMap,
                seedToHumidityMap,
                seedToLocationMap);

        long index = 0;

        for (TreeMap<Long, Destination> specificMap : maps) {

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
     *
     * @param line the line to add
     * @param map  the map to add to
     */
    private static void addRangeToMap(String line, TreeMap<Long, PartTwo.Destination> map) {
        String[] split = line.split(" ");
        long destinationRangeStart = Long.parseLong(split[0]);
        long sourceRangeStart = Long.parseLong(split[1]);
        long rangeLength = Long.parseLong(split[2]);

        Destination destination = new Destination(destinationRangeStart, rangeLength);
        map.put(sourceRangeStart, destination);
    }

    /**
     * Add the seeds to the list
     *
     * @param input the input to add
     */
    private static void addSeeds(String input) {
        input = input.replace("seeds:", "");
        String[] split = input.split(" ");

        for (int index = 0; index < split.length; index++) {
            String first = split[index];
            if (first.equals("")) {
                continue;
            }
            long start = Long.parseLong(first);
            Long range = Long.parseLong(split[index + 1]);
            index++;
            Destination destination = new Destination(start, range);
            seeds.add(destination);
        }
    }
}