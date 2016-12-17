package ms;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cli on 10/11/2016.
 */
public class RunLength {

    public static String decode(String source) {
        StringBuffer dest = new StringBuffer();
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            matcher.find();
            while (number-- != 0) {
                dest.append(matcher.group());
            }
        }
        return dest.toString();
    }

    public static Set<Character> set = new HashSet<>();

    public static void main(String[] args) {
        set.add('0');
        set.add('1');
        set.add('2');
        set.add('3');
        set.add('4');
        set.add('5');
        set.add('6');
        set.add('7');
        set.add('8');
        set.add('9');
        set.add('A');
        set.add('B');
        set.add('C');
        set.add('D');
        set.add('E');
        set.add('F');
        set.add('\\');


        Scanner sc = new Scanner(System.in);

        String line = "";
        List<String> test = new ArrayList<>();

        while(!(line = sc.nextLine()).equals("")) {
            test.add(line);
        }

        for (int i = 0; i < test.size(); i++) {
            boolean found = false;
            String s = test.get(i);
            char[] c = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < c.length; j++) {
                if (!set.contains(c[j])) {
                    System.out.println("CORRUPTED");
                    found = true;
                    break;
                }
                if(c[j] == '\\') {
                    if (j < c.length - 1 && c[j+1] == '\\') {
                        System.out.println("CORRUPTED");
                        found = true;
                        break;
                    } else {

                        if (j + 1 < c.length - 1) {
                            if (!set.contains(c[j+1])) {
                                System.out.println("CORRUPTED");
                                found = true;
                                break;
                            }
                            j++; // incrment the next element
                            int numNeeded = 0;
                            if (Character.isAlphabetic(c[j])) {
                                numNeeded = Integer.parseInt(""+c[j], 16 ) + 3;
                            } else {
                                numNeeded = Integer.parseInt("" + c[j]) + 3;
                            }
                            j++;

                            if (c[j] == '\\' || !set.contains(c[j])) {
                                System.out.println("CORRUPTED");
                                found = true;
                                break;
                            } else {
                                for (int k = 0; k < numNeeded; k++) {
                                    sb.append(c[j]);
                                }
                            }
                        } else {
                            System.out.println("CORRUPTED");
                            found = true;

                            break;
                        }
                    }
                } else {
                    sb.append(c[j]);
                }
            }
            if (!found) {
                System.out.println(sb.toString());
            }
        }

    }
}
