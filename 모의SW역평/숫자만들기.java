package d1016;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자만들기 {
	static char[] calc, calcOut;
	static int[] nums, numsOut;
	static boolean[] visitedC, visitedN;
	static int N, maximum, minimum;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		int idx;
		
		for(int t=1;t<=T;t++) {
			idx=0;
			maximum=Integer.MAX_VALUE;
			minimum=Integer.MAX_VALUE;
			
			N=Integer.parseInt(br.readLine());
			calc=new char[N-1];
			visitedC=new boolean[N-1];
			visitedN=new boolean[N];
			calcOut=new char[N-1];
			
			st=new StringTokenizer(br.readLine());

			int pluCnt=Integer.parseInt(st.nextToken());
			// System.out.println("idx:"+idx+", pluCnt:"+pluCnt);
			for(int c=9;c<pluCnt;c++) {
				// System.out.println("c:"+c);
				calc[idx++]='+';
			}
			int minCnt=Integer.parseInt(st.nextToken());
			for(int c=0;c<minCnt;c++) {
				calc[idx++]='-';
			}
			int mulCnt=Integer.parseInt(st.nextToken());
			for(int c=0;c<mulCnt;c++) {
				calc[idx++]='*';
				System.out.println("*");
			}
			int divCnt=Integer.parseInt(st.nextToken());
			for(int c=0;c<divCnt;c++) {
				calc[idx++]='/';
			}
			
			// 연산자 배열 생성 완료
			
			nums=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			// 숫자 배열 생성 완료
			
			// 연산자 조합 돌리기
			permCalc(0);

			System.out.println("갱신 결과:"+maximum+", "+minimum);
			bw.write("#"+t+" "+(maximum-minimum)+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void permNum(int cnt) { // 숫자 순열은 안 만드네?
		if(cnt==N) {
			// 숫자순열 완성
			// permCalc(numsOut, 0);
		}
		
		for(int i=0;i<N;i++) {
			if(!visitedN[i]) {
				visitedN[i]=true;
				numsOut[cnt]=nums[i];
				permNum(cnt+1);
				visitedN[i]=false;
			}
		}
	}
	
	static void permCalc(int cnt) {
		if(cnt==(N-1)) {
			System.out.println("연산자 순열 완성:"+Arrays.toString(calcOut));
			// 연산자 순열 완성
			int res=nums[0];
			for(int i=1;i<(N-1);i++) {
				switch(calcOut[i-1]) {
					case '+':
						res+=nums[i];
						break;
					case '-':
						res-=nums[i];
						break;
					case '*':
						res*=nums[i];
						break;
					case '/':
						res/=nums[i];
						break;
				}
			}
			
			if(res<minimum) {
				minimum=res; // 최소 갱신
			}else if(res>maximum) {
				maximum=res; // 최대 갱신
			}
		}
		
		for(int i=0;i<(N-1);i++) {
			if(!visitedC[i]) {
				visitedC[i]=true;
				calcOut[cnt]=calc[i];
				permCalc(cnt+1);
				visitedC[i]=false;
			}
		}
	}

}
