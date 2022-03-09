package com.algo.programmers;

public class L2_괄호변환 {
	public static void main(String[] args) {
		L2_괄호변환 t = new L2_괄호변환();
		String p = "()))((()";
		System.out.println(t.solution(p));
	}

	/**
	 * 모든 괄호를 뽑아서 순서대로 배치된 괄호를 알려주는 프로그램 균형잡힌 괄호 -> 올바른괄호로 변환 1.
	 */
	public String solution(String p) {
		return recur(p, 0, p.length());
	}

	private String recur(String str, int start, int end) {
		if (start == end)
			return "";
		int mid = splitStr(str, start, end);
		if (validateBracket(str, start, mid)) {
			// 3번 케이스
			return str.substring(start, mid) + recur(str, mid, end);
		} else {
			// 4번 케이스
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			sb.append(recur(str, mid, end));
			sb.append(")");
			for (int i = start + 1; i < mid - 1; i++) {
				if (str.charAt(i) == '(') {
					sb.append(")");
				} else if (str.charAt(i) == ')') {
					sb.append("(");
				}
			}
			return sb.toString();
		}
	}

	private int splitStr(String str, int start, int end) {
		int lCnt = 0;
		int rCnt = 0;
		for (int i = start; i < end; i++) {
			if (str.charAt(i) == '(') {
				lCnt++;
			} else if (str.charAt(i) == ')') {
				rCnt++;
			}
			if (lCnt == rCnt)
				return i + 1;
		}
		return end;
	}

	private boolean validateBracket(String str, int start, int mid) {
		int cnt = 0;
		for (int i = start; i < mid; i++) {
			if (str.charAt(i) == '(') {
				cnt++;
			} else if (str.charAt(i) == ')') {
				cnt--;
			}
			if (cnt < 0)
				return false;
		}
		return true;
	}
}
