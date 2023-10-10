package d1010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 참조 출처: https://toastfactory.tistory.com/226

public class 수영대회결승전 {
	static class Player{
		int row,col,cnt;
		
		public Player(int row, int col, int cnt) {
			this.row=row;
			this.col=col;
			this.cnt=cnt;
		}
	}
	
	static int A,B,C,D,N;
	static int arrive;
	static int[][] map;
	static boolean[][] visited;
	static int dx[]= {-1,1,0,0}, dy[]={0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			arrive=-1; // 이전 테스트값 영향 지우기
			
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			visited=new boolean[N][N];
			
			for(int n=0;n<N;n++) {
				map[n]=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			A=Integer.parseInt(st.nextToken());
			B=Integer.parseInt(st.nextToken()); // 시작 위치
			
			st=new StringTokenizer(br.readLine());
			C=Integer.parseInt(st.nextToken());
			D=Integer.parseInt(st.nextToken()); // 도착 위치
			
			// System.out.println("C:"+C+", D:"+D);
			bfs();
			

			bw.write("#"+t+" "+arrive+"\n");

		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// bfs 이론
	public static void bfs() {
		Queue<Player> q=new LinkedList<>();
		q.add(new Player(A,B,0));
		visited[A][B]=true;
		
		while(!q.isEmpty()) {
			Player player=q.poll();
			
			for(int i=0;i<4;i++) {
				int nx=player.row+dx[i];
				int ny=player.col+dy[i];
				
				if(nx<0||nx>=N||ny<0||ny>=N) continue;
				
				if(nx==C && ny==D) {
					arrive=player.cnt+1;
					return;
				}
				
				if(map[nx][ny]==1 || visited[nx][ny]) continue;
				
				if(map[nx][ny]==2) {
					if(player.cnt%3==2) {
						visited[nx][ny]=true;
						q.add(new Player(nx,ny,player.cnt+1));
					}else {
						visited[player.row][player.col]=true;
						q.add(new Player(player.row, player.col, player.cnt+1));
					}
				}else {
					visited[nx][ny]=true;
					q.add(new Player(nx,ny,player.cnt+1));
				}
			}
		}
	}
}
