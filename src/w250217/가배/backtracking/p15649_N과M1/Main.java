package w250217.가배.backtracking.p15649_N과M1;
/*1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 모두 구하기*/
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] v; // 방문 여부 체크 (중복 방지)
    static int[] arr; // 현재 선택된 수열 저장
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        v = new boolean[N + 1];
        arr = new int[M];

        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int depth) {
        if (depth == M) { // M개의 숫자 선택되면..~
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) { // 1부터 N까지 숫자 선택
            if (!v[i]) { // 아직 선택되지 않은 숫자면 
                v[i] = true; // 선택함
                arr[depth] = i; // 수열에 추가
                dfs(depth + 1); // 다음 단계 탐색
                v[i] = false; // 백트래킹!
            }
        }
    }
}
