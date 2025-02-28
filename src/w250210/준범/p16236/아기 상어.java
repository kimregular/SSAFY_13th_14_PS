//package w250210.준범.p16236;
//
//import java.util.*;
//import java.io.*;
//
//public class Main {
//    static int[][] data;
//    static boolean[][] visited;
//    static int curX;
//    static int curY;
//    static int size = 2;
//    static int cur_eat = 0;
//
//    static int[] dx = { 0, 0, 1, -1 };
//    static int[] dy = { 1, -1, 0, 0 };
//
//    static int bfs(int n) {
//
//        visited = new boolean[n][n];
//        Queue<int[]> q = new LinkedList<>();
//        q.add(new int[] { curX, curY, 0 });
//
//        int minX = -1;
//        int minY = -1;
//        int minDist = Integer.MAX_VALUE;
//
//        while (!q.isEmpty()) {
//            int[] cur = q.poll();
//            int x = cur[0];
//            int y = cur[1];
//            int dist = cur[2];
//
//            for (int i = 0; i < 4; i++) {
//                int nx = cur[0] + dx[i];
//                int ny = cur[1] + dy[i];
//
//                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
//                    continue;
//                if (visited[nx][ny] || data[nx][ny] > size)
//                    continue;
//
//                visited[nx][ny] = true;
//                int nDist = dist + 1;
//
//                if (data[nx][ny] > 0 && data[nx][ny] < size) { // 먹을 수 있는 물고기 발견
//                    if (nDist < minDist) {
//                        minDist = nDist;
//                        minX = nx;
//                        minY = ny;
//                    } else if (nDist == minDist) {
//                        if (nx < minX || (nx == minX && ny < minY)) {
//                            minX = nx;
//                            minY = ny;
//                        }
//                    }
//
//                }
//                q.add(new int[] { nx, ny, nDist });
//
//            }
//
//        }
//        if (minDist == Integer.MAX_VALUE)
//            return -1;
//        curX = minX;
//        curY = minY;
//        data[curX][curY] = 0;
//        return minDist;
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        int n = Integer.parseInt(br.readLine());
//        data = new int[n][n];
//
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < n; j++) {
//                data[i][j] = Integer.parseInt(st.nextToken());
//                if (data[i][j] == 9) {
//                    curX = i;
//                    curY = j;
//                    data[i][j] = 0;
//                }
//            }
//        }
//        int time = 0;
//        while (true) {
//            int dist = bfs(n);
//            if (dist == -1)
//                break;
//            time += dist;
//
//            cur_eat++;
//            if (cur_eat == size) {
//                cur_eat = 0;
//                size++;
//            }
//        }
//        System.out.println(time);
//
//    }
//}