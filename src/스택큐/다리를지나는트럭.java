package 스택큐;

import java.util.ArrayDeque;
import java.util.Deque;

public class 다리를지나는트럭 {

    class Bridge {
        int maxWeight;
        int nowWeight;
        int bridgeLength;

        public Bridge(int maxWeight, int nowWeight, int bridgeLength) {
            this.maxWeight = maxWeight;
            this.nowWeight = nowWeight;
            this.bridgeLength = bridgeLength;
        }

        boolean enter(int truckWeight) {
            return nowWeight + truckWeight <= maxWeight ? true : false;
        }
    }

    class Truck {
        int weight;
        int x;

        public Truck(int weight, int x) {
            this.weight = weight;
            this.x = x;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Bridge bridge = new Bridge(weight, 0, bridge_length);
        Deque<Truck> waitDeque = new ArrayDeque<>();
        Deque<Truck> enteredDeque = new ArrayDeque<>();

        for (int truckWeight : truck_weights) {
            waitDeque.add(new Truck(truckWeight, 0));
        }

        /**
         * Truck의 크기를 '점' 으로 간주할것.
         * 즉 길이가 2인(x=2) 다리를 건널 때 2초뒤에 트럭은 x=2인 장소에 있지만 아직 건넌 것은 아님...
         * 다만 시간의 최소단위를 1초로 정했기에 x=2인 트럭은 다리에 올라가져있는 트럭의 무게에서 제외해도됨.
         * 1.waitDeque가 empty가 될때까지 반복
         * 2.waitDeque에서 맨앞의 트럭을 다리에 실을 수 있는지 확인하고, 가능하면 enteredDeque에 싣는다.
         * 3.enteredDeque의 모든 트럭의 x값에 1을 더한다.
         * 4.enteredDeque 맨앞의 트럭가 다리의 끝에 도달했는지 확인하고, 도달했다면 다리에서 해당 트럭의 무게를 뺀다.
         *
         * 5.enteredDeque가 남아있다면 맨 뒤의 원소가 다리의 끝에 도달하는 시간을 계산하여 +1 추가하여 더한다.
         *
         * 박준영 : completeTime을 고려하여..
         *
         * */

        while (!waitDeque.isEmpty()) {
            Truck waitTruck = waitDeque.peekFirst();
            int waitTruckWeight = waitTruck.weight;

            if (bridge.enter(waitTruckWeight)) {
                bridge.nowWeight += waitTruckWeight;
                enteredDeque.add(waitDeque.pollFirst());
            }

            for (Truck truck : enteredDeque) {
                truck.x += 1;
            }
            if (enteredDeque.peekFirst().x == bridge_length) {
                Truck exitTruck = enteredDeque.pollFirst();
                bridge.nowWeight -= exitTruck.weight;
            }

            answer += 1;
        }
        if (!enteredDeque.isEmpty()) {
            answer += (bridge.bridgeLength - enteredDeque.peekLast().x) +1;
        }
        return answer;
    }

    public static void main(String[] args) {
        int bridge_length1 = 2;
        int weight1 = 10;
        int[] truck_weights1 = {7, 4, 5, 6};

        int bridge_length2 = 100;
        int weight2 = 100;
        int[] truck_weights2 = {10};

        int bridge_length3 = 100;
        int weight3 = 100;
        int[] truck_weights3 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

        System.out.println(new 다리를지나는트럭().solution(bridge_length1, weight1, truck_weights1));
        System.out.println(new 다리를지나는트럭().solution(bridge_length2, weight2, truck_weights2));
        System.out.println(new 다리를지나는트럭().solution(bridge_length3, weight3, truck_weights3));


    }
}
