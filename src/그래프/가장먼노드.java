package 그래프;

import java.util.*;

public class 가장먼노드 {


    class Node {
        int number;
        int distance;

        public Node(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }
    }

    public int solution(int n, int[][] edge) {
        int answer = 0;
        int maxDistance = -1;
        Map<Integer, Boolean> visitMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();


        Map<Integer, HashMap<Integer, Boolean>> edgeMap = new HashMap<>();

        for (int[] e : edge) {
            int from = e[0];
            int to = e[1];

            visitMap.put(from, false);
            visitMap.put(to, false);

            if (!edgeMap.containsKey(from)) {
                edgeMap.put(from, new HashMap<>());
            }

            if (!edgeMap.containsKey(to)) {
                edgeMap.put(to, new HashMap<>());
            }

            edgeMap.get(from).put(to, true);
            edgeMap.get(to).put(from, true);
        }


        visitMap.put(1, true);
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int nextDistance = node.distance + 1;
            Map<Integer, Boolean> nextNodes = edgeMap.get(node.number);
            for (int key : nextNodes.keySet()) {
                if (!visitMap.get(key)) {
                    visitMap.put(key, true);
                    queue.add(new Node(key, nextDistance));
                    maxDistance = maxDistance < nextDistance ? nextDistance : maxDistance;
                }
            }
        }

        edgeMap = new HashMap<>();
        visitMap = new HashMap<>();
        queue = new LinkedList<>();

        for (int[] e : edge) {
            int from = e[0];
            int to = e[1];

            visitMap.put(from, false);
            visitMap.put(to, false);

            if (!edgeMap.containsKey(from)) {
                edgeMap.put(from, new HashMap<>());
            }

            if (!edgeMap.containsKey(to)) {
                edgeMap.put(to, new HashMap<>());
            }

            edgeMap.get(from).put(to, true);
            edgeMap.get(to).put(from, true);
        }

        visitMap.put(1, true);
        queue.add(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.distance == maxDistance) {
                answer += 1;
            }
            int nextDistance = node.distance + 1;
            Map<Integer, Boolean> nextNodes = edgeMap.get(node.number);
            for (int key : nextNodes.keySet()) {
                if (!visitMap.get(key)) {
                    visitMap.put(key, true);
                    queue.add(new Node(key, nextDistance));
                }
            }
        }
        return answer;
    }
}
