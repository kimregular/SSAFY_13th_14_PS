package src.w250210.정이.p16236;
import java.util.*;
import java.io.*;

public class Main {
    
    static int N;
    static int totalTime = 0;
    static int sharkSize = 2;

    static int[][] ar;
    static boolean[][] visit;
    
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    
    static Queue<int[]> queue = new ArrayDeque<>();
    
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        N = Integer.parseInt(br.readLine());
        ar = new int[N][N];
        visit = new boolean[N][N];
        
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
                
                if(ar[i][j] == 9) {
                    queue.offer(new int[] {i, j, sharkSize, 0, 0});
                    ar[i][j] = 0;
                    visit[i][j] = true;
                }
            }
        }    
        shark();        
    }   
    
    public static void shark() {   
        while(!queue.isEmpty()) {
            ArrayList<int[]> list = new ArrayList<>();
            int qSize = queue.size();
            int time = 0;
            int eat = 0;

            for(int i=0; i<qSize; i++){
                int[] temp = queue.poll();
                int x = temp[0];
                int y = temp[1];
                sharkSize = temp[2];
                time = temp[3];
                eat = temp[4];
                        
                for(int k=0; k<4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    
                    //못지나감
                    if(nx<0 || nx>=N || ny<0 || ny>=N ||
                    ar[nx][ny] > sharkSize || visit[nx][ny]) continue;
                    //지나감
                    if(ar[nx][ny] == 0 || ar[nx][ny] == sharkSize) {
                        queue.offer(new int[] {nx, ny, sharkSize, time+1, eat});
                        visit[nx][ny] = true;
                        continue;
                    }
                    //먹을 수 있음
                    list.add(new int[] {nx, ny});
                    continue;
                }
            }
            if(!list.isEmpty() && list.size()>0)
                eat(list, time, eat);                                     
        } 
        System.out.println(totalTime);
    }

    public static void eat( ArrayList<int[]> list, int time, int eat) {
        int nx, ny;
        int[] temp = list.get(0);
        for(int[] arr : list) {
            if(arr[0] < temp[0]) temp = arr;
            else if(arr[0] == temp[0] && arr[1] < temp[1]) temp = arr;
        }     
        nx = temp[0];
        ny = temp[1];
        ar[nx][ny] = 0;
        eat++;
        totalTime = time + 1 ;
        visit = new boolean[N][N];
        visit[nx][ny] = true;               
        queue.clear();

        if(eat == sharkSize) queue.offer(new int[] {nx, ny, sharkSize+1, time+1, 0});
        else queue.offer(new int[] {nx, ny, sharkSize, time+1, eat});              
    }

}



