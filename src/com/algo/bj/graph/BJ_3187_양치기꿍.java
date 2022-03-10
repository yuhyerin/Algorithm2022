package com.algo.bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3187_양치기꿍 {
	/**
	 * . 빈공간
	 * # 울타리 
	 * v 늑대 
	 * k 양 
	 * 
	 * 상하좌우로만 이동 가능. 
	 * 양의 숫자가 늑대의숫자보다 많을경우만 늑대가 먹힘. 
	 * */
	static char[][] map; 
	static boolean[][] visit;
	static int R,C;
	static int sheepCnt;
	static int wolfCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // R,C : 3~250 
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		
		sheepCnt = 0;
		wolfCnt = 0;
		
		for(int i=0; i<R; i++) {
			String input = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = input.charAt(j);
			}
		}// end input 
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(!visit[i][j] && (map[i][j]=='.'||map[i][j]=='v'||map[i][j]=='k') ) {
					makeGroup(i,j); // group을 만든다. 
				}
			}
		}
		
		// 살아남게 되는 양과 늑대의 수를 출력... 
		System.out.println(sheepCnt+" "+wolfCnt);
		
	}
	static int[] dy= {1,-1,0,0}; // 상 하 좌 우 
	static int[] dx= {0,0,-1,1};
	private static void makeGroup(int y, int x) {
		
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {y,x});
		
		int sheep = 0;
		int wolf = 0;
		
		while(!que.isEmpty()) {
			
			int[] cur = que.poll();
			int cy = cur[0];
			int cx = cur[1];
			
			if(!visit[cy][cx]) {
				visit[cy][cx]=true;
				if(map[cy][cx]=='v') { //늑대면 
					wolf++;
				}else if(map[cy][cx]=='k') { //양이면 
					sheep++;
				}
				
				for(int d=0; d<4; d++) {
					int ny = cy+dy[d];
					int nx = cx+dx[d];
					if(!canGo(ny,nx)) continue;
					if(map[ny][nx]=='#') continue;
					if(!visit[ny][nx]) {
						que.add(new int[] {ny,nx});
					}
				}
			}
			
		}// end while 
		
		if(wolf==0 && sheep>0) {
			sheepCnt+=sheep;
		}else if(sheep==0 && wolf>0) {
			wolfCnt+=wolf;
		}else if(wolf<sheep) {
			sheepCnt+=sheep;
		}else if(wolf>=sheep) {
			wolfCnt+=wolf;
		}
		
	}
	
	private static boolean canGo(int y, int x) {
		if(0<=y && y<R && 0<=x && x<C) return true;
		return false;
	}
}	
