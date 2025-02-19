package src.w250210.정이.p7576;
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int tomato_total;
	static int tomato;
	static int dayCount;
	static int[][] ar;
	static boolean[][] visit;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		ar = new int[N][M];
		visit = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int t = Integer.parseInt(st.nextToken());
				
				if(t != -1) tomato_total++;
				if(t == 1) {
					queue.offer(new int[] {i, j, 0});
					visit[i][j] = true;
					tomato++;
					}
				ar[i][j] = t;			
			}
		}
		
		find();
	}
	
	public static void find() {
		
		while(!queue.isEmpty()) {
			int[] rowCol = queue.poll();
			int i = rowCol[0];
			int j = rowCol[1];
			int cnt = rowCol[2];
			dayCount = cnt;
			
			for(int k=0; k<4; k++) {
				int ni = i + dx[k]; 
				int nj = j + dy[k];
				
				if(ni<0 || nj<0 || ni>=N || nj>=M) continue;
				if(visit[ni][nj] || ar[ni][nj] != 0) continue;
				
				queue.offer(new int[] {ni, nj, cnt+1});
				visit[ni][nj] = true;
				tomato++;			
			}
		}
		if(tomato == tomato_total) System.out.println(dayCount);
		else System.out.println(-1);
		
	}

}
