package w250224.준범.p16234;

import java.util.*;
import java.io.*;


class Main {
    static int n, L,R;
    static int[][] data;
    static boolean[][] visited;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static List<List<int[]>> list = new ArrayList<>();
    //list : 하루에 생성되는 연합들의 list

    static int days=0;

    static void findUnion() {
        visited = new boolean[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(!visited[i][j]) {//방문x 국가 발견 -> 새로운 연합

                    bfs(i,j);//list에 new 연합 추가, 해당 연합에 국가 더하기
                }
            }
        }
    }

    static void bfs(int i, int j) {
        List<int[]> union = new ArrayList<>();//추가할 연합 생성

        Queue<int[]> queue = new LinkedList<>();//for bfs
        queue.add(new int[] {i,j});
        visited[i][j] = true;

        union.add(new int[] {i,j});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int k=0;k<4;k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx<0 || ny < 0|| n <= nx || n <= ny) continue;
                if(visited[nx][ny]) continue;
                //visited[nx][ny] = true;
                //-> 여기에 true 할 경우 현재는 갈 수 없어도 다른 칸에서 갈 수 있는 경우 체크 못함

                int diff = Math.abs(data[x][y] - data[nx][ny]);
                if(L <= diff && diff <= R) {
                    visited[nx][ny] = true; // v 위치!!!
                    union.add(new int[] {nx,ny});
                    queue.add(new int[] {nx,ny});
                }
            }
        }

        list.add(union);
    }

    static boolean move() {
        // 이동이 있으면 true : 연합이어도 이동X 가능
        // 연합 내에 하나라도 다르면 이동O
        boolean flag = false;

        for(int i=0;i<list.size();i++) {
            Set<Integer> set = new HashSet<>();

            List<int[]> union = list.get(i);
            int sum = 0;
            for(int[] country : union) {
                sum += data[country[0]][country[1]];
                set.add(data[country[0]][country[1]]);
            }

            //연합 내 모든 국가 인구수 동일 -> 이동 X
            //한 연합의 한 국가라도 움직이면 해당 일 이동 O
            //한 연합의 한 국가라도 인구 수가 다른 나라와 다른 경우 이동
            if(set.size() != 1) flag = true;;


            int val = sum / union.size();


            for(int[] country : union) {
                data[country[0]][country[1]] = val;
            }

        }

        list = new ArrayList<>(); // 해당 일 끝 -> list 초기화
        return flag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        data = new int[n][n];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            findUnion();
            boolean flag = move();
            if(flag) {//이동 O
                days++;
            }else {//이동 x
                break;
            }
        }
        System.out.println(days);

    }

}