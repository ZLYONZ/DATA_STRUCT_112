package tree;

public class ArrayBinaryTree {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };

		// 创建对象
		ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
		
		arrBinaryTree.preOrder();

		System.out.println();
		
		arrBinaryTree.infixOrder();
		
		System.out.println();

		arrBinaryTree.postOrder();
	}

}

// ArrayBinaryTree，顺序存储二叉树遍历
class ArrBinaryTree {

	private int[] arr; // 存储数据节点的数组

	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}

	// 重载preOrder
	public void preOrder() {
		this.preOrder(0);
	}

	// 重载infixOrder
	public void infixOrder() {
		this.infixOrder(0);
	}

	// 重载postOrder
	public void postOrder() {
		this.postOrder(0);
	}

	/**
	 * 
	 * @param index 数组的下标
	 */
	public void preOrder(int index) {

		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，不能前序遍历");
		}

		// 输出当前元素
		System.out.println(arr[index]);

		// 向左递归
		if ((index * 2 + 1) < arr.length) {
			preOrder(2 * index + 1);
		}

		// 向右递归
		if ((index * 2 + 2) < arr.length) {
			preOrder(2 * index + 2);
		}
	}

	public void infixOrder(int index) {

		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，不能前序遍历");
		}

		// 向左递归
		if ((index * 2 + 1) < arr.length) {
			infixOrder(2 * index + 1);
		}

		// 输出当前元素
		System.out.println(arr[index]);

		// 向右递归
		if ((index * 2 + 2) < arr.length) {
			infixOrder(2 * index + 2);
		}
	}

	public void postOrder(int index) {

		if (arr == null || arr.length == 0) {
			System.out.println("数组为空，不能前序遍历");
		}

		// 向左递归
		if ((index * 2 + 1) < arr.length) {
			postOrder(2 * index + 1);
		}

		// 向右递归
		if ((index * 2 + 2) < arr.length) {
			postOrder(2 * index + 2);
		}
		// 输出当前元素
		System.out.println(arr[index]);
	}
}
