package com.algo.bj.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14719_빗물 {

	/**
	 * 고이는 빗물의 총량 구하기
	 * 
	 * 풀이방법
	 */

	static int H, W;
	static int maxIdx = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int[] arr = new int[W];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (max < arr[i]) {
				max = arr[i];
				maxIdx = i;
			}
		} // end input

//		System.out.println("maxIdx: " + maxIdx + ", maxValue:" + arr[maxIdx]);
		int answer = 0;

		// 1개이상의 벽이 쌓여있는 가장 왼쪽 블록 찾기.
		int left = -1;
		int idx = 0;
		while (idx < W) {
			left = idx;
			if(arr[left]>0) {
				break;
			}
			idx++;
		}
//		System.out.println("left: " + left);
		if(left == -1 || W-2 <= left) {
			System.out.println(0);
			return;
		}

		// 왼쪽부터 시작 -->
		int next = left + 1;
		while (next <= maxIdx) {
			if (arr[left] > arr[next]) {
				// 왼쪽블록보다 낮아 빗물 고임
				int water = arr[left] - arr[next];
//				System.out.println("water: " + water);
				answer += water;
			} else {
				left = next;
//				System.out.println("left 갱신: " + left);
			}
			next++;
		}

		// 1개이상의 벽이 쌓여있는 가장 오른쪽 블록 찾기.
		int right = W-1;
		idx = W-1;
		while (idx >= 0) {
			right = idx;
			if(arr[right]>0) {
				break;
			}
			idx--;
		}
		System.out.println("right: " + right);

		// 오른쪽부터 시작 <--
		next = right-1;
		while (next >= maxIdx) {
			if (arr[right] > arr[next]) {
				// 오른쪽 블록보다 낮아 빗물 고임
				int water = arr[right] - arr[next];
				System.out.println("water: " + water);
				answer += water;
			} else {
				right = next;
				System.out.println("right 갱신: " + right);
			}
			next--;
		}

		System.out.println(answer);

	}
}
