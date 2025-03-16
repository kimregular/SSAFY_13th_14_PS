import java.io.*;
import java.util.*;

public class Main {

    static int n, d, c, resCount=0, resTime=0;
    static ArrayList<Pc>[] ar;
    static int[] timeAr;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for(int tc=0; tc<T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); //컴퓨터 갯수
            d = Integer.parseInt(st.nextToken()); //의존성 갯수
            c = Integer.parseInt(st.nextToken()); //시작 감염 컴퓨터
            
            ar = new ArrayList[n+1];
            for(int i=0; i<=n; i++) {
                ar[i] = new ArrayList<>();
            }

            timeAr = new int[n+1];
            Arrays.fill(timeAr, 2_000_000_000);
            timeAr[c] = 0;
            
            for(int i=0; i<d; i++) {
                st = new StringTokenizer(br.readLine());
                int childPc = Integer.parseInt(st.nextToken());
                int ParentPc = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                ar[ParentPc].add(new Pc(childPc, time));
            }
            find();
            
            sb.append(resCount).append(" ").append(resTime).append("\n");
            resCount=0;
            resTime=0;
        }
        System.out.println(sb);
    }

    public static void find() {
        PriorityQueue<Pc> queue = new PriorityQueue<>();
        queue.offer(new Pc(c, 0));

        while(!queue.isEmpty()) {
            Pc pc = queue.poll();
            for(Pc nextPc : ar[pc.num]) {
                if(timeAr[nextPc.num] > timeAr[pc.num] + nextPc.time) {
                    timeAr[nextPc.num] = timeAr[pc.num] + nextPc.time;
                    queue.offer(new Pc(nextPc.num, timeAr[nextPc.num]));
                }
            }
        }

        for(int time : timeAr) {
            if(time == 2_000_000_000) continue;
            resTime = Math.max(resTime, time);
            resCount ++;
        }

    }

}

class Pc implements Comparable<Pc>{
    int num;
    int time;

    public Pc(int num, int time) {
        this.num = num;
        this.time = time;
    }

    @Override
    public int compareTo(Pc o) {
        return Integer.compare(time, o.time);
    }
}
