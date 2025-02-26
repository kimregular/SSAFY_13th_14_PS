package w250210.가배.p9466_텀프로젝트;

import java.io.*;
import java.util.*;

public class Main {
    static int[] nextStu;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            nextStu = new int[n + 1];
            depth = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                nextStu[i] = Integer.parseInt(st.nextToken());
                depth[i] = 0;
            }

            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (depth[i] == 0) {
                    depth[i] = 1;
                    cnt += dfs(i);
                }
            }
            sb.append(n - cnt).append("\n");
        }
        System.out.print(sb);
    }

    public static int dfs(int nodeNum) {
        int next = nextStu[nodeNum];
        int cycleCnt = 0;
        if (depth[next] == 0) {
            depth[next] = depth[nodeNum] + 1;
            cycleCnt = dfs(next);
        }
        else {
            cycleCnt = depth[nodeNum] - depth[next] + 1;
        }
        
        depth[nodeNum] = 100001;
        return cycleCnt < 0 ? 0 : cycleCnt;
    }
}
