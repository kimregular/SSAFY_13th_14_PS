package src.w250217.정이.p2343;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, sum, total;
	static int[] ar;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			ar[i] = num;
			total += num;
		}
		
		find();
	}
	
	public static void find() {
		int low = ar[N-1];
		int hi = total;
		
		while(low >= hi) {
			int mid = (low+hi)/2;
			
			
		}
	}
	
	public static void splitAr(int index) {
		for(int i=index; i>=0; i--) {
			if(i == 0) break;
			
			sum += ar[i];
			// 배열안의 값이 토탈/M 보다 크면 무한반복
			if(sum > total/M) {
				sum = sum - ar[i];
				splitAr(i);
			}
		}
		return;
	}
	
}