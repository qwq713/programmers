package 정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class K번째수 {

    /**
     * subList(int fromIdx, int toIdx) 의 결과를 Collections.sort 했을때
     * 원본 List 가 정렬되어버림 ㅁㅊ ;
     */


    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        List<Integer> numList = new ArrayList<>();

        for (int num : array) {
            numList.add(num);
        }

        int idx = 0;
        for (int[] command : commands) {
            int startIdx = command[0] - 1;
            int endIdx = command[1];
            int k = command[2] - 1;

            List<Integer> subNumList = new ArrayList<>(new ArrayList<>(numList.subList(startIdx,endIdx)));
            Collections.sort(subNumList);
            answer[idx]= subNumList.get(k);
        }
        return answer;
    }

    public static void main(String[] args) {

        List<Integer> arr = new ArrayList<>();

        arr.add(3);
        arr.add(2);
        arr.add(1);

        List<Integer> subArr = arr.subList(0,2);
        Collections.sort(subArr);

        for(int num: arr){
            System.out.println(num);
        }


    }
}
