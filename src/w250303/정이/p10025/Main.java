import java.io.*;
import java.util.*;

public class Main {

    static int N, K, temp, res=0;
    static int ar[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K>500_000) K = 500_000;

        ar = new int[1_000_001];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int ice = Integer.parseInt(st.nextToken());
            ar[Integer.parseInt(st.nextToken())] = ice;
        }

        int to = K*2;
        for(int i=0; i<=to; i++) {
            res += ar[i];
        }

        temp = res;
        for(int i=1; i<1_000_001; i++) {
            int cnt = temp;
            to++;
            if(to>1_000_000) break;
            cnt += ar[to] - ar[i-1];
            temp = cnt;
            if(res < cnt) res = cnt;
        }
        System.out.println(res);
    }

}
