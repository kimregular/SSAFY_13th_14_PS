package w250303.준범.p16926_배열돌리기1;

import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

public class Main {
    static int n,m,r;
    static int[] dx= {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    
    static int[][] data;
    
    static void Layer(int lidx) {
    	int[] temp = new int[2*n + 2*m - 8*lidx-4];
    	
    	int x = lidx;
    	int y = lidx-1;
    	
    	int d = 0;
    	
    	for(int i=0;i<temp.length;i++) {
    		x += dx[d];
    		y += dy[d];
    		if(x<lidx || y<lidx || x >= n-lidx || y >= m-lidx) { // layer 밖 -> 방향 바꾸기
    			x -= dx[d];
    			y -= dy[d];
    			
    			d = (d+1)%4;
    			
    			x += dx[d];
        		y += dy[d];
    		}
    		
    		
    		temp[i] = data[x][y];
    	}

    	x = lidx;
    	y = lidx-1;
    	
    	d = 0;
    	
    	for(int i=1;i<temp.length;i++) {
    		x += dx[d];
    		y += dy[d];
    		if(x<lidx || y<lidx || x >= n-lidx || y >= m-lidx) { // layer 밖 -> 방향 바꾸기
    			x -= dx[d];
    			y -= dy[d];
    			
    			d = (d+1)%4;
    			
    			x += dx[d];
        		y += dy[d];
    		}
    		data[x][y] = temp[i];
    	}
    	
    	data[lidx+1][lidx] = temp[0];
    	
    	
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        data = new int[n][m];
        
        for(int i=0;i<n;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<m;j++) {
        		data[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(int f=0;f<r;f++) {
        	for(int i=0;i<Math.min(n,m)/2;i++) {
            	Layer(i);
            }
        }
        
        
        
        for(int i=0;i<n;i++) {
        	for(int j=0;j<m;j++) {
        		sb.append(data[i][j]).append(" ");
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);

    }
}
