package 동적계획법;

public class 등굣길 {
    public void print2D(int[][] arr2D) {
        for (int[] row : arr2D) {
            for (int x : row) {
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }

    public int solution(int m, int n, int[][] puddles) {
        // m : x
        // n : y
        int[][] arr2D = new int[n][m];
        for (int[] yx : puddles) {
            int x = yx[0];
            int y = yx[1];
            arr2D[y-1][x-1] = -1;
        }
        for (int y = 0; y < n; y++) {
            if (arr2D[y][0] == -1) {
                break;
            }
            arr2D[y][0] = 1;
        }

        for (int x = 0; x < m; x++) {
            if (arr2D[0][x] == -1) {
                break;
            }
            arr2D[0][x] = 1;
        }

        for (int y = 1; y < n; y++) {
            for (int x = 1; x < m; x++) {
                if (arr2D[y][x] == -1) {
                    continue;
                } else {
                    if (arr2D[y - 1][x] == -1 && arr2D[y][x - 1] == -1) {
                        continue;
                    } else if (arr2D[y - 1][x] == -1) {
                        arr2D[y][x] = arr2D[y][x - 1] % 1000000007;
                    } else if (arr2D[y][x - 1] == -1) {
                        arr2D[y][x] = arr2D[y-1][x] % 1000000007;
                    } else {
                        arr2D[y][x] = (arr2D[y - 1][x] + arr2D[y][x - 1]) % 1000000007;
                    }
                }
            }
        }

        int answer = arr2D[n - 1][m - 1]% 1000000007;
        return answer;
    }

    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int[][] puddles = new int[][]{{2, 2}};

        System.out.println(new 등굣길().solution(m, n, puddles));
    }
}
