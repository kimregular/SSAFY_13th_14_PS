package w250224.정규.p16435;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			Input ip = readInput(br);
			System.out.println(s.solution(ip.size, ip.fruits));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfFruits = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());
		int[] fruits = new int[numOfFruits];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < numOfFruits; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(size, fruits);
	}

	private static class Input {

		final int size;
		final int[] fruits;

		public Input(int size, int[] fruits) {
			this.size = size;
			this.fruits = fruits;
		}
	}
}

class Solution {

	private PriorityQueue<Integer> q;

	public int solution(int size, int[] fruits) {
		init(fruits);

		while (!q.isEmpty() && size >= q.peek()) {
			q.poll();
			size++;
		}
		return size;
	}

	private void init(int[] fruits) {
		q = new PriorityQueue<>();
		for (int fruit : fruits) {
			q.offer(fruit);
		}
	}
}