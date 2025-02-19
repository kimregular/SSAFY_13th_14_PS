package src.w250210.정이.p7562;
import java.util.*;
import java.io.*;

public class Main {

    static int T;
    static int N;
    static int[][] ar;
    static boolean[][] visit;
    static int targetX, targetY;

    static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    public static void main(String[] args) throws Exception {
        new Main().init();
    }

    public void init() throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        T = Integer.parseInt(br.readLine());

        for(int testCase=0; testCase<T; testCase++) {
            N = Integer.parseInt(br.readLine());
            ar = new int[N][N];
            visit = new boolean[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            targetX = Integer.parseInt(st.nextToken());
            targetY = Integer.parseInt(st.nextToken());

            run(a,b);
        }
    }

    public static void run(int startX, int startY) {
        Queue<int[]> queue = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();

        queue.offer(new int[] {startX, startY});
        
        visit[startX][startY] = true;
        if(startX == targetX && startY == targetY){
            list.add(ar[startX][startY]);
        } else {
            while(!queue.isEmpty()){
                int[] temp = queue.poll();
                
                for(int k=0; k<8; k++){
                    int nx = temp[0] + dx[k];
                    int ny = temp[1] + dy[k];
                    
                    if(nx>=0 && nx<N && ny>=0 && ny<N && !visit[nx][ny]) {
                        visit[nx][ny] = true;
                        ar[nx][ny] = ar[temp[0]][temp[1]] + 1;
                        queue.offer(new int[] {nx, ny});
                        if(nx == targetX && ny == targetY) {
                            list.add(ar[nx][ny]);
                        }
                    }
                }
            }
        }
        searchMin(list);
}

    public static void searchMin(ArrayList<Integer> list) {
        int min = list.get(0);
        for(int i=1; i<list.size(); i++){
            if(min > list.get(i)){
                min = list.get(i);
            }
        }
        System.out.println(min);
    }
}
