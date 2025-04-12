import java.util.*;
import java.io.*;

public class Main{
	static final int RUDOLPH = -1;
	
	static class Santa{
		int x;
		int y;
		int score = 0;
		int stun = 0;
		boolean out = false;
			
		Santa(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	// 루돌프 움직임
	static int[] dx1 = {-1,0,1,0,1,1,-1,-1};
	static int[] dy1 = {0,1,0,-1,1,-1,1,-1};
	
	// 산타 움직임
	static int[] dx2 = {-1,0,1,0};
	static int[] dy2 = {0,1,0,-1};
	
	static int[][] santaGraph = new int[52][52];
	static int[][] rudolphGraph = new int[52][52];
	static Santa[] santas = new Santa[32];
	
	// 루돌프 위치
	static int rX;
	static int rY;
	
	static int n;
	static int m;
	static int p;
	static int c;
	static int d;
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		rX = Integer.parseInt(st.nextToken());
		rY = Integer.parseInt(st.nextToken());
		rudolphGraph[rX][rY] = RUDOLPH;
		
		for(int i=0; i<p;i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			santaGraph[sr][sc] = p1;
			santas[p1] = new Santa(sr, sc);
		}
		
		
		for(int i=0;i<m;i++) {
			if(noSanta()) break; // 산타 전부 탈락한 경우 종료
			int nearestSanta = findNearestSanta(rX, rY);
			moveRudolph(nearestSanta);
			
			for(int j=1;j<=p;j++) { // 산타 이동
				moveSanta(j);
			}
			
			releaseStun();
			plusTurnScore();
		}
		
		printSantaScore();
	}

	private static void printSantaGraph() {
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(rudolphGraph[i][j]==RUDOLPH) {
					System.out.printf("%d ", rudolphGraph[i][j]);
					continue;
				}
				System.out.printf("%d ", santaGraph[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static void printSantaScore() {
		for(int i=1;i<=p;i++) {
			Santa santa = santas[i];
			System.out.printf("%d ", santa.score);
		}
		System.out.println();
		System.out.println();
	}
	
	static void plusTurnScore() {
		for(int i=1;i<=p;i++) {
			Santa santa = santas[i];
			if(santa.out== true) continue; // 탈락 산타는 패스
			santa.score+=1;
		}
	}
	
	static boolean noSanta() {
		for(int i=1;i<=p;i++) {
			Santa santa = santas[i];
			if(santa.out== false) return false;
		}
		
		return true;
	}
	
	static void interaction(int movedSantaIndex,int curSantaIndex, int dx, int dy) {
		Santa curSanta = santas[curSantaIndex]; // 원래 있던 산타 
		Santa movedSanta = santas[movedSantaIndex]; // 이번에 밀린 산타 
		
		// 밀린 산타는 우선 이동
		santaGraph[movedSanta.x][movedSanta.y] = movedSantaIndex;
		
		// 새로 이동할 위치 
		int nx = curSanta.x + dx;
		int ny = curSanta.y + dy;
		
		curSanta.x = nx;
		curSanta.y = ny;
		
		if(nx<1||nx>n||ny<1||ny>n) { // 움직인 산타가 탈락한 경우 
			curSanta.out = true;
			return;
		}
		else if(santaGraph[nx][ny]>0) { // 밀린 위치에 또 산타가 있을 경우
			interaction(curSantaIndex, santaGraph[nx][ny], dx, dy);
		}else if(santaGraph[nx][ny]==0) { // 빈 자리일 경우 
			santaGraph[curSanta.x][curSanta.y] = curSantaIndex;
		}
	}
	
	
	static void releaseStun() {
		for(int i=1;i<=p;i++) {
			Santa santa = santas[i];
			if(santa.stun>0) santa.stun-=1;
		}
	}
	
	static void stun(int santaIndex) {
		Santa santa = santas[santaIndex];
		santa.stun = 2; // 2턴동안 기절상태 
	}
	
	static void santaToRudolphCrush(int santaIndex, int dx, int dy) {
		Santa santa = santas[santaIndex];
		santa.score+=d; // 점수 획득 
		
		int newX = santa.x+(-dx*d);
		int newY = santa.y+(-dy*d);
		
		santaGraph[santa.x][santa.y] = 0;
		santa.x = newX;
		santa.y = newY;
		
		if(newX<1||newX>n||newY<1||newY>n) { // 산타 탈락한 경우
			santa.out = true;
		}else {
			if(santaGraph[santa.x][santa.y]!=0) { // 다른 산타가 있는 경우
				// 연쇄작용
				interaction(santaIndex, santaGraph[santa.x][santa.y], -dx, -dy);
			}else { // 빈칸인경우 
				santaGraph[santa.x][santa.y] = santaIndex;	
			}
		}
	}
	
	static void rudolphToSantaCrush(int santaIndex, int dx, int dy) {
		Santa santa = santas[santaIndex];
		santa.score+=c; // 점수 획득 
		
		int newX = santa.x+(dx*c);
		int newY = santa.y+(dy*c);
		
		santaGraph[santa.x][santa.y] = 0;
		santa.x = newX;
		santa.y = newY;
		
		if(newX<1||newX>n||newY<1||newY>n) { // 산타 탈락한 경우
			santa.out = true;
		}else {
			if(santaGraph[santa.x][santa.y]!=0) { // 다른 산타가 있는 경우
				// 연쇄작용
				interaction(santaIndex, santaGraph[santa.x][santa.y], dx, dy);
			}else { // 빈칸인경우 
				santaGraph[santa.x][santa.y] = santaIndex;	
			}
		}
	}
	
	static void moveSanta(int santaIndex) {
		Santa santa = santas[santaIndex];
		if(santa.out) return;
		if(santa.stun>0) return;
		int curDist = calDistance(santa.x, santa.y, rX, rY);
		int minX=0;
		int minY=0;
		int dx=0;
		int dy=0;
		boolean isMoved = false;
		
		for(int i=0;i<4;i++) {
			int nx = santa.x + dx2[i];
			int ny = santa.y + dy2[i];
			int dist = calDistance(nx, ny, rX, rY);
			if(nx<1||nx>n||ny<1||ny>n) continue; // 게임판 밖 
			if(santaGraph[nx][ny]!=0) continue; // 다른 산타 
			if(dist>=curDist) continue; // 거리가 가까워지지 않은 경우
			isMoved = true;
			curDist = dist;
			minX = nx;
			minY = ny;
			dx = dx2[i];
			dy = dy2[i];
		}
		
		if(!isMoved) return;
		santaGraph[santa.x][santa.y] = 0;
		santa.x = minX;
		santa.y = minY;
		santaGraph[santa.x][santa.y] = santaIndex;
		
		// 충돌 + 기절
		if(rudolphGraph[santa.x][santa.y]==RUDOLPH) { // 이동한 곳에 로돌프가 있는 경우
			santaToRudolphCrush(santaIndex, dx, dy);
			stun(santaIndex);
		}
	}
	
	static void moveRudolph(int santaIndex) {
		int minDist = Integer.MAX_VALUE;
		Santa santa = santas[santaIndex];
		int minX = 0;
		int minY = 0;
		int dx = 0;
		int dy = 0;
		
		
		for(int i=0;i<8;i++) {
			int nx = rX+ dx1[i];
			int ny = rY+ dy1[i];
			
			int dist = calDistance(nx,ny,santa.x, santa.y);
			if(dist<minDist) {
				minDist = dist;
				minX = nx;
				minY = ny;
				dx = dx1[i];
				dy = dy1[i];
			}
		}
		
		rudolphGraph[rX][rY] = 0;
		rX = minX;
		rY = minY;
		rudolphGraph[rX][rY] = RUDOLPH;
		
		// 충돌 코드 + 기절
		if(santaGraph[rX][rY]>0) { // 이동한 곳에 산타가 있는 경우
			rudolphToSantaCrush(santaIndex, dx, dy);
			stun(santaIndex);
		}
	}
	
	static int findNearestSanta(int x, int y) { // 최적화 가능
		int minDist = Integer.MAX_VALUE;
		int santaIndex = -1;
		
		for(int i=n; i>=1; i--) {
			for(int j=n;j>=1;j--) {
				if(santaGraph[i][j]==0) continue;
				int dist = calDistance(x,y,i,j); // 산타까지와의 거리
				if(dist<minDist) {
					minDist = dist;
					santaIndex = santaGraph[i][j];
				}
			}
		}
		
		
		return santaIndex;
	}
	
	
	static int calDistance(int x1, int y1, int x2, int y2) {
		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
	}
}