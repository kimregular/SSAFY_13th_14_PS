package src.w250210.정이.p15652;
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
        ar = new int[M];

        find(0, 1);
        System.out.println(sb);

        br.close();
    }

    public static void find(int depth, int start) {
        if(depth == M) {
            for(int i : ar) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<=N; i++) {
            ar[depth] = i;
            find(depth+1, i);
        } 
            
    }
}