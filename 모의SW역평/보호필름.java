package 모의sw역량.d1019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 보호필름업데이트 {
	// 참조: https://velog.io/@hyeon930/SWEA-2112-%EB%B3%B4%ED%98%B8-%ED%95%84%EB%A6%84-Java
	static int D,W,K;
	static int minimum;
	static int film[][], filmCopy[][];
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			st=new StringTokenizer(br.readLine());
			
			minimum=Integer.MAX_VALUE;
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken()); // 합격 기준
			
			if(K==1) {
				bw.write("#"+t+" "+0+"\n");
				continue;
			}
			
			film=new int[D][W];
			filmCopy=new int[D][W];
			
			for(int d=0;d<D;d++) {
				film[d]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			for(int i=0;i<D;i++) {
				filmCopy[i]=film[i];
			}
			
			dfs(0,0);
			
			bw.write("#"+t+" "+minimum+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();

	}
	
	public static void dfs(int cnt, int depth) {
		if(cnt>=minimum) return;
		
		if(depth==D) { // 주입조합 완성
			if(isPass()) {
				minimum=Math.min(minimum, cnt);
			}
			return;
		}
		// 주입X
		dfs(cnt, depth+1);
		// A주입
		for(int w=0;w<W;w++) {
			filmCopy[depth][w]=0;
		}
		dfs(cnt+1, depth+1);
		// B주입
		for(int w=0;w<W;w++) {
			filmCopy[depth][w]=1;
		}
		dfs(cnt+1, depth+1);
		// filmCoppy 되돌리기
		for(int w=0;w<W;w++) {
			filmCopy[depth][w]=film[depth][w];
		}
	}
	
	private static boolean isPass() {
		// TODO Auto-generated method stub
		int aCnt, bCnt, success=0;
		
		for(int y=0;y<W;y++) {
			aCnt=0; bCnt=0;
			
			// System.out.println(y+"번째 열 체크중");
			for(int x=0;x<D;x++) {
				if(filmCopy[x][y]==0) { // 현재 칸이 A일 때
					aCnt+=1;
					
					if(bCnt>0) { // 이전 칸이 B였다
						if(x==(D-K+1)) {
							return false;
						}
						bCnt=0;
					}
				}else { // 현재 칸이 B일 때
					bCnt+=1;
					
					if(aCnt>0) { // 이전 칸이 A였다
						if(x==(D-K+1)) {
							return false;
						}
						aCnt=0;
					}
				}
				
				if((aCnt==K) || (bCnt==K)) {
					success=1; // 성공
					break;
				}
			}
			if(success==0) {
				// System.out.println(y+"번째 열이 실패해서 탈락");
				return false;
			}else {
				success=0; // 다음 열로 넘어가기
			}
		}
		
		return true;
	}

}
