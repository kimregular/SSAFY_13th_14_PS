package src.w250210.정이.p1987;
import java.io.*;
import java.util.*;

public class Main {
	
	static int R, C;
	static char[][] ar;
	static boolean[] visit = new boolean[26];
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int maxStep = 1;
	
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ar = new char[R][C];
		
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				ar[i][j] = str.charAt(j);
			}
		}
		visit[ar[0][0]-65] = true;
		dfs(0, 0, 1);
		System.out.println(maxStep);
	}
	
	public static void dfs(int startX, int startY, int step) {
		maxStep = Math.max(maxStep, step);
		
		visit[ar[startX][startY]-65] = true;
		for(int k=0; k<4; k++) {
			int nx = startX + dx[k];
			int ny = startY + dy[k];
			
			if(nx<0 || nx>=R || ny<0 || ny>=C || visit[ar[nx][ny]-65]) continue;
			
			dfs(nx, ny, step+1);
			visit[ar[nx][ny]-65] = false;
		}
	}

}
