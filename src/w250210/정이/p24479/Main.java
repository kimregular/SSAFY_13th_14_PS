
import java.io.*;
import java.util.*;

public class Main {
	
	static int node;
	static int link;
	static int depth = 1;
	
	static ArrayList<Integer>[] ar;
	static boolean[] visit;
	static int[] depthAr;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		node = Integer.parseInt(st.nextToken());
		link = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(st.nextToken());
		
		ar = new ArrayList[node+1];
		visit = new boolean[node+1];
		depthAr = new int[node+1];
		
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
			list.sort(null);
		}
		dfs(startNode);
		print();
	}
	
	public static void dfs(int startNode) {
		visit[startNode] = true;
		depthAr[startNode] = depth;
		
		for(int node : ar[startNode]) {
			if(visit[node]) continue;
			visit[node] = true;
			depth++;
			dfs(node);
		}
	}
	
	public static void print() {
		for(int i=1; i<depthAr.length; i++) {
			System.out.println(depthAr[i]);
		}
	}
}
