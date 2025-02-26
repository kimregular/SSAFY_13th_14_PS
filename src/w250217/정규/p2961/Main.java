package w250217.정규.p2961;

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
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[][] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[][] ingredients = new int[len][2];
		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}
		return ingredients;
	}
}

class Solution {

	private int[][] ingredients;
	private int result = Integer.MAX_VALUE;

	public int solution(int[][] ingredients) {
		init(ingredients);
		calc(0, 0, 1, 0);
		return result;
	}

	private void init(int[][] ingredients) {
		this.ingredients = ingredients;
	}

	private void calc(int cnt, int pickCnt, int sour, int bitter) {
		if (cnt == ingredients.length) {
			if (pickCnt > 0 && result > Math.abs(sour - bitter)) {
				result = Math.abs(sour - bitter);
			}
			return;
		}

		calc(cnt + 1, pickCnt, sour, bitter);
		calc(cnt + 1, pickCnt + 1, sour * ingredients[cnt][0], bitter + ingredients[cnt][1]);
	}
}