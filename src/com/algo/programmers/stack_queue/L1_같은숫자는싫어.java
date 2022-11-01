package com.algo.programmers.stack_queue;

import java.util.Stack;

public class L1_같은숫자는싫어 {
	public static void main(String[] args) {
		L1_같은숫자는싫어 t = new L1_같은숫자는싫어();
		int[] arr = {1,1,3,3,0,1,1};
		int[] result = t.solution(arr);
		for(int r : result) {
			System.out.print(r+" ");
		}
		System.out.println();
	}
	
	public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for(int i=1; i<arr.length; i++) {
        	int num = arr[i];
        	if(stack.peek()!=num) {
        		stack.push(num);
        	}
        }
        int[] answer = new int[stack.size()];
        for(int i=stack.size()-1; i>=0; i--) {
        	answer[i] = stack.pop();
        }
        return answer;
    }
}
