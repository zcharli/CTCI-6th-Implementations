package ms;

import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cli on 12/17/2016.
 */
public class XortholianFashion {

    /**
     * Method is not safe unless l.size() is a power of three and each 3*i element in l is unique
     *
     * @param l
     * @return
     */
    public static Map<String, List<Set<String>>> mapPreferences(List<String> l) {
        Map<String, List<Set<String>>> prefs = new HashMap<>();
        for (int i = 0; i < l.size(); i += 3) {
            String name = l.get(i);
            String personalHats = l.get(i + 1);
            String distastefulHats = l.get(i + 2);
            Set<String> personal = Arrays.stream(personalHats.split(" ")).collect(Collectors.toSet());
            Set<String> distasteful = Arrays.stream(distastefulHats.split(" ")).collect(Collectors.toSet());
            List<Set<String>> personPrefs = new ArrayList<>(2);
            personPrefs.add(personal);
            personPrefs.add(distasteful);
            prefs.put(name, personPrefs);
        }
        return prefs;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";
        List<String> test = new ArrayList<>();

        while (!(line = sc.nextLine()).equals("")) {
            test.add(line);
        }

        Map<String, List<Set<String>>> namePref = mapPreferences(test);
        TreeSet<String> sorted = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char[] s1 = o1.toCharArray();
                char[] s2 = o2.toCharArray();
                for (int i = 0; i < s1.length; i++) {
                    if (i == s2.length) {
                        return 1;
                    }
                    if (s1[i] == s2[i]) {
                        continue;
                    }
                    if (s1[i] == '\'') {
                        if (s2[i] > 'i') {
                            return - 1;
                        }
                        return 1;
                    }
                    if (s2[i] == '\'') {
                        if (s1[i] >= 'i') {
                            return 1;
                        }
                        return -1;
                    }
                    if (s1[i] < s2[i]) {
                        return -1;
                    }
                    if (s1[i] > s2[i]) {
                        return 1;
                    }
                }
                return 0;
            }
        });

        Set<String> exclusionSet = new HashSet<>(); // Fix concurrent modificaiton.
        for (Map.Entry<String, List<Set<String>>> currentPerson : namePref.entrySet()) {
            if (exclusionSet.contains(currentPerson.getKey())) {
                continue;
            }
            Set<String> personal = currentPerson.getValue().get(0);
            Set<String> distasteful = currentPerson.getValue().get(1);
            String friendName = "";

            for (Map.Entry<String, List<Set<String>>> entry : namePref.entrySet()) {
                if (currentPerson == entry || exclusionSet.contains(entry.getKey())) {
                    continue;
                }
                Set<String> comparePersonal = entry.getValue().get(0);
                Set<String> compareDistasteful = entry.getValue().get(1);

                Set<String> personalTest = new HashSet<>(personal);
                personalTest.retainAll(compareDistasteful);
                if (personalTest.size() != 0) {
                    continue;
                }
                Set<String> distastefulTest = new HashSet<>(distasteful);
                distastefulTest.retainAll(comparePersonal);
                if (distastefulTest.size() != 0) {
                    continue;
                }
                friendName = entry.getKey();
                break;
            }
//            if (friendName != "") {
                // We are guarenteed to find a friend
                String newPair = currentPerson.getKey() + " " + friendName;
                String friendPair = friendName + " " + currentPerson.getKey();
                sorted.add(newPair);
                sorted.add(friendPair);

                exclusionSet.add(currentPerson.getKey());
                exclusionSet.add(friendName);
//            }

        }

        for (String pairs : sorted) {
            System.out.println(pairs);
        }

        System.out.println("done");
    }
}
