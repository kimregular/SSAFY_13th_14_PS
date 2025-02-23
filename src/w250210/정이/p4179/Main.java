package src.w250210.정이.p4179;
import java.io.*;
import java.util.*;

public class Main {
	
	static int R;
	static int C;
	static int totalTime;
	static boolean success;
	
	static char[][] ar;
	static boolean[][] visit;
	static boolean[][] visit_fire;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static Queue<int[]> queue = new ArrayDeque<int[]>();

	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		ar = new char[R][C];
		visit = new boolean[R][C];
		visit_fire = new boolean[R][C];
		
		int a=0;
		int b=0;
		
		for(int r=0; r<R; r++) {
			String str = br.readLine();
			for(int c=0; c<C; c++) {
				char ch = str.charAt(c);
				ar[r][c] = ch;
				if(ch == 'J') {
					a = r;
					b = c;
				}
				if(ch == 'F') {
					queue.offer(new int[] {r, c, 0, 0});
					visit_fire[r][c] = true;
				}
				if(ch == '#') visit_fire[r][c] = true;
			}
		}
		queue.offer(new int[] {a, b, 0, 1});
		visit[a][b] = true;
		
		bfs();
	}
	
	public static void bfs() {
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			int time = temp[2];
			int jihoon = temp[3];
			
			totalTime = time;
			
			if(jihoon == 1) {
				for(int k=0; k<4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					
					if(nx<0 || ny<0 || nx>=R || ny>=C) {
						success = true;
						queue.clear();
						totalTime++;
						break;
					}
					if(ar[nx][ny] == '#' || ar[nx][ny] == 'F' || visit[nx][ny]) continue;
					
					queue.offer(new int[] {nx, ny, time+1, 1});
					visit[nx][ny] = true;
					ar[nx][ny] = 'J';
					ar[x][y] = '.';				
				}
				continue;
			}
			
			// 불인 경우
			for(int k=0; k<4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(nx<0 || ny<0 || nx>=R || ny>=C || ar[nx][ny] == '#' || visit_fire[nx][ny]) continue;
				
				if(ar[nx][ny] == 'J') {
					ar[nx][ny] = 'F';
				}
				
				queue.offer(new int[] {nx, ny, time+1, 0});
				visit_fire[nx][ny] = true;
				ar[nx][ny] = 'F';
			}
			
			
		}
		
		System.out.println(success ? totalTime : "IMPOSSIBLE");
	}

}
