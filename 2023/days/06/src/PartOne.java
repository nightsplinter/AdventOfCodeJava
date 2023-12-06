/**
 * --- Day 6: Wait For It ---
 *
 *
 * https://adventofcode.com/2023/day/6
 **/

public class PartOne {
    public static void main(String[] args) throws Exception {
        int[] time = { 46, 85, 75, 82 };
        int[] distance = { 208, 1412, 1257, 1410 };

        int count = 0;
        int total = 1;

        for (int i = 0; i < time.length; i++) {
            int raceLasts = time[i];
            int recordDistance = distance[i];
            count = 0;
            for (int j = 0; j <= raceLasts; j++) {
                int travelDistance = j * (raceLasts - j);
                if (travelDistance > recordDistance) {
                    count++;
                }
            }
            if (count == 0) {
                count = 1;
            }
            total *= count;
        }
        System.out.println(total);
    }
}
