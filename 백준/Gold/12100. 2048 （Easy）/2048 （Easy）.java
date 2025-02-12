import java.util.*;
import java.io.*;

public class Main {
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] graph = new int[20][20];
    static int[][] tmpGraph = new int[20][20];
    static int n;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(input[j]);
            }
        }
        int cases = (int) Math.pow(4.0, 5);

        for (int c = 0; c < cases; c++) {
            StringBuilder sb = new StringBuilder(Integer.toString(c, 4));
            while (sb.length() < 5) {
                sb.insert(0, "0");
            }
            String directions = sb.toString();

            resetGraph();
            for (int i = 0; i < 5; i++) {
                int dir = directions.charAt(i) - '0';

                if (dir == UP) {
                    move(tmpGraph);
                } else if (dir == RIGHT) {
                    rotate(tmpGraph);
                    rotate(tmpGraph);
                    rotate(tmpGraph);
                    move(tmpGraph);
                    rotate(tmpGraph);
                } else if (dir == DOWN) {
                    rotate(tmpGraph);
                    rotate(tmpGraph);
                    move(tmpGraph);
                    rotate(tmpGraph);
                    rotate(tmpGraph);
                } else if (dir == LEFT) {
                    rotate(tmpGraph);
                    move(tmpGraph);
                    rotate(tmpGraph);
                    rotate(tmpGraph);
                    rotate(tmpGraph);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tmpGraph[i][j] > result) {
                        result = tmpGraph[i][j];
                    }
                }
            }
        }

        System.out.print(result);
    }

    static void rotate(int[][] graph) {
        int[][] tmp = new int[20][20];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = graph[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = tmp[n - 1 - j][i];
            }
        }
    }

    static void move(int[][] graph) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = graph[i][j];
                if (cur == 0) continue;
                for (int d = i + 1; d < n; d++) {
                    if (graph[d][j] > 0 && graph[d][j] != cur) break;
                    if (graph[d][j] == cur) {
                        graph[i][j] = 2 * cur;
                        graph[d][j] = 0;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = graph[i][j];
                if (cur == 0) continue;
                for (int u = 0; u < i; u++) {
                    if (graph[u][j] == 0) {
                        graph[u][j] = cur;
                        graph[i][j] = 0;
                        break;
                    }
                }
            }
        }
    }

    static void resetGraph() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmpGraph[i][j] = graph[i][j];
            }
        }
    }
}