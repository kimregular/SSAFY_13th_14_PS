package w250217.준범.p1759_암호만들기;

import java.util.*;
import java.io.*;


class Main {
    static int L;
    static int C;

    static char[] data;

    static boolean[] visited;

    static boolean isVow(char x) {
        switch (x) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;

        }
    }

    static void bt(int sidx, int cnt) {
        if(cnt == L) {
            StringBuilder sb = new StringBuilder();
            int v=0 , c=0;
            for(int i=0;i<C;i++) {
                if(visited[i]) {
                    sb.append(data[i]);
                    if(isVow(data[i])) {
                        v++;
                    }else {
                        c++;
                    }
                }
            }
            if(v >= 1 && c >=2) System.out.println(sb.toString());
            return;
        }

        for(int i=sidx;i<C;i++) {
            visited[i] = true;
            bt(i+1,cnt+1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        data = new char[C];
        visited = new boolean[C];

        for(int i=0;i<C;i++) {
            data[i] =  st.nextToken().charAt(0);
        }

        Arrays.sort(data);

        bt(0,0);




    }
}