package com.algo.programmers;

import java.util.ArrayList;
import java.util.Collections;

public class L2_빛의경로사이클 {
	public static void main(String[] args) {
//		String[] grid = { "SL", "LR" };
//		String[] grid = {"S"};
		String[] grid = {"R","R"};
		L2_빛의경로사이클 t = new L2_빛의경로사이클();
		int[] result = t.solution(grid);
		for (int r : result) {
			System.out.print(r + " ");
		}
		System.out.println();
	}

	/**
	 * S,L,R S : 직진 L : 좌회전 R : 우회전 경로사이클 : 빛이 이동하는 순환 경로. 경로사이클이 몇개? 각 사이클의 길이가 얼마?
	 * 
	 * grid : 1~500 grid의 각 문자열길이 : 1~500
	 * 
	 * 
	 * 0: 위, 1:왼, 2:아래, 3:오른 왼쪽> 기준+1 오른쪽> 기준-1
	 */

	class Node {
		char d;// 'S', 'L', 'R'
		boolean[] dirs;
		public Node(char d) {
			super();
			this.d = d;
			this.dirs = new boolean[4];
		}
	}
	
	public int[] solution(String[] grid) {
		
		int N = grid.length; // 행 수
		int M = grid[0].length(); // 열 수

		Node[][] map = new Node[N][M];

		for (int i = 0; i < N; i++) {
			String row = grid[i]; // SL
			for (int j = 0; j < M; j++) {
				char dir = row.charAt(j);
				map[i][j] = new Node(dir);
			}
		} // 초기화
		
		ArrayList<Integer> result = new ArrayList<>();
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				cnt=0;
				for(int d=0; d<4; d++) {
					cnt = go(N,M,i,j,d,map);
					if(cnt>0) result.add(cnt);
				}
			}
		}
		
		Collections.sort(result);
		int[] answer = new int[result.size()];
		for(int i=0; i<result.size(); i++) {
			answer[i] = result.get(i);
		}
		
		return answer;
	}

	// 0: 위, 1:왼, 2:아래, 3:오른 (반시계)
	int[] dy = {};
	int[] dx = {}; 
	private int go(int N, int M, int y, int x, int d, Node[][] map) {
		int cnt = 0;
		while(true) {
			
			if(map[y][x].dirs[d]) {
				// 한번 지나갔던 길을 만나면 종료 
				break;
			}
			
			map[y][x].dirs[d]=true; // 지나가 
			
			// 다음위치 (d방향으로 이동) 
			if(d==0) {
				y = y==0?N-1:y-1; //위로 
			}else if(d==1) {
				x = x==0?M-1:x-1; // 왼쪽으로  
			}else if(d==2) {
				y = y==N-1?0:y+1; // 아래로 
			}else if(d==3) {
				x = x==M-1?0:x+1; // 오른쪽으로 
			}
			
			// 다음위치에서의 방향
			if(map[y][x].d=='L') {
				d = changeDir(d,'L');
			}else if(map[y][x].d == 'R') {
				d = changeDir(d,'R');
			}
			cnt+=1;
		}
		return cnt;
	}
	
	// 0: 위, 1:왼, 2:아래, 3:오른 (반시계)
	// 방향:0 > 왼:1(+1), 오른:3(-1)
	private int changeDir(int d, char dir) {
		if(dir=='L') {
			d=d==3?0:d+1;
		}else if(dir=='R') {
			d=d==0?3:d-1;
		}
		return d;
	}

	/**
	 * 1. 나가는 방향만 체크하면 됨 
	 * 2. 재귀로 풀면 안됨. 어짜피 특정통로를 통해 지나가는 경우는 유니크함. 
	 * */
//	private void go2(int N, int M,int y, int x, int d, Node[][] map, int cnt) {
//		
//		// 나가는 위치 (y,x) , 방향: d 
//		map[y][x].outDirs[d] = true;
//		
//		// 들어가는 위치 (ny,nx) , 방향: nd  
//		int ny = y;
//		int nx = x;
//		
//		if(d==0) {
//			// 위 
//			ny=y-1<0?N-1:y-1;
//		}else if(d==1) {
//			// 왼 
//			nx = x-1<0?M-1:x-1;
//		}else if(d==2) {
//			// 아래 
//			ny = y+1>3?0:y+1;
//		}else if(d==3) {
//			// 오른 
//			nx = x+1>3?0:x+1;
//		}
//		
//		// 다음위치에서의 방향. 
//		int nd = d;
//		if (map[ny][nx].d == 'L') { // 좌회전 or 우회전인 경우 방향 체인지.
//			// 좌회전
//			nd = d + 1 > 3 ? 0 : d + 1;
//		} else if (map[y][x].d == 'R') {
//			// 우회전
//			nd = d - 1 < 0 ? 3 : d - 1;
//		}
//		
//		if (!map[ny][nx].inDirs[nd]) { // 다음위치로 들어가는 길이 방문하지 않았으면, 
//			System.out.println("("+ny+","+nx+") 에서 "+nd+" 방향으로 나감... ");
//			map[y][x].outDirs[d] = true; // d방향으로 나감.
//			
//			System.out.println("("+ny+","+nx+") 으로 이제 들어감. 방향> "+nd);
//			
//			map[ny][nx].inDirs[nd] = true;
//			go2(N,M,ny, nx, nd, map, cnt + 1);
//			
//			
//		} else {
//			// 더이상 갈 수 없음. 싸이클 종료.
//			System.out.println(y+","+x+" >>> 싸이클!");
//		}
//	}
}
