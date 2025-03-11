import java.io.*;
import java.util.*;

public class Main {

    static int K, M, firstCount, mNumber, totalCount;
    static int[][] map = new int[5][5], map2 = new int[5][5], copyMap = new int[5][5];
    static int[] ar, dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());;
            }
        }

        ar = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        // === 인풋 === //
        for(int k=0; k<K; k++){

            int isNotEmpty = 0; // 탐사단계 유물 있나 확인

            // (1,1) ~ (3,3) 기준점으로 90, 180, 270 회전
            for(int angle=90; angle<=270; angle+=90){
                for(int j=1; j<4; j++) {
                    for(int i=1; i<4; i++) {
                        isNotEmpty += turnMap(i, j, angle); // 기준점 + 각도
                    }
                }
            }

            if(isNotEmpty == 0) break; // 회전해서 탐색해도 유물 없음

            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    map[i][j] = map2[i][j];
                }
            }

            // 회전 후 최대 값 나오는 map 정해졌으니 탐색
            while(true) {
                int count = findAndDrop(); // 유물 탐색 후 0으로 바꾸고 갯수 반환
                if(count == 0) break;
                totalCount += count;
                fill(); // 조각 채우기
            }
            sb.append(totalCount).append(" ");
            firstCount = 0;
            totalCount = 0;
        }
        System.out.print(sb);
    }

    // 배열 회전
    public static int turnMap(int r, int c, int angle) {
        int[][] tempMap = new int[3][3];
        int[][] tempMap_turn = new int[3][3];

        // 기준점을 중심으로 한 부분을 3x3 임시배열로 만들기 
        int row = 0;
        for(int i=r-1; i<r+2; i++) {
            int col = 0;
            for(int j=c-1; j<c+2; j++) {
                tempMap[row][col] = map[i][j];
                col++;
            }
            row++;
        }
    
        // 배열 회전
        switch (angle) {
            case 90:
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {
                        tempMap_turn[i][j] = tempMap[2-j][i];
                    }
                }
                break;

            case 180:
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {
                        tempMap_turn[i][j] = tempMap[2-i][2-j];
                    }
                }
                break;

            case 270:
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {
                        tempMap_turn[i][j] = tempMap[j][2-i];
                    }
                }
                break;
        }

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        
        // 회전한 배열을 copyMap에 업데이트
        row = 0;
        for(int i=r-1; i<r+2; i++) {
            int col = 0;
            for(int j=c-1; j<c+2; j++) {
                copyMap[i][j] = tempMap_turn[row][col];
                col++;
            }
            row++;
        }
        
        int count = find();

        // 유물 탐색 후 이전 보다 갯수가 많다면 map2에 임시저장
        if(firstCount < count) {
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    map2[i][j] = copyMap[i][j];
                }
            }
            firstCount = count;
        }
        return count;
    }

    // 유물 탐색 (유물 갯수 반환)
    public static int find() {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[5][5];

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(visit[i][j]) continue;
                int cnt = 1;
                queue.offer(new int[] {i, j});
                visit[i][j] = true;

                int nowNumber = copyMap[i][j];

                while(!queue.isEmpty()) {
                    int temp[] = queue.poll();
                    int x = temp[0];
                    int y = temp[1];

                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if(nx<0 || ny<0 || nx>4 || ny>4 || visit[nx][ny] || nowNumber != copyMap[nx][ny]) continue;

                        queue.offer(new int[] {nx, ny});
                        visit[nx][ny] = true;
                        cnt++;
                    }
                }
                if(cnt>=3) count += cnt;
            }
        }
        return count;
    }

    // 유물 발굴 (탐색 후 유물 없애는 함수)
    public static int findAndDrop() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[5][5];
        int count = 0;

        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(visit[i][j]) continue;

                ArrayList<int[]> list = new ArrayList<>();
                int cnt = 1;
                int nowNumber = map[i][j];
                queue.offer(new int[] {i, j});
                visit[i][j] = true;
                list.add(new int[] {i, j});

                while(!queue.isEmpty()) {
                    int temp[] = queue.poll();
                    int x = temp[0];
                    int y = temp[1];

                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if(nx<0 || ny<0 || nx>4 || ny>4 || visit[nx][ny] || nowNumber != map[nx][ny]) continue;

                        queue.offer(new int[] {nx, ny});
                        visit[nx][ny] = true;
                        list.add(new int[] {nx, ny});
                        cnt++;
                    }
                }
                // 유물 비우기
                if(cnt >=3) {
                    for(int[] temp : list) {
                        map[temp[0]][temp[1]] = 0;
                    }
                    count += cnt;
                }
            }
        }
        return count;
    }

    // 유물조각 채우기
    public static void fill() {
        for(int j=0; j<5; j++) {
            for(int i=4; i>=0; i--) {
                if(map[i][j] != 0) continue;

                map[i][j] = ar[mNumber];
                mNumber++;
            }
        }
    }

}
