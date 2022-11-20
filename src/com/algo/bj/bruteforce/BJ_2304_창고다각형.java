package com.algo.bj.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 수평, 수직으로 구성. 모두 연결되어야 함.
 * 2. 수평부분은 반드시 어떤 기둥의 윗면과 닿아야 함. 
 * 3. 수직부분은 반드시 어떤 기둥의 옆면과 닿아야 함. 
 * 4. 지붕의 가장자리는 땅에 닿아야 함. 
 * 5. 비가올때 물이 고이면 안된다. (오목하게 들어간 부분이 없어야 한다.) 
 * 
 * 풀이방법
 * - 1. 왼쪽에서 시작해서 오른쪽 방향으로 이동하면서 
 * 가장높은 기둥을 만날때까지 쭉 자신의높이로 선을 그으며 이동한다.
 * 자신과 같거나 자신보다 높은 기둥을 만나면 그 기둥의 높이로 바꿔서 선을 긋는다. 
 * - 2. 오른쪽에서 시작해서 왼쪽 방향으로 마찬가지로 진행한다. */
public class BJ_2304_창고다각형 {
	
	static int N;
	static Pos max = new Pos(0,0);
	static int maxIdx = -1;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		List<Pos> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			Pos pos = new Pos(L,H);
			list.add(pos);
			if(max.h < pos.h) {
				max.h = pos.h;
				max.l = pos.l;
			}
		}
		
		// 정렬
		list.sort(Comparator.comparingInt(p -> p.l));
		
		// 정렬 후 높이가 가장높은 지점이 몇번째 index에 저장되어있는지 위치 기억
		for(int i=0; i<N; i++) {
			if(list.get(i).h == max.h ) {
				maxIdx = i;
				break;
			}
		}
		
		int answer = max.h;
		
		// 왼쪽 -> max 
		Pos left = list.get(0);
		int idx = 1;
		while(idx <= maxIdx) {
			Pos next = list.get(idx);
			// 높이가 같거나 더 큰 기둥을 만나면 면적계산하고 left를 체인지. 
			if(left.h <= next.h) {
				int area = (next.l - left.l)*left.h;
				answer += area;
				left = next;
			}
			idx++;
		}
		
		// max <- 오른쪽 
		Pos right = list.get(N-1);
		idx = N-2;
		while(idx >= maxIdx) {
			Pos next = list.get(idx);
			// 높이가 같거나 더 큰 기둥을 만나면 면적계산하고 right를 체인지. 
			if(right.h <= next.h) {
				int area = (right.l - next.l)*right.h;
				answer += area;
				right = next;
			}
			idx--;
		}
		
		System.out.println(answer);
	}
}

class Pos{
	int l; // x좌표 (왼쪽으로부터 얼마나 떨어져 있는지)
	int h; // y좌표 (높이)
	
	public Pos(int l, int h) {
		this.l = l;
		this.h = h;
	}
	
	public int getL() {
		return l;
	}
	
	public int getH() {
		return h;
	}
	
	public String toString() {
		return "L: "+l+", H: "+h;
	}
}