package w250210.가배.p5427_불;
/*불이 먼저 확산된 후, 상근이가 불보다 빨리 탈출할 수 있는지를 판단하여 최소 시간 구하기 or 불가능하다면 IMPOSSIBLE출력
 * 큐 두 개 사용하는거. 불 확산용 큐, 상근이 이동용 */
import java.io.*;
import java.util.*;

public class Main {
    static int w, h;
    static int[][] fire; // 불 확산 시간
    static int[][] visited; // 상근이 이동 시간
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            fire = new int[h][w];
            visited = new int[h][w];
            ArrayDeque<int[]> fireQ = new ArrayDeque<>(); //불 BFS 큐
            ArrayDeque<int[]> q = new ArrayDeque<>(); //상근이 BFS 큐

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    char c = line.charAt(j);
                    /*c가 뭔지에 따라서 다르게 처리*/
                    if (c == '#') { //벽인 경우 맵에 못 가게 -1처리, 방문 못 하게
                        fire[i][j] = visited[i][j] = -1;
                    } else if (c == '@') { //상근이 시작위치니까 큐에 넣어주고 1초 설정
                        q.offer(new int[]{i, j});
                        visited[i][j] = 1;
                    } else if (c == '*') { //불인 경우, fire큐에 넣어주고 1초 설정
                        fireQ.offer(new int[]{i, j});
                        fire[i][j] = 1;
                    }
                }
            }

            /*1. 불을 먼저 지르기 <- for 언제 불이 전파되는지 기록*/
            while (!fireQ.isEmpty()) {
                int[] now = fireQ.poll();
                int r = now[0], c = now[1];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i], nc = c + dc[i];

                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue; // 범위 초과
                    if (fire[nr][nc] == 0) { // 아직 불이 퍼지지 않은 곳이면 확산
                        fire[nr][nc] = fire[r][c] + 1;
                        fireQ.offer(new int[]{nr, nc});
                    }
                }
            }

            /*2. 상근이 이동*/
            boolean find = false;
            while (!q.isEmpty()) {
                int[] now = q.poll();
                int r = now[0], c = now[1];

                // 출구 찾으면 걸린시간 출력하고 break
                if (r == 0 || r == h - 1 || c == 0 || c == w - 1) {
                    System.out.println(visited[r][c]);
                    find = true;
                    break;
                }

                for (int i = 0; i < 4; i++) { //4방향 탐색
                    int nr = r + dr[i], nc = c + dc[i];

                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue; // 범위 밖 continue
                    if (visited[nr][nc] != 0) continue; // 이미 방문한 경우 continue

                    // 불이 도달하지 않았거나, 불보다 먼저 도달할 수 있는 경우 이동(현재 칸에서 한 칸 이동해도 불에 안 닿으면)
                    if (fire[nr][nc] == 0 || visited[r][c] + 1 < fire[nr][nc]) {
                        visited[nr][nc] = visited[r][c] + 1;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            if (!find) System.out.println("IMPOSSIBLE");
        }
    }
}