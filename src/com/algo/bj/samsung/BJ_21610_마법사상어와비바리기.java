package com.algo.bj.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_21610_마법사상어와비바리기 {
	/***
	 * start: PM1:05
	 */
	static int N; // 맵 크기
	static int M; // 이동 횟수
	static Node[][] map;
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 좌,좌상,상,우상,우,우하,하,좌하
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static ArrayList<int[]> clouds = new ArrayList<>();
	static ArrayList<int[]> waterCopy = new ArrayList<>();

	static class Node {
		int water;
		boolean isCloud;
		boolean isAddWater;
		Node(int water) {
			this.water = water;
			this.isCloud = false;
			this.isAddWater = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Node[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node(Integer.parseInt(st.nextToken()));
			}
		} // end for

		// 0. bibaragi start -> (N-1,0), (N-1,1), (N-2,0), (N-2,1)에 비구름 생성
		map[N-1][0].isCloud = true;
		map[N-1][1].isCloud = true;
		map[N-2][0].isCloud = true;
		map[N-2][1].isCloud = true;
		clouds.add(new int[] { N - 1, 0 });
		clouds.add(new int[] { N - 1, 1 });
		clouds.add(new int[] { N - 2, 0 });
		clouds.add(new int[] { N - 2, 1 });

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1; // 방향 (0~7)
			int s = Integer.parseInt(st.nextToken()); // 거리 (1~50)
			// 1. 모든 구름이 d방향으로 s칸 이동
			// 2. 물의양 1 증가.
			// 3. 구름 사라짐.
			moveAllClouds(d, s);

			// 4. 물이증가한칸에 물복사마법 시전.
			waterCopyMagic();

			// 5. 물양 2이상인 모든칸에 구름 생성. 물의양은 2 줄어든다.
			// 단, 3단계에서 구름이 사라졌던칸에는 생성불가.
			createCloud();

		} // end for

		// M번이동후 바구니에 들어있는 물의양의 총합 리턴.
		int result = getTotalWater();
		System.out.println(result);

	}

	private static void printCloud() {
		System.out.println("==구름상태==");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].isCloud) {
					System.out.print("● "); // ○ ● ◎
				} else {
					System.out.print("○ ");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println("==물이증가된 곳==");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].isAddWater) {
					System.out.print("● "); // ○ ● ◎
				} else {
					System.out.print("○ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j].water + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int getTotalWater() {
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += map[i][j].water;
			}
		}
		return result;
	}

	private static boolean canGo(int y, int x) {
		if (0 <= y && y < N && 0 <= x && x < N)
			return true;
		return false;
	}

	// 1. 모든 구름이 d방향으로 s칸 이동
	// 2. 물의양 1 증가.
	// 3. 구름 사라짐.
	private static void moveAllClouds(int d, int s) {
		boolean[][] addWater = new boolean[N][N];

		s = s % N;
		int len = clouds.size();
		for (int i = 0; i < len; i++) {
			int[] cloud = clouds.remove(0);
			int y = cloud[0];
			int x = cloud[1];
			map[y][x].isCloud = false; // 구름 제거. 
			int ny = y + dy[d] * s;
			if (ny < 0) {
				ny += N;
			} else if (ny >= N) {
				ny -= N;
			}
			int nx = x + dx[d] * s;
			if (nx < 0) {
				nx += N;
			} else if (nx >= N) {
				nx -= N;
			}
			addWater[ny][nx] = true; // tmp에 새로운 구름위치를 저장. 
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (addWater[i][j]) {
					map[i][j].isCloud = false; // 구름 제거. 
					map[i][j].isAddWater = true; // 물이증가한 자리 표시. 
					waterCopy.add(new int[] { i, j }); // 구름이동한 위치가 나중에 물복사 마법 시전할 위치임.
					map[i][j].water++; // 2. 물의 양 1증가.
				}
			}
		}
	}

	// 4. 물복사마법 시전.
	private static void waterCopyMagic() {
		// 대각선방향 거리 1인 물이있는 칸의 갯수만큼 바구니물양이 증가한다.
		// 단, 경계넘어가는 칸은 X
		int[][] tmp = new int[N][N]; // 물이 복사될 양을 저장하는 2차원배열 
		int len = waterCopy.size();
		for (int i=0; i<len; i++) {
			int[] pos = waterCopy.remove(0);
			int y = pos[0];
			int x = pos[1];
			int cnt = 0;
			for (int d = 1; d <= 7; d += 2) { // 대각선방향 검사. 
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (canGo(ny, nx) && map[ny][nx].water > 0) {
					cnt++;
				}
			}
			tmp[y][x] += cnt;
		}
		// 증가분 더해주기.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tmp[i][j] > 0) {
					map[i][j].water += tmp[i][j];
				}
			}
		}

	}

	// 5. 구름생성.
	// 물양 2이상인 모든칸에 구름 생성. 물의양은 2 줄어든다.
	// 단, 3단계에서 구름이 사라졌던칸에는 생성불가.
	private static void createCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].water >= 2 && !map[i][j].isAddWater) {
					map[i][j].water -= 2; // 물의양 2 줄어듬.
					clouds.add(new int[] { i, j });
					map[i][j].isCloud = true;
				} 
				else if (map[i][j].water >= 2 && map[i][j].isAddWater) {
					map[i][j].isAddWater = false; // 이제 구름이었던 흔적 지우기.
				}
			}
		}
	}
}
