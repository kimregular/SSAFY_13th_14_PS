package w250224.가배.p2961_도영이가만든맛있는음식;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr; // 신맛과 쓴맛 저장 배열
    static int minDiff = Integer.MAX_VALUE; // 최소 신맛-쓴맛 차이

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 신맛
            arr[i][1] = Integer.parseInt(st.nextToken()); // 쓴맛
        }

        subset(0, 1, 0, 0); 

        System.out.println(minDiff);
    }

    static void subset(int idx, int sour, int bitter, int cnt) {
        if (idx == N) { // 모든 재료를 확인
            if (cnt > 0) { // 최소 1개 이상 재료 사용해야
                minDiff = Math.min(minDiff, Math.abs(sour - bitter)); // 최소 차이 갱신
            }
            return;
        }

        //현재 재료를 선택
        subset(idx + 1, sour * arr[idx][0], bitter + arr[idx][1], cnt + 1);
        //현재 재료를 선택x
        subset(idx + 1, sour, bitter, cnt);
    }
}