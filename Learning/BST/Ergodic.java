package BST;

import queue.Queue;

public class Ergodic {

	public static void main(String[] args) {

		// 创建树
		BT<String, String> tree = new BT<>();

		// 添加数据
		tree.add("E", "5");
		tree.add("B", "2");
		tree.add("G", "7");
		tree.add("A", "1");
		tree.add("D", "4");
		tree.add("F", "6");
		tree.add("H", "8");
		tree.add("C", "3");

		// maxDepth
		int maxDepth = tree.maxDepth();
		System.out.println("最大深度为" + maxDepth);
		
		System.out.println("---------------------------------------------");
		
		// 前序遍历
		System.out.println("前序遍历");
		Queue<String> queue1 = tree.preErgodic();
		for (String key : queue1) {
			System.out.print(key + "=" + tree.get(key) + " ");
		}

		System.out.println();

		// 中序遍历
		System.out.println("中序遍历");
		Queue<String> queue2 = tree.infixErgodic();
		for (String key : queue2) {
			System.out.print(key + "=" + tree.get(key) + " ");
		}

		System.out.println();

		// 后序遍历
		System.out.println("后序遍历");
		Queue<String> queue3 = tree.postErgodic();
		for (String key : queue3) {
			System.out.print(key + "=" + tree.get(key) + " ");
		}

		System.out.println();

		// 层序遍历
		System.out.println("层序遍历");
		Queue<String> queue43 = tree.layerErgodic();
		for (String key : queue43) {
			System.out.print(key + "=" + tree.get(key) + " ");
		}
		
	}
}