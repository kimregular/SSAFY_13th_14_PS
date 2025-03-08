package w250210.준범.p2206;

import java.util.*;
import java.io.*;

public class Main {
    static int[][] data;
    static int[][][] visited; // 좌표, 부술 수 있는 벽의 개수

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] { 0, 0, 0 });
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int canBreak = cur[2];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                if (data[nx][ny] == 0) {// 길 발견
                    if (visited[nx][ny][canBreak] == 0) {// 아직 방문 x
                        q.offer(new int[] { nx, ny, canBreak });
                        visited[nx][ny][canBreak] = visited[cur[0]][cur[1]][canBreak] + 1;
                        if (nx == n - 1 && ny == m - 1) {

                            return visited[nx][ny][canBreak];
                        }
                    }
                } else {// 벽 발견
                    if (canBreak == 0) {// 아직 break X
                        if (visited[nx][ny][1] == 0) {// 부순 후 해당 위치 방문 x
                            q.offer(new int[] { nx, ny, 1 });
                            visited[nx][ny][1] = visited[cur[0]][cur[1]][0] + 1;
                            if (nx == n - 1 && ny == m - 1) {

                                return visited[nx][ny][1];
                            }
                        }
                    }
                }

            }

        }
        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        data = new int[n][m];
        visited = new int[n][m][2];

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

        System.out.println(bfs(n, m));
    }
}