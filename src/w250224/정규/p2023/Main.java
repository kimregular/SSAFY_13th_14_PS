package w250224.정규.p2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

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

	private int readInput(BufferedReader br) throws IOException {
		return Integer.parseInt(br.readLine());
	}
}

class Solution {

	private int LEN;
	private PriorityQueue<Integer> q;

	public String solution(int LEN) {
		init(LEN);
		calc();
		return getResult();
	}

	private void init(int LEN) {
		this.LEN = LEN;
		this.q = new PriorityQueue<>();
	}

	private void calc() {
		for (int i = 2; i < 10; i++) {
			calc(i);
		}
	}

	private void calc(int target) {
		if(isOutOfRange(target)) return;

		if(isNotPrime(target)) return;

		if(isWithinRange(target)) {
			q.offer(target);
		}

		for (int i = 1; i <= 9; i++) {
			calc(target * 10 + i);
		}
	}


	private boolean isOutOfRange(int target) {
		int cnt = 0;
		while (target > 0) {
			target /= 10;
			cnt++;
		}
		return cnt > LEN;
	}

	private boolean isNotPrime(int target) {
		if(target == 1) return true;
		for (int i = 2; i <= Math.sqrt(target); i++) {
			if(target % i == 0) return true;
		}
		return false;
	}

	private boolean isWithinRange(int target) {
		int cnt = 0;
		while (target > 0) {
			target /= 10;
			cnt++;
		}
		return cnt == LEN;
	}

	private String getResult() {
		StringBuilder result = new StringBuilder();
		while (!q.isEmpty()) {
			result.append(q.poll()).append("\n");
		}
		return result.toString().trim();
	}
}