package w250210.가배.p13460_구슬탈출2;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // 보드 크기
    static char[][] board; // 게임 보드
    static boolean[][][][] visited; // 방문 여부 저장 (빨간 구슬, 파란 구슬 위치별 방문 체크)
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우 이동 방향
    static int[] dc = {0, 0, -1, 1}; 
    static int redR, redC, blueR, blueC; // 빨간 구슬과 파란 구슬 위치

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        // 보드 입력 받기
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    redR = i;
                    redC = j;
                } else if (board[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{redR, redC, blueR, blueC, 0});
        visited[redR][redC][blueR][blueC] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int rR = cur[0], rC = cur[1], bR = cur[2], bC = cur[3], cnt = cur[4];

            if (cnt >= 10) return -1; // 10번 이상 이동 시 실패

            for (int i = 0; i < 4; i++) { // 네 방향으로 기울이기
                int[] redMove = move(rR, rC, dr[i], dc[i]); // 빨간 구슬 이동
                int[] blueMove = move(bR, bC, dr[i], dc[i]); // 파란 구슬 이동

                if (board[blueMove[0]][blueMove[1]] == 'O') continue; // 파란 구슬이 구멍에 빠지면 실패
                if (board[redMove[0]][redMove[1]] == 'O') return cnt + 1; // 빨간 구슬이 구멍에 빠지면 성공

                // 빨간 구슬과 파란 구슬이 같은 위치에 있으면 조정 
                if (redMove[0] == blueMove[0] && redMove[1] == blueMove[1]) {
                    if (redMove[2] > blueMove[2]) { // 빨간 구슬이 더 많이 움직였으면
                        redMove[0] -= dr[i];
                        redMove[1] -= dc[i];
                    } else { // 파란 구슬이 더 많이 움직였으면
                        blueMove[0] -= dr[i];
                        blueMove[1] -= dc[i];
                    }
                }

                // 방문하지 않은 상태라면 큐에 추가
                if (!visited[redMove[0]][redMove[1]][blueMove[0]][blueMove[1]]) {
                    visited[redMove[0]][redMove[1]][blueMove[0]][blueMove[1]] = true;
                    q.offer(new int[]{redMove[0], redMove[1], blueMove[0], blueMove[1], cnt + 1});
                }
            }
        }
        return -1; // 빨간 구슬을 10번 이하로 이동시킬 수 없으면 실패
    }

    // 구슬 이동 함수 (중력 방향으로 끝까지 이동)
    static int[] move(int r, int c, int dr, int dc) {
        int count = 0;
        while (board[r + dr][c + dc] != '#' && board[r][c] != 'O') { // 벽(#)이 아닐 때까지 이동
            r += dr;
            c += dc;
            count++;
        }
        return new int[]{r, c, count};
    }
}