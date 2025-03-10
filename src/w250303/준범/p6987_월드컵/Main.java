package w250303.준범.p6987_월드컵;

import java.util.*;
import java.io.*;


public class Main {
    static int[][] data = new int[6][3]; //각 나라 별 승,무,패 기록
    static int[][] result = new int[6][3];
    
    static boolean flag;
    static int[][] matches = new int[15][2];
    
    
    static void makeMaches() {
    	int idx = 0;
    	for(int i=0;i<6;i++) {
    		for(int j=i+1;j<6;j++) {
    			matches[idx][0] = i;
    			matches[idx][1] = j;
    			idx++;
    		}
    	}
    }
    static void dfs(int midx) {
    	if(flag) return;
    	if(midx ==15) { // 모든 경기 종료
    		for(int i=0;i<6;i++) {
    			for(int j=0;j<3;j++) {
    				if(data[i][j] != result[i][j])return;
    				
    			}
    		}
    		flag = true;
    		return;
    	}
    	
    	int a = matches[midx][0];
    	int b = matches[midx][1];
    	
    	result[a][0] +=1;
		result[b][2] += 1;
		if (result[a][0] <= data[a][0] && result[b][2] <= data[b][2]) {
			dfs(midx+1);
		}
		result[a][0] -=1;
		result[b][2] -= 1;
		
		result[a][1] +=1;
		result[b][1] += 1;
		if (result[a][1] <= data[a][1] && result[b][1] <= data[b][1]) {
			dfs(midx+1);	
		}
		result[a][1] -=1;
		result[b][1] -= 1;
		
		result[a][2] +=1;
		result[b][0] += 1;
		if (result[a][2] <= data[a][2] && result[b][0] <= data[b][0]) {
			dfs(midx+1);
		}
		result[a][2] -=1;
		result[b][0] -= 1;
    }
    

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        makeMaches();
        
        for(int f=0;f<4;f++) {
        	result = new int[6][3];
        	flag = false;
        	st = new StringTokenizer(br.readLine());
        	//18개 -> 나라마다 3개 -> 6나라
        	for(int s = 0;s<6;s++) {
        		data[s][0] = Integer.parseInt(st.nextToken());
        		data[s][1] = Integer.parseInt(st.nextToken());
        		data[s][2] = Integer.parseInt(st.nextToken());	
        	}
        	
        	dfs(0);
        	int r=0;
        	if(flag) r = 1;
        	

    		sb.append(r).append(" ");
        }
        System.out.println(sb);

    }
}
