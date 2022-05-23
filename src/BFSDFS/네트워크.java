package BFSDFS;

public class 네트워크 {

    void dfs(int now,int[] map, int[][] computers){
        map[now] = 1;
        // [[1, 1, 0], [1, 1, 0], [0, 0, 1]]

        int[] next = computers[now];
        for(int i=0;i<next.length;i++){
            if(i != now && next[i] == 1 && map[i] == 0){
                dfs(i,map,computers);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        int[] map = new int[n];
        // 0 ~ n-1 각 노드에서 dfs를 실행하여 몇번 dfs가 실행되는지 확인

        for (int i = 0; i < n; i++) {
            if(map[i] == 0){
                dfs(i,map,computers);
                answer += 1;
            }
        }
        return answer;
    }
}
