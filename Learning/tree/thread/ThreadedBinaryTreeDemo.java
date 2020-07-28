package tree.thread;

import tree.HeroNode;

public class ThreadedBinaryTreeDemo { // 线索二叉树

	public static void main(String[] args) {

		HeroNode root = new HeroNode(1, "tom");
		HeroNode node2 = new HeroNode(3, "jack");
		HeroNode node3 = new HeroNode(6, "smith");
		HeroNode node4 = new HeroNode(8, "mary");
		HeroNode node5 = new HeroNode(10, "king");
		HeroNode node6 = new HeroNode(14, "lyon");

		// 二叉树，后面我们要递归创建，现在简单处理
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);

		// 测试线索化
		threadedBinaryTree TBT = new threadedBinaryTree();
		TBT.setRoot(root);

		// 中序
//		TBT.infixThreadedOrder();
//		System.out.println("使用线索化的方式中序遍历二叉树");
//		TBT.infixThreadedList();
		
		HeroNode leftNode = node5.getLeft();
		System.out.println("10号节点的前驱节点=" + leftNode);
		HeroNode rightNode = node5.getRight();
		System.out.println("10号节点的后继节点=" + rightNode);

		// 前序
		TBT.preThreadedOrder();
		System.out.println("使用线索化的方式前序遍历二叉树");
		TBT.preThreadedList();
	}
}

//定义ThreadedBinaryTree，实现线索化功能的二叉树
class threadedBinaryTree {

	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	
	// 为了实现线索化，需创建一个指针，指向当前节点的前驱节点
	// 在递归进行线索化时，pre 总是保留前一个节点
	private HeroNode pre = null;

	
	// 二叉树的中序线索化

	// 重载infixThreadedOrder
	public void infixThreadedOrder() {
		this.infixThreadedOrder(root);
	}

	public void infixThreadedOrder(HeroNode node) {

		// 如果为空
		if (node == null) {
			return;
		}

		// （一） 线索化左子树
		infixThreadedOrder(node.getLeft());

		// （二） 线索化当前节点

		// 处理当前节点的前驱节点
		if (node.getLeft() == null) {
			// 让当前节点的左指针指向前驱节点
			node.setLeft(pre);
			// 修改当前节点的左指针的类型
			node.setLeftType(1);
		}

		// 处理当前节点的后继节点
		if (pre != null && pre.getRight() == null) {
			// 让前驱节点的右指针指向当前节点
			pre.setRight(node);
			// 修改前驱节点的右指针的类型
			pre.setRightType(1);
		}
		// !!! 每处理一个节点后，让当前节点成为下一个节点的前驱节点
		pre = node;

		// （三） 线索化右子树
		infixThreadedOrder(node.getRight());
	}

	// 遍历线索化二叉树的方法
	public void infixThreadedList() {

		// 定义一个变量，临时存储当前遍历的一个节点，从root开始
		HeroNode node = root;

		while (node != null) {
			// 循环的找到leftType == 1 的节点
			// 后面会随着遍历而变化，因为当leftType == 1 时，说明该节点是按照线索化处理后的有效节点
			while (node.getLeftType() == 0) {
				node = node.getLeft();
			}
			// 打印当前节点
			System.out.println(node);
			
			// 如果当前节点的右指针指向的是后继节点，就一直输出
			while (node.getRightType() == 1) {
				// 获取到当前节点的后继节点
				node = node.getRight();
				System.out.println(node);
			}
			// 替换这个遍历的节点
			node = node.getRight();
		}
	}

	// 二叉树的前序线索化

	// 重载preThreadedOrder
	public void preThreadedOrder() {
		this.preThreadedOrder(root);
	}

	public void preThreadedOrder(HeroNode node) {

		// 如果为空
		if (node == null) {
			return;
		}

		// （一） 线索化当前节点

		// 处理当前节点的前驱节点
		if (node.getLeft() == null) {
			// 让当前节点的左指针指向前驱节点
			node.setLeft(pre);
			// 修改当前节点的左指针的类型
			node.setLeftType(1);
		}

		// 处理当前节点的后继节点
		if (pre != null && pre.getRight() == null) {
			// 让前驱节点的右指针指向当前节点
			pre.setRight(node);
			// 修改前驱节点的右指针的类型
			pre.setRightType(1);
		}
		// !!! 每处理一个节点后，让当前节点成为下一个节点的前驱节点
		pre = node;

		// （二） 线索化左子树
		if (node.getLeftType() == 0) {
			preThreadedOrder(node.getLeft());
		}

		// （三） 线索化右子树
		if (node.getRightType() == 0) {
			preThreadedOrder(node.getRight());
		}
	}

	// 前序遍历
	public void preThreadedList() {

		// 定义一个变量，临时存储当前遍历的一个节点，从root开始
		HeroNode node = root;

		while (node != null) {
			// 循环的找到leftType == 1 的节点
			// 后面会随着遍历而变化，因为当leftType == 1 时，说明该节点是按照线索化处理后的有效节点
			while (node.getLeftType() == 0) {
				// 打印当前节点
				System.out.println(node);
				node = node.getLeft();
			}
			System.out.println(node);
			// 替换这个遍历的节点
			node = node.getRight();
		}
	}
}