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

        @Override
        public boolean equals(Object obj) {
            Pos pos = (Pos) obj;
            return pos.x == this.x && pos.y == this.y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board = new int[101][101];
    static String[] control = new String[10002];
    static LinkedList<Pos> snake = new LinkedList<Pos>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            control[Integer.parseInt(st.nextToken())] = st.nextToken();
        }

        int cnt = 0;
        int dir = 0;
        snake.addFirst(new Pos(1, 1));
        while (true) {
            cnt++;
            Pos head = snake.getFirst();
            Pos next = new Pos(head.x + dx[dir], head.y + dy[dir]);

            if (next.x < 1 || next.x > n || next.y < 1 || next.y > n) break;
            if (snake.contains(next)) break;

            snake.addFirst(next);
            if (board[next.x][next.y] == 1) board[next.x][next.y] = 0;
            else snake.removeLast();

            if (control[cnt] != null && control[cnt].equals("L")) {
                dir--;
                if (dir < 0) dir = 3;
            } else if (control[cnt] != null && control[cnt].equals("D")) {
                dir++;
                if (dir > 3) dir = 0;
            }
        }

        System.out.print(cnt);
    }
}