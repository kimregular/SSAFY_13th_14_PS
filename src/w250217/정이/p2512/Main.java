package src.w250217.정이.p2512;
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] ar;
	static int total;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		total = Integer.parseInt(br.readLine());
		
		ar = new int[N];
		for(int i=0; i<N; i++) {
			ar[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(find());
	}
	
	public static int find() {
		int res = 0;
		int low = 0;
		int high = Arrays.stream(ar).max().getAsInt();
		
		while(low <= high) {
			int sum = 0;
			int mid = (low + high) / 2;
			
			for(int n : ar) {
				sum += Math.min(n, mid);
			}
			if(sum > total) {
				high = mid - 1;
			}else {
				low = mid + 1;
				res = mid;
			}
		}
		return res;
		
	}

}
