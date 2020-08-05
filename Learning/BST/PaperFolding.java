package BST;

import queue.Queue;

public class PaperFolding {
	
	public static void main(String[] args) {

		Node<String> tree = create(3);
		
		print(tree);
	}
	
	// 通过模拟对折N次纸，产生树
	public static Node<String> create(int N) {
		
		// 定义根节点
		Node<String> root = null;
		
		// 遍历
		for (int i = 0; i < N; i++) {
			
			// 1.当前树为空，第一次对折
			if (i == 0) {
				root = new Node<>("down", null, null);
				continue;
			}
			// 2.当前不是第一次对折
			// 定义辅助遍历，通过层序遍历，找到叶子节点并添加子节点
			@SuppressWarnings("rawtypes")
			Queue<Node> queue = new Queue<>();
			queue.enqueue(root);
			
			// 循环遍历队列
			while (!queue.isEmpty()) {
				
				// 从队列中弹出一个节点
				@SuppressWarnings("unchecked")
				Node<String> temp = queue.dequeue();
				// 如有左子节点，则把左子节点放入到队列中
				if (temp.left != null) {
					queue.enqueue(temp.left);
				}
				// 如有右子节点，则把右子节点放入到队列中
				if (temp.right != null) {
					queue.enqueue(temp.right);
				}
				// 如左右子节点都无，则证明该节点为叶子节点，需添加左右子节点
				if (temp.left == null && temp.right == null) {
					temp.left = new Node<String>("down", null, null);
					temp.right = new Node<String>("up", null, null);
				}
			}
		}
		return root;
	}
	
	// 使用中序遍历，打印树中节点
	public static void print(Node<String> root) {
		
		// 中序遍历
		if (root == null) {
			return;
		}
		
		// 打印左子节点
		if (root.left != null) {
			print(root.left);
		}
		
		// 打印当前节点
		System.out.print(root.item + " ");
		
		// 打印右子节点
		if (root.right != null) {
			print(root.right);
		}
	}
	
	// 节点类
	private static class Node<T> {
		
		public T item;
		public Node<String> left;
		public Node<String> right;
		
		public Node(T item, Node<String> left, Node<String> right) {
			this.item = item;
			this.left = left;
			this.right = right;
		}
		
		
	}
}
