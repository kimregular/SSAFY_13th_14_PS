import java.io.*;
import java.util.*;

public class Main {
	
	static int N, testCase=1, minValue=0;
	static int[][] ar;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true){
			N = Integer.parseInt(br.readLine());
			
			if(N == 0) {
				System.out.println(sb);
				return;
			}
			
			ar = new int[N][N];
			visit = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					ar[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			bfs();
			sb.append("Problem ").append(testCase++).append(": ").append(minValue).append("\n");
			minValue = 0;
		}
	}

	public static void bfs() {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[3] - b[3]);
		queue.offer(new int[] {0, 0, ar[0][0], ar[0][0]});
		visit[0][0] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();

			if(now[0] == N-1 && now[1] == N-1) {
				minValue = now[3];
				break;
			}

			for(int k=0; k<4; k++) {
				int nx = now[0] + dx[k];
				int ny = now[1] + dy[k];

				if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny]) continue;
	
				queue.offer(new int[] {nx, ny, ar[nx][ny], now[3] + ar[nx][ny]});
				visit[nx][ny] = true;
			}
		}
	}

}
