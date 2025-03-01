import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static ArrayList<Integer> numbers = new ArrayList<>();
    static ArrayList<Integer> sum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            numbers.add(Integer.parseInt(st.nextToken()));

            // 첫 번째 숫자는 그대로 sum에 저장
            if(i == 0){
                sum.add(numbers.get(0));
                continue;
            }

            // 입력 받으며 누적합 저장
            sum.add(sum.get(i - 1) + numbers.get(i));
        }


        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 1번 index부터의 합이 질문일 경우 누적합인 sum[b]값 그대로 출력
            if(from == 1){
                System.out.println(sum.get(to-1));
                continue;
            }
            // numbers[a] + .. + numbers[b] = sum[b] - sum[a-1]
            System.out.println(sum.get(to - 1) - sum.get(from - 2));
        }
    }
}
