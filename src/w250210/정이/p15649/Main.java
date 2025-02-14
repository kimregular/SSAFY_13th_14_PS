
import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int[] ar;
	static boolean[] visit;
	static int[] answer;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ar = new int[n];
		visit = new boolean[n];
		answer = new int[m];
		
		for(int i=1; i<=n; i++) {
			ar[i-1] = i;
		}			
		find(0);
		System.out.println(sb);
	}
	
	public static void find(int depth) {
		if(depth == m) {

			for(int n : answer) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(visit[i]) continue;
			answer[depth] = ar[i];
			visit[i] = true;
			find(depth+1);
			visit[i] = false;
		}
		
	}
	
	public static void print(int[] answer) {
		for(int i=0; i<m; i++) {
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

}
