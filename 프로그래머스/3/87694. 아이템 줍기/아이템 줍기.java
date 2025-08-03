import java.util.*;

class Solution {
    
    static class Pos{
        int x, y;
        int dist = 0;
        
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
    
    static int[][] graph = new int[120][120];
    static boolean[][] vis = new boolean[120][120];
    static LinkedList<Pos> q = new LinkedList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        for(int i=0;i<rectangle.length;i++){
            draw(rectangle[i]);
        }
        
        
        q.offer(new Pos(characterX*2, characterY*2));
        vis[characterX*2][characterY*2] = true;
        
        while(!q.isEmpty()){
            Pos cur = q.poll();
            
            for(int i=0;i<4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if(nx<0||nx>=120||ny<0||ny>=120) continue;
                if(vis[nx][ny]) continue;
                if(graph[nx][ny]!=1) continue;
                if(nx == itemX*2 && ny == itemY*2){
                    return (cur.dist+1)/2;
                }
                
                q.offer(new Pos(nx, ny, cur.dist+1));
                vis[nx][ny] = true;
            }
        }
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.printf("%d ",graph[i][j]);
            }
            System.out.println();
        }
        
        
        return answer/2;
    }
    
    void draw(int[] rec){
        int x1 = rec[0]*2;
        int y1 = rec[1]*2;
        int x2 = rec[2]*2;
        int y2 = rec[3]*2;
        
        for(int i=x1;i<=x2;i++){
            for(int j=y1;j<=y2;j++){
                if(graph[i][j]==2) continue;
                if(i==x1||i==x2||j==y1||j==y2){
                    graph[i][j] = 1;
                    continue;
                }
                graph[i][j] = 2;
            }
        }
        
        //         for(int i=0;i<10;i++){
        //     for(int j=0;j<10;j++){
        //         System.out.printf("%d ",graph[i][j]);
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        // System.out.println();
    }
}




