package com.algo.bj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16236_아기상어 {
	/**
	 * 초기 아기상어 크기 : 2 자기보다 큰 물고기 있는 칸은 지나갈 수 없다. 크기가 같으면 지나갈 수는 있지만 먹을 순 없다.
	 * 
	 * 이동방법 1. 먹을수있는 물고기가 1마리 -> 먹으러 간다. 2. 1마리 이상이면 -> 가장 가까운 고기를 먹으러 간다. 가까운 물고기가
	 * 많으면, 가장 위에 있는 물고기. 가장 왼쪽에 있는 물고기. 3. 없으면 -> 엄마상어에게 도움요청 물고기를 먹으면 해당칸은 빈칸이 됨.
	 * 상어는 자기랑 같은크기랑 같은수의 물고기를 먹을때 크기가 1 증가함. ex) 상어크기가 2면 2마리 먹으면 크기가 3됨. 그다음은
	 * 3마리먹으면 4됨. ... 엄마상어에게 도움요청하지 않고 몇초동안 잡아먹을 수 있는지 구해라.
	 */
	static int N;
	static int M; // 물고기 M마리
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int sy = -1, sx = -1;// 아기상어 초기위치
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) { // 아기상어 초기위치.
					sy = i;
					sx = j;
					map[i][j] = 0; // 9는 제거.
				}
			}
		} // end input
		
		go(sy, sx, 0, 0, 2);
		
	}

	static int[] dy = { -1, 0, 0, 1 }; // 상, 좌, 우, 하 (순서중요)
	static int[] dx = { 0, -1, 1, 0 };

	private static void go(int y, int x, int time, int eatCnt, int sharkSize) {

		// 먹을 물고기의 좌표
		int[] fishInfo = findFish(y, x, sharkSize);
		if (fishInfo[0] == -1 && fishInfo[1] == -1) { // 물고기가 없으면 종료.
			System.out.println(time);
			return;

		} else { // 잡아먹을 물고기 있음.
			int ny = fishInfo[0];
			int nx = fishInfo[1];
			int dist = fishInfo[2];
			int fish = map[ny][nx];
			
			map[ny][nx] = 0; // 잡아먹음.

			if (eatCnt+1 == sharkSize) { // 상어크기만큼 잡아먹으면 크기증가하고, 카운팅 0으로 초기화
				eatCnt = 0; // 초기화
				go(ny, nx, time + dist, 0, sharkSize + 1);
			} else {
				go(ny, nx, time + dist, eatCnt + 1, sharkSize);
			}
			map[ny][nx] = fish; // 원복
		}
	}
	private static int[] findFish(int y, int x, int sharkSize) {
		
		// ==================== 거리배열만들기 START ===================
		int[][] dist = new int[N][N];
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {y,x});
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int cy = cur[0];
			int cx = cur[1];
			
			if(!visit[cy][cx]) {
				visit[cy][cx] = true;
				
				for (int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];
					if (!canGo(ny, nx)) continue;
					if (!visit[ny][nx] && map[ny][nx] <= sharkSize) {
						dist[ny][nx] = dist[cy][cx]+1;
						que.add(new int[] { ny, nx });
					}
					
				}//end for 
			}
		}// end while
		// ==================== 거리배열만들기 END ===================
		
		int minDist = Integer.MAX_VALUE;
		int fy = -1;
		int fx = -1;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==y && j==x) continue;
				if(0 < map[i][j] && map[i][j] < sharkSize) {
					int d = dist[i][j];
					if(d>0) {
						if(minDist > d) {
							minDist = d;
							fy = i;
							fx = j;
						}
					}
				}
			}
		}
		
		return new int[] {fy,fx,minDist};
	}

	// 가장 가까운 물고기를 찾음...
	private static int[] findFish2(int y, int x, int sharkSize) {

		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { y, x });

		boolean[][] visit = new boolean[N][N];

		while (!que.isEmpty()) {

			int[] cur = que.poll();
			int cy = cur[0];
			int cx = cur[1];

			if (!visit[cy][cx]) {

				visit[cy][cx] = true;

				for (int d = 0; d < 4; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];

					if (!canGo(ny, nx))
						continue;

					if (!visit[ny][nx]) {
						// (1) 먹을 수 있는 물고기가 있는지 검사
						if (map[ny][nx] > 0 && map[ny][nx] < sharkSize) {
							return new int[] { ny, nx };
							// (2) 먹을 수 있는 물고기가  없으면 지나갈수있는지 검사...
						} else if (map[ny][nx] == 0 || map[ny][nx] == sharkSize) {

							// 밟은애는 다시 못돌아오게
							que.add(new int[] { ny, nx });
						}
					}
				} // end for
			}

		} // end while 

		return new int[] { -1, -1 };
	}

	private static boolean canGo(int y, int x) {
		if (0 <= y && y < N && 0 <= x && x < N)
			return true;
		return false;
	}
}
