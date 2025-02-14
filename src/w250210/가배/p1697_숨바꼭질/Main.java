package w250210.가배.p1697_숨바꼭질;
/*숨바꼭질 : 1초에 x-1, x+1, 2*x로 이동 ㄱㄴ시 동생을 찾을 수 있는 가장 빠른 시간 구하기*/
import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[] arr = new int[100001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(n);
		arr[n] = 1;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			if (now == k)
				break;
			
			int[] can_next = { now - 1, now + 1, now * 2 };
			for (int i = 0; i < 3; i++) {
				int next = can_next[i];
				if(!(0<=next&&next<100001))
					continue;
				if (arr[next] == 0) {
					arr[next] = arr[now] + 1;
					q.offer(next);
				}
			}
		}
		
		System.out.println(arr[k]-1);
	}

}