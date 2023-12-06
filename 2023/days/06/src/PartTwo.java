import java.math.BigInteger;

/**
 * --- Day 6: Wait For It --- --- Part Two ---
 *
 *
 * https://adventofcode.com/2023/day/6
 **/

public class PartTwo {

    public static void main(String[] args) throws Exception {
        BigInteger raceLasts = new BigInteger("46857582");
        BigInteger recordDistance = new BigInteger("208141212571410");
        long count = 0;

        for (BigInteger j = BigInteger.ZERO; j.compareTo(raceLasts) <= 0; j = j.add(BigInteger.ONE)) {
            BigInteger travelDistance = j.multiply(raceLasts.subtract(j));
            if (travelDistance.compareTo(recordDistance) > 0) {
                count++;
            }
        }
        System.out.println(count);
    }
}
