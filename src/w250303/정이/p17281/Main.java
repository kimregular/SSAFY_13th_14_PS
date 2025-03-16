import java.io.*;
import java.util.*;

public class Main {

    static int N, number, score;
    static int[] turn = new int[9], comb = {0,1,2,4,5,6,7,8};
    static boolean[] v;
    static int[][] ar, game;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ar = new int[N][9];
        game = new int[N][9];
       
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        v = new boolean[9];
        turn[0] = 3; //4번타자 고정
        setTurn(1);

        System.out.println(score);
    }
    
    // 선수 순서
    public static void setTurn(int cnt) {
        if(cnt==9) {
            setTeam();
            return;
        }
        for(int i=0; i<8; i++) {
            if(v[i]) continue;
            turn[cnt] = comb[i];
            v[i] = true;
            setTurn(cnt+1);
            v[i] = false;
        }
    }

    // 만들어진 순서로 팀 빌드
    public static void setTeam() {
        int newScore = 0;
        number = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<9; j++) {
                game[i][turn[j]] = ar[i][j];
            }
            newScore += gameStart(game[i]);
        }
        score = Math.max(score, newScore);
    }

    public static int gameStart(int[] inning) {
        int[] ru = new int[4];
        int newScore = 0;
        int outCnt = 0;
        while(true) {
            if(outCnt==3){
                break;
            }
            switch (inning[number]) { //선수 번호(0 ~ 8)
                case 0:
                    outCnt++;
                    break;
                case 1:
                newScore += ru[3];
                    ru[3] = ru[2];
                    ru[2] = ru[1];
                    ru[1] = 1;
                    break;
                case 2:
                newScore += ru[3] + ru[2];
                    ru[3] = ru[1];
                    ru[2] = 1;
                    ru[1] = 0;
                    break;
                case 3:
                newScore += ru[3] + ru[2] + ru[1];
                    ru[3] = 1;
                    ru[2] = 0;
                    ru[1] = 0;
                    break;
                case 4:
                newScore += ru[3] + ru[2] + ru[1] + 1;
                    ru[3] = 0;
                    ru[2] = 0;
                    ru[1] = 0;
                    break;  
            }
            if(number == 8) {
                number = 0;
                continue;
            }
            number += 1;
        }
        return newScore;
    }
}
