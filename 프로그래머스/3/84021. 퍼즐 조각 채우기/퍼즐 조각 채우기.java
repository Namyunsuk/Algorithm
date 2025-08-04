import java.util.*;

class Solution {
    static class Pos{
        int x, y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static List<int[][]> puzzles = new ArrayList<>();
    static List<int[][]> blanks = new ArrayList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        int n = game_board.length;
        
        findBlanks(n, game_board);        
        findPuzzles(n, table);
        
        for(int i=0;i<blanks.size();i++){
            int[][] blank = blanks.get(i);
            int row = blank.length;
            int col = blank[0].length;
            
            for(int j=0;j<row;j++){
                for(int k=0;k<col;k++){
                    System.out.printf("%d ",blank[j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
        
        System.out.println("---------------------------------------------------------");
        
        for(int i=0;i<puzzles.size();i++){
            int[][] puzzle = puzzles.get(i);
            int row = puzzle.length;
            int col = puzzle[0].length;
            
            for(int j=0;j<row;j++){
                for(int k=0;k<col;k++){
                    System.out.printf("%d ",puzzle[j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
        
        boolean[] vis = new boolean[blanks.size()];
        
        for(int i=0;i<puzzles.size();i++){
            for(int j=0;j<blanks.size();j++){
                if(vis[j]) continue;
                int[][] blank = blanks.get(j);
                if(isSame(blank, puzzles, i)){
                    answer+= calCnt(blank);
                    vis[j] = true;
                    break;
                }
            }
        }
            
        return answer;
    }
    
    static int calCnt(int[][] blank){
        int cnt = 0;
        int row = blank.length;
        int col = blank[0].length;
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(blank[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }
    
    static boolean isSame(int[][] blank, List<int[][]> puzzles, int i){
        boolean flag = false;
        
        for(int rot=0;rot<4;rot++){
            puzzles.set(i, rotate(puzzles.get(i)));
            int[][] puzzle = puzzles.get(i);
            if(flag) continue;
            if(equal(blank, puzzle)){
                flag = true;   
            }
        }
        
        return flag;
    }
    
    static int[][] rotate(int[][] puzzle) {
        int row = puzzle.length;
        int col = puzzle[0].length;
        
        int[][] tmp = new int[row][col];
        
        int[][] newPuzzle = new int[col][row];
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                newPuzzle[j][row-1-i] = puzzle[i][j];
            }
        }
        
        return newPuzzle;
    }
    
    static boolean equal(int[][] blank, int[][] puzzle){
        int row = blank.length;
        int col = blank[0].length;
        
        if(row!=puzzle.length || col!=puzzle[0].length) return false;
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(blank[i][j]!=puzzle[i][j]) return false;
            }
        }
        return true;
    }
    
    static void findPuzzles(int n, int[][] table){
        LinkedList<Pos> q = new LinkedList<>();
        boolean[][] vis = new boolean[n+1][n+1];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(table[i][j] == 0) continue;
                if(vis[i][j]) continue;
                
                List<Pos> puzzle = new ArrayList<>();
                puzzle.add(new Pos(i, j));
                q.add(new Pos(i, j));
                vis[i][j] = true;
                
                int minX = i;
                int minY = j;
                
                int row = i+1;
                int col = j+1;
                
                while(!q.isEmpty()){
                    Pos cur = q.poll();
                    
                    for(int d=0;d<4;d++){
                        int nx = cur.x + dx[d];
                        int ny = cur.y + dy[d];
                        
                        if(nx<0||nx>=n||ny<0||ny>=n) continue;
                        if(vis[nx][ny]) continue;
                        if(table[nx][ny]==0) continue;
                        
                        puzzle.add(new Pos(nx, ny));
                        q.add(new Pos(nx, ny));
                        vis[nx][ny] = true;
                        
                        minX = Math.min(minX, nx);
                        minY = Math.min(minY, ny);
                        
                        row = Math.max(row, nx+1);
                        col = Math.max(col, ny+1);
                    }
                }
                
                int[][] p = new int[row-minX][col-minY];
                
                for(int k=0;k<puzzle.size();k++){
                    Pos pos = puzzle.get(k);
                    p[pos.x-minX][pos.y-minY] = 1;
                }
                
                puzzles.add(p);
            }
        }
    }
    
    static void findBlanks(int n, int[][] game_board){
        LinkedList<Pos> q = new LinkedList<>();
        boolean[][] vis = new boolean[n+1][n+1];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(game_board[i][j] == 1) continue;
                if(vis[i][j]) continue;
                
                List<Pos> blank = new ArrayList<>();
                blank.add(new Pos(i, j));
                q.add(new Pos(i, j));
                vis[i][j] = true;
                
                int minX = i;
                int minY = j;
                
                int row = i+1;
                int col = j+1;
                
                while(!q.isEmpty()){
                    Pos cur = q.poll();
                    
                    for(int d=0;d<4;d++){
                        int nx = cur.x + dx[d];
                        int ny = cur.y + dy[d];
                        
                        if(nx<0||nx>=n||ny<0||ny>=n) continue;
                        if(vis[nx][ny]) continue;
                        if(game_board[nx][ny]==1) continue;
                        
                        blank.add(new Pos(nx, ny));
                        q.add(new Pos(nx, ny));
                        vis[nx][ny] = true;
                        
                        minX = Math.min(minX, nx);
                        minY = Math.min(minY, ny);
                        
                        row = Math.max(row, nx+1);
                        col = Math.max(col, ny+1);
                    }
                }
                
                
                int[][] b = new int[row-minX][col-minY];
                
                for(int k=0;k<blank.size();k++){
                    Pos pos = blank.get(k);
                    b[pos.x-minX][pos.y-minY] = 1;
                }
                
                blanks.add(b);
            }
        }
    }
}