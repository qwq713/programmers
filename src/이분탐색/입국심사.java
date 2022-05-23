package 이분탐색;

import java.util.Arrays;

public class 입국심사 {
    long bSearch(long start, long end, int n, int[] times) {
        if(start == end){
            return start;
        }

        long mid = (start + end) / 2;
        //System.out.println(start+" , "+mid+" , "+end);
        long total = 0;
        for (int time : times) {
            long ltime = (long)time;
            if ((mid / ltime) == 0) {
                break;
            }
            total += mid / time;
        }

        if (total == n) {
            while (total == n) {
                mid -= 1;
                total = 0;
                for (int time : times) {
                    if ((int) (mid / time) == 0) {
                        break;
                    }
                    total += mid / time;
                }
            }
            mid += 1;
            return mid;
        } else if (total < n) {
            return bSearch(mid + 1, end, n, times);
        } else {
            return bSearch(start, mid - 1, n, times);
        }
    }

    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long maxTime = Long.MAX_VALUE;
        long answer = bSearch(0, maxTime, n, times);
        return answer;
    }

    public static void main(String[] args) {
        long total = 0;
        for(long temp = 0; temp < 1000000000;temp++){
            total += (1000000000 /1000000001);
        }
        System.out.println(total);

    }
}
