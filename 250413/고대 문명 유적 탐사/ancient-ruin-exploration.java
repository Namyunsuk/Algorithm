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
	static StringBuilder sb = new StringBuilder();
	
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] graph = new int[5][5];
	static int[][] rotatedGraph = new int[5][5];
	static int k;
	static int m;
	
	static LinkedList<Integer> wallNumbers = new LinkedList<>();
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<5;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<5;j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			int value = Integer.parseInt(st.nextToken());
			wallNumbers.offer((Integer)value);
		}
		
		for(int i=0; i<k; i++) { // k 턴만큼 진행
			int cnt = turn();
			if(cnt==0) break;
			sb.append(cnt+" ");
			
			resetRotatedGraph();
		}
		
		System.out.print(sb);
	}
	
	static int turn() {
		int cnt = 0;
		int maxV = 0;
		List<Pos> treasure = new ArrayList<>();
		int rotateValue = 0;
		int LTX = -1;
		int LTY = -1;
		
		// 1차 유물 획득
		for(int rotate=1;rotate<=3;rotate++) {
			for(int j=0; j<=2;j++) {
				for(int i=0;i<=2;i++) {
					for(int k=0;k<rotate;k++) { // 지정 각도 만큼 회전
						rotate(rotatedGraph, i,j,3);	
					}
					List<Pos> finds = find(rotatedGraph);
					if(finds.size()>maxV){ // 가치가 클 경우
						maxV = finds.size();
						treasure.clear();
						treasure.addAll(finds);
						
						rotateValue = rotate;
						LTX = i;
						LTY = j;
					}
					
					resetRotatedGraph(); // 회전그래프 복구
				}
			}	
		}
		if(maxV==0) return 0; // 더이상 유물 X
		
		cnt+=maxV;
		for(int i=0;i<rotateValue;i++) { // 가치가 최대가 되는 만큼 회전
			rotate(graph, LTX, LTY,3);
		}
		
		for(int i=0;i<treasure.size();i++) { // 유물 제거
			Pos pos = treasure.get(i);
			graph[pos.x][pos.y] = 0;
		}
		treasure.clear();
		
		// 벽면숫자로 채우기
		fill();
		
		// 유물 연쇄 획득
		while(true) {
			List<Pos> finds = find(graph); // 유물 찾기
			if(finds.size()==0) break; // 유물 연쇄 종료
			cnt+=finds.size();
			for(int i=0;i<finds.size();i++) { // 유물 제거
				Pos pos = finds.get(i);
				graph[pos.x][pos.y] = 0;
			}
			fill(); // 채우기
		}
		
		return cnt;
	}
	
	static void fill() {
		if(wallNumbers.isEmpty()) return;
		for(int j=0;j<5;j++) {
			for(int i=4;i>=0;i--) {
				if(wallNumbers.isEmpty()) return;
				if(graph[i][j]==0) {
					graph[i][j] = (int)wallNumbers.poll();
				}
			}
		}
	}
	
	static List<Pos> find(int[][] arr) {
		List<Pos> pieces = new ArrayList<>();
		List<Pos> treasure = new ArrayList<>();
		boolean[][] vis = new boolean[5][5];
		LinkedList<Pos> q = new LinkedList<>();
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(arr[i][j]==0 || vis[i][j]) continue; // 빈칸이거나, 방문한 경우 pass
				q.offer(new Pos(i,j));
				pieces.add(new Pos(i,j));
				vis[i][j] = true;
				
				while(!q.isEmpty()) {
					Pos cur = q.poll();
					int curNum = arr[cur.x][cur.y];
					for(int d=0; d<4;d++) {
						int nx = cur.x + dx[d];
						int ny = cur.y + dy[d];
						
						if(nx<0||nx>=5||ny<0||ny>=5) continue;
						if(vis[nx][ny]) continue;
						if(arr[nx][ny]!=curNum) continue;
						q.offer(new Pos(nx, ny));
						pieces.add(new Pos(nx,ny));
						vis[nx][ny] = true;
					}
				}
				if(pieces.size()>=3) treasure.addAll(pieces);
				pieces.clear();
			}
		}
		
		
		return treasure;
	}
	
	static void rotate(int[][]arr, int x, int y, int len) {
		int[][] tmp = new int[5][5];
		
		for(int i=x;i<x+len;i++) {
			for(int j=y;j<y+len;j++) {
				int ox = i-x;
				int oy = j-y;

				int nx = oy;
				int ny = len-1-ox;

				tmp[nx+x][ny+y] = arr[i][j];
			}
		}

		for(int i=x;i<x+len;i++) {
			for(int j=y;j<y+len;j++) {
				arr[i][j] = tmp[i][j];
			}
		}
	}
	
	static void resetRotatedGraph() {
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				rotatedGraph[i][j] = graph[i][j];
			}
		}
	}
}
