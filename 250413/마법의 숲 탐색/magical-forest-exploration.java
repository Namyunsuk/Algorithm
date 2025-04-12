import java.io.*;
import java.util.*;

public class Main{
	static class Pos{
		int x;
		int y;
		int exitDir;
		int index;
		
		Pos(int x, int y, int dir, int index){
			this.x = x;
			this.y = y;
			this.exitDir = dir;
			this.index = index;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] graph = new int[74][74];
	static List<Pos> angels = new ArrayList<>();
	static Pos curAngel;
	
	// 골렘 이동용 
	static int[] dx1 = {1,1,1};
	static int[] dy1 = {0,-1,1};
	
	// 정령 이동용 
	static int[] dx2 = {1,-1,0,0};
	static int[] dy2 = {0,0,-1,1};
	
	// 출구 방향 
	static int[] exitX = {-1,0,1,0};
	static int[] exitY = {0,1,0,-1};
	
	static int r;
	static int c;
	static int k;
	static int sum=0;
	
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<= k; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			angels.add(new Pos(0, c, d, i));
		}
		
		for(int i=0;i<k;i++) {
			Pos golem = angels.get(i);
			boolean isMoved = moveGolem(golem);
			
			if(!isMoved) continue;
			
			int row = moveAngel();
			sum+=row;
		}
		
		System.out.print(sum);
	}

	private static void printGraph() {
		for(int i=2;i<=r+1;i++) {
			for(int j=1;j<=c;j++) {
				System.out.printf("  %d  ", graph[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int moveAngel() {
		int maxRow = 0;
		boolean[][] vis = new boolean[74][74];
		LinkedList<Pos> q = new LinkedList<>();
		
		q.offer(curAngel);
		vis[curAngel.x][curAngel.y] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int i=0;i<4;i++) {
				int nx = cur.x + dx2[i];
				int ny = cur.y + dy2[i];
				int curIndex = cur.index;
				
				if(nx<2||nx>r+1||ny<1||ny>c) continue;
				if(graph[nx][ny]==0) continue;
				if(graph[nx][ny]!=curIndex){
					if(-curIndex!=graph[nx][ny] && curIndex > 0) continue;
				}
				if(vis[nx][ny]) continue;
				if(nx>maxRow) maxRow = nx-1;
				q.offer(new Pos(nx, ny, cur.exitDir, graph[nx][ny]));
				vis[nx][ny] = true;
			}
		}
		
		return maxRow;
	}
	
	static boolean moveGolem(Pos golem) { 
		int maxRow = 0;
		Pos resultGolem = golem;
		boolean[][] vis = new boolean[74][74];
		LinkedList<Pos> q = new LinkedList<>();
		
		q.offer(golem);
		vis[golem.x][golem.y] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			boolean isDown = false;
			boolean isLeft = false;
			for(int i=0;i<3;i++) {
				int nx = cur.x + dx1[i];
				int ny = cur.y + dy1[i];
				
				if(isDown) continue; // 아래로 하강한 상태 == 왼쪽 or 오른쪽으로 갈 필요가 X
				if(isLeft) continue;
				if(oob(nx, ny)) continue;
				if(isOverlapped(nx, ny)) continue;
				if(i==1) { // 왼쪽 이동 
					if(isOverlapped(nx-1, ny)) continue;
				}
				if(i==2) { // 오른쪽 이동 
					if(isOverlapped(nx-1, ny)) continue;
				}
				if(vis[nx][ny]) continue;
				int curExitDir = calExitDir(dx1[i], dy1[i], cur.exitDir);
				if(nx>maxRow) {
					maxRow = nx;
					resultGolem = new Pos(nx, ny, curExitDir, cur.index);
				}
				if(i==0) isDown = true;
				if(i==1) isLeft = true;
				q.offer(new Pos(nx, ny, curExitDir, cur.index));
				vis[nx][ny] = true;
			}
		}
		
		if(maxRow<3) { // 일부가 나온 경우
			graph = new int[74][74]; // 숲 초기화
			return false;
		}
		
		graph[resultGolem.x][resultGolem.y] = resultGolem.index; // 중심 
		for(int i=0;i<4;i++) { // 주변 
			int nx = resultGolem.x + dx2[i];
			int ny = resultGolem.y + dy2[i];
			graph[nx][ny] = resultGolem.index;
		}
		
		int nx = exitX[resultGolem.exitDir];
		int ny = exitY[resultGolem.exitDir];
		graph[resultGolem.x+nx][resultGolem.y+ny] = -resultGolem.index; // 출구 
		
		curAngel = resultGolem; // 현재 요정의 위치 저장 
		
		return true;
	}
	
	static int calExitDir(int dx, int dy, int dir) {
		if(dx==1&&dy==0) return dir; // 아래로 하강하는 경우 출구 그대로
		if(dx==1&&dy==-1) { // 왼쪽으로 이동
			int curDir = dir-1;
			if(curDir==-1) curDir=3;
			return curDir;
		}
		int curDir = dir+1;
		if(curDir==4) curDir=0;
		return curDir;
	}
	
	static boolean oob(int x, int y) { // 골렘의 경계 확인
		return x+1>r+1||y-1<1||y+1>c;
	}
	
	static boolean isOverlapped(int x, int y) { // 다른 골렘과 겹친여부 확인
		return graph[x][y]!=0  || graph[x+1][y]!=0 || graph[x][y-1]!=0 || graph[x][y+1]!=0;
	}
}