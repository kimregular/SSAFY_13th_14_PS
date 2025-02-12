package w250210.정규.p2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[][] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int[][] field = new int[height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return field;
	}
}

class Solution {

	private IceBerg iceBerg;

	public int solution(int[][] input) {
		init(input);

		int resultDay = 0;
		if(iceBerg.hasNoIce()) return resultDay;

		while(true) {
			iceBerg.melt(); // 얼음을 녹이고
			resultDay++; // 일수 증가

			if(iceBerg.hasNoIce()) return 0; // 만약 얼음이 2개가 아닌데 얼음이 없으면 0
			if(!iceBerg.hasOneIce()) return resultDay; // 얼음이 1 덩어리가 아니라면 일수 출력
		}
	}

	private void init(int[][] input) {
		this.iceBerg = new IceBerg(input);
	}
}

class IceBerg {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private int[][] field;
	private Queue<int[]> iceQ;

	public IceBerg(int[][] field) {
		this.field = field;
		this.iceQ = new ArrayDeque<>();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(field[i][j] != 0) iceQ.offer(new int[]{i, j});
			}
		}
	}


	public boolean hasOneIce() {
		Set<Integer> visited = new HashSet<>();

		int cnt = 0;

		for (int[] ice : iceQ) {
			if(field[ice[0]][ice[1]] == 0) continue;
			if (visited.contains(ice[0] * 100 + ice[1])) continue;
			visited.add(ice[0] * 100 + ice[1]);
			cnt++;
			if(cnt >= 2) return false;
			explore(ice[0], ice[1], visited);
		}
		return true;
	}

	private void explore(int x, int y, Set<Integer> visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		visited.add(x * 100 + y);

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = direction[0] + cur[0];
				int ny = direction[1] + cur[1];

				if(isOutOfBounds(nx, ny)) continue;
				if(field[nx][ny] == 0) continue;
				if(visited.contains(nx * 100 + ny)) continue;

				visited.add(nx * 100 + ny);
				q.offer(new int[]{nx, ny});
			}
		}
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}

	public void melt() {

		List<int[]> meltValues = new ArrayList<>();

		int len = iceQ.size();
		for (int i = 0; i < len; i++) {
			int[] cur = iceQ.poll();

			if(cur == null || field[cur[0]][cur[1]] == 0) continue;

			int cnt = 0;
			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBounds(nx, ny)) continue;
				if(field[nx][ny] != 0) continue;

				cnt++;
			}

			if(cnt != 0) meltValues.add(new int[]{cur[0], cur[1], cnt});
			iceQ.offer(cur);
		}
		apply(meltValues);
	}

	private void apply(List<int[]> meltValues) {
		for (int[] meltValue : meltValues) {
			int x = meltValue[0];
			int y = meltValue[1];
			int v = meltValue[2];

			if (field[x][y] - v > 0) {
				field[x][y] -= v;
			} else {
				field[x][y] = 0;
			}
		}
	}

	public boolean hasNoIce() {
		return iceQ.isEmpty();
	}
}