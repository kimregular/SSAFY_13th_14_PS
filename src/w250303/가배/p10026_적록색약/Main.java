package w250303.가배.p10026_적록색약;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] arr, rgArr;
    static boolean[][] v;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        rgArr = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j);
                rgArr[i][j] = (arr[i][j] == 'G') ? 'R' : arr[i][j];
            }
        }

        v = new boolean[N][N];
        int normalCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j]) {
                    bfs(i, j, arr[i][j], arr);
                    normalCount++;
                }
            }
        }

        v = new boolean[N][N];
        int rgCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v[i][j]) {
                    bfs(i, j, rgArr[i][j], rgArr);
                    rgCnt++;
                }
            }
        }

        System.out.println(normalCount + " " + rgCnt);
    }

    static void bfs(int x, int y, char color, char[][] board) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        v[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!v[nx][ny] && board[nx][ny] == color) {
                        v[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
