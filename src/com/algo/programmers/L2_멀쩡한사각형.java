package com.algo.programmers;

public class L2_멀쩡한사각형 {

	public static void main(String[] args) {
		L2_멀쩡한사각형 t = new L2_멀쩡한사각형();
		int w = 12;
		int h = 8;
		System.out.println(t.solution2(w, h));
	}
	
	public long solution(int w, int h) {
        long count = 0;
        for(int x=0; x<w; x++){
            double cur = Math.ceil((double)h*(x+1)/w);
            double prev = Math.floor((double)h*x/w);
            count += (cur-prev);
        }
        return (long)h*w-count;
    }
	
	public long solution2(int w, int h) {
		long answer = 0;
		long gcd = gcd(w,h); // 최대공약수
		long notAvailBox = (w/gcd)+(h/gcd)-1; // 사용불가능 박스갯수 구하는 공식
		answer = (long)w*h - notAvailBox*gcd;
		return answer;
	}
	
	// 유클리드 호제법 (최대공약수 구하기)
	private long gcd(int w, int h) {
		if(w==0) return h;
		return gcd(h%w, w);
	}
}
