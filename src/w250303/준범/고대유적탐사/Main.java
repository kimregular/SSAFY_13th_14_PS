package w250303.준범.고대유적탐사;

import java.util.*;
import java.io.*;

public class Main {
    static int k;
    static int m;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int[] wall;
    static int wallIdx;

    static int[][] data = new int[5][5];

    static int cur_sum;
    static boolean nothing;
    static boolean chainget;
    
    static List<List<int[]>> groups = new LinkedList<>();

    //target 배열 중심좌표 기준 90도 시계방향 회전
    static void rotate90(int x,int y, int[][] target) {

        int[][] origin = new int[3][3];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                origin[i][j] = target[x-1+i][y-1+j];
            }
        }

        int[][] temp = new int[3][3];
        // 90도 회전: 기존 (i, j) → 새로운 (j, n - 1 - i)

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = origin[2 - j][i]; // (i, j) → (j, 2 - i) 로 회전
            }
        }

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                target[x-1+i][y-1+j] = temp[i][j];
            }
        }
    }

    //회전할 좌표 , 회전 수 찾기
    static int[] check() {
        //target[0] , target[1] : x,y | target[2] = 회전수(90도 회전을 몇번?)
        int[] result = new int[3];

        int max = 0;

        //각 점에서 (3x3 submatrix의 중점이 가능한 점) 90도, 180도,270도 회전 가능
        //모든 경우에서 최댓값일 때 해당 점, 회전 횟수 저장
        for(int j=1;j<=3;j++) {
            for(int i=1;i<=3;i++) {

                int[][] temp = new int[5][5];
                for(int f = 0; f < 5; f++) {
                    System.arraycopy(data[f], 0, temp[f], 0, 5);
                }

                //1번 회전 후 -> 탐색 전 초기화
                boolean visited[][] = new boolean[5][5];
                int sum = 0;
                rotate90(i,j,temp);
                for(int a = 0;a<5;a++) {
                    for(int b=0;b<5;b++) {
                        if(!visited[a][b]){
                            sum += bfs(a,b, visited,temp);
                        }
                    }
                }
                if(sum > max || (sum == max && 1 < result[2])) {
                    max = sum;
                    result[0] = i;
                    result[1] = j;
                    result[2] = 1;
                }

                //2번 회전 후(1번 회전 후 한번 더 회전) 		-> 탐색 전 초기화
                visited = new boolean[5][5];
                sum = 0;
                rotate90(i,j,temp);
                for(int a = 0;a<5;a++) {
                    for(int b=0;b<5;b++) {
                        if(!visited[a][b]){
                            sum += bfs(a,b, visited,temp);
                        }
                    }
                }
                if(sum > max || (sum == max && 2 < result[2]) ) {
                    max = sum;
                    result[0] = i;
                    result[1] = j;
                    result[2] = 2;
                }

                //3번회전 후(2번 회전 후 한번 더 회전) -> 탐색 전 초기화
                visited = new boolean[5][5];
                sum = 0;
                rotate90(i,j,temp);
                for(int a = 0;a<5;a++) {
                    for(int b=0;b<5;b++) {
                        if(!visited[a][b]){
                            sum += bfs(a,b, visited,temp);
                        }
                    }
                }
                if(sum > max || (sum == max && 3 < result[2])) {
                    max = sum;
                    result[0] = i;
                    result[1] = j;
                    result[2] = 3;
                }

            }
        }

        if(max == 0) {
            nothing = true;
        }

        return result;
    }

    static int bfs(int x, int y, boolean[][] visited, int[][] target) {    	
        List<int[]> temp = new LinkedList<>();
        Deque<int[]> dq = new ArrayDeque<>();
        dq.push(new int[] {x,y});

        temp.add(new int[] {x,y});
        visited[x][y] = true;
        


        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int cx = cur[0];
            int cy = cur[1];
            for(int i=0;i<4;i++) {
                int nx = cx+dx[i];
                int ny = cy+dy[i];
                if(nx < 0 || ny < 0 || 5 <= nx || 5 <= ny) continue;
                if(visited[nx][ny] || target[nx][ny] != target[x][y]) continue;

                dq.push(new int[] {nx,ny});
                temp.add(new int[] {nx,ny});
                
                
                visited[nx][ny] = true;
            }
        }

        int sum = 0;

        if(temp.size() >= 3) {
            sum += temp.size();
            groups.add(temp);
        }

        return sum;
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        wall = new int[m];

        for(int i=0;i<5;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<5;j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++) {
            wall[i] = Integer.parseInt(st.nextToken());
        }


        for(int t=0;t<k;t++) {
        	//target[0] , target[1] : x,y | target[2] = 회전수(90도 회전을 몇번?)
        	int[] target = check();

        	if(nothing) break; // 모든 좌표 회전해도 얻을 수 있는거 없음

        	for(int i=0;i<target[2];i++) {
        		rotate90(target[0], target[1],data);
        	}

        	//bfs, 채우기 반복 ( 얻는것 없을 때까지)
            cur_sum = 0;
            chainget = true;

            while (chainget) {

                chainget = false;
                boolean[][] visited = new boolean[5][5];
                int removedCells = 0; // 이번 체인에서 제거된 칸의 총 개수

                // 모든 칸을 순회하며 0이 아니고 방문하지 않은 칸에서 그룹 찾기
                groups = new LinkedList<>();//전역변수 초기화 유의!!
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (!visited[i][j] && data[i][j] != 0) {
                        	int a = bfs(i,j,visited,data); // 리턴값 : 제거할 칸의 개수 : check 함수용
                        	
                        }
                    }
                }
                
                for(List<int[]> ptrs : groups) {
                	if(ptrs.size() >=3) {//크기가 3 이상이면 해당 그룹을 제거(0으로 설정)
                		
                		for (int[] cell : ptrs) {
                			data[cell[0]][cell[1]] = 0;
                		}
                		removedCells += ptrs.size();
                		chainget = true;
                	}
                }

                // 한 번이라도 제거된 그룹이 있다면
                if (chainget) {
                    cur_sum += removedCells;
                    // 빈 칸(0)을 wall[]의 값으로 채움 (왼쪽부터, 아래→위 순서)

                    for(int j=0;j<5;j++){
                        for (int i = 4; i >= 0; i--){
                            if (data[i][j] == 0) {
                                data[i][j] = wall[wallIdx];
                                wallIdx++;
                            }
                        }
                    }

                }

            }

            sb.append(cur_sum).append(" ");
        }

        System.out.println(sb);


    }
}
