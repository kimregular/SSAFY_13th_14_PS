package w250217.정규.p1759;

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
			System.out.println(s.solution(ip.LIMIT, ip.field));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int LIMIT = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken());

		String[] field = new String[len];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < len; i++) {
			field[i] = st.nextToken();
		}

		return new Input(LIMIT, field);
	}

	private static class Input {

		final int LIMIT;
		final String[] field;

		public Input(int LIMIT, String[] field) {
			this.LIMIT = LIMIT;
			this.field = field;
		}
	}
}

class Solution {

	private static final char[] vowels = {'a', 'e', 'i', 'o', 'u'};
	private static final int MIN_VOWELS = 1;
	private static final int MIN_CONSONANTS = 2;

	private int LIMIT;
	private String[] field;
	StringBuilder result;

	public String solution(int LIMIT, String[] field) {
		init(LIMIT, field);
		calc(0, new StringBuilder());
		return result.toString().trim();
	}

	private void init(int LIMIT, String[] field) {
		this.LIMIT = LIMIT;
		Arrays.sort(field);
		this.field = field;
		this.result = new StringBuilder();
	}

	private void calc(int cnt, StringBuilder combination) {
		if(combination.length() > LIMIT) return;
		if (cnt == field.length) {
			postProcess(combination);
			return;
		}

		combination.append(field[cnt]);
		calc(cnt + 1, combination);
		combination.setLength(combination.length() - 1);
		calc(cnt + 1, combination);
	}

	private void postProcess(StringBuilder candidate) {
		int numOfVowels = 0;
		int numOfConsonents = 0;

		for (int i = 0; i < candidate.length(); i++) {
			if(isVowel(candidate.charAt(i))) numOfVowels++;
			else numOfConsonents++;
		}

		if (candidate.length() == LIMIT && numOfVowels >= MIN_VOWELS && numOfConsonents >= MIN_CONSONANTS) {
			result.append(candidate).append("\n");
		}
	}

	private boolean isVowel(char target) {
		for (char vowel : vowels) {
			if(target == vowel) return true;
		}
		return false;
	}
}