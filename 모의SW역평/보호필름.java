package d1011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 보호필름 {
	static int D,W,K;
	static int minimum;
	static int film[][], filmCopy[][];
	static List<int[]> AnB, rowss;
	static int[] output;
	static boolean[] visited;
	static int input[], outAB[], outRow[];
	static int pill, flag;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			flag=0;
			
			st=new StringTokenizer(br.readLine());
			
			minimum=Integer.MAX_VALUE;
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken()); // 합격 기준
			
			if(K==1) {
				bw.write("#"+t+" "+0+"\n");
				continue;
			}
			
			input=new int[D];

			for(int d=0;d<D;d++) {
				input[d]=d;
			}
			
			film=new int[D][W];
			filmCopy=new int[D][W];
			
			for(int d=0;d<D;d++) {
				film[d]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			deepcopy(film); // 원본을 filmCopy에 딥카피. temp 개념
			
			fill();
			
			bw.write("#"+t+" "+minimum+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();

	}

	private static void fill() {
		// System.out.println("fill 수행중");
		for(int i=0;i<=D;i++) {
			if(i==1) continue; // 최소 투입수는 2
			
			pill=i;
			
			AnB=new ArrayList<>();
			rowss=new ArrayList<>();
			
			output=new int[i];
			outAB=new int[i];
			outRow=new int[i];
			
			// output=new int[i];
			visited=new boolean[D];
			
			perm(i,0);
			// 순열들 완성

			
			
			// System.out.println(rowss);
//			for(int[] AB:AnB) {					
//				for(int[] rows:rowss) {
////					System.out.println("AB:"+Arrays.toString(AB));
////					System.out.println("rows:"+Arrays.toString(rows));
//					
//					for(int idx=0;idx<i;idx++) {
//						for(int w=0;w<W;w++) {
//							filmCopy[rows[idx]][w]=AB[idx];
//						}
//					}
//					
////					System.out.println("필름 현황 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
////					for(int d=0;d<D;d++) {
////						System.out.println(Arrays.toString(filmCopy[d]));
////					}
////					System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//					if(isPass(film, filmCopy, rows)) {
//						// 최소 약품 투입수 갱신
//						minimum=Math.min(minimum, i);
//						// System.out.println(i+"케이스에서 끝냄.");
//						return;
//					}
//				}
//			}
			
		}
	}
	
	public static boolean contain(int[] arr, int target) {
		for(int element:arr) {
			if(element==target) {
				return true;
			}
		}
		
		return false;
	}

	private static boolean isPass(int[][] film, int[][] filmCopy, int[] rows) {
		// TODO Auto-generated method stub
		int aCnt, bCnt, success=0;
		
		for(int y=0;y<W;y++) {
			aCnt=0; bCnt=0;
			
			// System.out.println(y+"번째 열 체크중");
			for(int x=0;x<D;x++) {
				if(contain(rows,x)) { // 변경한 행이라면
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
				}else { // 원본 참조
					if(film[x][y]==0) { // 현재 칸이 A일 때
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

	private static void deepcopy(int[][] film) {
		// TODO Auto-generated method stub
		for(int i=0;i<D;i++) {
			filmCopy[i]=Arrays.copyOf(film[i], W);
		}
	}

	private static void comb(int R, int cnt, int start) {
		//System.out.println("comb("+max+", "+cnt+", "+start+")");
		if(cnt==R) {
			// System.out.println("행 조합:"+Arrays.toString(output));
			// int[] res=new int[R];
			for(int r=0;r<R;r++) {
				outRow[r]=output[r];
			}
			// rowss.add(res);
			
			// outAB, outRow 완성
			for(int idx=0;idx<pill;idx++) {
				for(int w=0;w<W;w++) {
					filmCopy[outRow[idx]][w]=outAB[idx];
				}
			}
			
//			System.out.println("필름 현황 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//			for(int d=0;d<D;d++) {
//				System.out.println(Arrays.toString(filmCopy[d]));
//			}
//			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			if(isPass(film, filmCopy, outRow)) {
				// 최소 약품 투입수 갱신
				minimum=Math.min(minimum, pill);
				flag=1;
				// System.out.println(i+"케이스에서 끝냄.");
			}
			return;
		}
		
		for(int i=start;i<D;i++) {
			if(visited[i]) continue;
			
			visited[i]=true;
			output[cnt]=input[i];
			comb(R,cnt+1,start+1);
			visited[i]=false;
			
		}
	}

	private static void perm(int max, int cnt) { // 0,1의 max 길이 순열을 만들어 내는 함수
		if(flag==1) {
			// System.out.println(pill+"번째 케이스 갱신함");
			return; // 더이상 시도하지 않는다.
		}
		if(cnt==max) {
			// System.out.println("행 순열:"+Arrays.toString(output)+"를 AnB에 추가함");
			// int[] res=new int[max];
			for(int m=0;m<max;m++) {
				outAB[m]=output[m];
			}
			// AnB.add(res);
			
//			for(int[] AB:AnB) {
//				System.out.println("AnB 안에 저장된 순열:"+Arrays.toString(AB));
//			}
			comb(pill,0,0);
			// 행 조합 완성
			return;
		}
		// TODO Auto-generated method stub
		for(int i=0;i<=1;i++) {
			output[cnt]=i;
			perm(max,cnt+1);
		}
	}

}
