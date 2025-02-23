package src.w250217.정이.p10816;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] n_ar;
    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        n_ar = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            n_ar[i] =  Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Arrays.sort(n_ar);

        for(int i=0; i<M; i++) {
            int m = Integer.parseInt(st.nextToken());
            int cnt = find(m);
            sb.append(cnt).append(" ");
        }
        System.out.println(sb);
    }

    public static int find(int target) {
        int low = findLow(target);
        int high = findHigh(target);
        return high - low;
    }

    public static int findLow(int target) {
        int low = 0;
        int high = N;

        while(low < high) {
            int mid = low + ((high - low) / 2); //int overflow 방지
            if(target <= n_ar[mid]) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    public static int findHigh(int target) {
        int low = 0;
        int high = N;

        while(low < high) {
            int mid = low + ((high - low) / 2);
            if(target < n_ar[mid]) high = mid;
            else low = mid + 1;
        }
        return low;
    }
}
