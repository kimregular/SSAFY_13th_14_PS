package w250210.정규.p16236;

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
		int len = Integer.parseInt(br.readLine());
		int[][] result = new int[len][len];
		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return result;
	}
}

class Solution {
	private Ocean ocean;
	private BabyShark babyShark;

	public int solution(int[][] map) {
		init(map);

		int cnt = 0;
		while (ocean.edibleFishAround()) {
			int[] target = ocean.getTargetPathAndSize(babyShark.getLocation(), babyShark.getSize());
			if (target == null) break;
			cnt += babyShark.moveAndEat(target, ocean.removeFishAt(target));
		}

		return cnt;
	}

	private void init(int[][] map) {
		this.ocean = new Ocean(map);
		this.babyShark = new BabyShark(ocean.initShark());
	}
}

class Ocean {
	private int[][] field;
	private int edibleFishCount;

	public Ocean(int[][] map) {
		this.field = map;
		countEdibleFish();
	}

	public int[] initShark() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == 9) {
					field[i][j] = 0;
					return new int[]{i, j};
				}
			}
		}
		return new int[]{0, 0};
	}

	public boolean edibleFishAround() {
		return edibleFishCount > 0;
	}

	public int[] getTargetPathAndSize(int[] sharkLocation, int sharkSize) {
		int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[field.length][field[0].length];
		q.offer(new int[]{sharkLocation[0], sharkLocation[1], 0});
		visited[sharkLocation[0]][sharkLocation[1]] = true;

		List<int[]> candidates = new ArrayList<>();
		int minDist = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int dist = cur[2];

			if (dist > minDist) break;
			if (0 < field[x][y] && field[x][y] < sharkSize) {
				candidates.add(new int[]{x, y, dist});
				minDist = dist;
				continue;
			}
			for (int[] dir : directions) {
				int nx = x + dir[0];
				int ny = y + dir[1];

				if (isOutOfBounds(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(field[nx][ny] > sharkSize) continue;

				visited[nx][ny] = true;
				q.offer(new int[]{nx, ny, dist + 1});
			}
		}

		candidates.sort((a, b) -> {
			if (a[2] != b[2]) return Integer.compare(a[2], b[2]);
			if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
			return Integer.compare(a[1], b[1]);
		});

		return candidates.isEmpty() ? null : candidates.get(0);
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}

	public int removeFishAt(int[] target) {
		if (field[target[0]][target[1]] > 0) edibleFishCount--;
		field[target[0]][target[1]] = 0;
		return target[2];
	}

	private void countEdibleFish() {
		edibleFishCount = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] > 0 && field[i][j] < 9) {
					edibleFishCount++;
				}
			}
		}
	}
}

class BabyShark {
	private int[] location;
	private int size;
	private int eatCount;

	public BabyShark(int[] location) {
		this.location = location;
		this.size = 2;
		this.eatCount = 0;
	}

	public int[] getLocation() {
		return location;
	}

	public int getSize() {
		return size;
	}

	public int moveAndEat(int[] targetLocation, int distance) {
		location[0] = targetLocation[0];
		location[1] = targetLocation[1];
		eatCount++;
		if (eatCount == size) {
			size++;
			eatCount = 0;
		}
		return distance;
	}
}
