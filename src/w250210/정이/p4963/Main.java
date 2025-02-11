import java.io.*;
import java.util.*;
public class Main {

    static String testCase;
    static int N; 
    static int M;

    static int[][] ar;
    static boolean[][] visit;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
    public static void main(String[] args) throws Exception{
        new Main().init();
    }

    public void init() throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        while(true) {
            String str[] = br.readLine().split(" ");

            if(str[0].equals("0") && str[1].equals("0")){
                break;
            }

            N = Integer.parseInt(str[1]);
            M = Integer.parseInt(str[0]);

            ar = new int[N][M];
            visit = new boolean[N][M];

            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    ar[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            run();
        }
    }

    public static void run() {
        int res = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(ar[i][j] == 1 && !visit[i][j]) {
                    find(i, j);
                    res++;
                }
            }
        }
        System.out.println(res);
    }

    public static void find(int i, int j) {
        Queue<int []> queue = new ArrayDeque<>();

        queue.offer(new int[] {i ,j});

        while(!queue.isEmpty()) {
            int temp[] = queue.poll();

            for(int k=0; k<8; k++){
                int nx = temp[0] + dx[k];
                int ny = temp[1] + dy[k];

                if(nx>=0 && nx<N && ny>=0 && ny<M && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    if(ar[nx][ny] == 1) {
                        queue.offer(new int[] {nx, ny});
                    }
                }
            }
        }
    }
}
