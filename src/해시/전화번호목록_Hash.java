package 해시;

import java.util.*;

public class 전화번호목록_Hash {
    public boolean solution(String[] phone_book) {
        Map<String, Boolean> phoneMap = new HashMap<>();
        for(String str : phone_book){
            phoneMap.put(str,true);
        }

        for(String str : phoneMap.keySet()){
            for(int i=1;i<str.length();i++){
                String subStr = str.substring(0,i);
                if(phoneMap.getOrDefault(subStr,false)){
                    return false;
                }
            }
        }
        return true;
    }
}