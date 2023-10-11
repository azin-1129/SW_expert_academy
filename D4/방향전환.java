package d1011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 방향전환 {
	// 참조 블로그 https://moons-memo.tistory.com/88
	
	static int x1,y1,x2,y2;
	static boolean visited[][][];
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static int res;
	
	static class Node{
		int x;
		int y;
		int dir;
		int cnt;
		
		public Node(int x, int y, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.dir=dir;
			this.cnt = cnt;
		}
		
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=T;t++) {
			// System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡTest"+t+"ㅡㅡㅡㅡㅡㅡㅡㅡ");
			st=new StringTokenizer(br.readLine());
			
			x1=Integer.parseInt(st.nextToken());
			y1=Integer.parseInt(st.nextToken());
			x2=Integer.parseInt(st.nextToken());
			y2=Integer.parseInt(st.nextToken());
			
			x1+=100;
			x2+=100;
			y1+=100;
			y2+=100;
			
			visited=new boolean[3][202][202];
			
			bfs();
			
			bw.write("#"+t+" "+res+"\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void bfs() {
		Queue<Node> q=new LinkedList<>();
//		q.add(new Node(x1,y1,1,0)); // 1: 가로, 2: 세로
//		q.add(new Node(x1,y1,2,0));
//		
		// System.out.println("x2:"+x2+", y2:"+y2);
		q.add(new Node(x1+dx[0],y1+dy[0],2,1));
		q.add(new Node(x1+dx[1],y1+dy[1],2,1));
		q.add(new Node(x1+dx[2],y1+dy[2],1,1));
		q.add(new Node(x1+dx[3],y1+dy[3],1,1));
		
		int nx=0, ny=0, nd=0;
		
		// visited[x1][y1]=true;
		
		while(!q.isEmpty()) {
			// System.out.println("q:"+q);
			Node node=q.poll();
			
			nx=node.x;
			ny=node.y;
			nd=node.dir;
			
			if(visited[nd][nx][ny]) {
				// System.out.println(nx+", "+ny+"는 방문했대");
				continue;
			}
			
			visited[nd][nx][ny]=true;

			// System.out.println("nx: "+nx+", ny:"+ny+", cnt:"+node.cnt+", dir:"+node.dir);
			
			if(nx==x2 && ny==y2) {
				res=node.cnt;
				// System.out.println("결과:"+res);
				return;
			}
			
			// if(nx<0||nx>) // ??
			
			if(node.dir==1) { // 가로
				// System.out.println("노드는 추가해?");
				// 0,1
				q.add(new Node(nx+dx[0],ny+dy[0],2,node.cnt+1));
				q.add(new Node(nx+dx[1],ny+dy[1],2,node.cnt+1));
			}else { // 세로
				// System.out.println("노드는 추가해?");
				q.add(new Node(nx+dx[2],ny+dy[2],1,node.cnt+1));
				q.add(new Node(nx+dx[3],ny+dy[3],1,node.cnt+1));
			}
			
			
		}
	}

}
