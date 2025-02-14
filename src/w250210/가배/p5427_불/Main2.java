package w250210.가배.p5427_불;
/*이건 완전 처음에 풀었던 풀이인데(포인터 클래스 무조건 정의해서 쓰던 때) 이 문제에서는 이 코드가 더 빠름
 * 왜인지 찾아보니 JVM의 최적화 받아서 그런거같다네...당연히 int[]넣는게 빠른줄..*/
import java.io.*;
import java.util.*;

public class Main2 {
	static int w, h;
	static int[][] fire;
    static int[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
        	st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            
            fire = new int[h][w];
            visited = new int[h][w];
            Queue<Point> q = new LinkedList<>();
            Queue<Point> fireQ = new LinkedList<>();
            
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    char c = line.charAt(j);
                    if (c == '#') {
                        fire[i][j] = visited[i][j] = -1;
                    } else if (c == '@') {
                        q.add(new Point(i, j));
                        visited[i][j] = 1;
                    } else if (c == '*') {
                        fireQ.add(new Point(i, j));
                        fire[i][j] = 1;
                    }
                }
            }
            
            while (!fireQ.isEmpty()) {
                Point now = fireQ.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];
                    if (isOutOfRange(nr, nc)) continue;
                    if (fire[nr][nc] == 0) {
                        fire[nr][nc] = fire[now.r][now.c] + 1;
                        fireQ.add(new Point(nr, nc));
                    }
                }
            }

            boolean find = false;
            while (!q.isEmpty()) {
                Point now = q.poll();
                if (isExit(now.r, now.c)) {
                    System.out.println(visited[now.r][now.c]);
                    find = true;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nr][nc] != 0) continue;
                    if (fire[nr][nc] == 0 || fire[nr][nc] > visited[now.r][now.c] + 1) {
                        visited[nr][nc] = visited[now.r][now.c] + 1;
                        q.add(new Point(nr, nc));
                    }
                }
            }
            if (!find) {
                System.out.println("IMPOSSIBLE");
            }
        }

    }

    static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= h || c < 0 || c >= w;
    }

    static boolean isExit(int r, int c) {
        return r == 0 || r == h - 1 || c == 0 || c == w - 1;
    }
}

class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
