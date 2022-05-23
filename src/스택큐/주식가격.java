package 스택큐;

import java.util.ArrayDeque;
import java.util.Deque;

public class 주식가격 {

    class Stock {
        int idx;
        int price;

        public Stock(int idx, int price) {
            this.idx = idx;
            this.price = price;
        }
    }

    public int[] solution(int[] prices) {
        int lastIdx = prices.length - 1;
        int[] answer = new int[lastIdx + 1];
        Deque<Stock> stockDeque = new ArrayDeque<>();


        // O(n^2)
        // O(2n)
        int idx = 0;
        for (int price : prices) {
            if (stockDeque.isEmpty() || stockDeque.peekFirst().price <= price) {
                stockDeque.addFirst(new Stock(idx, price));
            } else {
                while (!stockDeque.isEmpty() && stockDeque.peekFirst().price > price) {
                    Stock stock = stockDeque.pollFirst();
                    answer[stock.idx] = idx - stock.idx;
                }
                stockDeque.addFirst(new Stock(idx, price));
            }
            idx++;
        }

        while (!stockDeque.isEmpty()) {
            Stock stock = stockDeque.pollFirst();
            answer[stock.idx] = lastIdx - stock.idx;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] answer = new 주식가격().solution(new int[]{1, 2, 3, 2, 3});
        for (int ans : answer) {
            System.out.println(ans);
        }
    }
}

/***
 * 0 1 2 3    4
 * 1 2 3 4 || 2
 *
 * stack 1 2 3 ( idx : 2 , price : 3 ) => answer[2] = 4 - 2 = 2
 *
 * stackFirst : idx : 3 , price : 4 => 삭제하고 answer[3] = 4 - 3 = 1
 * newStock :  idx : 4 , price : 2

 * 0 1 2 3    4
 * 1 2 || 2
 *
 * stack 1(idx 0, price 1 ) , 2 , 2
 * peek : idx 1 , price 2
 * newStock : idx 4 , price 2
 *
 * answer[idx] = lastIdx - idx
 *
 *
 */


