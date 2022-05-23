package 스택큐;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class 기능개발 {

    class Job {
        int progress;
        int speed;

        public Job(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }

        void addProgress(int days) {
            this.progress += speed * days;
        }
    }

    public int[] solution(int[] progresses, int[] speeds) {
        /**
         * 큐에 넣은다음에 맨 앞에 있는 자원이 배포하기까지 걸리는 시간을 구함.
         * 배포하기까지 걸리는 시간 * 스피드 연산하여 모든 작업에 더함
         * 맨앞에 있는 작업부터 배포가 가능한 작업을 추출하여 그 개수를 answer에 넣음
         * */

        List<Integer> answer = new ArrayList<>();
        Deque<Job> jobDeque = new ArrayDeque<>();

        for (int i = 0; i < progresses.length; i++) {
            jobDeque.add(new Job(progresses[i], speeds[i]));
        }

        while (!jobDeque.isEmpty()) {
            Job firstJob = jobDeque.getFirst();
            // 이해하기 어려움
            int durationTime = (int) Math.ceil((100 - firstJob.progress) / (float) firstJob.speed);
            for (Job job : jobDeque) {
                job.addProgress(durationTime);
            }

            int deployCount = 0;
            while (!jobDeque.isEmpty() && jobDeque.getFirst().progress >= 100) {
                jobDeque.pollFirst();
                deployCount += 1;
            }
            answer.add(deployCount);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
