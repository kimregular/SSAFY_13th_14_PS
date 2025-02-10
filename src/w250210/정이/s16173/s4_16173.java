// 실버4 16173 [점프왕 쩰리]
package w250210.정이.s16173;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class s4_16173 {
    
    public static void main(String[] args) throws Exception {
//       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("C:\\SSAFY Study\\SSAFY_13th_14_PS\\src\\w250210\\정이\\s16173\\input.txt"));
        int N = Integer.parseInt(br.readLine());
        
/*      
        int[] ar = new int[N*N];
        int index = 0;
        String line;
        while((line = br.readLine()) != null) {
            String[] str = line.split(" ");
            for(String s : str) {
                ar[index++] = Integer.parseInt(s);
            }
        }
*/
        int[][] ar = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N*N];
        
        if(N == 2){

        }
        else {
            int index_ar = 0;
            for(int now : ar){
                queue.add(now);
                visit[index_ar++] = true;

                while(!queue.isEmpty()) {
                   int start = queue.remove();

                   // c위치에서 우 , 하 방향으로 탐색하는 함수
                   bfs(start, visit);

                }
            }

        }

        //System.out.println("HaruHaru");
        //System.out.println("Hing");
    }

    public static void bfs(int start, boolean[] visit) {
        
    }
}
