package src.w250210.정이.p2606;
import java.io.*;
import java.util.*;

public class Main {
	
	static int node;
	static int line;
	
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static boolean[] visit;

	public static void main(String[] args) throws Exception{
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		
		node = Integer.parseInt(br.readLine());
		line = Integer.parseInt(br.readLine());
		
		visit = new boolean[node];
		
		for(int i=0; i<node; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<line; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a1 = Integer.parseInt(st.nextToken()) -1;
			int a2 = Integer.parseInt(st.nextToken()) -1;
			
			list.get(a1).add(a2);
			list.get(a2).add(a1);
		}
		
		run();
	}
	
	public static void run() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		visit[0] = true;
		
		while(!queue.isEmpty()) {
			int index = queue.poll();
			
			for(int i=0; i<list.get(index).size(); i++) {
				int pcNum = list.get(index).get(i);
				if(visit[pcNum]) continue;
				
				queue.offer(pcNum);
				visit[pcNum] = true;
			}
		}
		count();
	}
	public static void count() {
		int cnt = -1;
		for(boolean chk : visit) {
			if(chk) cnt++;
		}
		System.out.println(cnt);
	}

}

