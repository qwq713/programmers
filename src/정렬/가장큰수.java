package 정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 가장큰수 {

    static Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            // o1 : 1
            // o2 : 20
            // o102 : 1 20
            // o2o1 : 20 1 -> 20 가 앞에 와야지 ( 더 큰수 ) 20 이 1보다 크다.
            String o1o2 = o1.toString() + o2.toString();
            String o2o1 = o2.toString() + o1.toString();

            int o1o2Int = Integer.parseInt(o1o2);
            int o2o1Int = Integer.parseInt(o2o1);

            return -(o1o2Int - o2o1Int);
        }
    };

    public String solution(int[] numbers) {
        String answer = "";

        List<Integer> numberList = new ArrayList<>();
        for (int num : numbers) {
            numberList.add(num);
        }

        Collections.sort(numberList, comparator);

        // "000000000000000000000000000000000000000000000000000000" 방지..
        // 이거 Integer.ParseInt도 안먹힘.
        if (numberList.get(0) == 0) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int num : numberList) {
            stringBuilder.append(Integer.toString(num));
        }
        return stringBuilder.toString();
    }
}
