import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, cnt=1;
	static boolean[][] chk;
	static int[] ar;

	public static void main(String[] args) throws Exception{
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new FileReader("JY/input.txt"));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ar = new int[N];
		chk = new boolean[N+1][N+1];

		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			chk[i][j] = true;
		}

		// --
		while(true){
			ArrayList<Integer> list = new ArrayList<>();
			if(checkEnd()) break;

			for(int j=1; j<=N; j++){		
				if(check(j)){
					ar[j-1] = cnt;
					chk[0][j] = true;
					list.add(j);
				}
			}
			for(int j : list){
				Arrays.fill(chk[j], false);
			}
			cnt++;
		}
		for(int n : ar) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
	}

	public static boolean check(int j) {
		for(int i=0; i<=N; i++) {
			if(chk[i][j]) return false;
		}
		return true;
	}

	public static boolean checkEnd() {
		for(int i=1; i<=N; i++){
			if(!chk[0][i]) return false;
		}
		return true;
	}
}
