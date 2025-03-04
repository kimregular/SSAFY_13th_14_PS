package w250224.가배.p11659_구간합구하기4;

import java.io.*;
import java.util.*;

public class Main {
	static int[] sum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		//배열에 값 넣기
		int[] arr = new int[N+1];
		int[] sum = new int[N+1];
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//구간합 계산
		for(int i=1;i<=N;i++) {
			sum[i] = sum[i-1]+arr[i];
		}
		
		//테스트케이스 입력 받으면 구간 합 출력
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			System.out.println(sum[r]-sum[l-1]);
		}
	}

}