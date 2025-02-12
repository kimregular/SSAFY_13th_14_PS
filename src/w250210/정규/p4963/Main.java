package w250210.정규.p4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {

		StringBuilder answer = new StringBuilder();
		Solution s = new Solution();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			for (int[][] testCase : readInput(br)) {
				answer.append(s.solution(testCase)).append("\n");
			}

			System.out.println(answer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<int[][]> readInput(BufferedReader br) throws IOException {
		List<int[][]> result = new ArrayList<>();

		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			if(height == 0 && width == 0) break;

			int[][] map = new int[height][width];

			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < width; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result.add(map);
		}
		return result;
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

	private int[][] map;
	private boolean[][] visited;

	public int solution(int[][] map) {
		init(map);

		int result = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					explore(new int[]{i, j});
					result++;
				}
			}
		}
		return result;
	}

	private void init(int[][] map) {
		this.map = map;
		this.visited = new boolean[map.length][map[0].length];
	}

	private void explore(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);
		visited[start[0]][start[1]] = true;

		while(!q.isEmpty()){
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBounds(nx, ny)) continue;
				if(map[nx][ny] == 0) continue;
				if(visited[nx][ny]) continue;

				q.offer(new int[]{nx, ny});
				visited[nx][ny] = true;
			}
		}
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= map.length || y < 0 || y >= map[x].length;
	}
}
