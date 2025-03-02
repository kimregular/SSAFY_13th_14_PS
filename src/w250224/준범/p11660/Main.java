package w250224.준범.p11660;

import java.util.*;
import java.io.*;


class Main {
    static int n;
    static int[][] data;
    static int[][] dp;
    static int[][] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        data = new int[n+1][n+1];
        dp = new int[n+1][n+1];
        points = new int[m][4];

        for(int i=1;i<=n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=n;j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<=n;i++) {
            data[0][i] = 0;
            data[i][0] = 0;
            dp[0][i] = 0;
            dp[i][0] = 0;
        }

        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<4;j++) {
                points[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + data[i][j];
            }
        }

        for(int[] pt : points) {
            int x1 = pt[0];
            int y1 = pt[1];
            int x2 = pt[2];
            int y2 = pt[3];

            System.out.println(dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1]);
        }

    }

}