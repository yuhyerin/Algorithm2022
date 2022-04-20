package com.algo.bj.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 마법사상어와복제 {
	/**
	 * (1,1) ... (4,4)
	 * 
	 * 물고기 : M마리 이동방향: 8방 상어
	 */
	static int M;
	static int S;
	static int sy, sx;
	static int[] dy = { 999, 0, -1, -1, -1, 0, 1, 1, 1 }; // 8방 (좌,좌상,상,우상,우,우하,하,좌하)
	static int[] dx = { 999, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[][] smell;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 물고기 수 (1~10)
		S = Integer.parseInt(st.nextToken()); // 연습횟수 (1~100)
		List<Integer>[][] map = new ArrayList[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = new ArrayList<>();
			}
		} // init

		for (int i = 0; i < M; i++) { // 물고기 정보
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			map[y][x].add(d);
		} // end for
		// 상어좌표
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken()) - 1;
		sx = Integer.parseInt(st.nextToken()) - 1;

		smell = new int[4][4]; // 냄새 표시
		
		for (int s = 1; s <= S; s++) { // 마법연습 S번 반복. 

			List<Integer>[][] origin = new ArrayList[4][4];
			// 1. 복제 - 이작업은 오래걸려서 아래 5번에서 물고기 복제된게 칸에 나타남.
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					origin[i][j] = new ArrayList<>(map[i][j]);
				}
			}// end for
			

			// 2. 모든물고기가 한칸 이동
			map = moveFish(map);
			
			// 냄새표시하기 전에 기존냄새들 카운팅.... (두번전 냄새를 구분하기 위함)
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if (smell[y][x] > 0) {
						smell[y][x]++;
					}
				}
			}
			
			// 3. 상어가 연속해서 3칸이동 - 이과정에서 냄새가 생김.
			moveShark(map);

			// 4. 두번전 연습에서 생긴 물고기냄새 사라짐.
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					if (smell[y][x] >= 3) {
						smell[y][x] = 0;
					}
				}
			}

			// 5. 1에서 사용한 복제마법 완료.
			// 복제된 물고기는 1에서의 위치, 방향을 그대로 가짐.
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					for (int d : origin[y][x]) {
						map[y][x].add(d);
					}
				}
			}
		} // end for 'S' 

		int result = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result += map[i][j].size();
			}
		}
		System.out.println(result);

	}


	// 상어 이동.
	private static void moveShark(List<Integer>[][] map) {
		
		// 3. 상어가 연속해서 3칸이동. 
		// (상,좌,하,우) 4방향 이동가능.(순서중요!)
		// 4*4*4 가지 경우의수. 
		// 가능한 방향중에 제외되는 물고기수가 가장 많은 방향으로 이동하면 됨.
		// 그런방법이 여러개라면, 사전순으로 앞서는 거부터..
		// ** 사전순
		// (상,상,상), (상,상,좌), (상,상,하) .....
		int[] dy = { -1, 0, 1, 0 }; // 상 좌 하 우
		int[] dx = { 0, -1, 0, 1 };
		
		// 이동하는중에 격자벗어나면 거기로는 못가는것임.
		// (제외된 물고기는 냄새를 남김!!!)
		
		int max = -1;
		int[][] maxPos = new int[3][2];

		// (c,b,a) (상,상,상) 
		for (int c = 0; c < 4; c++) {

			// 3번째 이동 상어위치
			int csy = sy + dy[c];
			int csx = sx + dx[c];
			if (!canGo(csy, csx)) continue; // 갈수없으면 다른방향.
			
			List<Integer> tmpC = new ArrayList<>(map[csy][csx]);
			map[csy][csx].clear(); // 물고기 제외.
			
			for (int b = 0; b < 4; b++) {
				int bsy = csy + dy[b];
				int bsx = csx + dx[b];
				if (!canGo(bsy, bsx)) continue; // 갈수없으면 다른방향.

				List<Integer> tmpB = new ArrayList<>(map[bsy][bsx]);
				map[bsy][bsx].clear(); // 물고기 제외.
				
				for (int a = 0; a < 4; a++) {
					int asy = bsy + dy[a];
					int asx = bsx + dx[a];
					if (!canGo(asy, asx)) continue; // 갈수없으면 다른방향.
					
					List<Integer> tmpA = new ArrayList<>(map[asy][asx]);
					map[asy][asx].clear();
					
					int current = tmpA.size() + tmpB.size() + tmpC.size();
					if (max < current) {
						max = current;
						maxPos[0][0] = csy;
						maxPos[0][1] = csx;
						maxPos[1][0] = bsy;
						maxPos[1][1] = bsx;
						maxPos[2][0] = asy;
						maxPos[2][1] = asx;
					}
					map[asy][asx] = tmpA; 
				}// end for a 
				map[bsy][bsx] = tmpB;
			}// end for b
			map[csy][csx] = tmpC;
		} // end for c

		sy = maxPos[2][0];
		sx = maxPos[2][1];
		
//		System.out.println("max> " + max + " 이동후의 상어위치>" + sy + "," + sx);

		// 제외된물고기 냄새 남기기. & 물고기 제외.
		for (int c = 0; c < 3; c++) {
			if(map[maxPos[c][0]][maxPos[c][1]].size()>0) {
				smell[maxPos[c][0]][maxPos[c][1]]=1;
				map[maxPos[c][0]][maxPos[c][1]].clear();
			}
		}
	}


	// 물고기 이동. 
	private static List<Integer>[][] moveFish(List<Integer>[][] map) {
		List<Integer>[][] result = new ArrayList[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				result[i][j] = new ArrayList<>();
			}
		}// init 
		
		// 자신의이동방향이 이동할수있는칸을 향할때까지 45도반시계회전한다.
		// 이동할수있는칸이 없으면 안함.
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for (int d : map[y][x]) {
					int ny = y + dy[d]; // 최초방향(d)으로 이동했을때의 좌표
					int nx = x + dx[d];

					// 격자벗어나거나, 물고기냄새있거나, 상어있으면 이동x 
					if (!canGo(ny, nx) || smell[ny][nx] > 0 || (sy == ny && sx == nx)) {
						int nd = d; // 기존방향.  
						boolean move = false;
						// 최대 7번회전 하면서, 이동가능방향 찾음. 
						for (int i=0; i < 7; i++) {
							// 45도 반시계 회전
							nd = nd == 1 ? 8 : nd - 1;
							ny = y + dy[nd];
							nx = x + dx[nd];
							// 이동가능방향 찾음.
							if (canGo(ny, nx) && smell[ny][nx] == 0 && !(sy == ny && sx == nx)) {
								result[ny][nx].add(nd);
								move = true;
								break;
							}
						}// end for
						if(!move) {
							// 움직이지 못했으면 그대로.
							result[y][x].add(d);
						}
					} else {
						// 이동가능함.
						result[ny][nx].add(d);
					}
				} // map[y][x] 에 있는 물고기들 전부 이동.
			} // end for 'x'
		} // end for 'y'
		
		return result;
	}


	private static void printMap(List[][] map) {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(map[i][j].size()>0) {
					System.out.print("("+i+","+j+") >> ");
					for(int k=0; k<map[i][j].size();k++) {
						System.out.print(map[i][j].get(k)+" ");
					}
					System.out.println();
				}
			}
		}

	}

	private static boolean canGo(int y, int x) {
		if (0 <= y && y < 4 && 0 <= x && x < 4)
			return true;
		return false;
	}
}
