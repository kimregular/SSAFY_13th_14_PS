import java.io.*;
import java.util.*;

public class Main {

    static int R, C, K, answer;
    static int[][] golemAr;
    static int[][] map;
    static boolean[][] visit, v;
    static int[] dx = {0,1,0, -1,0,1, -1,0,1}, dy={-1,0,1, 0,-1,0, 0,1,0}; // 하단, 왼쪽, 오른쪽 (왼 -> 우, 위 -> 아래)
    
    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        golemAr = new int[K][3];

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            golemAr[i][0] = Integer.parseInt(st.nextToken()) -1;
            golemAr[i][1] = Integer.parseInt(st.nextToken());
            golemAr[i][2] = i + 1; //골램번호
        }

        start();
        System.out.println(answer);
    }

    public static void start() {
        for(int[] golem : golemAr) {
            v = new boolean[R][C];
            int row = -1;
            int col = golem[0];
            int exit = golem[1];
            int golemNumber = golem[2];
            
            while(true) {
                if(row>0 && col>0) v[row][col] = true;

                if(ifDown(row, col)){
                    row += 1;
                }else if(ifLeft(row-1, col-1) && ifDown(row, col-1)){
                    col -= 1;
                    if(exit == 0) exit = 3;
                    else exit -= 1;
                }else if(ifRight(row-1, col+1) && ifDown(row, col+1)){
                    col += 1;
                    if(exit == 3) exit = 0;
                    else exit += 1;
                }else{
                    if(row < 2) {
                        map = new int[R][C];
                        break;
                    }
                    golemStop(row, col, exit, golemNumber);
                    findMaxDown(row-1, col); 
                    break;
                }
            }         
            
        }
    }

    public static void golemStop(int row, int col, int exit, int golemNumber) {
        map[row-2][col] = golemNumber; // head
        map[row-1][col-1] = golemNumber; // left
        map[row-1][col] = golemNumber; // center
        map[row-1][col+1] = golemNumber; // right
        map[row][col] = golemNumber; // foot

        switch (exit) { // 출구는 77777
            case 0:
                map[row-2][col] = 77777 + golemNumber;
                break;
        
            case 1:
                map[row-1][col+1] = 77777 + golemNumber;
                break;
        
            case 2:
                map[row][col] = 77777 + golemNumber;
                break;
        
            case 3:
                map[row-1][col-1] = 77777 + golemNumber;
                break;
        }
    }

    // 정령이 최대한 하단으로 이동
    public static void findMaxDown(int x, int y) {
        int[] dr={-1, 0, 1, 0}, dc={0, 1, 0, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        int maxRow = 0;
        visit = new boolean[R][C];
        queue.offer(new int[] {x, y});
        visit[x][y] = true;

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            int r = temp[0];
            int c = temp[1];
            int golemNumber = map[r][c];
            boolean isExit = false;
            if(golemNumber > 70000) isExit = true;
            maxRow = Math.max(maxRow, r);

            for(int k=0; k<4; k++) {
                int nx = r + dr[k];
                int ny = c + dc[k];


                if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == 0 || visit[nx][ny]) continue;

                if(isExit) { // 현재 위치가 출구
                    queue.offer(new int[] {nx, ny});
                    visit[nx][ny] = true;
                } 
 
                if(map[nx][ny] == 77777 + golemNumber || map[nx][ny] == golemNumber) { // 같은 골램 위 이동
                    queue.offer(new int[] {nx, ny});
                    visit[nx][ny] = true;
                }
            }
        }
        System.out.println(maxRow+1);
        answer += maxRow+1;
    }

    // 한 칸 내려갈 수 있나 확인 
    public static boolean ifDown(int row, int col) {
        for(int k=0; k<3; k++) {
            int nx = row + dx[k];
            int ny = col + dy[k];

            if(nx<0) continue;
            if(ny<0 || nx>=R || ny>=C || map[nx][ny] != 0) return false;
        }
        return true;
    }

    public static boolean ifLeft(int row, int col) {
        for(int k=3; k<6; k++) {
            int nx = row + dx[k];
            int ny = col + dy[k];

            if(nx<0) continue;
            if(ny<0 || nx>=R || ny>=C || map[nx][ny] != 0 || v[nx][ny]) return false;
        }
        return true;
    }
 
    public static boolean ifRight(int row, int col) {
        for(int k=6; k<9; k++) {
            int nx = row + dx[k];
            int ny = col + dy[k];

            if(nx<0) continue;
            if(ny<0 || nx>=R || ny>=C || map[nx][ny] != 0 || v[nx][ny]) return false;
        }
        return true;
    }

}
