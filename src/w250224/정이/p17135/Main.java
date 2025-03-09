import java.io.*;
import java.util.*;

public class Main {
	static int N, M, D, archerRow, maxCount=0;
	static boolean[][] map, gameMap, visit;
	static int[] archer = new int[3];
	static int[] dx = {0, -1, 0}, dy = {-1, 0, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				if(st.nextToken().equals("1")) map[i][j] = true;
			}
		}
		comb(0, 0);
		System.out.println(maxCount);
	}

	public static void comb(int cnt, int start) {
		if(cnt == 3){
			gameMap();
			game();
			return;
		}
		for(int i=start; i<M; i++) {
			archer[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

	public static void gameMap() {
		gameMap = new boolean[N][M];
		for(int i=0; i<N; i++){
			for(int  j=0; j<M; j++) {
				if(map[i][j])
				gameMap[i][j] = true;
			}
		}
	}

	public static void game() {
		int totalCnt = 0;
		archerRow = N;
		
		while(archerRow > 0) {
			Set<int[]> set = new HashSet<>();
			
			for(int archerCol : archer) {
				Queue<int[]> queue = new ArrayDeque<>();
				ArrayList<int[]> list = new ArrayList<>();

				visit = new boolean[N][M];

				queue.offer(new int[] {archerRow-1, archerCol, 1});
				visit[archerRow-1][archerCol] = true;

				while(!queue.isEmpty()){
					int[] archerInfo = queue.poll();

					int x = archerInfo[0];
					int y = archerInfo[1];
					int dist = archerInfo[2];

					if(gameMap[x][y]) {
						list.add(new int[] {x, y, dist});
					}

					dist += 1;
					if(dist > D) continue;

					for(int k=0; k<3; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny]) continue;
						
						if(gameMap[nx][ny]) {
							list.add(new int[] {nx, ny, dist});
						}

						queue.offer(new int[] {nx, ny, dist});
						visit[nx][ny] = true;
					}
					
				}
				if(!list.isEmpty()) {
					list.sort((a, b) -> {
						if(a[2] == b[2]) return a[1] - b[1];
						return a[2] - b[2];
					});
					int[] tar = list.get(0);
					set.add(tar);
				}
			}
			for(int[] e : set) {
				if(gameMap[e[0]][e[1]]){
					gameMap[e[0]][e[1]] = false;
					totalCnt++;
				}
			}
			archerRow -= 1;
		}
		maxCount = Math.max(maxCount, totalCnt);
	}
}
