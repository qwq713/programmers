package 완전탐색;

public class 카펫 {
    /****
     *
     * 제약 1. 가로길이 >= 세로길이보다
     *
     * 문제풀이
     * 1. brown / yellow 격자의 개수르 제공
     * 2. brown + yellow = total
     * 3. 세로 : col , 가로 : row
     *    total % col == 0 && row >= col 일때만 개수를 채움
     *
     *    int edgeRow = row
     *    int edgeCol = col
     *    while( brown >0 ){
     *        외곽 칠함.
     *        edgeRow -= 1
     *        edgeCol -= 1
     *    }
     *    if (brown != 0 ){
     *        답 아님
     *    }
     *    if( total - brown == yellow ) {
     *        답.
     *    }
     *
     *
     * */


    public int[] solution(int brown, int yellow) {
        int[] answer = {0,0};
        int MIN_ROW = 3;
        int total = brown + yellow;
        int maxCol = total / MIN_ROW;

        for (int col = 3; col <= maxCol; col++) {
            int tempBrown = brown;
            if (total % col == 0) {
                int row = total / col;

                int edgeCol = col;
                int edgeRow = row;

                while (tempBrown > 0 && edgeCol > 0 && edgeRow > 0) {
                    int edgeBrownPaintCount = 2 * (edgeCol + edgeRow) - 4;
                    tempBrown -= edgeBrownPaintCount;
                    edgeRow -= 1;
                    edgeCol -= 1;
                }

                // break문 안쓰기...
                if(tempBrown == 0){
                    answer[0] = row;
                    answer[1] = col;
                    break;
                }
            }
        }
        return answer;
    }
}
