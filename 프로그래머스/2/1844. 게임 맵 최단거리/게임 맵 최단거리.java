import java.util.*;

class Solution {
    
    static class Pos{
        int x, y;
        int dist=0;
        
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        Pos(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    static LinkedList<Pos> q = new LinkedList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] vis = new boolean[101][101];
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        int n = maps.length;
        int m = maps[0].length;
        
        
        q.offer(new Pos(0,0));
        vis[0][0] = true;
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx<0||nx>=n||ny<0||ny>=m) continue;
                if(maps[nx][ny]==0) continue;
                if(vis[nx][ny]) continue;
                if(nx==n-1 && ny==m-1){
                    return cur.dist+2;
                }
                
                q.offer(new Pos(nx, ny, cur.dist+1));
                vis[nx][ny] = true;
                
            }
        }
        
        
        return -1;
    }
}