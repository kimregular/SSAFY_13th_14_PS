package w250210.가배.p2178_미로탐색;
/*미로탐색 문제 : (1,1)에서 (N,M)까지 최단 거리 찾는 문제  */
import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] arr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{0, 0});
		arr[0][0] = 1;//여기서 시작해서 (n-1, m-1)까지 탐색

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int r = now[0], c = now[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (0 <= nr && nr < n && 0 <= nc && nc < m && arr[nr][nc] == 1) { 
					arr[nr][nc] = arr[r][c] + 1;
					q.offer(new int[]{nr, nc});
				}
			}
		}

		System.out.println(arr[n - 1][m - 1]);
	}
}
