import java.io.*;
import java.util.*;

public class Main{
	static class Pos{
		int x;
		int y;
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
	
	static class Person{
		int num;
		int x;
		int y;
		boolean isArrived = false;
		
		Person(int num){
			this.num = num;
		}
	}
	static final int BASECAMP = 1;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[][] graph = new int[17][17];
	static boolean[][] canGo = new boolean[17][17];
	static Person[] people = new Person[32];
	static Pos[] stores = new Pos[32];
	
	static LinkedList<Pos> q = new LinkedList<>();
	static boolean[][] vis;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	
	static int n;
	static int m;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			people[i] = new Person(i);
			stores[i] = new Pos(x,y);
		}
		
		initCango();
		
		int t = 0;
		while(!checkAllArrived()) {
			t+=1;
			// step1: 편의점 이동
			if(t>=2) {
				int index = Math.min(m, t-1);
				for(int i=1;i<=index;i++) {
					moveStore(i);
				}
			}
			
			updateCango(t);
			
			// step3: 베이스캠프 이동
			if(t<=m) {
				moveBaseCamp(t);
			}
			
			// 막힘 여부 업데이트
			updateCango(t);
			
		}
		
		System.out.print(t);
	}
	
	static boolean checkAllArrived() {
		
		for(int i=1;i<=m;i++) {
			Person person = people[i];
			if(person.isArrived==false) return false;
		}
		return true;
	}
	
	static void updateCango(int t) {
		int maxIndex = Math.min(m, t);
		for(int i=1;i<=maxIndex;i++) {
			Person person = people[i];
			Pos store = stores[i];
			// 목표 편의점 도착한 경우 더이상 못가게 막음
			if(person.x==store.x && person.y==store.y) {
				canGo[person.x][person.y] = false;
				continue;
			}
			// 이제 막 베이스캠프로 간 경우 해당 베이스캠프 더이상 못가게 막음 
			if(i==t) {
				canGo[person.x][person.y] = false;
			}
		}
	}
	
	static void moveBaseCamp(int num) {
		Person person = people[num];
		Pos store = stores[person.num];
		int minDist = Integer.MAX_VALUE;
		int mx = 0;
		int my = 0;
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(graph[i][j]!=BASECAMP) continue; // 베이스캠프 아닌 경우 제외
				if(!canGo[i][j]) continue; // 누가 이미 간 경우 제외
				int dist = findMinDist(i,j,store.x, store.y);
				if(dist==-1) continue;
				if(dist<minDist) {
					minDist = dist;
					mx = i;
					my = j;
				}
			}
		}
		
		person.x = mx;
		person.y = my;
	}
	
	static void moveStore(int num) {
		Person person = people[num];
		if(person.isArrived) return;
		Pos store = stores[person.num];
		int minDist = Integer.MAX_VALUE;
		int mx = 0;
		int my = 0;
		
		for(int i=0;i<4;i++) {
			int nx = person.x + dx[i];
			int ny = person.y + dy[i];
			
			if(nx<1||nx>n||ny<1||ny>n) continue;
			if(!canGo[nx][ny]) continue; // 막힌 곳인 경우
			if(nx==store.x&&ny==store.y) { // 편의점 도착
				person.x = nx;
				person.y = ny;
				person.isArrived = true;
				return;
			}
			int dist = findMinDist(nx, ny, store.x, store.y);
			if(dist==-1) continue; // 해당 방향에서는 가능한 길이 없는 경우 
			if(dist<minDist) {
				minDist = dist;
				mx = nx;
				my = ny;
			}
		}
		
		person.x = mx;
		person.y = my;
	}
	
	static int findMinDist(int x, int y, int desX, int desY) {
		q.clear();
		vis = new boolean[17][17];
		
		q.offer(new Pos(x, y));
		vis[x][y] = true;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			for(int i=0;i<4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx<1||nx>n||ny<1||ny>n) continue;
				if(!canGo[nx][ny]) continue; // 막힌 곳인 경우
				if(vis[nx][ny]) continue;
				if(nx==desX&&ny==desY) { // 도착 한 경우 
					return cur.dist + 1;
				}
				q.offer(new Pos(nx, ny, cur.dist+1));
				vis[nx][ny] = true;
			}
		}
		
		
		return -1;
	}
	
	static void initCango() {
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				canGo[i][j] = true;
			}
		}
	}
}
