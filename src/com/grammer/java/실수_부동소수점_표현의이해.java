package com.grammer.java;

public class 실수_부동소수점_표현의이해 {
	public static void main(String[] args) {
		double num = 1.0000001; // => 2진수로 변환하면. fraction 영역이 디게 길게 변환됨. 23비트 이후의 영역은 짤려나가서 사라짐. 그만큼 오차가 발생함.
		double num2 = 2.0000001;
		System.out.println(num);
		System.out.println(num2);
		System.out.println(num+num2);
		
		double answer = 1.0/3.0;
		System.out.printf("%.20f\n", answer);
		System.out.println(answer);
		
	}
}
