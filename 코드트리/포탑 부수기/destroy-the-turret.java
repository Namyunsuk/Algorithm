import java.util.*;
import java.io.*;

public class Main{
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Potab{
		int x;
		int y;
		int power;
		int attackTurn=0;
		int attackedTurn = -1;
		
		Potab(int x, int y, int power){
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Potab[][] graph = new Potab[12][12];
	
	// 레이저 경로 
	static int[] dx1 = {0,1,0,-1};
	static int[] dy1 = {1,0,-1,0};
	
	// 포탄 경로
	static int[] dx2 = {1,-1,0,0,1,1,-1,-1};
	static int[] dy2 = {0,0,-1,1,1,-1,1,-1};
	
	static int n;
	static int m;
	static int k;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=m;j++) {
				graph[i][j] = new Potab(i,j,Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=1;i<=k;i++) {
			Potab attacker = findAttacker(i);
			Potab attacked = findAttacked(attacker);
			boolean isAttacked = leisure(i,attacker, attacked);
			if(!isAttacked) {
				bomb(i,attacker, attacked);
			}
			int remainPotabCnt = remainPotab();
			if(remainPotabCnt==1) break;
			powerUp(i);
		}
		
		int maxPower = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				Potab potab = graph[i][j];
				if(potab.power>maxPower) maxPower = potab.power;
			}
		}
		
		System.out.print(maxPower);
	}
	
	static int remainPotab() {
		int cnt = 0;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				Potab potab = graph[i][j];
				if(potab.power>0) cnt++;
			}
		}
		
		return cnt;
	}
	
	static void powerUp(int turn) {
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				Potab potab = graph[i][j];
				if(potab.attackTurn==turn) continue; // 공격 당한 경우
				if(potab.attackedTurn==turn) continue; // 공격 당한 경우
				if(potab.power<=0) continue; // 파괴 당한 경우
				potab.power+=1;
			}
		}
	}
	
	static void bomb(int turn, Potab attacker, Potab attacked) {
		// 주변 공격
		for(int i=0;i<8;i++) {
			int nx = oobX(attacked.x+dx2[i]); // 경계값 작업 
			int ny = oobY(attacked.y+dy2[i]);
			
			if(nx==attacker.x&&ny==attacker.y) continue; // 공격자는 제외
			if(graph[nx][ny].power<=0) continue; // 부서진 포탑인 경우 제외
			Potab potab = graph[nx][ny];
			potab.attackedTurn = turn;
			potab.power-=(attacker.power/2);
		}
		
		// 대상 공격
		attacked.attackedTurn = turn; 
		attacked.power-=attacker.power;
	}
	
	static boolean leisure(int turn, Potab attacker, Potab attacked) {
		LinkedList<Pos> q = new LinkedList<>();
		boolean[][] vis = new boolean[n+1][m+1];
		Pos[][] pathGraph = new Pos[n+1][m+1];
		List<Pos> path = new ArrayList<>();
		
		q.offer(new Pos(attacker.x, attacker.y));
		vis[attacker.x][attacker.y] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int i=0;i<4;i++) {
				int nx = oobX(cur.x+dx1[i]); // 경계값 작업 
				int ny = oobY(cur.y+dy1[i]);
				
				
				if(graph[nx][ny].power<=0) continue; // 부서진 포탑인 경우 제외
				if(vis[nx][ny]) continue;
				if(nx==attacked.x&&ny==attacked.y) { // 경로 발견한 경우
					if(cur.x==attacker.x&&cur.y==attacker.y) { // 중간 포탑 없이 바로 대상인 경우
						attacked.attackedTurn = turn; // 공격대상 공격 
						attacked.power-=attacker.power;
						return true;
					}
					path.add(new Pos(cur.x, cur.y)); // 공격당하는 포탑 이전 포탑을 경로에 추가
					Pos before = pathGraph[cur.x][cur.y]; // 어디서 왔는지에 대한 정보 
					while(true) {
						
						if(before.x==attacker.x&&before.y==attacker.y) break; // 공격자에서 온 경우 종료
						path.add(new Pos(before.x, before.y));
						before = pathGraph[before.x][before.y];
					}
					
					for(int j=0;j<path.size();j++) { // 경로에 있는 포탑들 공격 
						Pos pos = path.get(j);
						Potab potab = graph[pos.x][pos.y];
						potab.attackedTurn = turn;
						potab.power-=(attacker.power/2);
					}
					
					attacked.attackedTurn = turn; // 공격대상 공격 
					attacked.power-=attacker.power;
					
					return true;
				}
				q.offer(new Pos(nx, ny));
				pathGraph[nx][ny] = new Pos(cur.x, cur.y);
				vis[nx][ny] = true;
			}
		}
		
		
		return false;
	}
	
	static int oobY(int y) {
		if(y==0) return m;
		else if(y==m+1) return 1;
		else return y;
	}
	
	static int oobX(int x) {
		if(x==0) return n;
		else if(x==n+1) return 1;
		else return x;
	}
	
	static Potab findAttacked(Potab attacker) {
		Potab attacked = new Potab(11,11,0);
		
		// 가장 강한 포탑 결정
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				if(i==attacker.x&&j==attacker.y) continue; // 공격자 제외
				Potab potab = graph[i][j];
				if(potab.power<=0) continue; // 공격력 0이하는 제외
				if(potab.power>attacked.power) attacked = potab; // 공력력이 큰 경우 
				else if(potab.power==attacked.power) {
					if(potab.attackTurn<attacked.attackTurn) attacked = potab; // 공격한지 오래된 경우
					else if(potab.attackTurn==attacked.attackTurn) { 
						if(potab.x+potab.y<attacked.x+attacked.y) attacked = potab; // 행+열이 작은 경우
						else if(potab.x+potab.y==attacked.x+attacked.y) {
							if(potab.y<attacked.y) attacked = potab; // 열이 작은 경우
						}
					}
				}
			}
		}
		
		return attacked;
	}
	
	
	static Potab findAttacker(int turn) {
		Potab attacker = new Potab(0,0,10000);
		
		// 가장 약한 포탑 결정
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				Potab potab = graph[i][j];
				if(potab.power<=0) continue; // 공격력 0이하는 제외
				if(potab.power<attacker.power) attacker = potab; // 공력력이 작은 경우 
				else if(potab.power==attacker.power) {
					if(potab.attackTurn>attacker.attackTurn) attacker = potab; // 최근에 공격한 경우
					else if(potab.attackTurn==attacker.attackTurn) { 
						if(potab.x+potab.y>attacker.x+attacker.y) attacker = potab; // 행+열이 큰 경우
						else if(potab.x+potab.y==attacker.x+attacker.y) {
							if(potab.y>attacker.y) attacker = potab; // 열이 작은 경우
						}
					}
				}
			}
		}
		
		attacker.power+=(n+m);
		attacker.attackTurn = turn;
		
		return attacker;
	}
}