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
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int[][] graph = new int[52][101];
	static int[] height = new int[101];
	
	static int n;
	static int k;
	
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			graph[0][i] = Integer.parseInt(st.nextToken());
			height[i] = 1;
		}
		
		int t = 1;
		while(true) {
			turn();
			if(isFinished()== true) break;
			t++;
		}
		
		System.out.print(t);
	}
	
	static void turn() {
		findMinFishAndAdd();
		while(stackFirst());
		balancing();
		makeStraight();
		stackSecond();
		balancing();
		makeStraight();
	}
	
	static boolean isFinished() {
		int minV = Integer.MAX_VALUE;
		int maxV = Integer.MIN_VALUE;
		
		for(int i=0;i<n;i++) {
			int v = graph[0][i];
			
			if(v<minV) minV = v;
			if(v>maxV) maxV = v;
		}
		
		return (maxV - minV) <= k;
	}
	
	static void stackSecond() {
		for(int j=0;j<n/2;j++) {
			graph[1][n-1-j] = graph[0][j];
			graph[0][j] = 0;
			height[j]--;
			height[n-1-j]++;
		}
		int startJ = 3*n/4;
		for(int j=(3*n/4)-1;j>=n/2;j--,startJ++) {
			graph[3][startJ] = graph[0][j];
			graph[2][startJ] = graph[1][j];
			
			graph[0][j] = 0;
			graph[1][j] = 0;
			height[j]-=2;
			height[startJ]+=2;
		}
	}
	
	static void makeStraight() {
		int startJ = 0;
		int maxI = 0;
		for(int i=0;i<n;i++) {
			if(height[i]>=2) {
				startJ = i;
				maxI = height[i];
				break;
			}
		}
		
		int newJ = 0;
		
		for(int j=startJ;j<n;j++) {
			if(height[j]<2) break;
			for(int i=0;i<maxI;i++, newJ++) {
				graph[0][newJ] = graph[i][j];
				graph[i][j] = 0;
				height[j]--;
				height[newJ]++;
			}
		}
	}
	
	
	static void balancing() {
		int maxHeight = Integer.MIN_VALUE;
		
		for(int i=0;i<n;i++) {
			if(height[i]!=0) {
				maxHeight = height[i];
				break;
			}
		}
		
		boolean[][] vis = new boolean[maxHeight][n+1];
		int[][] save = new int[52][101];
		
		for(int i=0;i<maxHeight;i++) {
			for(int j=0;j<n;j++) {
				vis[i][j] = true;
				if(graph[i][j]==0) continue;
				Pos pos = new Pos(i,j);
				int curFish = graph[i][j];
				for(int d = 0; d<4 ; d++) {
					int nx = pos.x + dx[d];
					int ny = pos.y + dy[d];
					if(nx<0||nx>=maxHeight|| ny<0||ny>n) continue;
					if(vis[nx][ny]) continue; // 이미 방문한 곳인 경우
					if(graph[nx][ny]==0) continue;
					int nearFish = graph[nx][ny];
					int lostFish = calLostFish(curFish,nearFish);
					save[i][j]-=lostFish;
					save[nx][ny]+=lostFish;
				}
			}
		}
		
		for(int i=0;i<maxHeight;i++) {
			for(int j=0;j<n;j++) {
				graph[i][j]+=save[i][j];
			}
		}
	}
	
	static int calLostFish(int n1, int n2) {
		// 내가 큰 숫자인 경우 -> 양수로 나옴 
		// 내가 작은 숫자인 경우 -> 음수로 나옴
		int v = Math.abs(n1-n2) / 5;
		if(n1>n2) return v;
		return -v;
	}
	
	static boolean stackFirst() {
		if(isFirstTry()) { // 처음 쌓는 경우 
			graph[1][1] = graph[0][0];
			graph[0][0] = 0;
			// 높이 업데이트 
			height[0]--;
			height[1]++;
			return true;
		}
		
		int firstIndex = 0;
		int h = 0;
		for(int i=n-1;i>=0;i--) {
			if(height[i]>=2) {
				firstIndex = i;
				h = height[i];
				break;
			}
		}
		
		// 더이상 못 쌓는 경우
		if(firstIndex+h>=n) return false;
		
		int startheight = 1;
		for(int j=firstIndex;j>=0;j--,startheight++) {
			int startIndex = firstIndex+1;
			if(height[j]==0) break;
			for(int i=0;i<h;i++, startIndex++) {
				graph[startheight][startIndex] = graph[i][j];
				graph[i][j] = 0;
				height[startIndex]++;
				height[j]--;
			}
		}
		
		return true;
	}
	
	static boolean isFirstTry() {
		for(int i=0;i<n;i++) {
			if(height[i]>=2) return false;
		}
		return true;
	}

	
	static void findMinFishAndAdd() {
		List<Pos> minFishes = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		
		for(int i=0;i<n;i++) {
			int fish = graph[0][i];
			if(fish<min) {
				min = fish;
				minFishes.clear();
				minFishes.add(new Pos(0,i));
			}else if(fish == min) {
				minFishes.add(new Pos(0,i));
			}
		}
		
		for(int i=0;i<minFishes.size();i++) {
			Pos pos = minFishes.get(i);
			graph[pos.x][pos.y]+=1;
		}
	}
}
