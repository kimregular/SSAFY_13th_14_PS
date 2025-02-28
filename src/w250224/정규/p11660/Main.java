package w250224.정규.p11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			Input ip = readInput(br);
			System.out.println(s.solution(ip.field, ip.ranges));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int fieldLen = Integer.parseInt(st.nextToken());
		int rangeLen = Integer.parseInt(st.nextToken());

		int[][] field = new int[fieldLen][fieldLen];

		for (int i = 0; i < fieldLen; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < fieldLen; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] ranges = new int[rangeLen][4];

		for (int i = 0; i < rangeLen; i++) {
			st = new StringTokenizer(br.readLine());
			ranges[i][0] = Integer.parseInt(st.nextToken());
			ranges[i][1] = Integer.parseInt(st.nextToken());
			ranges[i][2] = Integer.parseInt(st.nextToken());
			ranges[i][3] = Integer.parseInt(st.nextToken());
		}
		return new Input(field, ranges);
	}

	private static class Input {

		final int[][] field;
		final int[][] ranges;

		public Input(int[][] field, int[][] ranges) {
			this.field = field;
			this.ranges = ranges;
		}
	}
}

class Solution {

	private int[][] accumulatedField;

	public String solution(int[][] field, int[][] ranges) {
		init(field);

		StringBuilder result = new StringBuilder();
		for (int[] range : ranges) {
			result.append(getValueOf(range)).append("\n");
		}
		return result.toString().trim();
	}

	private void init(int[][] field) {
		int n = field.length;
		this.accumulatedField = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				accumulatedField[i][j] = field[i - 1][j - 1]
						+ accumulatedField[i - 1][j]
						+ accumulatedField[i][j - 1]
						- accumulatedField[i - 1][j - 1];
			}
		}
	}

	private int getValueOf(int[] range) {
		int startX = range[0];
		int startY = range[1];
		int endX = range[2];
		int endY = range[3];

		return accumulatedField[endX][endY]
				- accumulatedField[startX - 1][endY]
				- accumulatedField[endX][startY - 1]
				+ accumulatedField[startX - 1][startY - 1];
	}
}