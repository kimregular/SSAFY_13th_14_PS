package w250210.가배.p16236_아기상어;
/*아기상어 : 아기 상어가 자신의 크기보다 작은 물고기를 먹으며 성장할 때까지 
 * 최단 거리로 이동하는 총 시간을 구하는 문제*/
import java.io.*;
import java.util.*;

public class Main {
    static int n, curSize = 2, curX, curY, time = 0, ate = 0;
    static int[][] arr;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        // 맵 입력받기
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    curX = i;
                    curY = j;
                    arr[i][j] = 0;
                }
            }
        }

        while (true) {
            int[] fishList = bfs();
            if (fishList == null) break; //잡아먹을 물고기 없으면 중단

            curX = fishList[0];
            curY = fishList[1];
            time += fishList[2];
            arr[curX][curY] = 0;
            ate++;

            if (ate == curSize) { //현재 아기 상어 사이즈만큼 고기 잡아먹었으면 아기 상어 성장~
                curSize++;
                ate = 0;
            }
        }

        System.out.println(time);
    }

    static int[] bfs() {
    	int minX = -1, minY = -1, minDist = Integer.MAX_VALUE;
    	//큐에 아기 상어 현재 위치 넣고 방문
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{curX, curY, 0});
        boolean[][] visited = new boolean[n][n];
        
        visited[curX][curY] = true;

        while (!q.isEmpty()) { 
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];
            //물고기 있는 경우, 우선순위에 따라 먹을 물고기 선택(거리/위쪽/왼쪽 우선)
            if (0 < arr[x][y] && arr[x][y] < curSize) {
                if (dist < minDist || (dist == minDist && (x < minX || (x == minX && y < minY)))) {
                    minX = x;
                    minY = y;
                    minDist = dist;
                }
            }
            //4방향 탐색하면서 맵 범위 내, 방문하지 않은 곳, 상어 크기 이하의 공간이면 방문하고 큐에 넣음
            for (int i = 0; i < 4; i++) { 
                int nx = x + dr[i];
                int ny = y + dc[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny] && arr[nx][ny] <= curSize) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return minX == -1 ? null : new int[]{minX, minY, minDist}; //물고기 못찾으면 null 찾았으면 위치와 거리 return
    }
}