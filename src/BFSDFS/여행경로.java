package BFSDFS;

import java.util.*;

public class 여행경로 {

    static String[] answer = null;

    class AirPort implements Comparable<AirPort> {

        String name;
        boolean visit;

        public AirPort(String name, boolean visit) {
            this.name = name;
            this.visit = visit;
        }

        public int compareTo(AirPort other) {
            return this.name.compareTo(other.name);
        }
    }


    public void dfs(String now,
                    Map<String, List<AirPort>> airPortMap,
                    Deque<String> visitDeque,
                    int ticketCount,
                    int targetTicketCount) {

        if (ticketCount == targetTicketCount) {
            if (answer == null) {
                answer = new String[targetTicketCount + 1];
                int i = 0;

                for (String visit : visitDeque) {
                    answer[i] = new String(visit);
                    i++;
                }
            }
        }

        if (!airPortMap.containsKey(now)) {
            return;
        }

        List<AirPort> nextAirPorts = airPortMap.get(now);

        for (AirPort nextAirPort : nextAirPorts) {
            if (!nextAirPort.visit) {
                nextAirPort.visit = true;
                visitDeque.add(nextAirPort.name);

                dfs(nextAirPort.name, airPortMap, visitDeque, ticketCount + 1, targetTicketCount);

                visitDeque.removeLast();
                nextAirPort.visit = false;
            }
        }
    }

    public String[] solution(String[][] tickets) {
        Deque<String> visitDeque = new ArrayDeque();
        Map<String, List<AirPort>> airPortMap = new HashMap<>();


        for (String[] ticket : tickets) {
            String source = ticket[0];
            String destination = ticket[1];

            if (!airPortMap.containsKey(source)) {
                airPortMap.put(source, new ArrayList<AirPort>());
            }

            airPortMap.get(source).add(new AirPort(destination, false));
        }

        for (String key : airPortMap.keySet()) {
            Collections.sort(airPortMap.get(key));
        }

        visitDeque.add("ICN");
        dfs("ICN", airPortMap, visitDeque, 0, tickets.length);

        return answer;
    }

    public static void main(String[] args) {
        // String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        new 여행경로().solution(tickets2);
    }
}
