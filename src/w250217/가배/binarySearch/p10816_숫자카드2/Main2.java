package w250217.가배.binarySearch.p10816_숫자카드2;
//테스트해보니 이게 더 느리긴 함..
import java.io.*;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        // 개수 저장
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int num : arr) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        while (M-- > 0) {
            int x = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(arr, x, cntMap)).append(" ");
        }

        System.out.println(sb);
    }

    /**
     * 이진 탐색을 사용하여 `x`가 존재하는지 확인하고 존재하면 개수 반환.
     * 존재하지 않으면 0 반환.
     */
    public static int binarySearch(int[] arr, int x, Map<Integer, Integer> cntMap) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (arr[m] == x) {
                return cntMap.get(x); // 존재하면 개수 반환
            } else if (arr[m] > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return 0; // 존재하지 않음
    }
}
