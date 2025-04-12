import java.io.*;
import java.util.*;

public class Main{
	static class Player{
		int x;
		int y;
		int power;
		int dir;
		int gun = 0;
		int point = 0;
		
		Player(int x, int y, int dir, int power){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static List<Integer>[][] graph;
	static Player[] players;
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int n;
	static int m;
	static int k;
	
	public static void main(String[] args) throws IOException{
		init();

		for(int i=1;i<=k;i++) {
			for(int j=1;j<=m;j++) {
				Player player = players[j];
				movePlayer(player);
				fight(j, player.x, player.y);
			}
		}
		
		for(int i=1;i<=m;i++) {
			System.out.printf("%d ",players[i].point);
		}
	}
	
	static void fight(int num, int x, int y) {
		Player player = players[num];
		int anotherNum = isPlayerExist(num, x, y);
		// 해당 칸에 플레이어 없는 경우
		if(anotherNum==-1) {
			getGun(player); // 총 줍고 마무리 
			return;  
		}
		
		Player another = players[anotherNum];
		
		if(player.power+player.gun > another.power + another.gun) {
			// 점수 획득 
			player.point+=((player.power+player.gun) - (another.power + another.gun));
			
			// 패자 총 내려놓음
			if(another.gun!=0) { // 패자가 총이 있는 경우 
				graph[x][y].add(another.gun);
				another.gun = 0;
			}
			
			// 패자 이동 후 총기 주움 
			moveLoser(anotherNum);
			
			// 승자 총기 주움
			getGun(player);
			
		}
		
		else if(another.power + another.gun > player.power + player.gun) {
			// 점수 획득
			another.point+=((another.power + another.gun )- (player.power + player.gun));
			
			// 패자 총 내려놓음
			if(player.gun!=0) { // 패자가 총이 있는 경우 
				graph[x][y].add(player.gun);
				player.gun = 0;
			}
			
			// 패자 이동 후 총기 주움 
			moveLoser(num);
			
			// 승자 총기 주움
			getGun(another);
		}
		
		
		else { // 같은 경우 (점수획득X)
			if(player.power > another.power) {
				// 패자 총 내려놓음
				if(another.gun!=0) { // 패자가 총이 있는 경우 
					graph[x][y].add(another.gun);
					another.gun = 0;
				}
				
				// 패자 이동 후 총기 주움 
				moveLoser(anotherNum);
				
				// 승자 총기 주움
				getGun(player);
			}else {
				// 패자 총 내려놓음
				if(player.gun!=0) { // 패자가 총이 있는 경우 
					graph[x][y].add(player.gun);
					player.gun = 0;
				}
				
				// 패자 이동 후 총기 주움 
				moveLoser(num);
				
				// 승자 총기 주움
				getGun(another);
			}
		}
	}
	
	static void moveLoser(int num) {
		Player player = players[num];
		int dir = player.dir;
		int nx = player.x + dx[dir];
		int ny = player.y + dy[dir];
		int anotherNum = isPlayerExist(num, nx, ny);
		while((nx<1||nx>n||ny<1||ny>n) || anotherNum!=-1) { // 해당 방향이 범위 밖이거나 다른 사람 있는 경우
			dir = rotateToRight(dir);
			nx = player.x + dx[dir];
			ny = player.y + dy[dir];
			anotherNum = isPlayerExist(num, nx, ny);
		}
		player.x = nx;
		player.y = ny;
		player.dir = dir;
		
		getGun(players[num]);
	}
	
	
	static int rotateToRight(int dir) {
		return (dir+1)%4;
	}
	
	static int isPlayerExist(int num, int x, int y) {
		for(int i=1;i<=m;i++) {
			if(i==num) continue;
			Player player = players[i];
			if(player.x==x && player.y == y) return i;
		}
		
		return -1;
	}
	
	static void getGun(Player player) {
		int x = player.x;
		int y = player.y;
		if(graph[x][y].isEmpty()) return; // 총이 없는 경우
		Collections.sort(graph[x][y]);
		Integer maxPowerGun = graph[x][y].get(graph[x][y].size()-1);
		
		if(player.gun==0) { // 총 소지 X
			player.gun = (int)maxPowerGun;
			graph[x][y].remove(maxPowerGun); // 주운 총 바닥에서 제거
		}
		else if((int)maxPowerGun > player.gun) { // 총 소지 O + 바닥에 놓인 총이 더 쎈 경우
			graph[x][y].add((Integer)player.gun); // 총 내려 놓음
			graph[x][y].remove(maxPowerGun); // 교체한 총 바닥에서 제거
			player.gun = (int)maxPowerGun; // 총 교체 
		}
	}
	
	static void movePlayer(Player player) {
		int dirX = dx[player.dir];
		int dirY = dy[player.dir];
		int nx = player.x + dirX;
		int ny = player.y + dirY;
		
		if(nx<1||nx>n||ny<1||ny>n) {
			nx = player.x - dirX;
			ny = player.y - dirY;
			player.dir = (player.dir+2)%4;
		}
		
		player.x = nx;
		player.y = ny;
	}
	
	static void init() throws IOException {
		graph = new ArrayList[22][22];
		players = new Player[32];
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				graph[i][j] = new ArrayList<>();
				graph[i][j].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			players[i] = new Player(x,y,d,s);
		}
		
	}
}