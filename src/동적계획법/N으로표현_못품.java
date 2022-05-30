package 동적계획법;

public class N으로표현_못품 {


    int find(int N, int number, int maxValue, int[] arr) {
        if (number < 0 || number > maxValue) {
            return 99999;
        }

        if (arr[number] > 0) {
            return arr[number];
        }
        if (number % 5 == 0) {
            return arr[number] =
                    Math.min(
                            Math.min(
                                    find(N, number - 5, maxValue, arr),
                                    find(N, number + 5, maxValue, arr)
                            ),
                            Math.min(
                                    find(N, number / 5, maxValue, arr),
                                    find(N, number * 5, maxValue, arr)
                            ));
        }else{
            return arr[number] = Math.min(
                    Math.min(
                            find(N, number - 5, maxValue, arr),
                            find(N, number + 5, maxValue, arr)
                    ),
                            find(N, number * 5, maxValue, arr)
                    );
        }

    }


    public int solution(int N, int number) {
        int answer = 0;
        String numStr = "";
        for (int i = 0; i < 8; i++) {
            numStr += Integer.toString(N);
        }
        int maxValue = Integer.parseInt(numStr);

        int[] arr = new int[maxValue + 1];

        String nStr = Integer.toString(N);
        for (int i = 0; i < 8; i++) {
            int num = Integer.parseInt(nStr);
            arr[num] = i + 1;
            nStr += Integer.toString(N);
        }

        return find(N,number,maxValue,arr);

    }

    public static void main(String[] args) {
        System.out.println(new N으로표현_못품().solution(5,12));
    }
}
