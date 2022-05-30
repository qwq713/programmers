package 동적계획법;

public class 정수삼각형 {
    public int solution(int[][] triangle) {

        int height = triangle.length;

        int[][] arr2D = new int[height][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < triangle[y].length; x++) {
                if (y == 0) {
                    arr2D[y][x] = triangle[y][x];
                } else if (x == 0) {
                    arr2D[y][x] = arr2D[y-1][x] + triangle[y][x];
                } else if (y == x) {
                    arr2D[y][x] = arr2D[y - 1][x - 1] + triangle[y][x];
                } else {
                    int leftSum = arr2D[y - 1][x] + triangle[y][x];
                    int rightSum = arr2D[y - 1][x - 1] + triangle[y][x];
                    arr2D[y][x] = Math.max(leftSum, rightSum);
                }
            }
        }

        int answer = -1;
        for (int x = 0; x < height; x++) {
            answer = Math.max(answer, arr2D[height - 1][x]);
        }
        return answer;
    }
}
