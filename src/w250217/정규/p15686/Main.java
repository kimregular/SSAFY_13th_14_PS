package w250217.정규.p15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.reduceNum, ip.field));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int reduceNum = Integer.parseInt(st.nextToken());

		int[][] field = new int[len][len];

		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return new Input(reduceNum, field);
	}

	private static class Input {

		final int reduceNum;
		final int[][] field;

		public Input(int reduceNum, int[][] field) {
			this.reduceNum = reduceNum;
			this.field = field;
		}
	}
}

class Solution {

	private int reduceNum;
	private int[][] field;
	private List<int[]> homes;
	private List<int[]> chickens;
	private int[] savedChickenIndexes;
	private int result;

	public int solution(int reduceNum, int[][] field) {
		init(reduceNum, field);

		simulate(0, 0);

		return result;
	}

	private void init(int reduceNum, int[][] field) {
		this.reduceNum = reduceNum;
		this.field = field;
		this.homes = new ArrayList<>();
		this.chickens = new ArrayList<>();
		this.savedChickenIndexes = new int[reduceNum];
		this.result = Integer.MAX_VALUE;

		postProcess();
	}

	private void postProcess() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == 0) continue;
				if (field[i][j] == 1) homes.add(new int[]{i, j});
				if (field[i][j] == 2) chickens.add(new int[]{i, j});
			}
		}
	}

	private void simulate(int cnt, int startIdx) {
		if (cnt == reduceNum) {
			calcDistance();
			return;
		}
		for (int idx = startIdx; idx < chickens.size(); idx++) {
			savedChickenIndexes[cnt] = idx;
			simulate(cnt + 1, idx + 1);
		}
	}

	private void calcDistance() {
		int distanceSum = 0;

		for (int[] home : homes) {
			int shortestDistance = Integer.MAX_VALUE;
			for (int savedChickenIndex : savedChickenIndexes) {
				int tempDistance = getDistance(home, chickens.get(savedChickenIndex));
				if (shortestDistance <= tempDistance) continue;
				shortestDistance = tempDistance;
			}
			distanceSum += shortestDistance;
		}

		if (distanceSum < result) {
			result = distanceSum;
		}
	}

	private int getDistance(int[] home, int[] chicken) {
		return Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
	}
}