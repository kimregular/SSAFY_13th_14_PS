import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] ar;
    static boolean[] visit;
    static LinkedHashSet<String> set = new LinkedHashSet<>();

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ar = new int[N];
        visit = new boolean[N];
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ar);
        br.close();

        find(0, "");
        print();
    }

    public static void find(int depth, String str) {
        if(depth == M) {
            set.add(str);
            return;
        }
        for(int i=0; i<N; i++) {
            if(visit[i]) continue;
            String str2 = (str + " " + ar[i]).trim();
            visit[i] = true;
            find(depth+1, str2);
            visit[i] = false;
        }    
    }

    public static void print() {
        StringBuilder sb =new StringBuilder();
        for(String str : set) {
            sb.append(str).append("\n");
        }
        System.out.println(sb);
    }
}