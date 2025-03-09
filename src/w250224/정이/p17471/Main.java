import java.io.*;
import java.util.*;

public class Main {
	
	static int N, minValue = Integer.MAX_VALUE;
	static int[] ar, isGroup;
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		ar = new int[N+1];
		isGroup = new int[N+1];
		graph = new ArrayList[N+1];

		for(int i=1; i<=N; i++) {
			ar[i] = Integer.parseInt(st.nextToken());
			graph[i] = new ArrayList<>();
		}
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				graph[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		subs(1);
		br.close();
		System.out.println(minValue==Integer.MAX_VALUE ? -1 : minValue);
	}

	public static void subs(int cnt) {
		if(cnt == N+1) {
			splitGraph();
			return;
		}
		isGroup[cnt] = 1;
		subs(cnt+1);

		isGroup[cnt] = 2;
		subs(cnt+1);
	}

	public static void splitGraph() {
		boolean chk1 = false; boolean chk2 = false;
		int cnt1 = 0; int cnt2 = 0;
		int sum1 = 0; int sum2 = 0;
		int st1 = 1; int st2 = 1;
		for(int i=1; i<=N; i++){
			if(isGroup[i] == 1) {
				cnt1 ++;
				sum1 += ar[i];
				st1 = i;
			}
			if(isGroup[i] == 2) {
				cnt2 ++;
				sum2 += ar[i];
				st2 = i;
			}
		}
		if(cnt1 > 0) chk1 = bfs(st1, cnt1, 1);
		if(cnt2 > 0) chk2 = bfs(st2, cnt2, 2);

		if(chk1 && chk2) setMinValue(sum1, sum2);
	}

	public static boolean bfs(int startNode, int groupSize, int groupNum) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visit = new boolean[N+1];
		int cnt = 1;
		queue.offer(startNode);
		visit[startNode] = true;

		while(!queue.isEmpty()) {
			int start = queue.poll();
			for(int node : graph[start]) {
				if(visit[node] || isGroup[node] != groupNum) continue;
				visit[node] = true;
				queue.offer(node);
				cnt++;
			}
		}
		if(cnt == groupSize) return true;
	 	else return false;
	}

	public static void setMinValue(int sum1, int sum2) {
		int sum = Math.abs(sum1 - sum2);
		minValue = Math.min(minValue, sum);
	}

}
