package w250224.정규.p12891;

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
			System.out.println(s.solution(ip.len, ip.str, ip.requires));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		int len = Integer.parseInt(st.nextToken());
		String str = br.readLine();

		int requireLen = 4;
		int[] requires = new int[requireLen];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < requireLen; i++) {
			requires[i] = Integer.parseInt(st.nextToken());
		}

		return new Input(len, str, requires);
	}

	private static class Input {

		final int len;
		final String str;
		final int[] requires;

		public Input(int len, String str, int[] requires) {
			this.len = len;
			this.str = str;
			this.requires = requires;
		}
	}
}

class Solution {

	private int len;
	private String str;
	private int[] requires;
	private int result;

	public int solution(int len, String str, int[] requires) {
		init(len, str, requires);
		calc();
		return result;
	}

	private void init(int len, String str, int[] requires) {
		this.len = len;
		this.str = str;
		this.requires = requires;
		this.result = 0;
	}

	private void calc() {
		for (int i = 0; i < len; i++) {
			char letter = str.charAt(i);
			requires[Index.of(letter)]--;
		}

		if(isMeetingCondition()) result++;

		int idx = 0;
		for (int i = len; i < str.length(); i++) {
			requires[Index.of(str.charAt(idx++))]++;
			requires[Index.of(str.charAt(i))]--;
			if(isMeetingCondition()) result++;
		}
	}

	private boolean isMeetingCondition() {
		for (int require : requires) {
			if(require > 0) return false;
		}
		return true;
	}

	enum Index {
		A(0), C(1), G(2), T(3);

		int idx;

		Index(int idx) {
			this.idx = idx;
		}

		static int of(char letter) {
			switch (letter) {
				case 'A':
					return A.idx;
				case 'C':
					return C.idx;
				case 'G':
					return G.idx;
				default:
					return T.idx;
			}
		}
	}
}

