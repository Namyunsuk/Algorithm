import java.io.*;
import java.util.*;


public class Main{
	static class Runner{
		int x;
		int y;
		int dx;
		int dy;
		boolean isCatched = false;
		
		Runner(int x, int y, int d){
			this.x = x;
			this.y = y;
			if(d==1) {
				dx = 0;
				dy = 1;
			}else {
				dx = 1;
				dy = 0;
			}
		}
		
		public void changeDir() {
			dx = -dx;
			dy = -dy;
		}
	}
	
	static class Catcher{
		int x;
		int y;
		int dist=1;
		int moveCount = 0;
		int dir = 0;
		boolean isOut = true;
		int index=0;
		
		Catcher(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int TREE = 1000;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int[][] graph = new int[101][101];
	static boolean[][] vis = new boolean[101][101];
	static List<Runner> runners = new ArrayList<>();
	static Catcher catcher;
	
	static int n;
	static int m;
	static int h;
	static int k;
	
	static int point = 0;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		catcher = new Catcher((n+1)/2, (n+1)/2);
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			runners.add(new Runner(x,y,d));
		}
		
		for(int i=0;i<h;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph[x][y] = TREE;
		}
		
		for(int turn=1;turn<=k;turn++) {
			// 도망자 이동 
			for(int i=0;i<m;i++) {
				moveRunner(i);
			}
			// 술래 이동 
			moveCatcher();
			
			// 잡기 
			catchRunners(turn);
			
		}
		
		System.out.print(point);
	}
	
	static void catchRunners(int turn) {
		int catchCount = 0;
		
		int nx = catcher.x;
		int ny = catcher.y;
		
		for(int i=0;i<3;i++) {
			if(nx<1||nx>n||ny<1||ny>n) break;
			if(graph[nx][ny]!=TREE) {
				catchCount+=runnerExistCount(nx, ny);
			}
			nx+=dx[catcher.dir];
			ny+=dy[catcher.dir];
		}
		point+=(catchCount * turn);
	}
	
	static int runnerExistCount(int x, int y) {
		int cnt = 0;
		
		for(int i=0;i<m;i++) {
			Runner runner = runners.get(i);
			if(runner.isCatched) continue;
			if(runner.x==x && runner.y == y) {
				cnt++;
				runner.isCatched = true;
			}
		}
		
		return cnt;
	}
	
	static void moveCatcher() {
		if(catcher.isOut) {
			moveCatcherOutside();
		}else {
			moveCatcherInside();
		}
	}
	
	static void moveCatcherInside() {
		int nx = catcher.x + dx[catcher.dir];
		int ny = catcher.y + dy[catcher.dir];
		catcher.x = nx;
		catcher.y = ny;
		
		vis[nx][ny] = true;
		
		// 중앙인 경우 -> 방향전환 
		if(catcher.x==(n+1)/2 && catcher.y == (n+1)/2) {
			catcher.isOut = true;
			catcher.dir = 0;
			vis = new boolean[101][101];
			return;
		}
		
		// 움직인 후 그 다음에 움직일 좌표 
		int nnx = catcher.x + dx[catcher.dir];
		int nny = catcher.y + dy[catcher.dir];

		
		// 현재 위치가 격자 가장자리인 경우 -> 방향 전환 
		if(nnx<1||nnx>n||nny<1||nny>n) {
			catcher.dir = chageDirWhenMoveInside(catcher.dir);
		}
		else if(vis[nnx][nny]) { // 이미 지나온 곳인 경우 -> 방향 전환
			catcher.dir = chageDirWhenMoveInside(catcher.dir);
		}
	}
	
	static int chageDirWhenMoveInside(int dir) {
		int newDir = dir-1;
		if(newDir==-1) newDir = 3;
		
		return newDir;
	}
	
	static void moveCatcherOutside() {
		if(catcher.index < catcher.dist) {
			catcher.index+=1;
			int nx = catcher.x + dx[catcher.dir];
			int ny = catcher.y + dy[catcher.dir];
			catcher.x = nx;
			catcher.y = ny;
		}
		
		// 방향 전환 (밖 -> 안)
		if(catcher.x==1&&catcher.y==1) {
			catcher.index = 0;
			catcher.dir = 2;
			catcher.moveCount = 0;
			catcher.dist = 1;
			catcher.isOut = false;
			vis[1][1] = true;
			return;
		}
		
		if(catcher.index == catcher.dist) {
			catcher.index = 0;
			catcher.dir = (catcher.dir+1)%4;
			catcher.moveCount+=1;
			if(catcher.moveCount==2) {
				catcher.moveCount = 0;
				catcher.dist+=1;
			}	
		}
	}
	
	static void moveRunner(int i) {
		Runner runner = runners.get(i);
		int dist = calDist(runner.x, runner.y, catcher.x, catcher.y);
		if(runner.isCatched) return; // 이미 잡힌 경우 
		if(dist>3) return; // 거리가 3초과인 경우 
		
		int nx = runner.x + runner.dx;
		int ny = runner.y + runner.dy;
		
		if(nx<1||nx>n||ny<1||ny>n) { // 격자 밖으로 넘어가는 경우 
			runner.changeDir();
			nx = runner.x + runner.dx;
			ny = runner.y + runner.dy;
		}
		
		if(nx==catcher.x && ny==catcher.y) return; // 움직이려는 위치에 술래가 있는 경우 
		
		runner.x = nx;
		runner.y = ny;
	}
	
	static int calDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}