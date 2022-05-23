package 해시;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 위장 {
    public int solution(String[][] clothes) {
        Map<String, List<String>> clotheMap = new HashMap<>();

        for (String[] clothe : clothes) {
            String name = clothe[0];
            String type = clothe[1];
            if (clotheMap.get(type) == null) {
                clotheMap.put(type, new ArrayList<>());
            }
            clotheMap.get(type).add(name);
        }

        int answer = 1;
        for (String type : clotheMap.keySet()) {
            answer *= (clotheMap.get(type).size() + 1);
        }
        return answer - 1;
    }
}



