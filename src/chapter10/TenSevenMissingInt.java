package chapter10;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by czl on 12/07/16.
 */
public class TenSevenMissingInt {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            final long max = ((long) Integer.MAX_VALUE); // < 4 billion, max 2bil
            final byte[] bitset = new byte[ (int) max / 8];
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line);
                int index = number / 8;
                bitset[ index ] |= ( 1 << (number % 8) );
            }

            for (int i = 0; i < bitset.length; i++) {
                if (bitset[i] != -1) {
                    byte cur = bitset[i];
                    for (int j = 0; j < 8; j++) {
                        if ((cur & (1 << j)) == 0) {
                            System.out.println(i * 8 + j);
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
