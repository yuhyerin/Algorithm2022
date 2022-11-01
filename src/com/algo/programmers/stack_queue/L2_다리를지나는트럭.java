package com.algo.programmers.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

public class L2_다리를지나는트럭 {

	public static void main(String[] args) {
		L2_다리를지나는트럭 t = new L2_다리를지나는트럭();
		// example 1
//		int bridge_length = 2;
//		int weight = 10;
//		int[] truck_weights = { 7, 4, 5, 6 };
		
		// example 2
//		int bridge_length = 100;
//		int weight = 100;
//		int[] truck_weights = {10 };
		
		// example 3
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};
		
		int result = t.solution(bridge_length, weight, truck_weights);
		System.out.println(result);
	}

	// return 모든 트럭이 건너려면 몇초
	// 다리에 최대 bridge_length대의 트럭이 올라갈 수 있다.
	// 최대 weight 무게를 견딜 수 있다.

	// bridge_length : 1~10,000
	// weight : 1~10,000
	// truck_weight 길이 : 1~10,000
	// 모든 트럭의 무게는 1~weight 이하
	public int solution(int bridge_length, int weight, int[] truck_weights) {

		// waiting 초기화
		Queue<Truck> waiting = new LinkedList();
		for (int truck_weigh : truck_weights) {
			Truck truck = new Truck(truck_weigh);
			waiting.add(truck);
		}

		Queue<Truck> crossing = new LinkedList();
		Queue<Truck> finished = new LinkedList();

		int time = 1;
		while (true) {
			time++;
			
			// waiting -> crossing (check weight)
			if (checkWeight(weight, waiting, crossing)) {
				crossing.add(waiting.poll());
			}

			// 한칸씩 이동
			moveTrucks(crossing);
			
			// crossing -> finished
			if (!crossing.isEmpty() && crossing.peek().pos >= bridge_length) {
				finished.add(crossing.poll());
			}

			// 다 건너감.
			if (finished.size() == truck_weights.length) {
				break;
			}

		}

		return time;
	}

	private void moveTrucks(Queue<Truck> crossing) {
		if (!crossing.isEmpty()) {
			crossing.forEach(truck -> {
				truck.move();
			});
		}
	}

	public boolean checkWeight(int max_weight, Queue<Truck> waiting, Queue<Truck> crossing) {
		// 대기중인 트럭이 없음.
		if (waiting.isEmpty())
			return false;

		Truck wait_truck = waiting.peek();
		int totalWeight = totalWeight(crossing);
		if (totalWeight + wait_truck.weight <= max_weight) {
			return true;
		}
		return false;
	}

	class Truck {
		private int pos; // 위치
		private int weight; // 무게

		public Truck(int weight) {
			this.weight = weight;
		}

		public void move() {
			this.pos += 1;
		}

		public String toString() {
			return "Truck 무게:" + weight + ", 위치:" + pos;
		}
	}

	int totalWeight = 0;
	public int totalWeight(Queue<Truck> que) {
		totalWeight = 0;
		que.forEach(truck -> {
			totalWeight += truck.weight;
		});
		return totalWeight;
	}
}
