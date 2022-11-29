package com.algo.bj.graph.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2021_최소환승경로 {
	
	static int N, L; // N: 역 갯수, L: 노선 갯수 
	
	// 방문 체크 
	static boolean[] visitedStation;
	static boolean[] visitedLine;
	// 노선, 역 
	static List<Integer>[] stations; // stations[3] = 3번 역이 속하는 노선들...  = {1,3} 
	static List<Integer>[] lines; // lines[1] = 1번 라인에 포함된 역들... = {1,2,3,4,5}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 역 갯수 
		L = Integer.parseInt(st.nextToken()); // 노선 갯수 
		
		visitedStation = new boolean[N+1];
		visitedLine = new boolean[L+1];
		stations = new ArrayList[N+1];
		lines = new ArrayList[N+1];
		
		for(int i=1; i<= N; i++) {
			stations[i] = new ArrayList<>();
			lines[i] = new ArrayList<>();
		}

		for(int i=1; i<= L; i++) {
			String[] s = br.readLine().split(" ");
			for(String strStation : s) {
				int station = Integer.parseInt(strStation);
				if(station == -1) {
					break;
				}
				stations[station].add(i); // 특정 역이 포함되는 노선들. 
				lines[i].add(station); // 특정 노선에 포함된 역들. 
			}
		}// end for
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int answer = bfs(start, end);
		System.out.println(answer);
	}
	
	/**
	 * 출발 역 : 1번역 
	 * 도착 역 : 10번역 */
	public static int bfs(int start, int end) {
		
		// 우선순위 큐 -> 최소 환승 순서로 poll
		Queue<Station> que = new PriorityQueue<>(Comparator.comparing(n->n.transCount));
		
		visitedStation[start] = true; // 출발 역 방문처리.
		
		for(int line : stations[start]) {
			que.add(new Station(line, start, 0));
			visitedLine[line]= true;  // 출발 역이 속해 있는 노선들 방문처리.
		}
		
		while(!que.isEmpty()) {
			Station now = que.poll();
			
			if(now.station == end) {
				return now.transCount;
			}
			
			// 현재 노선에 포함된 역들을 이동... 
			for(int nextStation : lines[now.line]) {
				// nextStation : 다음 이동할 역. 
				if(!visitedStation[nextStation]) {
					
					visitedStation[nextStation] = true; // 해당 역을 방문 처리 
					que.add(new Station(now.line, nextStation, now.transCount));
					
					// 다음 이동할 역이 혹시나 다른 노선에 있는 경우도 있을 것임. 
					// 이 경우 환승횟수 +1 
					for(int otherLine : stations[nextStation]) {
						
						if(!visitedLine[otherLine]) {
						
							visitedLine[otherLine] = true; // 해당 역이 속한 다른 노선을 방문 처리.
							que.add(new Station(otherLine, nextStation, now.transCount+1));
						}
					}
				}
			}// end for 
			
			System.out.println("=========== END ============= ");
		}// end while 
		
		return -1;
	}
	
	private static class Station{
		int line;
		int station;
		int transCount; // 환승 횟수 
		
		public Station(int line, int station, int transCount) {
			this.line = line;
			this.station = station;
			this.transCount = transCount;
		}

		@Override
		public String toString() {
			return "Station [line=" + line + ", station=" + station + ", transCount=" + transCount + "]";
		}
		
		
	}
}
