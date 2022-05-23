package 탐욕법;

import java.util.Arrays;

public class 체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int[] clothes = new int[n];
        Arrays.fill(clothes, 1);


        for (int idx : lost) {
            clothes[idx - 1] -= 1;
        }
        for (int idx : reserve) {
            clothes[idx - 1] += 1;
        }


        for(int idx=0;idx<n;idx++){
            if(idx == 0){
                if( clothes[idx] == 2 && clothes[idx+1] == 0){
                    clothes[idx] = 1;
                    clothes[idx+1] = 1;
                }
            }

            else if(idx == n-1 ){
                if(clothes[idx] == 2 && clothes[idx-1] == 0){
                    clothes[idx] = 1;
                    clothes[idx-1] = 1;
                }
            }

            else{
                if(clothes[idx] == 2){
                    if(clothes[idx-1] == 0){
                        clothes[idx] = 1;
                        clothes[idx-1] = 1;
                    }else if(clothes[idx+1] == 0){
                        clothes[idx] = 1;
                        clothes[idx + 1] = 1;
                    }
                }
            }
        }

        for (int clothe : clothes) {
            if (clothe >= 1) {
                answer += 1;
            }
        }

        return answer;
    }
}

// 0 2 0 2