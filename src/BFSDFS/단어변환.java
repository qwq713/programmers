package BFSDFS;

import java.util.HashMap;
import java.util.Map;

public class 단어변환 {

    static int minAnswer = Integer.MAX_VALUE;
    // 미리 만듬
    // 이걸 미리 안만들면, diff 비교하는 과정을 존나게 계속 해야되.. 필요할때마다.
    Map<String, Map<String, Boolean>> buildMap(String[] words) {
        Map<String, Map<String, Boolean>> wordMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String fromStr = words[i];
            wordMap.put(fromStr, new HashMap<>());
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                String toStr = words[j];
                int diffCount = 0;
                for (int k = 0; k < fromStr.length(); k++) {
                    if (fromStr.charAt(k) != toStr.charAt(k)) {
                        diffCount += 1;
                        if (diffCount > 1) {
                            break;
                        }
                    }
                }
                if (diffCount == 1) {
                    wordMap.get(fromStr).put(toStr, true);
                } else {
                    wordMap.get(fromStr).put(toStr, false);
                }
            }
        }
        return wordMap;
    }


    void dfs(int nowIdx, int step, String target, String[] words, int[] visitMap, Map<String, Map<String, Boolean>> wordMap) {
        String nowStr = words[nowIdx];
        System.out.println(nowStr);
        visitMap[nowIdx] = 1;

        if (nowStr.equals(target)) {
            minAnswer = Math.min(step, minAnswer);
        }

        for (int i = 0; i < words.length; i++) {
            String nextStr = words[i];
            if (visitMap[i] == 0) {
                if (wordMap.get(nowStr).get(nextStr)) {
                    dfs(i, step + 1, target, words, visitMap, wordMap);
                }
            }

        }

        visitMap[nowIdx] = 0;
    }


    public int solution(String begin, String target, String[] words) {
        boolean beTarget = false;

        for (String word : words) {
            if (word.equals(target)) {
                beTarget = true;
            }
        }

        if (!beTarget) {
            return 0;
        }

        int[] visitMap = new int[words.length];

        Map<String, Map<String, Boolean>> wordMap = buildMap(words);

        for (int i = 0; i < words.length; i++) {
            String nextStr = words[i];

            int diff = 0;
            for(int j=0;j<begin.length();j++){
                if(begin.charAt(j) != nextStr.charAt(j)){
                    diff += 1;
                }
            }
            if(diff == 1){
                dfs(i, 1, target, words, visitMap, wordMap);
            }
        }

        return minAnswer;
    }

    public static void main(String[] args) {
        System.out.println("1");
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println("2");
        new 단어변환().solution(begin,target,words);

        System.out.println(minAnswer);
    }
}
