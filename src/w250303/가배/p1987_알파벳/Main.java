package w250303.가배.p1987_알파벳;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C, maxDist = 0;
    static char[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        Set<Character> v = new HashSet<>();
        v.add(arr[0][0]);
        dfs(0, 0, 1, v);
        System.out.println(maxDist);
    }

    static void dfs(int x, int y, int depth, Set<Character> v) {
        maxDist = Math.max(maxDist, depth);

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                char nextChar = arr[nx][ny];
                if (!v.contains(nextChar)) {
                    v.add(nextChar);
                    dfs(nx, ny, depth + 1, v);
                    v.remove(nextChar);
                }
            }
        }
    }
}