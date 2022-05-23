package 해시;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> participantMap = new HashMap();

        for (String elt : participant) {
            int count = participantMap.getOrDefault(elt, 0) + 1;
            participantMap.put(elt, count);
        }

        for (String elt : completion) {
            int count = participantMap.get(elt) - 1;
            participantMap.put(elt, count);
        }

        Set<String> keySet = participantMap.keySet();
        for (String elt : keySet) {
            if (participantMap.get(elt) > 0) {
                answer = elt;
                break;
            }
        }
        return answer;
    }
}
