import java.io.*;
import java.util.*;


public class Main{
	static class Soldier{
		int x;
		int y;
		int h;
		int w;
		int life;
		int damage=0;
		
		Soldier(int x, int y, int h, int w, int life){
			this.x = x;
			this.y = y;
			this.h = h;
			this.w = w;
			this.life = life;
		}
		
		boolean isAlive() {
			return life>damage;
		}
	}
	
	static final int BLANK = 0;
	static final int BOMB = 1;
	static final int WALL = 2;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] graph = new int[42][42];
	static int[][] soldierGraph = new int[42][42];
	static Soldier[] soldiers = new Soldier[32];
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int l;
	static int n;
	static int q;
	
	static boolean canMoveFlag = true;
	static List<Integer> movedSoldiers = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=l;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=l;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			soldiers[i] = new Soldier(r,c,h,w,k);
		}
		
		initSoldiers();
		
		for(int i=0;i<q;i++) {
			canMoveFlag = true;
			movedSoldiers.clear();
			st = new StringTokenizer(br.readLine());
			int soldierIndex = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int dirX = dx[dir];
			int dirY = dy[dir];
			if(!soldiers[soldierIndex].isAlive()) continue;
			validateMove(soldierIndex, dirX, dirY);
			if(canMoveFlag) {
				moveSoldiers(dirX, dirY);
				getDamage(soldierIndex);
			}
		}
		
		int sum = 0;
		
		for(int i=1;i<=n;i++) {
			Soldier soldier = soldiers[i];
			if(soldier.isAlive()) {
				sum+=soldier.damage;
			}
		}
		
		System.out.print(sum);
	}
	
	static void getDamage(int soldierIndex) {
		for(int i=0;i<movedSoldiers.size();i++) {
			int index= (int)movedSoldiers.get(i);
			if(index==soldierIndex) continue; // 명령받은 기사는 제외
			Soldier soldier = soldiers[index];
			int damage = findBomb(index);
			soldier.damage += damage;
			if(!soldier.isAlive()) {
				removeSoldier(index);
			}
		}
	}
	
	static void removeSoldier(int soldierIndex) {
		Soldier soldier = soldiers[soldierIndex];
		int x = soldier.x;
		int y = soldier.y;
		int h = soldier.h;
		int w = soldier.w;
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				soldierGraph[x+i][y+j] = BLANK;
			}
		}
	}
	
	static int findBomb(int soldierIndex) {
		int cnt = 0;
		Soldier soldier = soldiers[soldierIndex];
		int x = soldier.x;
		int y = soldier.y;
		int h = soldier.h;
		int w = soldier.w;
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(graph[x+i][y+j]==BOMB) cnt++;
			}
		}
		return cnt;
	}
	
	static void moveSoldiers(int dx, int dy) { // canMoveFlag가 false면 실행 X
		for(int i=0;i<movedSoldiers.size();i++) {
			int soldierIndex = (int)movedSoldiers.get(i);
			Soldier soldier = soldiers[soldierIndex];
			soldier.x+=dx;
			soldier.y+=dy;
		}
		
		clearSoldierGraph();
		initSoldiers(); // soldierGraph 다시 그림
	}
	
	
	static void validateMove(int soldierIndex, int dx, int dy) {
		if(!movedSoldiers.contains((Integer)soldierIndex))movedSoldiers.add((Integer)soldierIndex);
		
		Soldier soldier = soldiers[soldierIndex];
		int x = soldier.x;
		int y = soldier.y;
		int h = soldier.h;
		int w = soldier.w;
		
		if(!canMove(soldierIndex, dx, dy)) {
			canMoveFlag = false;
			return;
		}
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				int nx = x+i+dx;
				int ny = y+j+dy;
				if(soldierGraph[nx][ny]==BLANK) continue; // 빈공간인 경우
				if(soldierGraph[nx][ny]!=soldierIndex) {
					validateMove(soldierGraph[nx][ny], dx, dy); // 다른 기사인 경우
				}
			}
		}
	}
	
	static boolean canMove(int soldierIndex, int dx, int dy) {
		Soldier soldier = soldiers[soldierIndex];
		int x = soldier.x;
		int y = soldier.y;
		int h = soldier.h;
		int w = soldier.w;
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				int nx = x+i+dx;
				int ny = y+j+dy;
				if(nx<1||nx>l||ny<1||ny>l) return false; // 경계 밖 
				if(graph[nx][ny]==WALL) return false; // 벽이 있는 경우
			}
		}
		
		return true;
	}
	
	static void initSoldiers() {
		for(int i=1;i<=n;i++) {
			Soldier soldier = soldiers[i];
			if(!soldier.isAlive()) continue; // 사라진 기사의 경우
			initSoldierGraph(i,soldier.x, soldier.y, soldier.h, soldier.w);
		}
	}
	
	static void initSoldierGraph(int soldierIndex, int x, int y, int h, int w) {
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				soldierGraph[x+i][y+j] = soldierIndex;
			}
		}
	}
	
	static void clearSoldierGraph() {
		for(int i=1;i<=l;i++) {
			for(int j=1;j<=l;j++) {
				soldierGraph[i][j] = BLANK;
			}
		}
	}
}