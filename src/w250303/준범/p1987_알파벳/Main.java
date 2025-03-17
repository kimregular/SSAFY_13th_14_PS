package w250303.준범.p1987_알파벳;

import java.util.*;
import java.io.*;


public class Main {
    static int r,c;
    static char[][] data;
    static boolean[] visited = new boolean[100];
    static int max = Integer.MIN_VALUE;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    
    static void dfs(int x, int y,int cnt) {
    	max = Math.max(max, cnt);

    	
    	
    	
    	for(int i=0;i<4;i++) {
    		int nx = x+dx[i];
    		int ny = y+dy[i];
    		
    		if(nx<0 || ny<0 || nx >=r||ny>=c)continue;
    		if(visited[data[nx][ny]-'A']) continue;
    		
    		visited[data[nx][ny] - 'A'] = true;
            dfs(nx, ny, cnt + 1);
            visited[data[nx][ny] - 'A'] = false; 
    		
    		
    	}
    	
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	data = new char[r][c];
    	
    	for(int i=0;i<r;i++) {
    		String line = br.readLine();
    		for(int j=0;j<c;j++) {
    			data[i][j] = line.charAt(j);
    		}
    	}
    	visited[data[0][0] - 'A'] = true;
    	dfs(0,0,1);
    	
    	System.out.println(max);
    	
    }
}

