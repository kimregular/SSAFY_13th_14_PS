package src.w250210.정이.p15657;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] ar;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ar = new int[N];
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ar);

        find(0,0, "");
        System.out.println(sb);

        br.close();
    }

    public static void find(int start, int depth, String str) {
        if(depth == M) {
            sb.append(str).append("\n");
            return;
        }
        for(int i=start; i<N; i++) {
            String str2 = (str + " " + ar[i]).trim();
            find(i, depth+1, str2);
        }

            
    }
}