import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] nums, sum;

    public static void main(String[] args) throws IOException {
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           StringTokenizer st = new StringTokenizer(br.readLine());

           n = Integer.parseInt(st.nextToken());
           m = Integer.parseInt(st.nextToken());
           nums = new int[n+1][n+1];
           sum = new int[n+1][n+1];

           for(int i = 1; i <= n; i++){
               st = new StringTokenizer(br.readLine());
               for(int j = 1; j <= n; j++){
                   nums[i][j] = Integer.parseInt(st.nextToken());

                   // 입력 받으며 누적합 초기화
                   sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + nums[i][j];
               }
           }


           for(int i = 0; i < m; i++){
               int fromY, fromX, toY, toX;
               st = new StringTokenizer(br.readLine());
               fromY = Integer.parseInt(st.nextToken());
               fromX = Integer.parseInt(st.nextToken());
               toY = Integer.parseInt(st.nextToken());
               toX = Integer.parseInt(st.nextToken());

               // arr[fromY][fromX] + ... + arr[toY][toX] = sum[toY][toX] - sum[toY][fromX - 1] - sum[fromY - 1][toX] + sum[fromY - 1][fromX - 1]
               // sum[toY][toX] 에서 왼쪽 합, 위쪽 합 뺀 뒤 sum[fromY-1][fromX-1]은 두번 뺐으니 한번 더 해주기
               System.out.println(sum[toY][toX] - sum[toY][fromX - 1] - sum[fromY - 1][toX] + sum[fromY - 1][fromX - 1]);

           }


    }
}
