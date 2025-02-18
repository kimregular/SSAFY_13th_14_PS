
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int cnt;
	static List<List<Integer>> list;
	static boolean[] visit;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		visit = new boolean[N+1];
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list.get(from).add(to);
			list.get(to).add(from);
		}
		find();	
	}
	
	public static void find() {
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i=1; i<=N; i++) {
			if(visit[i]) continue;
			
			queue.offer(i);
			visit[i] = true;
			
			while(!queue.isEmpty()) {
				List<Integer> nodeList = list.get(queue.poll());
				for(int node : nodeList) {
					if(visit[node]) continue;
					queue.offer(node);
					visit[node] = true;
				}
			}
			cnt++;
		}
		System.out.println(cnt);
	}

}
