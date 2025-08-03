import java.util.*;

class Solution {
    static LinkedList<Integer> q = new LinkedList<>();
    static List<Integer>[] graph = new ArrayList[201];
    static boolean[] vis = new boolean[201];
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;
                if(computers[i][j] == 1){
                    graph[i].add(j);
                }
            }
        }
        
        for(int i=0;i<n;i++){
            if(vis[i]) continue;
            answer++;
            
            q.offer(i);
            vis[i] = true;
            
            while(!q.isEmpty()){
                int start = q.poll();
                
                for(int j=0; j<graph[start].size();j++){
                    Integer num = graph[start].get(j);
                    if(vis[num]) continue;
                    q.offer(num);
                    vis[num] = true;
                }
            }
            
        }
        
        
        return answer;
    }
}