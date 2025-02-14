package w250210.가배.p12851_숨바꼭질2;
/*숨바꼭질2:수빈이가 1초에 x-1, x+1, 2*x로 이동 ㄱㄴ시 
 * 동생을 찾을 수 있는 최단 시간과 해당 경로의 개수를 구하는 문제*/
import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[] arr = new int[100001];
	static int[] cnt = new int[100001];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(n);
		arr[n] = 1;
		cnt[n] = 1;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			if (now == k)
				break;
			
			int[] can_next = { now - 1, now + 1, now * 2 };
			for (int i = 0; i < 3; i++) {
				int next = can_next[i];
				
				if(!(0<=next&&next<100001))
					continue;
				
				if (arr[next] == 0) { //처음 방문
					arr[next] = arr[now] + 1;
					q.offer(next);
					cnt[next] = cnt[now];
				}else if(arr[next]==arr[now]+1) {//방문했었지만, 이전 단계에서 한 번의 이동으로 같은 최단시간이 걸리면
					cnt[next] += cnt[now]; //최단 경로 수 +1
				}
			}
		}
		
		System.out.println(arr[k]-1);
		System.out.println(cnt[k]);
	}

}