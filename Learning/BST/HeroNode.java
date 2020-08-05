package BST;

//创建HeroNode
public class HeroNode {

	private int num;
	private String name;
	private HeroNode left; // null
	private HeroNode right; // null

	public HeroNode(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode [num=" + num + ", name=" + name + "]";
	}

	
	// 1. 如果leftType == 0 表示指向的是左子树，== 1 则表示指向前驱节点
	// 2. 如果rightType == 0 表示指向的是右子树，== 1 则表示指向后继节点
	private int leftType;
	private int rightType;

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}
	
	private HeroNode parent;
	
	public HeroNode getParent() {
		return parent;
	}

	public void setParent(HeroNode parent) {
		this.parent = parent;
	}

	
	
	
	// 前序遍历
	public void preOrder() {
		System.out.println(this); // 先输出父节点

		// 递归向左子树前序遍历
		if (this.left != null) {
			this.left.preOrder();
		}
		// 递归向右子树前序遍历
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// 中序遍历
	public void infixOrder() {
		// 递归向左子树中序遍历
		if (this.left != null) {
			this.left.infixOrder();
		}

		System.out.println(this); // 输出父节点

		// 递归向右子树中序遍历
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// 后序遍历
	public void postOrder() {
		// 递归向左子树后序遍历
		if (this.left != null) {
			this.left.postOrder();
		}

		// 递归向右子树后序遍历
		if (this.right != null) {
			this.right.postOrder();
		}

		System.out.println(this); // 输出父节点
	}

	// 前序遍历查找
	/**
	 * 
	 * @param num 查找num
	 * @return 找到就返回该node，否则返回null
	 */
	public HeroNode preOrderSearch(int num) {

		HeroNode resNode = null;

		// 比较当前节点
		if (this.num == num) {
			return this;
		}
		// 1. 判断node.left是否为空，如果不为空，则递归前序查找
		// 2. 如果左递归前序查找成功，则返回

		if (this.left != null) {
			resNode = this.left.preOrderSearch(num);
		}
		if (resNode != null) { // 找到左子树
			return resNode;
		}
		// 1. 判断node.right是否为空，如果不为空，则递归前序查找
		// 2. 如果右递归前序查找成功，则返回
		if (this.right != null) {
			resNode = this.right.preOrderSearch(num);
		}
		return resNode;
	}

	// 中序遍历查找
	public HeroNode infixOrderSearch(int num) {

		HeroNode resNode = null;

		// 判断node.left是否为空，如果不为空，则递归前序查找
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(num);
		}
		if (resNode != null) {
			return resNode;
		}

		// 和当前节点比较
		if (this.num == num) {
			return this;
		}

		// 判断node.right是否为空，如果不为空，则递归前序查找
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(num);
		}
		return resNode;
	}

	// 后序遍历查找
	public HeroNode postOrderSearch(int num) {

		HeroNode resNode = null;

		// 判断node.left是否为空，如果不为空，则递归前序查找
		if (this.left != null) {
			resNode = this.left.postOrderSearch(num);
		}
		if (resNode != null) {
			return resNode;
		}

		// 判断node.right是否为空，如果不为空，则递归前序查找
		if (this.right != null) {
			resNode = this.right.postOrderSearch(num);
		}
		if (resNode != null) {
			return resNode;
		}

		// 和当前节点比较
		if (this.num == num) {
			return this;
		}
		return resNode;
	}

	// 递归删除节点
	// 1. 如果删除节点是叶子节点，则删除该节点
	// 2. 如果删除节点不是叶子节点，则删除该子树
	public void delNode(int num) {

		// 因为二叉树是单向的，所以判断当前节点的子节点是否为待删除节点
		// 1. 如果当前节点的左子节点不为空，且就是删除节点，则将this.left = null；并且返回（结束递归）
		// 2. 如果当前节点的右子节点不为空，且就是删除节点，则将this.right = null；并且返回（结束递归）
		// 3. 若还未删除，则向左子树进行递归删除
		// 4. 若还未删除，则向右子树进行递归删除

		if (this.left != null && this.left.num == num) {
			this.left = null;
			return;
		}

		if (this.right != null && this.right.num == num) {
			this.right = null;
			return;
		}

		if (this.left != null) {
			this.left.delNode(num);
		}

		if (this.right != null) {
			this.right.delNode(num);
		}
	}
}
