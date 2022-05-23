package BFSDFS;

public class 타겟넘버 {


    int dfs(int nowIdx, boolean plus, int nowSum, int[] numbers, int target) {
        int num = numbers[nowIdx];

        if (plus) {
            nowSum += num;
        } else {
            nowSum -= num;
        }

        if (nowIdx == numbers.length - 1) {
            if (nowSum == target) {
                return 1;
            }
            return 0;
        } else {
            return dfs(nowIdx + 1, true, nowSum, numbers, target)
                    + dfs(nowIdx + 1, false, nowSum, numbers, target);
        }
    }

    public int solution(int[] numbers, int target) {
        return dfs(0, true, 0, numbers, target)
                + dfs(0, false, 0, numbers, target);
    }

}
