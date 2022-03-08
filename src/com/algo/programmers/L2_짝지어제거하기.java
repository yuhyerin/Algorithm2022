package com.algo.programmers;

import java.util.Stack;

public class L2_짝지어제거하기 {
	public static void main(String[] args) {
		L2_짝지어제거하기 t = new L2_짝지어제거하기();
//		String s = "baabaa";
		String s = "abcedffdecba";
//		String s = "cdcd";
		System.out.println(t.solution(s));
	}
	
	// 1. 같은알파벳 2개 붙어있는 짝을 찾는다.
	// 2. 둘을 제거하고, 앞뒤로 문자열을 이어붙인다.
	// 이과정을 반복해 문자열 모두가 제거되면 종료. 
	// 성공적으로 수행할 수 있으면 리턴1, 아니면 리턴0 
	public int solution(String s)
    {
		Stack<Character> stack = new Stack<>();
		char[] alpha = s.toCharArray();
		for(char c : alpha) {
			if(stack.isEmpty()) {
				stack.push(c);
			}else {
				if(stack.peek()==c) {
					stack.pop();
				}else {
					stack.push(c);
				}
			}
		}
		
		if(stack.isEmpty()) {
			return 1;
		}
        return 0;
    }
}
