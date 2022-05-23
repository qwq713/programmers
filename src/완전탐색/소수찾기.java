package 완전탐색;

import java.util.HashSet;
import java.util.Set;

public class 소수찾기 {

    // 이거 빼기.. 뭐야 스테틱..
    static Set<Integer> primeSet = new HashSet<>();

    boolean isPrime(int number) {
        if (number == 1 || number == 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(number);
        for (int quot = 2; quot <= sqrt; quot++) {
            if (number % quot == 0) {
                return false;
            }
        }
        return true;
    }

    // void 를 인트ㅜ로..
    void dfs(int nowIdx, String numbers, StringBuilder nowStringBuilder, boolean[] map) {
        map[nowIdx] = true;
        Character nowChar = numbers.charAt(nowIdx);
        nowStringBuilder.append(nowChar);

        int nowNumber = Integer.parseInt(nowStringBuilder.toString());

        if (isPrime(nowNumber)) {
            primeSet.add(nowNumber);
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (map[i] == false) {
                dfs(i, numbers, nowStringBuilder, map);
            }
        }

        map[nowIdx] = false;
        nowStringBuilder.deleteCharAt(nowStringBuilder.length() - 1);
    }

    public int solution(String numbers) {

        boolean[] map = new boolean[numbers.length()];

        for (int i = 0; i < numbers.length(); i++) {
            dfs(i, numbers, new StringBuilder(), map);
        }

        int answer = primeSet.size();
        return answer;
    }
}
