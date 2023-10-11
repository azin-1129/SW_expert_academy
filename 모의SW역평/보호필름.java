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
	static int[] input;
	
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
			
			input=new int[D];
			for(int d=0;d<D;d++) {
				input[d]=d;
			}
			
			film=new int[D][W];
			filmCopy=new int[D][W];
			
			for(int d=0;d<D;d++) {
				film[d]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			fill();
			
			bw.write("#"+t+" "+minimum+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();

	}

	private static void fill() {
		System.out.println("fill 수행중");
		for(int i=2;i<=D;i++) {
			AnB=new ArrayList<>();
			rowss=new ArrayList<>();
			
			output=new int[i];
			visited=new boolean[D];
			
			perm(i,0);
			// 순열들 완성
			comb(i,0,0);
			// 행 조합 완성

			// System.out.println(rowss);
			for(int[] AB:AnB) {					
				for(int[] rows:rowss) {
//					System.out.println("AB:"+Arrays.toString(AB));
//					System.out.println("rows:"+Arrays.toString(rows));
					
					deepcopy(film); // 원본을 filmCopy에 딥카피
					
					for(int idx=0;idx<i;idx++) {
						for(int w=0;w<W;w++) {
							filmCopy[rows[idx]][w]=AB[idx];
						}
					}
					if(isPass(filmCopy)) {
						// 최소 약품 투입수 갱신
						minimum=Math.min(minimum, i);
						return;
					}
				}
			}
			
		}
	}

	private static boolean isPass(int[][] filmCopy) {
		// TODO Auto-generated method stub
		int cnt=0, success=0;
		
		for(int y=0;y<W;y++) {
			for(int x=0;x<(D-1);x++) {
				if(filmCopy[x][y]==filmCopy[x+1][y]) {
					cnt+=1;
					
					if(cnt==K) {
						success=1;
						break;
					}
				}else {
					cnt=0;
				}
			}
			if(success==0) {
				return false;
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
			System.out.println("행 조합:"+Arrays.toString(output));
			rowss.add(output);
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
		if(cnt==max) {
			System.out.println("행 순열:"+Arrays.toString(output)+"를 AnB에 추가함");
			AnB.add(output);
			
			for(int[] AB:AnB) {
				System.out.println("AnB 안에 저장된 순열:"+Arrays.toString(AB));
			}
			
			return;
		}
		// TODO Auto-generated method stub
		for(int i=0;i<=1;i++) {
			output[cnt]=i;
			perm(max,cnt+1);
		}
	}

}
