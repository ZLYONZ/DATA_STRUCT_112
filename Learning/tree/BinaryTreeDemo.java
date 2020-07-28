package tree;

public class BinaryTreeDemo {

	public static void main(String[] args) {

		// 创建二叉树
		BinaryTree binaryTree = new BinaryTree();
		// 创建节点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(2, "吴用");
		HeroNode node3 = new HeroNode(3, "卢俊义");
		HeroNode node4 = new HeroNode(4, "林冲");
		HeroNode node5 = new HeroNode(5, "武松");

		// 先手动创建该二叉树，后面再学习递归的方式创建
		root.setLeft(node2);
		root.setRight(node3);
		node3.setLeft(node5);
		node3.setRight(node4);
		binaryTree.setRoot(root);

		// 前序遍历
		System.out.println("preOrder：");
		binaryTree.preOrder();

		// 中序遍历
		System.out.println("infixOrder：");
		binaryTree.infixOrder();

		// 后序遍历
		System.out.println("postOrder：");
		binaryTree.postOrder();
		
		System.out.println();

		// 前序遍历查找
		System.out.println("preOrderSearch：");
		HeroNode resNode1 = binaryTree.preOrderSearch(5);
		if (resNode1 != null) {
			System.out.printf("找到了信息为no=%d name=%s \n", resNode1.getNum(), resNode1.getName());
		} else {
			System.out.printf("没有找到信息为no=%d name=%s \n", 5);
		}

		// 中序遍历查找
		System.out.println("infixOrderSearch：");
		HeroNode resNode2 = binaryTree.infixOrderSearch(3);
		if (resNode2 != null) {
			System.out.printf("找到了信息为no=%d name=%s \n", resNode2.getNum(), resNode2.getName());
		} else {
			System.out.printf("没有找到信息为no=%d name=%s \n", 3);
		}

		// 后序遍历查找
		System.out.println("postOrderSearch：");
		HeroNode resNode3 = binaryTree.postOrderSearch(4);
		if (resNode3 != null) {
			System.out.printf("找到了信息为no=%d name=%s \n", resNode3.getNum(), resNode3.getName());
		} else {
			System.out.printf("没有找到信息为no=%d name=%s \n", 4);
		}
		
		System.out.println();

		// 删除节点
		System.out.println("删除前,前序遍历为");
		binaryTree.preOrder();
		binaryTree.delNode(5);
		System.out.println("删除后，前序遍历为");
		binaryTree.preOrder();
		
		System.out.println();

		
		System.out.println("删除前,中序遍历为");
		binaryTree.infixOrder();
		binaryTree.delNode(3);
		System.out.println("删除后，中序遍历为");
		binaryTree.infixOrder();
	}
}

// 定义BinaryTree
class BinaryTree {
	
	private HeroNode root;
	
	public void setRoot(HeroNode root) {
		this.root = root;
	}

	// 前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 后序遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	// 前序遍历查找
	public HeroNode preOrderSearch(int num) {
		if (root != null) {
			return root.preOrderSearch(num);
		} else {
			return null;
		}
	}

	// 中序遍历查找
	public HeroNode infixOrderSearch(int num) {
		if (root != null) {
			return root.infixOrderSearch(num);
		} else {
			return null;
		}
	}

	// 后序遍历查找
	public HeroNode postOrderSearch(int num) {
		if (root != null) {
			return root.postOrderSearch(num);
		} else {
			return null;
		}
	}
	
	// 递归删除节点
	public void delNode(int num) {
		if (root != null) {
			// 如果只有一个root node，则判断是否为待删除节点
			if (root.getNum() == num) {
				root = null;
			} else {
				// 递归删除
				root.delNode(num);
			}
		} else {
			System.out.println("空树，不能删除");
		}
	}
}