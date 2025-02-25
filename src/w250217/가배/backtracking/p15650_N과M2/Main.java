package w250217.가배.backtracking.p15650_N과M2;

/*1부터 N까지 자연수 중에서 중복 없이 M개를 고르는데 오름차순인 수열 모두 구하기*/
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr; // 현재 선택된 수열 저장
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		dfs(0, 1);
		System.out.println(sb);
	}

	public static void dfs(int depth, int start) {
		if (depth == M) { // M개의 숫자 선택되면..~
			for (int num : arr) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}

		//이전 숫자보다 큰 숫자만 탐색하므로 중복 발생 자체가 불가능!!해서 v지움
		for (int i = start; i <= N; i++) { //start부터 N까지 숫자 선택
			arr[depth] = i; // 수열에 추가
			dfs(depth + 1, i + 1); // i+1임을 주의하자..ㅎ 
		}
	}
}
