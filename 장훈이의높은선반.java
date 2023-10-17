package d1017;

// 참조: https://lily-lee.postype.com/post/4507985

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 장훈이의높은선반 {
	static boolean[] hvisited;
	static int[] output, heights;
	static int N,B, minimum;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			minimum=Integer.MAX_VALUE;
			
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken()); // 점원 수
			B=Integer.parseInt(st.nextToken()); // 선반 높이
			
			heights=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			hvisited=new boolean[N];
		
			comb(0,0,0);
			
			bw.write("#"+t+" "+(minimum-B)+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static void comb(int start, int sum, int cnt) {
		if(cnt==N || sum>=B) {
			if(sum<minimum) {
				minimum=sum;
			}
			return;
		}

		for(int i=start;i<N;i++) {
			// if(hvisited[i]) continue;
			
			// hvisited[i]=true;
			
			comb(start+1, sum+heights[i], cnt+1);
			
			// hvisited[i]=false;
			
		}
		
	}

}
