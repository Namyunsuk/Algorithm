import java.util.*;

class Solution {
    static List<Integer>[] graph = new ArrayList[102];
    static boolean[][] cases = new boolean[102][102];
    static boolean[][] cutted = new boolean[102][102];
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int i=0;i<wires.length;i++){
            int[] wire = wires[i];
            graph[wire[0]].add((Integer)wire[1]);
            graph[wire[1]].add((Integer)wire[0]);
        }
        
        for(int i=1;i<=n;i++){
            for(int j=0;j<graph[i].size();j++){
                int num = graph[i].get(j);
                if(cases[i][num]) continue;
                cases[i][num] = true;
                cases[num][i] = true;
                
                cutted[i][num] = true;
                cutted[num][i] = true;
                
                int cnt = getCnt(i);
                int other = n-cnt;
                
                System.out.printf("i: %d j: %d cnt: %d\n", i,num,cnt);
                
                answer = Math.min(answer, Math.abs(cnt - other));
                
                cutted[i][num] = false;
                cutted[num][i] = false;
            }
        }
        
        return answer;
    }
    
    static int getCnt(int start){
        boolean[] vis = new boolean[102];
        LinkedList<Integer> q = new LinkedList<>();
        int cnt = 0;
        
        q.offer((Integer)start);
        vis[start] = true;
        cnt++;
        
        while(!q.isEmpty()){
            int cur = (int)q.poll();
            
            int n = graph[cur].size();
            
            for(int i=0;i<n;i++){
                int num = (int)graph[cur].get(i);
                
                if(vis[num]) continue;
                if(cutted[cur][num]) continue;
                q.offer((Integer)num);
                cnt++;
                vis[num] = true;
            }
        }
        
        
        return cnt;
    }
}




