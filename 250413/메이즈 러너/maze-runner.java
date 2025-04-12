import java.util.*;
import java.io.*;

public class Main{
	static class Player{
		int x;
		int y;
		int dist=0;
		boolean isExit=false;
		
		Player(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Rectangle{
		int x;
		int y;
		int size;
		Rectangle(int x, int y, int size){
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int EXIT = 10;
	
	static int[][] graph = new int[12][12];
	static List<Player> players = new ArrayList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int n;
	static int m;
	static int k;
	static int exitX;
	static int exitY;
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			players.add(new Player(x, y));
		}
		
		st = new StringTokenizer(br.readLine());
		exitX = Integer.parseInt(st.nextToken());
		exitY = Integer.parseInt(st.nextToken());
		graph[exitX][exitY] = EXIT;
		
		for(int i=0;i<k;i++) {
			movePlayers();
			if(isEveryOneExit()) break;
			Rectangle rec = findRectangle();
			rotate(rec.x, rec.y, rec.size);
		}
		
		int sum = 0;
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			sum+=player.dist;
		}
		
		System.out.println(sum);
		System.out.printf("%d %d", exitX, exitY);
	}
	
	static boolean isPlayer(int x, int y) {
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			if(player.x == x&&player.y==y) return true; 
		}
		return false;
	}
	
	static boolean isEveryOneExit() {
		// 플레이어 업데이트 
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			if(!player.isExit) return false; // 탈출하지 못한 플레이어 있는 경우 
		}
		
		return true;
	}
	
	static void rotate(int x, int y, int size) {
		int[][] tmp = new int[n+2][n+2];
		
		// 내구도 감소
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				if(graph[i][j]>=1&&graph[i][j]<=9) { // 벽인 경우 
					graph[i][j]--;
				}
			}
		}
		
		
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				int ox = i-x;
				int oy = j-y;
				
				int nx = oy;
				int ny = size-1-ox;
				tmp[nx+x][ny+y] = graph[i][j];
			}
		}
		
		for(int i=x;i<x+size;i++) {
			for(int j=y;j<y+size;j++) {
				graph[i][j] = tmp[i][j];
			}
		}
		
		
		
		// 출구 업데이트 
		int ox = exitX-x;
		int oy = exitY-y;
		int nx = oy;
		int ny = size-1-ox;
		exitX = nx+x;
		exitY = ny+y;
		
		// 플레이어 업데이트 
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			if(player.x>=x&&player.x<x+size && player.y>=y&&player.y<y+size) {
				int ox1 = player.x-x;
				int oy1 = player.y-y;
				int nx1 = oy1;
				int ny1 = size-1-ox1;
				player.x = nx1+x;
				player.y = ny1+y;
			}
		}
	}
	
	static Rectangle findRectangle(){
		int x=0;
		int y=0;
		int minSize = Integer.MAX_VALUE;
		for(int i=1;i<n;i++) {
			for(int j=1;j<n;j++) {
				int size = findRectangleSize(i,j);
				if(size==-1) continue;
				if(size<minSize) {
					minSize = size;
					x = i;
					y = j;
				}
			}
		}
		
		return new Rectangle(x,y, minSize);
	}
	
	static int findRectangleSize(int x, int y) {
		int maxSize = Math.min(n-x+1, n-y+1);
		for(int size=2;size<=maxSize;size++) {
			if(isValidRectangle(x,y,size)) return size;
		}
		
		return -1;
	}
	
	static boolean isValidRectangle(int x, int y, int size) {
		boolean hasPlayer = false;
		boolean hasExit = false;
		
		for(int i=x;i<x+size;i++) {
			boolean flag= false;
			for(int j=y;j<y+size;j++) {
				if(i>n||j>n) continue;
				if(graph[i][j]==EXIT) {
					hasExit = true;
					flag= true;
					break;
				}
			}
			if(flag) break;
		}
		
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			if(player.isExit) continue;
			if(player.x>=x && player.x<x+size && player.y>=y && player.y<y+size) {
				hasPlayer = true;
				break;
			}
		}
		
		return hasPlayer&&hasExit;
	}
	
	static void movePlayers() {
		for(int i=0;i<players.size();i++) {
			Player player = players.get(i);
			if(player.isExit) continue; // 탈출한 플레이어는 제외
			boolean isMoved = false;
			int mx = 0;
			int my = 0;
			int minDist = calDist(player.x, player.y, exitX, exitY);
			for(int d=0;d<4;d++) {
				int nx = player.x+dx[d];
				int ny = player.y+dy[d];
				
				if(nx<1||nx>n||ny<1||ny>n) continue;
				if(graph[nx][ny]>=1&&graph[nx][ny]<=9) continue; // 벽인 경우
				if(graph[nx][ny]==EXIT) {
					player.x = nx;
					player.y = ny;
					isMoved = true;
					player.isExit = true;
					break;
				}
				int dist = calDist(nx, ny, exitX, exitY);
				if(dist<minDist) {
					isMoved = true;
					minDist = dist;
					mx = nx;
					my = ny;
				}
			}
			if(isMoved) {
				player.x = mx;
				player.y = my;
				player.dist+=1;
			}
		}
	}
	
	static int calDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}	
}