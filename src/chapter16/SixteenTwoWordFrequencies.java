package chapter16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by cli on 7/17/16.
 */
public class SixteenTwoWordFrequencies {

    public static HashMap<String, Integer> freq;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Supply a path for txt file and a word to look for");
            return;
        }

        String bookPath = args[1];
        String checkFreq = args[2];
        freq = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(bookPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null && line.length() > 0 && line.trim() != "") {
                    String[] words = line.split(" ");
                    for (String w : words) {
                        if (!freq.containsKey(w)) {
                            freq.put(w, 1);
                        } else {
                            freq.put(w, freq.get(w) + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("The string %s came up %d times", checkFreq, freq.get(checkFreq)));
    }
}
