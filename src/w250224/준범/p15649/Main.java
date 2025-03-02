package w250224.준범.p15649;

import java.util.*;
import java.io.*;


class Main {
    static int n, m;
    static boolean[] visited;//1-based

    static int[] temp;
    static void perm(int idx) {
        if(idx == m) {
            for(int i=0;i<temp.length;i++) {
                System.out.print(temp[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=1;i<=n;i++) {
            if(visited[i]) continue;
            visited[i] = true;
            temp[idx] = i;
            perm(idx+1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        temp = new int[m];

        perm(0);

    }

}