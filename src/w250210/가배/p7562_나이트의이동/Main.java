package w250210.가배.p7562_나이트의이동;
/*나이트의 이동 : 체스판에서 나이트가 특정 위치에서 목표 위치까지 이동할 때 최소 이동 횟수를 구하는 문제*/
import java.io.*;
import java.util.*;

public class Main {

	static int l;
	static int[][] arr;
	static int[] dr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 체스판 한 변의 길이 l
			l = Integer.parseInt(br.readLine());
			arr = new int[l][l];

			// 나이트가 현재 있는 칸
			st = new StringTokenizer(br.readLine());
			int sX = Integer.parseInt(st.nextToken());
			int sY= Integer.parseInt(st.nextToken());
			// 나이트가 이동하려고 하는 칸
			st = new StringTokenizer(br.readLine());
			int eX = Integer.parseInt(st.nextToken());
			int eY= Integer.parseInt(st.nextToken());

			
			ArrayDeque<int[]> q = new ArrayDeque<>();
			q.offer(new int[]{sX, sY});
			arr[sX][sY]=1; //시작 위치
			
			while (!q.isEmpty()) {
				int[] now = q.poll();
				int nowX = now[0];
				int nowY = now[1];
				
				if (nowX == eX && nowY == eY)
					break;
				
				for (int i = 0; i < 8; i++) {
					int nr = nowX + dr[i];
					int nc = nowY + dc[i];
					
					if (nc < 0 || nc >= l || nr < 0 || nr >= l)
						continue;
					
					if (arr[nr][nc] == 0) { //방문 안했으면 이동
						arr[nr][nc] = arr[nowX][nowY] + 1;
						q.offer(new int[] {nr, nc});
					}

				}
			}
			System.out.println(arr[eX][eY]-1);
		}

	}
}