import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1697 {
	
	static int start;
	static int end;
	static int count = 0;
	static boolean[] visit = new boolean[100001];
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		run();
	}
	
	public static void run() {
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {start, count});
		
		while(!queue.isEmpty()) {
			int[] ar = queue.poll();
			int s = ar[0];
			int cnt = ar[1];
			
			if(s == end) {
				System.out.println(cnt); 
				break;
			}
			
			for(int i=0; i<3; i++) {
				int target = go(i, s); 
				
				if(target<0 || target>100000 || visit[target]) continue;
				
				queue.offer(new int[] {target, cnt+1});
				visit[target] = true;			
			}
		}
	
	}
	
	public static int go(int i, int start) {
		switch (i) {
		case 0: {
			return start - 1;
		}
		case 1: {
			return start + 1;
		}
		case 2: {
			return start * 2;
		}
		}
		return start;
	}
}
