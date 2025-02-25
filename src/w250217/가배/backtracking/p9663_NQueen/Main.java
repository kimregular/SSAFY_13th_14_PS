package w250217.가배.backtracking.p9663_NQueen;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		N = Integer.parseInt(str);
		arr = new int[N];

		dfs(0);
		System.out.println(cnt);
	}

	public static void dfs(int row) {

		if (row == N) {
			cnt++;
			return;
		}

		for (int i = 0; i < N; i++) {
			arr[row] = i;
			if (canPlace(row)) {
				dfs(row + 1);
			}
		}
	}

	public static boolean canPlace(int curRow) {
		for (int prevRow = 0; prevRow < curRow; prevRow++) {
			//같은 열에 있으면 안됨
			if (arr[prevRow] == arr[curRow]) {
				return false;
			}
			//같은 대각선에 있으면 안됨 
			else if (Math.abs(curRow - prevRow) == Math.abs(arr[curRow] - arr[prevRow])) {
				return false;
			}

		}
		return true;
	}
}