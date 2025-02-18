package w250210.가배.swea3234_준환이의양팔저울;

import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[] arr;
	static boolean[] v;
	static int ans;
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("src/w250210/가배/swea3234_준환이의양팔저울/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			v = new boolean[N];
			ans = 0;
			st = new StringTokenizer(br.readLine());
			
			for(int i=0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			chudfs(0,0,0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			
		}
		System.out.print(sb);

	}
	
	static void chudfs(int l, int r, int cnt) {
		
		if(l<r) return;
		
		if(cnt==N) {
			ans++;
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(v[i]) continue;
			
			v[i] = true;
			
			chudfs(l+arr[i],r, cnt+1);
			if(r+arr[i]<=l) {
				chudfs(l,r+arr[i],cnt+1);
			}
			v[i] = false;
		}
	}

}
