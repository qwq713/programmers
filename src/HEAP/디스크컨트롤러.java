package HEAP;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Job {
    int enterTime;
    int duration;

    public Job(int enterTime, int duration) {
        this.enterTime = enterTime;
        this.duration = duration;
    }
}

public class 디스크컨트롤러 {
    /***
     *
     * 현재 처리할 수 있는 것들 중에 duration이 짧은 것 순서로 처리.
     * 단 처리할때 Duration이 작은 1개만을 처리해야함
     * (이유)
     * A: {0,3} B: {0,2} C: {1, 1}
     * B -> C -> A 순으로해야 최소 waitTime 가짐
     * 만약에 여러개를 한번에 처리할 경우
     * B -> A -> C 로 처리됨.
     */

    public int solution(int[][] jobs) {
        int count = jobs.length;
        int time = 0;
        int waitTime = 0;


        Queue<Job> epq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.enterTime - o2.enterTime;
            }
        });

        Queue<Job> dpq = new PriorityQueue<>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.duration - o2.duration;
            }
        });

        for (int[] job : jobs) {
            int enterTime = job[0];
            int duration = job[1];
            epq.add(new Job(enterTime, duration));
        }


        while (!epq.isEmpty()) {

            if (!epq.isEmpty() && epq.peek().enterTime > time) {
                time = epq.peek().enterTime;
            }

            while (!epq.isEmpty() && epq.peek().enterTime <= time) {
                Job jobSortStartTime = epq.poll();
                dpq.add(new Job(jobSortStartTime.enterTime, jobSortStartTime.duration));
            }

            Job job = dpq.poll();
            time += job.duration;
            waitTime += (time - job.enterTime);

            while(!dpq.isEmpty()){
                epq.add(dpq.poll());
            }
        }

        int answer = waitTime / count;

        return answer;
    }

    public static void main(String[] args) {
        int[][] jobs = {{100,50},{120,50}}; // 1
        // 500, 900 , 1300
        // 500, 800 , 1400
        System.out.println("정답 :" + new 디스크컨트롤러().solution(jobs));
    }
}
