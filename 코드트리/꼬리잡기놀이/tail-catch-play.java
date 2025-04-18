import java.io.*;
import java.util.*;

public class Main{
	static class Pos{
		int x;
		int y;
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static class Person{
		int x;
		int y;
		
		Person(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	
	static int[][] graph = new int[22][22];
	static List<Person>[] teams = new ArrayList[7];
	static int point = 0;
	
	static int n;
	static int m;
	static int k;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=1;i<=m;i++) {
			teams[i] = new ArrayList<>();
		}
		int teamIndex = 1;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j]==1) { // 머리 발견
					teams[teamIndex].add(new Person(i,j));
					teamIndex++;
				}
			}
		}
		
		// 팀 찾기
		for(int i=1;i<=m;i++) {
			findTeam(i);	
		}
		
		// 라운드 시작(1부터 시작)
		for(int i=1;i<=k;i++) {
			for(int j=1;j<=m;j++) {
				moveTeam(j);
			}
			moveBall(i);
		}
		
		System.out.print(point);
	}
	
	static void moveBall(int k) {
		Pos start = getStartPos(k);
		int nx = start.x;
		int ny = start.y;
		int dir = getDir(k);
		
		while(true) {
			// 공이 끝까지 간 경우 
			if(nx<0||nx>=n||ny<0||ny>=n) break;
			int scoredTeam = getScoredTeam(nx, ny);
			if(scoredTeam!=0) { // 득점한 팀이 있는 경우
				int order = getOrder(scoredTeam, nx, ny); // 해당 사람의 순서 
				int score = order*order; // 점수 
				point += score; // 획득 	
				
				// 팀 방향 반대로
				Collections.reverse(teams[scoredTeam]);
				
				return;
			}
			
			// 공 한칸 이동
			nx = nx+dx[dir];
			ny = ny+dy[dir];
		}
	}
	
	static int getOrder(int num, int x, int y) {
		for(int i=0;i<teams[num].size();i++) {
			Person person = teams[num].get(i);
			if(person.x==x&&person.y==y) return i+1;
		}
		return -1;
	}
	
	static int getScoredTeam(int x, int y) {
		for(int i=1;i<=m;i++) {
			if(isPersonExist(i,x,y)) return i;	
		}
		
		return 0; // 아무 팀도 없는 경우 
	}
	
	static int getDir(int k) {
		int round = k%(4*n);
		
		if(1<=round&&round<=n) {
			return 0;
		}else if(n+1<=round && round<=2*n) {
			return 1;
		}else if(2*n+1<=round && round<=3*n) {
			return 2;
		}
		return 3;
	}
	
	static Pos getStartPos(int k) {
		int round = k%(4*n);
		int x = 0;
		int y = 0;
		
		if(1<=round&&round<=n) {
			x=round-1;
			y=0;
		}else if(n+1<=round && round<=2*n) {
			x=n-1;
			y=round-(n+1);
		}else if(2*n+1<=round && round<=3*n) {
			x=3*n-round;
			y=n-1;
		}else if(3*n+1<=round && round<=4*n) {
			x=0;
			y=4*n-round;
		}
		
		
		return new Pos(x, y);
	}
	
	static void moveTeam(int num) {
		Person head = teams[num].get(0);
		boolean isMoved = false;
		int nextX = head.x;
		int nextY = head.y;
		
		for(int i=0;i<4;i++) {
			int nx = head.x + dx[i];
			int ny = head.y + dy[i];
			if(nx<0||nx>=n||ny<0||ny>=n) continue;
			if(graph[nx][ny]!=0 && !isPersonExist(num, nx, ny)) { // 길이고 사람이 존재하지 않는 경우 이동
				head.x = nx;
				head.y = ny;
				isMoved = true;
				break;
			}
		}
		
		if(!isMoved) { // 빈 길이 없어, 3번 위치로 이동
			Person tail = teams[num].get(teams[num].size()-1);
			int nx = tail.x;
			int ny = tail.y;
			head.x = nx;
			head.y = ny;
		}
		
		for(int i=1;i<teams[num].size();i++) {
			Person person = teams[num].get(i);
			
			// 내 위치 기억
			int tmpX = person.x;
			int tmpY = person.y;
			
			// 앞 사람의 이전 위치로 이동 
			person.x = nextX;
			person.y = nextY;
			
			// 다음 사람이 이동할 위치 업데이트
			nextX = tmpX;
			nextY = tmpY;
		}
		
	}
	
	static boolean isPersonExist(int num, int x, int y) {
		for(int i=0;i<teams[num].size();i++) {
			Person person = teams[num].get(i);
			if(person.x==x && person.y==y) return true;
		}
		
		return false;
	}
	
	static void findTeam(int num) {
		LinkedList<Person> q = new LinkedList<>();
		boolean[][] vis = new boolean[n+1][n+1];
		Person head = teams[num].get(0);
		q.offer(head); // 머리 큐에 삽입 
		vis[head.x][head.y] = true;
		Person tail = new Person(0,0);
		boolean findTail = false;
		
		while(!q.isEmpty()) {
			Person cur = q.poll();
			
			for(int i=0;i<4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx<0||nx>=n||ny<0||ny>=n) continue;
				if(vis[nx][ny]) continue;
				if(graph[nx][ny]==2) { // 팀원 발견한 경우 
					Person person = new Person(nx, ny);
					teams[num].add(person);
					q.offer(person);
					vis[person.x][person.y] = true;
					
					findTail = false;
					break;
				}
				if(graph[nx][ny]==3) { // 꼬리 발견한 경우 
					tail = new Person(nx, ny);
					findTail = true;
				}
			}
			if(findTail) {
				teams[num].add(tail);
				return;
			}
		}
		
	}
}