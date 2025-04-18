import java.util.*;
import java.io.*;

public class Main {
	static class Pos{
		int x;
		int y;
		int surface = 4;
		int turns = 0;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		Pos(int x, int y, int surface){
			this.x = x;
			this.y = y;
			this.surface = surface;
		}
		
		Pos(int x, int y, int surface, int turns){
			this.x = x;
			this.y = y;
			this.surface = surface;
			this.turns = turns;
		}
	}
	
	static class TimePar{
		int x;
		int y;
		int dir;
		int v;
		
		TimePar(int x, int y, int dir, int v){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.v = v;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static LinkedList<Pos> q = new LinkedList<>();
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	static int[][] graph = new int[21][21];
	static boolean[][] vis = new boolean[21][21];
	static int[][] top = new int[11][11];
	static int[][] east = new int[11][11];
	static int[][] west = new int[11][11];
	static int[][] south = new int[11][11];
	static int[][] north = new int[11][11];
	static int outSurfacesDir;
	static Pos outPosBefore;
	static Pos outPos;
	
	static int[][] timeGraph = new int[21][21];
	static List<TimePar> timepars = new ArrayList<>();
	
	static int n;
	static int m;
	static int f;
	static int turnCnt = 0;
	
    public static void main(String[] args) throws IOException {
       st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());
       f = Integer.parseInt(st.nextToken());
       
      for(int i=0; i<n; i++) {
    	  st = new StringTokenizer(br.readLine());
    	  for(int j=0; j<n;j++) {
    		  graph[i][j] = Integer.parseInt(st.nextToken());
    	  }
      }
      
      for(int i=0; i<n; i++) {
    	  for(int j=0; j<n;j++) {
    		  timeGraph[i][j] = Integer.MAX_VALUE;
    	  }
      }
      
      readSurfaces();
      
      for(int i=0; i<f; i++) {
    	  st = new StringTokenizer(br.readLine());
    	  int x = Integer.parseInt(st.nextToken());
    	  int y = Integer.parseInt(st.nextToken());
    	  int d = Integer.parseInt(st.nextToken());
    	  int v = Integer.parseInt(st.nextToken());
    	  timeGraph[x][y] = 0;
    	  timepars.add(new TimePar(x,y,d,v));
      }
      
      findOutPos();
      boolean canOut = calOutTurn();
      if(!canOut) {
    	  System.out.println(-1);
    	  return;
      }
      
      for(int i=1;i<=100000;i++) { // 미리 시간 이상 전파
    	  for(int j=0;j<timepars.size();j++) {
    		  TimePar t = timepars.get(j);
    		  if(i%t.v!=0) continue; // v의 배수가 아닌 경우 패스
    		  int nx = t.x + dx[t.dir];
    		  int ny = t.y + dy[t.dir];
    		  if(nx<0||nx>=n||ny<0||ny>=n) continue;
    		  if(graph[nx][ny]==1||graph[nx][ny]==3||graph[nx][ny]==4) continue; // 장애물 있는 경우
    		  timeGraph[nx][ny]=i;
    		  t.x = nx;
    		  t.y = ny;
    	  }
      }
      
      if(timeGraph[outPos.x][outPos.y]<=turnCnt) {
    	  System.out.println(-1);
    	  return;
      }
      
      q.clear();
      q.offer(new Pos(outPos.x, outPos.y, 0, turnCnt)); // 입구위치
      vis[outPos.x][outPos.y] = true;
      while(!q.isEmpty()) {
    	  Pos cur = q.poll();
    	  for(int i=0;i<4;i++) {
    		  int nx = cur.x + dx[i];
    		  int ny = cur.y + dy[i];
    		  if(nx<0||nx>=n||ny<0||ny>=n) continue;
    		  if(vis[nx][ny]) continue;
    		  if(graph[nx][ny]==1) continue; // 장애물 
    		  if(timeGraph[nx][ny]<=cur.turns+1) continue; // 시간 이상(시간이 먼저 전파되니, 같다도 포함)
    		  if(graph[nx][ny]==4) {
    	    	  System.out.println(cur.turns+1);
    	    	  return;
    		  }
    		  q.offer(new Pos(nx, ny, 0, cur.turns+1));
    		  vis[nx][ny] = true;
    	  }
      }
      System.out.println(-1);
    }
    
    static boolean calOutTurn() {
    	while(!q.isEmpty()) {
    		Pos cur = q.poll();
    		for(int i=0;i<4;i++) {
    			int nx = cur.x + dx[i];
    			int ny = cur.y + dy[i];
    			
    			Pos newPos = changeSurfaces(nx, ny, cur.surface);
    			nx = newPos.x;
    			ny = newPos.y;
    			if(nx<0||nx>=m||ny<0||ny>=m) continue;
    			int[][] curSurface =getSurfaces(newPos.surface); 
    			if(curSurface[nx][ny]!=0) continue;
    			if(outSurfacesDir == newPos.surface&&outPosBefore.x==newPos.x&&outPosBefore.y==newPos.y) {
    				turnCnt = cur.turns+2;
    				return true;
    			}
    			curSurface[nx][ny] = -1;
    			q.offer(new Pos(nx,ny,newPos.surface, cur.turns+1));
    		}
    	}
    	return false;
    }
    
    static int[][] getSurfaces(int dir) {
    	if(dir==0) return east;
    	else if(dir==1) return west;
    	else if(dir == 2) return south;
    	else if(dir == 3) return north;
    	return top;
    }
    
    static Pos changeSurfaces(int x, int y, int surfaceDir) {
    	if(surfaceDir==0) {
    		if(x<0) {
    			return new Pos(m-1-y, m-1,4);
    		}
    		if(y>=m) {
    			return new Pos(x,0, 3);
    		}
    		if(y<0) {
    			return new Pos(x, m-1,2);
    		}
    	}else if(surfaceDir==1) {
    		if(x<0) {
    			return new Pos(y, 0,4);
    		}
    		if(y>=m) {
    			return new Pos(x,0,2);
    		}
    		if(y<0) {
    			return new Pos(x, m-1, 3);
    		}
    		
    	}else if(surfaceDir==2) {
    		if(x<0) {
    			return new Pos(m-1, y, 4);
    		}
    		if(y>=m) {
    			return new Pos(x,0, 0);
    		}
    		if(y<0) {
    			return new Pos(x, m-1, 1);
    		}
    	}else if(surfaceDir==3) {
    		if(x<0) {
    			return new Pos(0, m-1-y,4);
    		}
    		if(y>=m) {
    			return new Pos(x,0,1);
    		}
    		if(y<0) {
    			return new Pos(x, m-1,0);
    		}
    	}else {
    		if(x<0) {
    			return new Pos(0, m-1-y,3);
    		}
    		if(y>=m) {
    			return new Pos(0,m-1-x,0);
    		}
    		if(x>=m) {
    			return new Pos(0,y,2);
    		}
    		if(y<0) {
    			return new Pos(0, x,1);
    		}
    	}
    	return new Pos(x, y, surfaceDir);
    }
    
    static void findOutPos() {
    	// 윗줄
    	boolean flag = false;
    	for(int i=0;i<n;i++) {
    		for(int j=n-1;j>=0;j--) {
    			if(graph[i][j]==3) {
    				for(int k=0;k<m;k++) {
    					if(graph[i-1][j-k]==0) {
    						outSurfacesDir = 3;
    						outPosBefore = new Pos(m-1, k);
    						outPos = new Pos(i-1,j-k);
    						return;
    					}
    				}
    				flag = true;
    				break;
    			}
    			if(flag) break;
    		}
    		if(flag) break;
    	}
    	
    	//왼쪽줄
    	flag = false;
    	for(int j=0;j<n;j++) {
    		for(int i=0;i<n;i++) {
    			if(graph[i][j]==3) {
    				for(int k=0;k<m;k++) {
    					if(graph[i+k][j-1]==0) {
    						outSurfacesDir = 1;
    						outPosBefore = new Pos(m-1, k);
    						outPos = new Pos(i+k,j-1);
    						return;
    					}
    				}
    				flag = true;
    				break;
    			}
    			if(flag) break;
    		}
    		if(flag) break;
    	}
    	
    	
    	// 아래줄
    	flag = false;
    	for(int i=n-1;i>=0;i--) {
    		for(int j=0;j<n;j++) {
    			if(graph[i][j]==3) {
    				for(int k=0;k<m;k++) {
    					if(graph[i+1][j+k]==0) {
    						outSurfacesDir = 2;
    						outPosBefore = new Pos(m-1, k);
    						outPos = new Pos(i+1,j+k);
    						return;
    					}
    				}
    				flag = true;
    				break;
    			}
    			if(flag) break;
    		}
    		if(flag) break;
    	}
    	
    	// 오른쪽줄
    	flag = false;
    	for(int j=n-1;j>=0;j--) {
    		for(int i=n-1;i>=0;i--) {
    			if(graph[i][j]==3) {
    				
    				for(int k=0;k<m;k++) {
    					if(graph[i-k][j+1]==0) {
    						outSurfacesDir = 0;
    						outPosBefore = new Pos(m-1, k);
    						outPos = new Pos(i-k,j+1);
    						return;
    					}
    				}
    				flag = true;
    				break;
    			}
    			if(flag) break;
    		}
    		if(flag) break;
    	}
    }

	private static void readSurfaces() throws IOException {
		for(int i=0; i<m; i++) {
			  st = new StringTokenizer(br.readLine());
			  for(int j=0; j<m;j++) {
				  east[i][j] = Integer.parseInt(st.nextToken());
			  }
		  }
		  
		  for(int i=0; i<m; i++) {
			  st = new StringTokenizer(br.readLine());
			  for(int j=0; j<m;j++) {
				  west[i][j] = Integer.parseInt(st.nextToken());
			  }
		  }
		  
		  for(int i=0; i<m; i++) {
			  st = new StringTokenizer(br.readLine());
			  for(int j=0; j<m;j++) {
				  south[i][j] = Integer.parseInt(st.nextToken());
			  }
		  }
		  
		  for(int i=0; i<m; i++) {
			  st = new StringTokenizer(br.readLine());
			  for(int j=0; j<m;j++) {
				  north[i][j] = Integer.parseInt(st.nextToken());
			  }
		  }
		  
		  for(int i=0; i<m; i++) {
			  st = new StringTokenizer(br.readLine());
			  for(int j=0; j<m;j++) {
				  top[i][j] = Integer.parseInt(st.nextToken());
				  if(top[i][j]==2) {
					  q.offer(new Pos(i,j));
				  }
			  }
		  }
	}
}