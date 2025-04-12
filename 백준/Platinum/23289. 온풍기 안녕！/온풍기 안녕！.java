import java.util.*;
import java.io.*;

public class Main {
	static class Pos{
		int x;
		int y;
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Heater{
		int x;
		int y;
		int temp;
		int dir;
		
		Heater(int x, int y, int temp, int dir){
			this.x = x;
			this.y = y;
			this.temp = temp;
			this.dir = dir;
		}
		
		Heater(int x, int y, int temp){
			this.x = x;
			this.y = y;
			this.temp = temp;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] graph = new int[22][22];
	static boolean [][][][] walls = new boolean[22][22][22][22];
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static LinkedList<Heater> q = new LinkedList<>();
	
	static int r;
	static int c;
	static int k;
	static int w;
	
	static List<Pos> investedPos = new ArrayList<>();
	static List<Heater> heaters = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		for(int i=1;i<=r;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=c;j++) {
				int v = Integer.parseInt(st.nextToken());
				if(v==5) {
					investedPos.add(new Pos(i,j));
				}
				if(1<=v && v<=4) heaters.add(new Heater(i, j,5,v));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		for(int i=0;i<w;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if(t==0) {
				walls[x][y][x-1][y] = true;
				walls[x-1][y][x][y] = true;
			}else {
				walls[x][y][x][y+1] = true;
				walls[x][y+1][x][y] = true;
			}
		}
		
		int cnt = 0;
		
		while(true) {
			if(isFinished()) break;
			turn();
			cnt++;
			if(cnt>100) break;
		}
		
		System.out.print(cnt);
	}
	
	static void printGraph() {
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				System.out.printf("%d ", graph[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}
	
	static void turn() {
		blow();
		balancing();
		downSideTemp();
	}
	
	static boolean isFinished() {
		for(int i=0;i<investedPos.size();i++) {
			Pos pos = investedPos.get(i);
			if(graph[pos.x][pos.y]<k) return false;
		}
		
		return true;
	}
	
	static void downSideTemp() {
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				if(i==1||i==r||j==1||j==c) {
					if(graph[i][j]==0) continue;
					graph[i][j]-=1;
				}
			}
		}
	}
	
	static void balancing() {
		int[][] save = new int[22][22];
		boolean[][] vis = new boolean[22][22];
		
		
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				vis[i][j] = true;
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(oob(nx,ny)) continue;
					if(vis[nx][ny]) continue;
					if(walls[i][j][nx][ny]) continue;
					int cur = graph[i][j];
					int near = graph[nx][ny];
					int lostTemp = calLostTemp(cur, near);
					save[i][j]-=lostTemp;
					save[nx][ny]+=lostTemp;
				}
			}
		}
		
		for(int i=1;i<=r;i++) {
			for(int j=1;j<=c;j++) {
				graph[i][j]+=save[i][j];
			}
		}
	}
	
	static int calLostTemp(int cur, int near) {
		int v = Math.abs(cur - near)/4;
		if(cur>near) return v;
		return -v;
	}
	
	static void blow() {
		for(int i=0;i<heaters.size();i++) {
			Heater heater = heaters.get(i);
			if(heater.dir == 1) {
				blowRight(heater);
			}else if(heater.dir == 2) {
				blowLeft(heater);
			}else if(heater.dir == 3) {
				blowUp(heater);
			}else if(heater.dir == 4) {
				blowDown(heater);
			}
		}
	}
	
	static void blowDown(Heater heater) {
		boolean[][] vis = new boolean[22][22];
		
		int nx = heater.x+1;
		int ny = heater.y;
		
		if(oob(nx, ny)) return;
		
		graph[nx][ny]+=5;
		q.offer(new Heater(nx,ny,4));
		vis[nx][ny] = true;
		
		while(!q.isEmpty()) {
			Heater cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int temp = cur.temp;
			// 좌측 하단
			nx = x+1;
			ny = y-1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][x][ny] && !walls[x][ny][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}

			// 정면
			nx = x+1;
			ny = y;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
			
			// 우측 하단 
			nx = x+1;
			ny = y+1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][x][ny] && !walls[x][ny][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
		}
		
	}
	
	static void blowUp(Heater heater) {
		boolean[][] vis = new boolean[22][22];
		
		int nx = heater.x-1;
		int ny = heater.y;
		
		if(oob(nx, ny)) return;
		
		graph[nx][ny]+=5;
		q.offer(new Heater(nx,ny,4));
		vis[nx][ny] = true;
		
		while(!q.isEmpty()) {
			Heater cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int temp = cur.temp;
			// 좌측 상단
			nx = x-1;
			ny = y-1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][x][ny] && !walls[x][ny][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}

			// 정면
			nx = x-1;
			ny = y;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
			
			// 우측 상단 
			nx = x-1;
			ny = y+1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][x][ny] && !walls[x][ny][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
		}
		
	}
	
	static void blowLeft(Heater heater) {
		boolean[][] vis = new boolean[22][22];
		
		int nx = heater.x;
		int ny = heater.y-1;
		
		if(oob(nx, ny)) return;
		
		graph[nx][ny]+=5;
		q.offer(new Heater(nx,ny,4));
		vis[nx][ny] = true;
		
		while(!q.isEmpty()) {
			Heater cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int temp = cur.temp;
			// 좌측 상단
			nx = x-1;
			ny = y-1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][y] && !walls[nx][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}

			// 정면
			nx = x;
			ny = y-1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
			
			// 좌측 하단 
			nx = x+1;
			ny = y-1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][y] && !walls[nx][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
		}
		
	}
	
	
	static void blowRight(Heater heater) {
		boolean[][] vis = new boolean[22][22];
		
		int nx = heater.x;
		int ny = heater.y+1;
		
		if(oob(nx, ny)) return;
		
		graph[nx][ny]+=5;
		q.offer(new Heater(nx,ny,4));
		vis[nx][ny] = true;
		
		while(!q.isEmpty()) {
			Heater cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int temp = cur.temp;
			// 우측 상단
			nx = x-1;
			ny = y+1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][y] && !walls[nx][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
			nx = x;
			ny = y+1;
			// 정면 
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
			
			// 우측 하단 
			nx = x+1;
			ny = y+1;
			if(!oob(nx,ny) && !vis[nx][ny]) {
				// 벽 확인 
				if(!walls[x][y][nx][y] && !walls[nx][y][nx][ny]) {
					graph[nx][ny]+=temp;
					if(temp-1!=0)q.offer(new Heater(nx, ny, temp-1));
					vis[nx][ny] = true;
				}
			}
		}
		
	}
	
	static boolean oob(int x, int y) {
		return x<1||x>r||y<1||y>c;
	}
}