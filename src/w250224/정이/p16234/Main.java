package w250224.정이.p16234;

import java.io.*;
import java.util.*;
    
public class Main {
	static int N, L, R, count;
	static int[][] ar;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		ar = new int[N][N];
		visit = new boolean[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				ar[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		find();
		System.out.println(count);
	}

	public static void find() {
		while(true){
			boolean chk = false;

			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					Queue<int[]> queue = new ArrayDeque<>();
					ArrayList<int[]> list = new ArrayList<>();
					int sum = 0; int cnt = 0;
					if(visit[i][j]) continue;
					queue.offer(new int[] {i ,j});

					while(!queue.isEmpty()) {
						int[] xy = queue.poll();

						for(int k=0; k<4; k++) {
							int nx = xy[0] + dx[k];
							int ny = xy[1] + dy[k];
							
							if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny]) continue;
							if(Math.abs(ar[xy[0]][xy[1]] - ar[nx][ny]) < L || Math.abs(ar[xy[0]][xy[1]] - ar[nx][ny]) > R) continue;
							
							visit[nx][ny] = true;
							sum += ar[nx][ny];
							cnt += 1;
							list.add(new int[] {nx, ny});
							queue.offer(new int[] {nx, ny});
							chk = true;
						}
					}
					for(int[] xy : list){
						ar[xy[0]][xy[1]] = sum / cnt;
					}
				}
			}
			if(chk) {
				count++;
				visit = new boolean[N][N];
			}else {
				return;
			}
		}
	}

}

