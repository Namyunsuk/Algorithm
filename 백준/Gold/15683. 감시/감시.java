import java.util.*;
import java.io.*;

public class Main {
    static class Pos {
        int x;
        int y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int BLANK = 0;
    static final int WALL = 6;
    static final int SEARCHED = -1;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] graph = new int[9][9];
    static int[][] tmpGraph = new int[9][9];
    static List<Pos> cctvs = new ArrayList<>();
    static int blanksCnt = 0;
    static int n;
    static int m;
    static int searchedCnt = 0;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int v = Integer.parseInt(input[j]);
                graph[i][j] = v;
                if (v >= 1 && v <= 5) cctvs.add(new Pos(i, j));
                if (v == 0) blanksCnt++;
            }
        }

        int cases = (int) Math.pow(4, cctvs.size());

        for (int i = 0; i < cases; i++) {
            StringBuilder sb = new StringBuilder(Integer.toString(i, 4));
            while (cctvs.size() != 0 && sb.length() != cctvs.size()) {
                sb.insert(0, "0");
            }
            String directions = sb.toString();
            resetGraph();
            searchedCnt = 0;
            for (int j = 0; j < cctvs.size(); j++) {
                Pos pos = cctvs.get(j);
                int cctv = graph[pos.x][pos.y];
                int dir = directions.charAt(j) - '0';

                if (cctv == 1) {
                    search(tmpGraph, pos, dir);
                } else if (cctv == 2) {
                    search(tmpGraph, pos, dir);
                    search(tmpGraph, pos, dir + 2);
                } else if (cctv == 3) {
                    search(tmpGraph, pos, dir);
                    search(tmpGraph, pos, dir + 1);
                } else if (cctv == 4) {
                    search(tmpGraph, pos, dir);
                    search(tmpGraph, pos, dir + 1);
                    search(tmpGraph, pos, dir + 2);
                } else if (cctv == 5) {
                    search(tmpGraph, pos, dir);
                    search(tmpGraph, pos, dir + 1);
                    search(tmpGraph, pos, dir + 2);
                    search(tmpGraph, pos, dir + 3);
                }
            }
            result = Math.min(result, blanksCnt - searchedCnt);
        }

        System.out.println(result);
    }

    static void search(int[][] graph, Pos pos, int direction) {
        int x = pos.x;
        int y = pos.y;
        int dir = direction % 4;

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (oob(nx, ny)) break;
            if (graph[nx][ny] == WALL) break;
            else if (graph[nx][ny] == BLANK) {
                graph[nx][ny] = SEARCHED;
                searchedCnt++;
            }
            x = nx;
            y = ny;
        }
    }

    static Boolean oob(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    static void resetGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmpGraph[i][j] = graph[i][j];
            }
        }
    }
}
