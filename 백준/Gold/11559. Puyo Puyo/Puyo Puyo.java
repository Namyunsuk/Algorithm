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

    static int n = 12;
    static int m = 6;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static String[][] graph = new String[n][m];
    static int result = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                graph[i][j] = input[j];
            }
        }

        while (true) {
            if (!burst()) {
                System.out.print(result);
                return;
            }
            moveDown();
            result++;
        }
    }

    static boolean burst() {
        List<Pos> removed = new ArrayList<>();
        LinkedList<Pos> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j].equals(".") || vis[i][j]) continue;
                q.offer(new Pos(i, j));
                removed.add(new Pos(i, j));
                vis[i][j] = true;

                while (!q.isEmpty()) {
                    Pos cur = q.poll();
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];

                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                        if (vis[nx][ny]) continue;
                        if (!graph[nx][ny].equals(graph[i][j])) continue;
                        q.offer(new Pos(nx, ny));
                        removed.add(new Pos(nx, ny));
                        vis[nx][ny] = true;
                    }
                }

                if (removed.size() >= 4) {
                    for (int k = 0; k < removed.size(); k++) {
                        Pos cur = removed.get(k);
                        graph[cur.x][cur.y] = ".";
                    }
                    flag = true;
                }
                removed.clear();
            }
        }

        return flag;
    }

    static void moveDown() {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                String cur = graph[i][j];
                if (cur.equals(".")) continue;
                for (int d = n - 1; d > i; d--) {
                    if (graph[d][j].equals(".")) {
                        graph[d][j] = cur;
                        graph[i][j] = ".";
                        break;
                    }
                }
            }
        }
    }
}