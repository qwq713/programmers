package 완전탐색;

import java.util.ArrayList;
import java.util.List;

public class 모의고사 {

    public int[] solution(int[] answers) {
        int[] user1 = {1, 2, 3, 4, 5};
        int[] user2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] user3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] answerCount = {0, 0, 0};

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == user1[i % user1.length]) {
                answerCount[0] += 1;
            }
            if (answers[i] == user2[i % user2.length]) {
                answerCount[1] += 1;
            }
            if (answers[i] == user3[i % user3.length]) {
                answerCount[2] += 1;
            }
        }

        int maxCount = Math.max(answerCount[0],answerCount[1]);
        maxCount = Math.max(maxCount, answerCount[2]);

        List<Integer> answerList = new ArrayList<>();
        for(int i=0;i<answerCount.length;i++){
            if(answerCount[i] == maxCount){
                answerList.add(i+1);
            }
        }
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}
