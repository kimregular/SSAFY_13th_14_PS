package src.w250210.정이.p1260;
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer>[] ar;
	static int node;
	static int link;
	static boolean[] visit;

	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		node = Integer.parseInt(st.nextToken());
		link = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(st.nextToken());
		
		ar = new ArrayList[node+1];
		visit = new boolean[node+1];
		
		for(int i=0; i<=node; i++) {
			ar[i] = new ArrayList<>();
		}
		
		for(int i=0; i<link; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			ar[from].add(to);
			ar[to].add(from);
		}
		for(List<Integer> list : ar) {
			Collections.sort(list);
		}
		
		dfs(startNode);
		System.out.println();
		visit = new boolean[node+1];
		bfs(startNode);
	}

	public static void dfs(int startNode) {
		visit[startNode] = true;
		System.out.print(startNode + " ");
		List<Integer> list = ar[startNode];
		
		for(int n : list) {
			if(visit[n]) continue;
			dfs(n);
		}
		
		
		
	}
	
	public static void bfs(int startNode) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(startNode);
		visit[startNode] = true;
		
		while(!queue.isEmpty()) {
			int start = queue.poll();
			System.out.print(start + " ");
			List<Integer> list = ar[start];
			
			for(int n : list) {
				if(visit[n]) continue;
				queue.offer(n);
				visit[n] = true;
			}
		}
	}
}
