package chapter16;

import lib.Person;

import java.util.Random;

/**
 * Created by czl on 10/08/16.
 */
public class SixteenTenLivingPeople {

    public static int mostAliveYear(Person[] p, int first, int last) {
        int[] years = new int[last - first + 1];
        int totalYears = last - first;
        for( Person per : p ) {

            int startYear = per.birth - first;
            int endYear = per.death - first;
            int yearsAlive = endYear - startYear + 1;

            for( int i = 0; i < yearsAlive; ++i ) {
                years[startYear + i]++;
            }
        }

        int curMax = Integer.MIN_VALUE;
        int maxYear= 0;
        for( int i = 0; i < totalYears; ++i ) {
            if (years[i] > curMax) {
                curMax = years[i];
                maxYear = i;
            }
        }
        return first + maxYear;
    }

    public static int maxAliveYear(Person[] people, int min, int max) {
		/* Build population delta array. */
        int[] populationDeltas = getPopulationDeltas(people, min, max);
        int maxAliveYear = getMaxAliveYear(populationDeltas);
        return maxAliveYear + min;
    }

    /* Add birth and death years to deltas array. */
    public static int[] getPopulationDeltas(Person[] people, int min, int max) {
        int[] populationDeltas = new int[max - min + 2];
        for (Person person : people) {
            int birth = person.birth - min;
            populationDeltas[birth]++;

            int death = person.death - min;
            populationDeltas[death + 1]--;
        }
        return populationDeltas;
    }

    /* Compute running sums and return index with max. */
    public static int getMaxAliveYear(int[] deltas) {
        int maxAliveYear = 0;
        int maxAlive = 0;
        int currentlyAlive = 0;
        for (int year = 0; year < deltas.length; year++) {
            currentlyAlive += deltas[year];
            if (currentlyAlive > maxAlive) {
                maxAliveYear = year;
                maxAlive = currentlyAlive;
            }
        }

        return maxAliveYear;
    }

    public static void main(String[] args) {
        int n = 100;
        int first = 1900;
        int last = 2000;

        Random random = new Random();
        Person[] people = new Person[n + 1];
        for (int i = 0; i < n; i++) {
            int birth = first + random.nextInt(last - first);
            int death = birth + random.nextInt(last - birth);
            people[i] = new Person(birth, death);
            //System.out.println(birth + ", " + death);
        }
        people[n] = new Person(first, first);

        int yearA = mostAliveYear(people, first, last);
        int yearB = maxAliveYear(people, first, last);

        System.out.println("A: " + yearA);
        System.out.println("B: " + yearB);

    }
}
