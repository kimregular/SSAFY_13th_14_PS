package w250210.가배.p9019_DSLR;

import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws Exception{
		// DSLR명령어 이용해서 A->B로 바꾸는 최소 명령어 나열
		
		//0. 테스트케이스
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			//0. 테스트 케이스 별 A, B입력 받기
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			char[] dslr = {'D', 'S', 'L', 'R'};
			
			Queue<Cal> q = new LinkedList<>();
			int[] visited = new int[10000];
			q.add(new Cal(a, new StringBuilder()));
			visited[a] = 1;
			
			while(!q.isEmpty()) { //큐가 비지 않을 때까지
				Cal now = q.poll();
				if(now.n==b) { //b랑 같아지면 그때까지의 cmd출력
					System.out.println(now.cmd);
					break;
				}
				
				int[] next_cmd = {now.calcD(), now.calcS(), now.calcL(), now.calcR()};

				for(int i=0;i<4;i++) { //명령어 돌면서 검사
					if(visited[next_cmd[i]]==0) { //아직 도달하지 못한 번호면..큐에 추가
						q.add(new Cal(next_cmd[i], new StringBuilder(now.cmd).append(dslr[i])));
						visited[next_cmd[i]]=1;
					}
				}
			}
			
		}
		
	}

}
//Cal 클래스에 DSLR동작 정의
class Cal{
	int n;
	StringBuilder cmd;
	Cal(int n, StringBuilder cmd){
		this.n=n;
		this.cmd=cmd;
	}
	
	public int calcD() {
		return (n*2)%10000;
	}
	public int calcS() {
		return n==0?9999:n-1;
	}
	public int calcL() {//1234 2341
		return (n%1000)*10+n/1000;
	}
	public int calcR() { //1234 4123
		return (n%10)*1000+n/10;
	}
}