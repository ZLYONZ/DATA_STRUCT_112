package huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {

		int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
		System.out.println(Arrays.toString(arr));
		Node root = huffmanTree(arr);

		preOrder(root);
		System.out.println("前序遍历后：" + Arrays.toString(arr));
	}

	// 编写一个前序遍历的方法
	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("树为空树！");
		}
	}

	/**
	 * 
	 * @param arr 需要创建成huffmanTree的数组
	 * @return 创建好后的huffmanTree的root节点
	 */
	// 创建huffmanTree的方法
	public static Node huffmanTree(int[] arr) {
		// 第一步
		// 1. 遍历arr数组
		// 2. 将arr的每个元素构造成一个Node
		// 3. 将Node放到ArrayList中
		List<Node> nodes = new ArrayList<Node>();
		for (int value : arr) {
			nodes.add(new Node(value));
		}

		// 处理的过程是循环过程
		while (nodes.size() > 1) {

			// 排序 从小到大
			Collections.sort(nodes);
			System.out.println("nodes= " + nodes);

			// 取出根节点权值最小的两颗二叉树
			// （1）取出权值最小的二叉树节点
			Node leftNode = nodes.get(0);
			// （2）取出权值次小的二叉树节点
			Node rightNode = nodes.get(1);
			// （3）构建新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			// （4）从ArrayList删除处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// （5）将新的parent加入到nodes
			nodes.add(parent);
		}
		// 返回huffmanTree的root节点
		return nodes.get(0);
	}
}

// 创造一个节点的类
// 为了让 Node 对象持续排序  Collections集合排序
// 让 Node 实现comparable接口
class Node implements Comparable<Node> {
	int value; // 节点权值
	Node left; // 指向左子节点
	Node right; // 指向右子节点

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		// 表示从小到大排序
		return this.value - o.value;
	}

	// 前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}
}