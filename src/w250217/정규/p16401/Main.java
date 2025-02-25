package w250217.정규.p16401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.target, ip.cookies));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int target = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken());
		int[] cookies = new int[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			cookies[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(target, cookies);
	}

	private static class Input {

		final int target;
		final int[] cookies;

		public Input(int target, int[] cookies) {
			this.target = target;
			this.cookies = cookies;
		}
	}
}

class Solution {

	public int solution(int target, int[] cookies) {
		Arrays.sort(cookies);
		int lp = 1;
		int rp = cookies[cookies.length - 1];

		while (lp <= rp) {
			int mid = (lp + rp) / 2;

			int cnt = 0;
			for (int cookie : cookies) {
				if (cookie < mid) continue;
				cnt += cookie / mid;
			}

			if (cnt >= target) {
				lp = mid + 1;
			} else {
				rp = mid - 1;
			}
		}
		return lp - 1;
	}
}

