package com.algo.programmers;

import java.util.ArrayList;

public class L2_수식최대화 {

	public static void main(String[] args) {
		L2_수식최대화 t = new L2_수식최대화();
//		String expression = "100-200*300-500+20";
		String expression = "50*6-3*2";
		System.out.println(t.solution(expression));
	}

	/**
	 * 숫자, 3가지 연산문자 (+,-,*) 포함된 연산수식 
	 * 연산문자 우선순위를 자유롭게 재정의하여 만들수 있는 가장 큰 수를 제출. 
	 * 같은순위의 연산자는 안됨.
	 * 결과 음수는 절대값 처리.
	 * 
	 * expression 길이 3~100 공백, 괄호 없음. 피연산자는 0~999
	 * 
	 * 중간, 최종 결과값이 2^63-1 이하가 되도록 입력 주어짐.
	 * 
	 * + - * ===>  1) + 앞의수, 뒤의수 연산. 2) - 앞의수, 뒤의수 연산. 3) * 앞에수, 뒤의수 .
	 * + * -
	 * 
	 */

	public long solution(String expression) {
		
		String[] buhos = { "+", "-", "*" };
		String[] strbuho = expression.split("[0-9]+"); // 부호갯수: N
		String[] strnums = expression.split("[^0-9]+"); // 숫자갯수: N+1
		
		ArrayList<String> buho = new ArrayList<>(); // 부호 list 
		ArrayList<Long> nums = new ArrayList<>(); // 숫자 list 
		
		for(String b: strbuho) {
			if(!b.isEmpty()) {
				buho.add(b);
			}
		}
		for(String n: strnums) {
			if(!n.isEmpty()) {
				nums.add(Long.parseLong(n));
			}
		}
		
		long max = 0;
		for(int i=0; i<3; i++) { // 첫번째 
			for(int j=0; j<3; j++) { // 두번째 
				if(i==j) continue;
				for(int k=0; k<3; k++) { // 세번째 
					if(i==k || j==k) continue;
					int[] pick = {i,j,k}; // 부호우선순위 
					long result = go(buhos, nums, buho, pick);
					if(max<result) {
						max = result;
					}
				}
			}
		}
		
		return max;
	}
	
	private long go(String[] buhos, ArrayList<Long> nums, ArrayList<String> buho, int[] pick) {
		
		ArrayList<Long> nums_copy = new ArrayList<>();
		ArrayList<String> buho_copy = new ArrayList<>();
		// copy list 생성 
		for(Long num : nums) {
			nums_copy.add(num);
		}
		for(String b : buho) {
			buho_copy.add(b);
		}
		for(int i=0; i<3; i++) { // 부호 우선순위... 
			int idx = pick[i];
			String cur = buhos[idx];
			int j = 0;
			while(j<buho_copy.size()){
				boolean isRemove = false;
				if(buho_copy.get(j).equals(cur)) {
					long tmp = calc(cur, nums_copy.get(j),nums_copy.get(j+1));
					// 연산한 연산자, 피연산자 제거 
					nums_copy.remove(j);
					nums_copy.remove(j);
					buho_copy.remove(j);
					
					// 연산결과 nums에 add 
					nums_copy.add(j, tmp);
					isRemove = true;
				}
				if(!isRemove) { // 연산자를 제거하지 않은 경우에만 j++ 
					j++;
				}
				if(buho_copy.size()==0) {
					break;
				}
			}
			
		}// end for 
		
		return Math.abs(nums_copy.get(0));
	}

	private long calc(String cur, long a, long b) {
		switch(cur) {
			case "+" :
				return a+b;
			case "-":
				return a-b;
			case "*":
				return a*b;
		}
		return 0;
	}

	private void printList(ArrayList<?> list) {
		System.out.println();
		System.out.println("======= list print ======= ");
		for(Object o : list) {
			System.out.print(o+" ");
		}
		System.out.println();
		System.out.println("======= list print END ======= ");
		System.out.println();
	}
}
