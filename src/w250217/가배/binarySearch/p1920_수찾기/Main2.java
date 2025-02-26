package w250217.가배.binarySearch.p1920_수찾기;

import java.io.*;
import java.util.*;
//풀이2 : 직접 구현(이게 제출하니까 좀 더 느렸음)
public class Main2 {

    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int x = Integer.parseInt(st.nextToken());
            boolean ans = binarySearch(arr, x);
            System.out.println(ans ? 1 : 0);
        }
    }
    
    static boolean binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] < x) l = m + 1;
            else if (arr[m] > x) r = m - 1;
            else return true;
        }
        return false;
    }
}