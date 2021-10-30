import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * --- Day 4: The Ideal Stocking Stuffer --- --- Part Two ---
 *
 * https://adventofcode.com/2015/day/4
 **/

public class PartTwo {
    public static void main(String[] args) throws Exception {
        String input;
        String hashtext;

        String puzzleInput = "yzbqklnj";
        int num = 0;

        MessageDigest md = MessageDigest.getInstance("MD5");

        do {
            input = puzzleInput + String.valueOf(++num);

            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

        } while (!hashtext.substring(0, 6).equals("000000"));

        System.out.println(num);
    }
}
