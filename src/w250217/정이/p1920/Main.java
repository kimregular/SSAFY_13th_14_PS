
import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static int[] n_ar, m_ar;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		n_ar = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) n_ar[i] = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		m_ar = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) m_ar[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(n_ar);
		
		for(int m : m_ar) {
			boolean chk = find(m);
			System.out.println(chk? 1:0);
		}
	}
	
	public static boolean find(int m) {
		boolean chk = false;
		int low = 0;
		int high = N-1;
		while(low <= high) {
			int tp = (low + high) /2;
			if(m == n_ar[tp]) {
				chk = true;
				break;
			}
			if(m < n_ar[tp]) high = tp - 1;
			if(m > n_ar[tp]) low = tp + 1;
			
		}
		
		return chk;
	}

}
