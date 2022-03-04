package com.algo.bj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_5639_이진검색트리 {
	/**
	 * 왼쪽 서브트리에 있는 모든노드 키는 노드의키보다 작다.
	 * 오른쪽 서브트리에 있는 모든노드 키는 노드의키보다 크다.
	 * 왼쪽, 오른쪽 서브트리도 이진검색트리다. 
	 * 
	 * 전위순회 한 결과가 주어졌을 때,
	 * --> 후위순회 결과 출력하라.
	 * */
	
	static class Node{
		int num;
		Node left;
		Node right;
		
		Node(int num){
			this.num = num;
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node root = new Node(Integer.parseInt(br.readLine()));
		
		// 전위순회 결과를 순서대로 입력받음.
		while(true) {
			String input = br.readLine();
			if(input == null || input.equals("")) break;
			
			Node nextNode = new Node(Integer.parseInt(input));
			putNode(root, nextNode);
		}
		
		postOrder(root); // 후위순회
		System.out.println(sb.toString());
	}

	
	// 후위순회 : L -> R -> V 
	private static void postOrder(Node node) {
		if(node.left != null) postOrder(node.left); // 1.L
		if(node.right != null) postOrder(node.right); // 2.R
		sb.append(node.num+"\n"); // 3.V
		
	}


	private static void putNode(Node startNode, Node inputNode) {

		// inputNode값이 루트노드보다 작으면 왼쪽에 배치. 
		if(inputNode.num < startNode.num) {
			if(startNode.left != null) {
				putNode(startNode.left, inputNode);
			}else {
				startNode.left = inputNode;
			}
		}else {
			// inputNode값이 루트노드보다 크면 오른쪽에 배치 
			if(startNode.right != null) {
				putNode(startNode.right, inputNode);
			}else {
				startNode.right = inputNode;
			}
		}
	}
}
