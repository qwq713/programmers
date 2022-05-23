package 이분탐색;

import java.util.Arrays;

public class 입국심사2 {

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long left=1;
        long right = Long.MAX_VALUE;
        while (left <= right){
            long mid = (left + right) / 2;
            long people = 0;
            for(int time :times){
                people +=  mid / time;
                if(people >= n){
                    break;
                }
            }

            if (people >=n ){
                answer = mid;
                right = mid -1;
            }else if(people < n){
                left = mid + 1;
            }
        }
        return answer;
    }
}
