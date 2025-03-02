package w250224.가배.p15686_치킨배달;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<int[]> house = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static int[][] selected;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        selected = new int[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    house.add(new int[]{i, j});  // 집 위치 저장
                } else if (a == 2) {
                    chicken.add(new int[]{i, j}); // 치킨집 위치 저장
                }
            }
        }
        
        comb(0, 0);
        System.out.println(ans);
    }
    private static void comb(int cnt, int start) { //M개 고르는 조합 찾기
        if (cnt == M) {
            ans = Math.min(ans, calDistance());
            return;
        }
        for (int i = start; i < chicken.size(); i++) {
            selected[cnt] = chicken.get(i);
            comb(cnt + 1, i + 1);
        }
    }
    private static int calDistance() { //집과 치킨집 간에 거리 최소값(제일 가까운 치킨집과의 거리)
        int tot = 0;
        for (int[] house : house) { 
            int minDis = Integer.MAX_VALUE;
            for (int[] chicken : selected) {
                int dis = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
                minDis = Math.min(minDis, dis);
            }
            tot += minDis;
        }
        return tot;
    }
}
