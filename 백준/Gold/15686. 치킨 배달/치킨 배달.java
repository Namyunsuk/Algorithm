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

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] graph = new int[50][50];
    static List<Pos> houses = new ArrayList<>();
    static List<Pos> chickens = new ArrayList<>();
    static int result = Integer.MAX_VALUE;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) {
                    houses.add(new Pos(i, j));
                } else if (graph[i][j] == 2) {
                    chickens.add(new Pos(i, j));
                }
            }
        }

        dfs(0, new ArrayList<>());

        System.out.println(result);
    }

    static void dfs(int idx, List<Pos> selected) {
        if (selected.size() == m) {
            calculate(selected);
            return;
        }

        for (int i = idx; i < chickens.size(); i++) {
            selected.add(chickens.get(i));
            dfs(i + 1, selected);
            selected.remove(chickens.get(i));
        }
    }

    static void calculate(List<Pos> selected) {
        int sum = 0;
        for (int i = 0; i < houses.size(); i++) {
            sum += find(houses.get(i), selected);
        }

        result = Math.min(result, sum);
    }

    static int find(Pos house, List<Pos> selected) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < selected.size(); i++) {
            Pos chicken = selected.get(i);
            min = Math.min(min, Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y));
        }
        return min;
    }
}