package w250303.가배.고대문명유적탐사;

import java.io.*;
import java.util.*;

public class Main {
    static int K, M;
    static int[][] arr = new int[5][5];
    static List<Integer> lst = new ArrayList<>();
    static List<Integer> ans = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            lst.add(Integer.parseInt(st.nextToken()));
        }

        for (int k = 0; k < K; k++) {
            int maxCount = 0;
            int[][] bestArr = new int[5][5];

            for (int rot = 1; rot <= 3; rot++) {
                for (int sj = 0; sj < 3; sj++) {
                    for (int si = 0; si < 3; si++) {
                        int[][] rotatedArr = deepCopy(arr);
                        for (int r = 0; r < rot; r++) {
                            rotatedArr = rotate(rotatedArr, si, sj);
                        }

                        int count = countClear(rotatedArr, 0);
                        if (count > maxCount) {
                            maxCount = count;
                            bestArr = rotatedArr;
                        }
                    }
                }
            }

            if (maxCount == 0) break;
            arr = bestArr;

            int count = 0;
            while (true) {
                int t = countClear(arr, 1);
                if (t == 0) break;
                count += t;

                for (int j = 0; j < 5; j++) {
                    for (int i = 4; i >= 0; i--) {
                        if (arr[i][j] == 0 && !lst.isEmpty()) {
                            arr[i][j] = lst.remove(0);
                        }
                    }
                }
            }
            ans.add(count);
        }

        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    static int[][] rotate(int[][] arr, int si, int sj) {
        int[][] narr = deepCopy(arr);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                narr[si + j][sj + i] = arr[si + 3 - i - 1][sj + j];
            }
        }
        return narr;
    }

    static int bfs(int[][] arr, boolean[][] v, int si, int sj, int clr) {
        Queue<int[]> q = new ArrayDeque<>();
        Set<String> sset = new HashSet<>();
        int cnt = 0;
        q.offer(new int[]{si, sj});
        v[si][sj] = true;
        sset.add(si + "," + sj);
        cnt++;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0], cj = cur[1];

            for (int d = 0; d < 4; d++) {
                int ni = ci + dx[d], nj = cj + dy[d];
                if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5 && !v[ni][nj] && arr[ni][nj] == arr[ci][cj]) {
                    q.offer(new int[]{ni, nj});
                    v[ni][nj] = true;
                    sset.add(ni + "," + nj);
                    cnt++;
                }
            }
        }

        if (cnt >= 3) {
            if (clr == 1) {
                for (String pos : sset) {
                    String[] parts = pos.split(",");
                    arr[Integer.parseInt(parts[0])][Integer.parseInt(parts[1])] = 0;
                }
            }
            return cnt;
        }
        return 0;
    }

    static int countClear(int[][] arr, int clr) {
        boolean[][] v = new boolean[5][5];
        int cnt = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!v[i][j]) {
                    cnt += bfs(arr, v, i, j, clr);
                }
            }
        }
        return cnt;
    }

    static int[][] deepCopy(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return copy;
    }
}