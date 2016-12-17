package ms;

import java.util.*;

/**
 * Created by cli on 10/11/2016.
 */
public class XortholianArchitecture {

    public static  int findInflectionIndex(String s) {
        int j = 3;
        while (j < s.length()) {
            String right = s.substring(0, j);
            String left = s.substring(j, j << 1);
            if (right.equals(left)) {
                return j;
            }
            j++;
        }
        return j;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String line = "";
        List<String> test = new ArrayList<>();

        while(!(line = sc.nextLine()).equals("")) {
            test.add(line);
        }

        for (String caseCase : test) {

           int i = findInflectionIndex(caseCase);
           if (i == caseCase.length()) break;

           String subStr = caseCase.substring(0, i);

           i=0;
           int j = 0;
           int height = 0;
           while(i<caseCase.length()) {
               if (caseCase.charAt(i) == subStr.charAt(j)) {
                   i++;
                   j++;
               } else {
                   System.out.println(height);
                   System.out.println(subStr);
                   break;
               }
               if (j == subStr.length()) {
                   j = 0;
                   height++;
               }
           }

        }
        System.out.println("done");

    }
}
