package com.algo.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class L2_메뉴리뉴얼 {
	static class Key{
		String name;
		int cnt;
	}
	public static void main(String[] args) {
		L2_메뉴리뉴얼 t = new L2_메뉴리뉴얼();
//		String[] orders = {
//				"ABCFG", 
//				"AC", 
//				"CDE", 
//				"ACDE", 
//				"BCFG", 
//				"ACDEH"};
//		String[] orders = { "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" };
		String[] orders = {"XYZ","XWY", "WXA"};
		int[] course = { 2,3,4 };
		String[] result = t.solution(orders, course);
		for (String r : result) {
			System.out.print(r + " ");
		}
		System.out.println();
	}

	/**
	 * 기존 메뉴들을 조합해 코스요리로 재구성. 최소 2명이상 주문된 메뉴를 2가지 이상 조합. 손님1 - A,B,C,F,G 손님2 - A,C
	 * ...
	 * 
	 * 가장많이 함께 주문된 메뉴구성이 여러개면, 모두 리턴하면 됨.
	 */
	public String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		// orders : 주문내역 (배열크기 2이상 20이하)-> 손님 20명 이하.
		// 각 원소의 크기(2~20) -> 한번에 시킨메뉴가 2개이상 20개이하.
		
		// course : 코스요리를 구성하는 단품메뉴들의 갯수.
		// 배열크기는 1이상 10이하. (오름차순 정렬되어 있음. 값 중복X)
		// 배열원소는 2이상 10이하 자연수. 
		// 리턴값은 알파벳 오름차순 정렬.
		
		// course : 2,3,5 
		Set<String> keySet = new HashSet<String>();
		for(int size : course) {
			for(String order : orders) {
				if(size<=order.length()) {
					boolean[] visit = new boolean[order.length()];
					makeKeys(0,order, size, keySet, visit, 0);
				}
			}
		}
		
		List<String> keys = new ArrayList<>(keySet);
		Collections.sort(keys);
		int[] count = new int[keys.size()]; //카운팅 배열 
		
		// 키의 한자리 한자리가 order에 포함되는지 검사. 
		for(int k=0; k<keys.size(); k++) {
			String key = keys.get(k);
			for(String order : orders) {
				if(containsKey(order, key)) {
					count[k]++;
				}
			}
		}
		
		int[] max = new int[11];
		for(int i=0; i<keys.size(); i++) {
			if(count[i]>=2) {
				int len = keys.get(i).length();
				if(count[i]>max[len]) {
					max[len]=count[i];
				}
			}
		}
		
		ArrayList<String> result = new ArrayList<>();
		for(int i=0; i<keys.size(); i++) {
			int j = keys.get(i).length();
			if(count[i] == max[j]) {
				result.add(keys.get(i));
			}
		}
		Collections.sort(result);
		answer = new String[result.size()];
		for(int i=0; i<result.size(); i++) {
			answer[i] = result.get(i);
		}
		
		return answer;
	}

	private boolean containsKey(String order, String key) {
		for(int i=0; i<key.length(); i++) {
			if(!order.contains(key.charAt(i)+"")) {
				return false;
			}
		}
		return true;
	}

	private void makeKeys(int idx, String order, int size, Set<String> keys, boolean[] visit, int cnt) {
		
		if(cnt==size) {
			List<Character> list = new LinkedList<>();
			
			for(int i=0; i<order.length(); i++) {
				if(visit[i]) {
					list.add(order.charAt(i));
				}
			}
			Collections.sort(list);
			String key = new String();
			for(char c: list) {
				key+=c;
			}
			keys.add(key);
			
			return;
		}
		
		for(int i=idx; i<order.length(); i++) {
			if(!visit[i]) {
				visit[i]=true;
				makeKeys(i+1,order, size, keys, visit, cnt+1);
				visit[i]=false;
			}
			
		}
	}

}
