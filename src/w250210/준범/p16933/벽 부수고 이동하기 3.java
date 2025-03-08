package w250210.준범.p16933;

import java.util.*;
import java.io.*;

public class Main {
    static int[][] data;
    static boolean[][][] visited;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int bfs(int n, int m, int k) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] { 0, 0, 0, 0, 1 });
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int w = cur[2];
            int d = cur[3];
            int dist = cur[4];

            if (x == n - 1 && y == m - 1) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nd = (d + 1) % 2;

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                if (data[nx][ny] == 0 && !visited[nx][ny][w]) {// 길, 방문x
                    q.add(new int[] { nx, ny, w, nd, dist + 1 });
                    visited[nx][ny][w] = true;
                    continue;
                }

                if (data[nx][ny] == 1 && w < k && !visited[nx][ny][w + 1]) {// 벽, 부술 수 있음, 방문x
                    if (d == 0) {// 낮
                        q.add(new int[] { nx, ny, w + 1, nd, dist + 1 });
                        visited[nx][ny][w + 1] = true;
                    } else {// 밤
                        q.add(new int[] { x, y, w, nd, dist + 1 });
                    }
                }

            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        data = new int[n][m];
        visited = new boolean[n][m][k + 1];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                data[i][j] = line.charAt(j) - '0';
            }
        }

        if (n == 1 && m == 1) {
            System.out.println(1);
            return;
        }

        System.out.println(bfs(n, m, k));
    }
}