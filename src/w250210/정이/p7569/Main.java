package src.w250210.정이.p7569;
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int H;
	
	static int totalTomato;
	static int tomato;
	static int dayCount;
	
	static int[][][] ar;
	static boolean[][][] visit;
	
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static int[] dx = {-1, 0, 1, 0, 0, 0};
	static int[] dy = {0, 1, 0, -1, 0, 0};
	
	static Queue<int[]> queue = new ArrayDeque<int[]>();
	
	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		ar = new int[H][N][M];
		visit = new boolean[H][N][M];
		
		for(int z=0; z<H; z++) {
			for(int x=0; x<N; x++) {
				st = new StringTokenizer(br.readLine());
				for(int y=0; y<M; y++) {
					int toma = Integer.parseInt(st.nextToken());
					ar[z][x][y] = toma;
					if(toma != -1) totalTomato++;
					if(toma ==  1) {
						queue.offer(new int[] {z, x, y, 0});
						visit[z][x][y] = true;
						tomato++;
					}
				}
			}
		}
		find();
	}
	
	public static void find() {
		
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int z = temp[0];
			int x = temp[1];
			int y = temp[2];
			int cnt = temp[3];
			
			dayCount = cnt;
			
			for(int k=0; k<6; k++) {
				int nz = z + dz[k];
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(nz<0 || nz>=H || nx<0 || nx>=N || ny<0 || ny>=M) continue;
				if(visit[nz][nx][ny] || ar[nz][nx][ny] != 0) continue;
				
				queue.offer(new int[] {nz,nx,ny,cnt+1});
				visit[nz][nx][ny] = true;
				tomato++;
			}
		}
		System.out.println(totalTomato == tomato ? dayCount : -1);
	}

}
