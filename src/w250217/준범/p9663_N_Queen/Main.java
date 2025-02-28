package w250217.준범.p9663_N_Queen;

import java.io.*;


public class Main {
    static int n;
    static int cnt = 0;
    static int[] board;//board[row] = col : 해당 행에 배치된 퀸의 열

    static void place(int row) {
        if(row == n) {
            cnt++;
            return;
        }

        for(int col = 0;col<n;col++) {
            if(check(row,col)) {
                board[row] = col;
                place(row + 1);
            }
        }
    }

    static boolean check(int row, int col) {
        for(int r = 0;r<row;r++) {
            if(board[r] == col) return false; // 각 행에 배치된 퀸의 열이 현재 열과 동일

            if(Math.abs(board[r] - col) == Math.abs(r-row)) return false; // 열-열 = 행-행

        }
        return true;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n];
        place(0);
        System.out.println(cnt);

    }
}