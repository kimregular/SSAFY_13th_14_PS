package w250310.준범.p마법의숲탐색;

import java.util.*;
import java.io.*;



public class Main {
    static int r,c,k;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int[] ex = {-1,0,1,0};//상 우 하 좌
    static int[] ey = {0,1,0,-1};

    static int[][] visited;

    
    static int sum = 0;

    //---------------------------------- : 남, 서, 동 이동 가능한지
    static boolean notRange(int x, int y) {
        return (x < 0 || y < 0 || r+3 <= x || c <= y);
    }

    static boolean canMove(int[][] moves, int x, int y) {
        for (int[] move : moves) {
            int nx = x + move[0];
            int ny = y + move[1];
            if (notRange(nx, ny) || visited[nx][ny] != 0) {
                return false;
            }
        }
        return true;
    }

    static boolean southCan(int x, int y) {
        int[][] moves = {{1, -1}, {1, 1}, {2, 0}};
        return canMove(moves, x, y);
    }

    static boolean leftCan(int x, int y) {
        int[][] moves = {{-1, -1}, {0, -2}, {1, -1}, {1, -2}, {2, -1}};
        return canMove(moves, x, y);
    }

    static boolean rightCan(int x, int y) {
        int[][] moves = {{-1, 1}, {0, 2}, {1, 1}, {1, 2}, {2, 1}};
        return canMove(moves, x, y);
    }
    //----------------------------------



    static void after(int x, int y,int value) { // 골렘 이동 후 자리 v : true로
        visited[x][y] = value;
        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(notRange(nx,ny)) continue;
            visited[nx][ny] = value;
        }
    }


    static void golemMove(int x, int y, int d,int value) {


        if(southCan(x,y)) {
            golemMove(x+1,y,d, value);
        }else if(leftCan(x,y)) {
        	//상 -> 좌 ->하 ->우 ->상
        	d+=4;
        	d-=1;
        	d = d%4;
            golemMove(x+1,y-1,d, value);
        }else if(rightCan(x,y)) {
        	//상->우->하-> 좌
        	d+=1;
        	d = d%4;
            golemMove(x+1,y+1,d, value);
        }else { // 이동 끝
            after(x,y,value);//최종 골렘 위치 visited
            if(x<=3){//이동 했는데 중심이 밖 -> 초기화

                visited = new int[r+3][c];
                return;
            }else{
            	int eX = x+ex[d];
            	int eY = y+ey[d];
            	visited[eX][eY] = -1*value;
            	peopleMove(x,y,x);
            }
        }


        return;
    }


    //bfs, 0은 골렘 X-> 못감, 현재 음수 -> 다른 값 골렘 가능, 현재 양수 -> 다른 값 골렘 불가능
    static void peopleMove(int x,int y, int mx) {
    	boolean[][] v = new boolean[r+3][c];

    	
    	Deque<int[]> dq = new ArrayDeque<>();
    	dq.offer(new int[] {x,y});
    	v[x][y] = true;
    	
    	int max = mx-2;
    	int tx = x;
		int ty = y;
    	while(!dq.isEmpty()) {
    		int[] cur = dq.poll();
    		int cx = cur[0];
    		int cy = cur[1];
    		
    		
    		if(cx - 2 > max) {
    			max = cx-2;
    			tx = cx;
        		ty = cy;
    		}
    		
    		
    		for(int i=0;i<4;i++) {
    			int nx = cx+dx[i];
    			int ny = cy+dy[i];
    			if(notRange(nx,ny)) continue;
    			if(v[nx][ny]) continue;
    			if(visited[nx][ny] == 0) continue;
    			
    			if(visited[cx][cy] < 0) { // 현재 출구 -> 골렘 다 갈수있음
    				dq.offer(new int[] {nx,ny});
        			v[nx][ny] = true;
    			}else {//현재 출구 x
    				if(visited[cx][cy] != visited[nx][ny] && visited[cx][cy] != -1 * visited[nx][ny]) continue; // 다른골렘
    				dq.offer(new int[] {nx,ny});
        			v[nx][ny] = true;
    			}
    		}
    	}
    	

    	sum+= max;
    	
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new int[r+3][c];// r+2 : 골렘 시작지점은 밖에있음 -> 마지막에 행 더할 때 +1


        int[][] golems = new int[k][2];

        for(int i=0;i<k;i++) {
            st = new StringTokenizer(br.readLine());
            golems[i][0] = Integer.parseInt(st.nextToken())-1;//열
            golems[i][1] = Integer.parseInt(st.nextToken());//출구정보
        }
        for(int i=0;i<golems.length;i++) {
        	int x = 1; // 중앙.행 : 1에서 시작
            int y = golems[i][0]; // 열 :
            int d = golems[i][1];
            
            golemMove(x,y,d,i+1);
        }


        System.out.println(sum);
    }
}

