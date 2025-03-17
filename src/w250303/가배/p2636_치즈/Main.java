package w250303.가배.p2636_치즈;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, cheese, lastCheese, time;
	static int[][] arr;
	static boolean[][] v;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==1) cheese++;
			}
		}
		
		while(cheese>0) {
			int meltCheeseCnt = bfs();
			lastCheese = cheese;
			cheese-=meltCheeseCnt;
			time++;
		}
		
		System.out.println(time);
		System.out.println(lastCheese);
	}
	
	static int bfs(){
		Queue<int[]> q = new ArrayDeque<>();
		v = new boolean[N][M];
		q.offer(new int[] {0,0});
		v[0][0] = true;
		
		List<int[]> meltCheese = new ArrayList<>();
		int cnt=0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			for (int d = 0; d < 4; d++) {
				int nx = x+dx[d];
				int ny = y+dy[d];
				
				if(nx<0||nx>=N||ny<0||ny>=M||v[nx][ny]) continue;
				v[nx][ny]=true;
				if(arr[nx][ny]==1) {
					meltCheese.add(new int[]{nx, ny});
				}else {
					q.offer(new int[] {nx, ny});
				}
			}
		}
        
		for(int[] c:meltCheese) {
			arr[c[0]][c[1]] = 0;
		}
        
		return meltCheese.size();
	}

}
