package w250224.준범.p2961;

import java.util.*;
import java.io.*;


public class Main {
    static int[][] data;
    static int minDiff = Integer.MAX_VALUE;

    static void dfs(int index, int s,int b,int selectedCount){
        if(index == data.length ){
            if(Math.abs(s-b) < minDiff && selectedCount != 0){
                minDiff = Math.abs(s-b);
            }
            return;
        }

        int ns = s * data[index][0];
        int nb = b+ data[index][1];

        dfs(index+1, ns,nb,selectedCount+1);
        dfs(index+1, s,b,selectedCount);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;


        data = new int[n][2];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 1, 0,0);

        System.out.println(minDiff);
    }
}