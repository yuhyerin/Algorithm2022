package com.algo.kakao.y2019;

import java.util.ArrayList;
import java.util.Collections;

public class 실패율 {
	public static void main(String[] args) {
		실패율 t = new 실패율();
		int N = 5;
		int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };
		int[] result = t.solution(N, stages);
		for (int r : result) {
			System.out.print(r + " ");
		}
		System.out.println();
	}

	class Stage implements Comparable<Stage> {
		int no;
		double failRate;

		public Stage(int no, double failRate) {
			this.no = no;
			this.failRate = failRate;
		}

		@Override
		public int compareTo(Stage o) {
			if (o.failRate - failRate > 0) {
				return 1;
			} else if (o.failRate - failRate < 0) {
				return -1;
			} else {
				return no-o.no;
			}
		}

	}

	/**
	 * N : 스테이지 갯수 (1~500) stages : 사용자가 현재 멈춰있는 스테이지번호 배열. stages의 길이 : 1~200,000
	 * stages에는 1이상, N+1이하의 자연수가 담겨있음. 실패율 : 스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에
	 * 도달한 플레이어 수
	 * 
	 */
	public int[] solution(int N, int[] stages) {
		int len = stages.length; // 스테이지에 도전한 사용자 수
		ArrayList<Stage> fails = new ArrayList<>();
		for (int stage = 1; stage <= N; stage++) {
			double cnt = 0;
			double total = 0;
			for (int n = 0; n < len; n++) {
				if (stages[n] == stage) {
					cnt++;
				}
				if (stages[n] >= stage) {
					total++;
				}
			}
			fails.add(new Stage(stage, cnt / total));
		}
		Collections.sort(fails);
		for(Stage stage : fails) {
			System.out.println("["+stage.no+"] :"+stage.failRate);
		}
		int[] answer = new int[N];
		for (int i = 0; i < N; i++) {
			answer[i] = fails.get(i).no;
		}
		return answer;
	}
}
