package com.algo.programmers.stack_queue;

import java.util.Stack;

public class L2_올바른괄호 {
	public static void main(String[] args) {
		L2_올바른괄호 t = new L2_올바른괄호();
//		String s = "()()";
//		String s = "(())()";
//		String s = ")()(";
		String s = "(()(";
		System.out.println(t.solution(s));
	}

	boolean solution(String s) {

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (stack.isEmpty() || c == '(') {
				stack.push(c);
				continue;
			}
			
			// c== ')'
			if(stack.peek()=='(') {
				stack.pop();
			}else {
				stack.push(c);
			}
		}

		return stack.size()==0?true:false;
	}
}
