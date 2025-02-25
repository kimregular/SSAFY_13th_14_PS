package src.w250210.정이.p1012;

import java.util.*;
import java.io.*;
public class Main {

    static int[][] ar;
    static boolean[][] visit;
    static int N;
    static int M;
    static int beChu = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static Queue<int []> queue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        new Main().init();
    }

    public void init() throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int T = Integer.parseInt(br.readLine());

        for(int testCase=0; testCase<T; testCase++){
        // ---------
            String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]);
            M = Integer.parseInt(str[1]);
            beChu = Integer.parseInt(str[2]);

            ar = new int[N][M];
            visit = new boolean[N][M];

            for(int i=0; i<beChu; i++) {
                String index = br.readLine();
                StringTokenizer st = new StringTokenizer(index);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                ar[x][y] = 1;
            }

            run();
        }// -testCase-
    }

    public static void run() {
        int res = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(ar[i][j]==1 && !visit[i][j]){
                    find(i, j);
                    res++;
                }
            }
        }
        System.out.println(res);
    } // -run-

    public static void find(int i, int j) {
        queue.offer(new int[] {i, j});

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            for(int k=0; k<4; k++) {
                int nx = temp[0] + dx[k];
                int ny = temp[1] + dy[k];
                
                if(nx>=0 && nx<N && ny>=0 && ny<M && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    if(ar[nx][ny] == 1) {
                        queue.offer(new int[] {nx, ny});
                    }
                }

            }
        } // -while-
    } // -find-

}
