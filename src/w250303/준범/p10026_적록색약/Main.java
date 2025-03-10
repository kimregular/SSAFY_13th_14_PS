package w250303.준범.p10026_적록색약;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        char[][] data = new char[N][N];
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        for(int i=0;i<N;i++){
            data[i] = br.readLine().toCharArray();
        }

        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        int normal = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visited[i][j]){
                    visited[i][j] = true;
                    q.add(new int[]{i,j});
                    normal++;
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int curVal = data[cur[0]][cur[1]];

                        for(int k=0;k<4;k++){
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];
                            if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && data[nx][ny] == curVal){
                                q.add(new int[]{nx,ny});
                                visited[nx][ny] = true;

                            }
                        }
                    }
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(data[i][j] == 'G'){
                    data[i][j] = 'R';
                }
            }
        }

        visited = new boolean[N][N];
        q = new LinkedList<>();
        int cnt=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visited[i][j]){
                    visited[i][j] = true;
                    q.add(new int[]{i,j});
                    cnt++;
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int curVal = data[cur[0]][cur[1]];

                        for(int k=0;k<4;k++){
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];
                            if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && data[nx][ny] == curVal){
                                q.add(new int[]{nx,ny});
                                visited[nx][ny] = true;

                            }
                        }
                    }
                }
            }
        }
        System.out.println(normal + " " +cnt);

    }

}
