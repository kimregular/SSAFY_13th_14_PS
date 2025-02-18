import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P6118 {
	
	static int N;
	static int M;
	static boolean[] visit;
	static int[] count;
//	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static ArrayList<Integer>[] ar;
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()) +1;
		M = Integer.parseInt(st.nextToken());
		ar = new ArrayList[N];
		visit = new boolean[N];
		count = new int[N];
		
//		for(int i=0; i<N; i++) {
//			list.add(new ArrayList<>());
//		}
//		
//		for(int i=0; i<M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int index = Integer.parseInt(st.nextToken());
//			int item = Integer.parseInt(st.nextToken());
//			
//			list.get(index).add(item);
//		}
			
		for(int i=1; i<N; i++) {
			ar[i] = new ArrayList<Integer>();
		}
		int[] a1 = new int[M];
		int[] a2 = new int[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			int item = Integer.parseInt(st.nextToken());

			ar[item].add(index);
			ar[index].add(item);
		}
		run();	
	}
	
	public static void run() {
		Queue<Integer[]> queue = new ArrayDeque<>();
		queue.offer(new Integer[] {1, 0});
		visit[1] = true;
		while(!queue.isEmpty()) {
			Integer[] temp = queue.poll();
			int start = temp[0];
			int cnt = temp[1];
			count[start] = cnt;
			
			for(int i=0; i<ar[start].size(); i++) {
				int node = ar[start].get(i);
				if(visit[node]) continue;
				queue.offer(new Integer[] {node, cnt+1});
				visit[node] = true;
			}
		}
		count();
	}
	
	public static void count() {
		int resNum = 0;;
		int max = 0;
		int cnt = 0;
		for(int i=0; i<count.length; i++) {
			if(count[i] > max) max = count[i];
		}	
		for(int i=count.length-1; i>=0; i--) {
			if(count[i] == max) {
				resNum = i;
				cnt++;
			}
		}
		
		System.out.println(resNum + " " + max+ " " + cnt);
	}
}
