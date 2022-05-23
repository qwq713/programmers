package 탐욕법;


public class 조이스틱_재귀로접근해야함 {
    int[] charToNum(String str) {
        int[] result = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = Character.getNumericValue(str.charAt(i)) - Character.getNumericValue('A');
        }
        return result;
    }

    int[] move(char ch,int nowIdx, int[] numStr) {
        int count = 0;
        boolean find =false;
        if (ch == '>') {
            while(find == false){
                if(numStr[nowIdx] > 0){
                    find = true;
                }else{
                    nowIdx = ( nowIdx + 1 ) % numStr.length;
                    count += 1;
                }
            }
        } else if (ch == '<') {
            while(find == false){
                if(numStr[nowIdx] > 0){
                    find = true;
                }else{
                    nowIdx = ( nowIdx - 1  + numStr.length) % numStr.length;
                    count += 1;
                }
            }
        }
        return new int[]{count,nowIdx};
    }

    public int solution(String name) {
        int answer = 0;
        // ABC
        // 0 , 1, 2
        int[] numStr = charToNum(name);

        // add up or down
        for (int num : numStr) {
            answer += Math.min(num, 26 - num);
        }

        // 움직이는것만 고려하면됨.
        int toVisit = 0;
        int nowIdx = 0;

        // 변경해야하는것의 개수를 셈.
        for (int i = 0; i < numStr.length; i++) {
            if (numStr[i] > 0) {
                toVisit += 1;
            }
        }

        while (toVisit > 0) {
            int[] moveLeft = move('<',nowIdx,numStr); // {왼쪽으로 움직였을때 몇번 움직여야 변경할 문자를 만나는지 횟수, 그 문자가 있는 인덱스}
            int[] moveRight = move('>',nowIdx,numStr);// {오른쪽으로 움직였을때 몇번 움직여야 변경할 문자를 만나는지 횟수, 그 문자가 있는 인덱스}

            if (moveLeft[0] < moveRight[0]) {
                answer += moveLeft[0];
                nowIdx = moveLeft[1];
            } else {
                answer += moveRight[0];
                nowIdx = moveRight[1];
            }
            numStr[nowIdx] = 0; // 'A' 로 변경
            toVisit -= 1;
        }
        return answer;
    }
}


// A B A A A C
//   1       2
//   3(변경) + 3(이동)


/****
 *
 *
 * 1 0 1 0 1
 * 0 0 1 0 1 => 0
 * 0 0 1 0 0 => 1
 * 0 0 0 0 0 => 2
 * total : 3
 *
 * 0 1 1 0 0 1
 * 0 0 1 0 0 1 => 1
 * 0 0 0 0 0 1 => 1
 * 0 0 0 0 0 0 => 2
 * : 4
 *
 * 0 1 1 0 0 1
 * 0 1 1 0 0 0 => 1
 * 0 0 1 0 0 0 => 2
 * 0 0 0 0 0 0 => 1
 * : 4
 * 0 1 1 0 0 1
 * 0 1 1 0 0 0 => 1
 * 0 1 0 0 0 0 => 3
 * 0 0 0 0 0 0 => 1
 * : 5
 */
