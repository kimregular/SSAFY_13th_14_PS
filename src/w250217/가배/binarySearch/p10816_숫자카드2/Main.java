package w250217.가배.binarySearch.p10816_숫자카드2;
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int x = Integer.parseInt(st.nextToken());
             int cnt = upperBound(arr,x) - lowerBound(arr,x);
             sb.append(cnt).append(" ");
        }
        System.out.println(sb);
	}
	
	//x가 처음 등장하는 위치 인덱스 반환, 없으면 마지막 위치 반환 
	static int lowerBound(int[] arr, int x) {
		int l=0, r=arr.length;
		while(l<r) {
			int m = l+(r-l)/2;
			if(arr[m]>=x) r=m; //x 이상이 처음 나오는 위치를 찾아야 함
			else l=m+1;
		}
		return l;
	}
	
	//x가 마지막으로 등장하는 위치의 다음 인덱스 반환, 없으면 마지막 위치 반환 
	static int upperBound(int[] arr, int x) {
		int l=0, r=arr.length;
		while(l<r) {
			int m = l+(r-l)/2;
			if(arr[m]>x) r=m; //x 초과 값이 처음 나오는 위치를 찾아야 함
			else l=m+1;
		}
		return l;
	}
	
}
