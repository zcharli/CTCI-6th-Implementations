package ms;

import java.util.*;

/**
 * Created by cli on 12/20/2016.
 */
public class XorlithianFlightPaths {

    public static final int MAXTAKEOFF = 2880;
    public static String endAirport;

    public static class FlightCost {
        private int timeInAir;
        private List<FlightDetail> flights;
        private int currentTime = 0;

        public FlightCost(int timeInAir) {
            setFlights(new ArrayList<>());
            this.setTimeInAir(0);
        }

        public FlightCost(FlightCost copy) {
            this.setTimeInAir(copy.getTimeInAir());
            this.setCurrentTime(copy.getCurrentTime());
            this.setFlights(new ArrayList<>(copy.getFlights()));
        }

        public FlightCost(int timeInAir, List<FlightDetail> path) {
            setFlights(path);
            this.setTimeInAir(0);
        }

        public void addFlight(FlightDetail flight) {
            setTimeInAir(getTimeInAir() + flight.getTimeInAir());
            setCurrentTime(flight.getArrivalTime());
            getFlights().add(flight);
        }

        public void mergeCost(FlightCost cost) {
            getFlights().addAll(cost.getFlights());
            setTimeInAir(getTimeInAir() + cost.getTimeInAir());
        }

        public void addTimeInAir(int time) {
            setTimeInAir(getTimeInAir() + time);
        }

        public FlightDetail getLastAirport() {
            return getFlights().get(getFlights().size() - 1);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Time: ").append(getCurrentTime()).append(" flied for ").append(getTimeInAir());
            return sb.toString();
        }

        public int getTimeInAir() {
            return timeInAir;
        }

        public void setTimeInAir(int timeInAir) {
            this.timeInAir = timeInAir;
        }

        public List<FlightDetail> getFlights() {
            return flights;
        }

        public void setFlights(List<FlightDetail> flights) {
            this.flights = flights;
        }

        public int getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(int currentTime) {
            this.currentTime = currentTime;
        }
    }

    public static class FlightDetail {
        private String id;
        private String departure;
        private String arrival;
        private int departureTime;
        private int arrivalTime;
        private String airportName;

        public FlightDetail(String i, String depart, String a, int d, int t, String nm) {
            setId(i);
            setArrival(a);
            setDepartureTime(d);
            setArrivalTime(t);
            setDeparture(depart);
            setAirportName(nm);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Flight ").append(getId()).append(" to ").append(getArrival()).append(" departing ").append(getDepartureTime()).append(" arriving ").append(getArrivalTime());
            return sb.toString();
        }

        public int getTimeInAir() {
            return getArrivalTime() - getDepartureTime();
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public int getDepartureTime() {
            return departureTime;
        }

        public void setArrivalTime(int arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeparture() {
            return departure;
        }

        public void setDeparture(String departure) {
            this.departure = departure;
        }

        public String getArrival() {
            return arrival;
        }

        public void setArrival(String arrival) {
            this.arrival = arrival;
        }

        public void setDepartureTime(int departureTime) {
            this.departureTime = departureTime;
        }

        public String getAirportName() {
            return airportName;
        }

        public void setAirportName(String airportName) {
            this.airportName = airportName;
        }
    }

    public static List<String> findLongest(Map<String, TreeSet<FlightDetail>> flights, String currentAirport) {
        List<String> path = new ArrayList<>();

        FlightCost cost = _findLongest(flights, currentAirport, null, new FlightCost(0));

        for (FlightDetail detail : cost.getFlights()) {
            path.add(detail.getId());
        }

        return path;
    }

    public static FlightCost _findLongest(Map<String, TreeSet<FlightDetail>> flights,
                                          String currentAirport,
                                          FlightDetail lastFlightLanding,
                                          FlightCost currentCost) {

        if (currentCost != null && currentCost.getCurrentTime() >= MAXTAKEOFF) {
            if (currentAirport.equals(endAirport)) {
                return currentCost;
            }
            return null;
        }

        SortedSet<FlightDetail> collection = flights.get(currentAirport);

        FlightCost best = null;
        for (FlightDetail detail : collection) {

            if (lastFlightLanding != null && detail.getDepartureTime() < lastFlightLanding.getArrivalTime()) {
                continue;
            }

            FlightCost cost = new FlightCost(0);
            cost.addFlight(detail);
            FlightCost newCost = _findLongest(flights, detail.getAirportName(), detail, cost);

            if (newCost == null) {
                continue;
            }
            if (best == null) {
                best = newCost;
            } else if (best.getTimeInAir() < newCost.getTimeInAir()) {
                best = newCost;
            }
        }

        if (best != null) {
            currentCost.mergeCost(best);
        }

        return currentCost;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";

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
                    return;
                }
                flightTimes.put(currentAirport, new TreeSet<>((o1, o2) -> {
                    if (o1.getDepartureTime() == o2.getDepartureTime()) {
                        if (o1.getArrivalTime() == o2.getArrivalTime()) {
                            return -1;
                        }
                        return o1.getArrivalTime() - o2.getArrivalTime();
                    }
                    return o1.getDepartureTime() - o2.getDepartureTime();
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

        for (String id : findLongest(flightTimes, start)) {
            System.out.println(id);
        }

        System.out.println("done");
    }
}
