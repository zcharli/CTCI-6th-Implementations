package ms;

import java.util.*;

/**
 * Created by cli on 12/20/2016.
 */
public class XorlithianFlightPaths {

    public static final int MAXTAKEOFF = 2880;
    public static String endAirport;
    public static Map<String, FlightCost> memo;

    public static class FlightCost {
        public int timeInAir;
        public List<String> flights;
        public int currentTime = 0;

        public FlightCost(int timeInAir) {
            flights = new ArrayList<>();
            this.timeInAir = 0;
        }

        public FlightCost(FlightCost copy) {
            this.timeInAir = copy.timeInAir;
            this.currentTime = copy.currentTime;
            this.flights = new ArrayList<>(copy.flights);
        }

        public FlightCost(int timeInAir, List<String> path) {
            flights = path;
            this.timeInAir = 0;
        }

        public void addFlight(String flight) {
            flights.add(flight);
        }

        public void addTimeInAir(int time) {
            timeInAir += time;
        }

        public String getLastAirport() {
            return flights.get(flights.size() - 1);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Time: ").append(currentTime).append(" flied for ").append(timeInAir);
            return sb.toString();
        }
    }

    public static class FlightDetail {
        public String id;
        public String departure;
        public String arrival;
        public int departureTime;
        public int arrivalTime;
        public String airportName;

        public FlightDetail(String i, String depart, String a, int d, int t, String nm) {
            id = i;
            arrival = a;
            departureTime = d;
            arrivalTime = t;
            departure = depart;
            airportName = nm;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Flight ").append(id).append(" to ").append(arrival).append(" departing ").append(departureTime).append(" arriving ").append(arrivalTime);
            return sb.toString();
        }

        public int getTimeInAir() {
            return arrivalTime - departureTime;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";
        List<String> test = new ArrayList<>();

        String start = sc.nextLine();
        String dest = sc.nextLine();
        endAirport = dest;

        String currentAirport = "";
        System.out.printf("Starting at %s and ending at %s\n", start, dest);

        Map<String, TreeSet<FlightDetail>> flightTimes = new HashMap<>();

        while (true) {
            line = sc.nextLine();
            if (line.equals("")) {
                line = sc.nextLine();
                if (line.equals("")) {
                    break;
                }
            }
            String[] splitted = line.split(" ");
            if (splitted.length == 1) {
                // new airport
                currentAirport = splitted[0];
                if (flightTimes.containsKey(currentAirport)) {
                    System.out.println("What the fuck");
                    return;
                }
                flightTimes.put(currentAirport, new TreeSet<>(new Comparator<FlightDetail>() {
                    @Override
                    public int compare(FlightDetail o1, FlightDetail o2) {
                        if (o1.departureTime == o2.departureTime) {
                            return 0;
                        }
                        return o1.departureTime < o2.departureTime ? -1 : 1;
                    }
                }));
                continue;
            }
            String id = splitted[1];
            String forwardAirport = splitted[7];
            String depart = splitted[3];
            String arrival = splitted[7];
            int dept = Integer.parseInt(splitted[5]);
            int arrv = Integer.parseInt(splitted[9]);
            FlightDetail detail = new FlightDetail(id, depart, arrival, dept, arrv, forwardAirport);
            flightTimes.get(currentAirport).add(detail);
        }

        memo = new HashMap<>();

//        for (FlightDetail detail : flightTimes.get(currentAirport)) {
//            List<String> path = new ArrayList<>();
//            path.add(detail.id);
//            FlightCost cost = new FlightCost(0, path);
//            memo.put(detail.id, cost);
//            findLongest(flightTimes, cost);
//        }

        FlightCost cost = new FlightCost(0, new ArrayList<>());
        FlightCost bestCost = findLongest(flightTimes, cost, start);

        for (String id : bestCost.flights) {
            System.out.println(id);
        }
        System.out.println("done");
    }

    public static FlightCost findLongest(Map<String, TreeSet<FlightDetail>> flights, FlightCost cost, String currentAirport) {
        if (cost.currentTime >= MAXTAKEOFF) {
            // base case
            if (currentAirport != endAirport) {
                return null;
            }
            return cost;
        }
        FlightCost bestCost = null;
        int currentTime = cost.currentTime;
        try {
            for (FlightDetail detail : flights.getOrDefault(currentAirport, new TreeSet<>())) {
                if (detail.departureTime < currentTime) {
                    continue;
                }
                if (detail.departureTime >= MAXTAKEOFF || (detail.arrivalTime >= MAXTAKEOFF && detail.arrival != endAirport)) {
                    continue;
                }
                FlightCost costOfTravel = new FlightCost(cost);
                costOfTravel.addFlight(detail.id);
                costOfTravel.currentTime = detail.arrivalTime;
                costOfTravel.timeInAir += detail.arrivalTime - detail.departureTime;
                FlightCost newCost = findLongest(flights, costOfTravel, detail.airportName);
                if (newCost == null) {
                    bestCost = costOfTravel;
                    bestCost.flights.remove(costOfTravel.flights.size() - 1);
                    continue;
                }
                if (bestCost == null) {
                    bestCost = newCost;
                }
                if (newCost.timeInAir > bestCost.timeInAir) {
                    bestCost = newCost;
                }
            }
        } catch (Exception e) {
            System.out.println("wtf");
        }

        return bestCost;
    }
}
