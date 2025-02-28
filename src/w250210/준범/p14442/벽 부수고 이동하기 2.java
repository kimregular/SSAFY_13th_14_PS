//package w250210.준범.p14442;
//
//import java.util.*;
//import java.io.*;
//
//public class Main {
//	static int[][] data;
//	static int[][][] visited;
//	static int[] dx = { 0, 0, 1, -1 };
//	static int[] dy = { 1, -1, 0, 0 };
//
//	static int bfs(int n, int m, int k) {
//		Queue<int[]> q = new LinkedList<>();
//
//		q.add(new int[] { 0, 0, 0 });
//		visited[0][0][0] = 1;
//
//		while (!q.isEmpty()) {
//			int[] cur = q.poll();
//			int x = cur[0];
//			int y = cur[1];
//			int w = cur[2];
//			for (int i = 0; i < 4; i++) {
//				int nx = x + dx[i];
//				int ny = y + dy[i];
//
//				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
//					continue;
//
//				if (data[nx][ny] == 0) {
//					if (visited[nx][ny][w] == 0) {
//						visited[nx][ny][w] = visited[x][y][w] + 1;
//						q.add(new int[] { nx, ny, w });
//						if (nx == n - 1 && ny == m - 1) {
//							return visited[nx][ny][w];
//						}
//					}
//				} else {
//					if (w < k) {
//						if (visited[nx][ny][w + 1] == 0) {
//							visited[nx][ny][w + 1] = visited[x][y][w] + 1;
//							q.add(new int[] { nx, ny, w + 1 });
//							if (nx == n - 1 && ny == m - 1) {
//								return visited[nx][ny][w + 1];
//							}
//						}
//					}
//				}
//			}
//
//		}
//
//		return -1;
//	}
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int n = Integer.parseInt(st.nextToken());
//		int m = Integer.parseInt(st.nextToken());
//		int k = Integer.parseInt(st.nextToken());
//
//		data = new int[n][m];
//		visited = new int[n][m][k + 1];
//
//		for (int i = 0; i < n; i++) {
//			String line = br.readLine();
//			for (int j = 0; j < m; j++) {
//				data[i][j] = line.charAt(j) - '0';
//			}
//		}
//
//		if (n == 1 && m == 1) {
//			System.out.println(1);
//			return;
//		}
//
//		System.out.println(bfs(n, m, k));
//
//	}
//}