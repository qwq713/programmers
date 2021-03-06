package 해시;

import java.util.*;

public class 전화번호목록 {

    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            String now = phone_book[i];
            String next = phone_book[i + 1];
            if (now.length() >= next.length()) {
                continue;
            }
            if (next.startsWith(now)) {
                return false;
            }
        }
        return true;
    }
}