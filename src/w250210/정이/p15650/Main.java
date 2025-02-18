import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] visit;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();
  
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        answer = new int[M];

        find(0, 0);

        System.out.println(sb);
        br.close();
    }

    public static void find(int depth, int start) {
        if(depth == M) {
            for(int i : answer) sb.append(i).append(" ");           
            sb.append("\n");
            return;
        }
        for(int i=start; i<N; i++) {
            if(visit[i]) continue;
            answer[depth] = i+1;
            find(depth+1, i+1);            
        }
    }
}
