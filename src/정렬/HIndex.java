package 정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HIndex {

    public int solution(int[] citations) {

        int answer = -1;
        List<Integer> citationList = new ArrayList<>();

        for (int citation : citations) {
            citationList.add(citation);
        }

        Collections.sort(citationList);

        for (int i = 0; i < citationList.size(); i++) {
            int citation = citationList.get(i);
            int small = Math.min(citation, citationList.size()-i);
            answer = Math.max(small,answer);
        }
        return answer;
    }
}

/***
 *
 * n = 5
 *
 * [0, 1, 4, 5, 6]
 *  idx : 3
 *  h1 : 4
 *  h2 : h1이상 인용된 논문의 개수 3
 *  h3 : answer와 비교할 값. 3
 *  answer = max(h3,answer) / 3
 *
 *  answer 의 초기값은 -1
 *
 *
 * n = 4
 *
 * 10 20 30 40
 *
 * n= 4
 * idx = 1
 * h1 : 20
 * h2 : 3
 *
 * h3 : 3
 *
 *
 * 0 ~ 차례대로 예시 말고 큰 예시를 들어보자..
 * Example)
 * 10 12
 */