import java.io.*;
import java.util.*;


public class Main {
	static class Pos{
		int x;
		int y;
		boolean alive = true;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public void setAlive(boolean alive) {
			this.alive = alive;
		}
		
		@Override
		public boolean equals(Object obj) {
			Pos pos = (Pos)obj;
			return pos.x==this.x&&pos.y==this.y;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x,y);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static final int PARK = 4;
	
	// 상하좌우
	static int[] dx1 = {-1,1,0,0};
	static int[] dy1 = {0,0,-1,1};
	
	// 좌우상하
	static int[] dx2 = {0,0,-1,1};
	static int[] dy2 = {-1,1,0,0};
	
	static LinkedList<Pos> q = new LinkedList<>();
	
	static int[][] graph = new int[51][51];
	static int[][] sight;
	static List<Pos> path = new ArrayList<>();
	static Pos[][] pathGraph = new Pos[51][51];
	static boolean[][] vis = new boolean[51][51];
	
	static int[][] soldierGraph = new int[51][51];
	static List<Pos> soldiers = new ArrayList<>();

	
	static int n;
	static int m;
	static int attackSoldiersCnt;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int mx = Integer.parseInt(st.nextToken());
		int my = Integer.parseInt(st.nextToken());
		
		int px = Integer.parseInt(st.nextToken());
		int py = Integer.parseInt(st.nextToken());
		
		graph[px][py] = PARK;

        if(mx==px&&my==py){
            System.out.print(0);
            return;
        }
		
        st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			soldierGraph[sx][sy]++;
			soldiers.add(new Pos(sx, sy));
		}
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int value = Integer.parseInt(st.nextToken());
				if(graph[i][j]==PARK) continue;
				graph[i][j] = value;
			}
		}
		
		// 메두사 이동
		boolean isValid = moveMedusa(mx, my);
		if(!isValid) {
			System.out.println(-1);
			return;
		}
		
		Collections.reverse(path);
		
		
		for(int i=1;i<path.size()-1; i++) {
			attackSoldiersCnt = 0;
			Pos medusa = path.get(i);
			
			
			for(int j=0;j<soldiers.size();j++) {
				Pos soldier = soldiers.get(j);
				if(soldier.equals(medusa)) { // 메두사가 전사 위치로 온 경우
					soldier.setAlive(false);
					soldierGraph[soldier.x][soldier.y]--;

				}
			}
			
			
			int stoneSoldierCnt = see(medusa);
			int soldiersMovedCnt = moveSoldiers(medusa);
			attack(medusa);

			sight = new int[51][51]; 
			
			sb.append(soldiersMovedCnt + " " + stoneSoldierCnt + " " + attackSoldiersCnt +"\n");
		}
		
		sb.append("0");
		System.out.print(sb);
	}
	
	static boolean moveMedusa(int x, int y) {
		q.offer(new Pos(x, y));
		vis[x][y] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int i=0;i<4;i++) {
				int nx = cur.x + dx1[i];
				int ny = cur.y + dy1[i];
				
				if(nx<0||nx>=n||ny<0||ny>=n) continue;
				if(graph[nx][ny]==1) continue;
				if(vis[nx][ny]) continue;
				if(graph[nx][ny]==PARK) {
					path.add(new Pos(nx, ny));
					recordPath(cur.x, cur.y, x,y);
					return true;
				}
				q.offer(new Pos(nx, ny));
				pathGraph[nx][ny] = new Pos(cur.x, cur.y);
				vis[nx][ny] = true;
			}
		}
		
		return false;
	}
	
	static void recordPath(int x, int y, int desX, int desY) {
		int nx = x;
		int ny = y;
		while(true) {
			if(nx==desX&&ny==desY) {
				path.add(new Pos(nx, ny));
				return;
			}
			path.add(new Pos(nx, ny));
			Pos before = pathGraph[nx][ny];
			nx = before.x;
			ny = before.y;
		}
	}
	
	
	static int see(Pos pos) {
		int maxDir = 0;
		int maxCnt = Integer.MIN_VALUE;
		for(int i=0;i<4;i++) {
			int cnt = dirSee(i, pos);
			if(cnt>maxCnt) {
				maxDir = i;
				maxCnt = cnt;
			}
		}
		
		return dirSee(maxDir, pos); // sight 재할당
	}
	
	static int dirSee(int dir, Pos pos) {
		if(dir==0) return up(pos);
		else if(dir==1) return down(pos);
		else if(dir==2) return left(pos);
		return right(pos);
	}
	
	static int up(Pos pos) {
		sight = new int[51][51];
		
		int soldiersCnt = 0;
		int x = pos.x;
		int y = pos.y;
		
		for(int i = x-1;i>=0;i--) {
			int left = Math.max(0,  y+(i-x));
			int right = Math.min(y-(i-x), n-1);
			
			for(int j=left;j<=right;j++) {
				sight[i][j] = 1;
			}
		}
		
		boolean flag = false;
		for(int i = x-1;i>=0;i--) { // 직선 검증
			if(flag) {
				sight[i][y] = 0;
			}
			if(soldierGraph[i][y]>0) {
				flag = true;
			}
		}
		
		for(int i = x-1;i>=0;i--) {
			int left = Math.max(0, y+(i-x));
			int right = Math.min(y-(i-x), n-1);
			
			for(int j=left;j<y;j++) { // 좌측 검증
				if(soldierGraph[i][j]>0 || sight[i][j]==0) {
					if(i-1>=0) sight[i-1][j] = 0;
					if(i-1>=0&&j-1>=0) sight[i-1][j-1] = 0;
				}
			}
			
			for(int j=y+1;j<=right;j++) { // 우측 검증
				if(soldierGraph[i][j]>0 || sight[i][j]==0) {
					if(i-1>=0) sight[i-1][j] = 0;
					if(i-1>=0&&j+1<n) sight[i-1][j+1] = 0;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(sight[i][j]==1 && soldierGraph[i][j]>0) soldiersCnt+=soldierGraph[i][j];
			}
		}
		
		
		
		return soldiersCnt;
	}
	
	static int down(Pos pos) {
		sight = new int[51][51];
		
		int soldiersCnt = 0;
		int x = pos.x;
		int y = pos.y;
		
		for(int i = x+1;i<n;i++) {
			int left = Math.max(0, y-(i-x));
			int right = Math.min(y+(i-x), n-1);
			
			for(int j=left;j<=right;j++) {
				sight[i][j] = 1;
			}
		}
		
		boolean flag = false;
		for(int i=x+1;i<n;i++) { // 직선 검증
			if(flag) {
				sight[i][y] = 0;
			}
			if(soldierGraph[i][y]>0) {
				flag = true;
			}
		}
		
		for(int i = x+1;i<n;i++) {
			int left = Math.max(0,  y-(i-x));
			int right = Math.min(y+(i-x), n-1);
			
			for(int j=left;j<y;j++) { // 좌측 검증
				if(soldierGraph[i][j]>0 || sight[i][j]==0) {
					if(i+1<n) sight[i+1][j] = 0;
					if(i+1<n&&j-1>=0) sight[i+1][j-1] = 0;
				}
			}
			
			for(int j=y+1;j<=right;j++) { // 우측 검증
				if(soldierGraph[i][j]>0 || sight[i][j]==0) {
					if(i+1<n) sight[i+1][j] = 0;
					if(i+1<n&&j+1<n) sight[i+1][j+1] = 0;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(sight[i][j]==1 && soldierGraph[i][j]>0) soldiersCnt+=soldierGraph[i][j];
			}
		}
		
		
		return soldiersCnt;
	}
	
	static int left(Pos pos) {
		sight = new int[51][51];
		
		int soldiersCnt = 0;
		int x = pos.x;
		int y = pos.y;
		
		for(int i = y-1;i>=0;i--) {
			int up = Math.max(0,  x+(i-y));
			int down = Math.min(x-(i-y), n-1);
			
			for(int j=up;j<=down;j++) {
				sight[j][i] = 1;
			}
		}
		
		boolean flag = false;
		for(int i = y-1;i>=0;i--) { // 직선 검증
			if(flag) {
				sight[x][i] = 0;
			}
			if(soldierGraph[x][i]>0) {
				flag = true;
			}
		}
		
		for(int i = y-1;i>=0;i--) {
			int up = Math.max(0, x+(i-y));
			int down = Math.min(x-(i-y), n-1);
			
			for(int j=up;j<x;j++) { // 상단 검증
				if(soldierGraph[j][i]>0 || sight[j][i]==0) {
					if(i-1>=0) sight[j][i-1] = 0;
					if(j-1>=0&&i-1>=0) sight[j-1][i-1] = 0;
				}
			}
			
			for(int j=x+1;j<=down;j++) { // 하단 검증
				if(soldierGraph[j][i]>0 || sight[j][i]==0) {
					if(i-1>=0) sight[j][i-1] = 0;
					if(j+1<n&&i-1>=0) sight[j+1][i-1] = 0;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(sight[i][j]==1 && soldierGraph[i][j]>0) soldiersCnt+=soldierGraph[i][j];
			}
		}
		
		
		
		return soldiersCnt;
	}
	
	static int right(Pos pos) {
	sight = new int[51][51];
		
		int soldiersCnt = 0;
		int x = pos.x;
		int y = pos.y;
		
		for(int i = y+1;i<n;i++) {
			int up = Math.max(0,  x-(i-y));
			int down = Math.min(x+(i-y), n-1);
			
			for(int j=up;j<=down;j++) {
				sight[j][i] = 1;
			}
		}
		
		boolean flag = false;
		for(int i = y+1;i<n;i++) { // 직선 검증
			if(flag) {
				sight[x][i] = 0;
			}
			if(soldierGraph[x][i]>0) {
				flag = true;
			}
		}
		
		for(int i = y+1;i<n;i++) {
			int up = Math.max(0,  x-(i-y));
			int down = Math.min(x+(i-y), n-1);
			
			for(int j=up;j<x;j++) { // 상단 검증
				if(soldierGraph[j][i]>0 || sight[j][i]==0) {
					if(i+1<n) sight[j][i+1] = 0;
					if(j-1>=0&&i+1<n) sight[j-1][i+1] = 0;
				}
			}
			
			for(int j=x+1;j<=down;j++) { // 하단 검증
				if(soldierGraph[j][i]>0 || sight[j][i]==0) {
					if(i+1<n) sight[j][i+1] = 0;
					if(j+1<n&&i+1<n) sight[j+1][i+1] = 0;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(sight[i][j]==1 && soldierGraph[i][j]>0) soldiersCnt+=soldierGraph[i][j];
			}
		}
		
		
		
		return soldiersCnt;
	}
	
	static int moveSoldiers(Pos medusa) { // 이동거리 합 구해야함
		int distSum = 0;
		
		for(int i=0; i<soldiers.size(); i++) {
			Pos soldier = soldiers.get(i);
			
			if(sight[soldier.x][soldier.y]==1) continue; // 석화
			if(soldier.alive==false) continue; // 공격 후 사라진경우
			
			Pos next = findShortestPos(soldier, medusa, dx1, dy1);
			if(next.equals(soldier)) continue; // 첫번째에서 움직이지 않은 경우
			soldierGraph[soldier.x][soldier.y]--;
			soldier.x = next.x;
			soldier.y = next.y;
			soldierGraph[soldier.x][soldier.y]++;
			distSum++;
			Pos next2 = findShortestPos(soldier, medusa, dx2, dy2);
			if(!next2.equals(soldier)) {  // 두번째도 움직인 경우
				soldierGraph[soldier.x][soldier.y]--;
				soldier.x = next2.x;
				soldier.y = next2.y;
				soldierGraph[soldier.x][soldier.y]++;
				distSum++;
			}
		}
		
		
		return distSum;
	}
	
	static Pos findShortestPos(Pos cur, Pos medusa, int[] dx, int[] dy) {
		Pos shortestPos = new Pos(cur.x, cur.y);
		int minV = Math.abs(cur.x-medusa.x)+Math.abs(cur.y-medusa.y);
		for(int i=0;i<4;i++) {
			int nx = cur.x + dx[i];
			int ny = cur.y + dy[i];
			
			if(nx<0||nx>=n||ny<0||ny>=n) continue;
			if(sight[nx][ny]==1) continue; // 석화
			
			int dist = Math.abs(nx-medusa.x)+Math.abs(ny-medusa.y);
			if(dist<minV) {
				minV = dist;
				shortestPos = new Pos(nx, ny);
			}
		}
		
		return shortestPos;
	}
	
	
	static void attack(Pos medusa) {
		for(int i=0; i<soldiers.size(); i++) {
			Pos soldier = soldiers.get(i);
			if(soldier.alive==false) continue;
			if(soldier.x==medusa.x&&soldier.y==medusa.y) {
				soldier.setAlive(false);
				soldierGraph[soldier.x][soldier.y]--;
				attackSoldiersCnt++;
			}
		}
	}
	
}