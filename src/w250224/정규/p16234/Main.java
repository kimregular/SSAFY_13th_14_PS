package w250224.정규.p16234;

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
			Input ip = readInput(br);
			System.out.println(s.solution(ip.lowerBound, ip.upperBound, ip.field));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int lowerBound = Integer.parseInt(st.nextToken());
		int upperBound = Integer.parseInt(st.nextToken());
		int[][] field = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return new Input(lowerBound, upperBound, field);
	}

	private static class Input {

		final int lowerBound;
		final int upperBound;
		final int[][] field;

		public Input(int lowerBound, int upperBound, int[][] field) {
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
			this.field = field;
		}
	}
}

class Solution {

	private Field field;

	public int solution(int lowerBound, int upperBound, int[][] field) {
		init(lowerBound, upperBound, field);
		return calc();
	}

	private void init(int lowerBound, int upperBound, int[][] field) {
		this.field = new Field(lowerBound, upperBound, field);
	}

	private int calc() {
		int days = 0;

		while (field.hasDistributeTargets()) {
			field.distribute();
			days++;
		}
		return days;
	}
}

class Field {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	private int lowerBound;
	private int upperBound;
	private int[][] map;
	private boolean[][] visited;
	private List<DistributeTarget> targets;

	public Field(int lowerBound, int upperBound, int[][] map) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.map = map;
	}

	public boolean hasDistributeTargets() {
		int n = map.length;
		visited = new boolean[n][n];
		targets = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j]) continue;
				explore(i, j);
			}
		}
		return !targets.isEmpty();
	}

	private void explore(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		visited[x][y] = true;

		DistributeTarget target = new DistributeTarget(getPopulationOf(x, y), new int[]{x, y});

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if (isOutOfBounds(nx, ny)) continue;
				if (visited[nx][ny]) continue;
				if (isNotMeetingCondition(getPopulationOf(cur[0], cur[1]), getPopulationOf(nx, ny))) continue;

				q.offer(new int[]{nx, ny});
				visited[nx][ny] = true;
				target.addLocation(new int[]{nx, ny});
				target.addPopulation(getPopulationOf(nx, ny));
			}
		}

		if (target.isValid()) {
			targets.add(target);
		}
	}

	private int getPopulationOf(int x, int y) {
		return map[x][y];
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= map.length || y < 0 || y >= map.length;
	}

	private boolean isNotMeetingCondition(int pop1, int pop2) {
		return upperBound < Math.abs(pop1 - pop2) || Math.abs(pop1 - pop2) < lowerBound;
	}

	public void distribute() {
		for (DistributeTarget target : targets) {
			int newPop = target.getCalcedPop();
			for (int[] location : target.getLocations()) {
				setPopulationOf(location, newPop);
			}
		}
	}

	private void setPopulationOf(int[] location, int pop) {
		map[location[0]][location[1]] = pop;
	}
}

class DistributeTarget {

	private int population;
	private List<int[]> locations;

	public DistributeTarget(int population, int[] location) {
		this.population = population;
		this.locations = new ArrayList<>();
		locations.add(location);
	}

	public void addPopulation(int pop) {
		population += pop;
	}

	public void addLocation(int[] location) {
		locations.add(location);
	}

	public boolean isValid() {
		return locations.size() > 1;
	}

	public int getCalcedPop() {
		return population / locations.size();
	}

	public List<int[]> getLocations() {
		return locations;
	}
}