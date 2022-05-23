package HEAP;

import java.util.PriorityQueue;
import java.util.Queue;

public class 더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        Queue<Integer> pq = new PriorityQueue<>();

        for (int num : scoville) {
            pq.add(num);
        }

        while (pq.peek() < K && pq.size() >= 2) {
            int first = pq.poll();
            int second = pq.poll();
            int result = first + 2 * second;
            pq.add(result);
            answer += 1;
        }

        if (pq.peek() < K) {
            return -1;
        }

        return answer;
    }
}
