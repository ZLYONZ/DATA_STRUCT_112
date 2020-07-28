package tree.BST;

// Binary Sort Tree
public class BSTNode {

	int value;
	BSTNode left;
	BSTNode right;

	public BSTNode(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BSTNode [value=" + value + "]";
	}

	// 添加节点
	// 递归，满足二叉排序树的要求
	public void add(BSTNode node) {
		if (node == null) {
			return;
		}
		// 判断传入的节点的值，和当前子树的根节点的值的关系
		if (node.value < this.value) {
			// 如果当前节点的左子节点为null
			if (this.left == null) {
				this.left = node;
			} else {
				// 递归的向左子树添加
				this.left.add(node);
			}
		} else { // 添加节点的值大于当前节点的值
			if (this.right == null) {
				this.right = node;
			} else {
				// 递归的向左子树添加
				this.right.add(node);
			}
		}
	}

	// 查找要删除的节点
	/**
	 * 
	 * @param value 希望删除的节点的值
	 * @return 如果找到，返回该节点；否则返回null
	 */
	public BSTNode search(int value) {
		if (value == this.value) { // 找到就是该节点
			return this;
		} else if (value < this.value) { // 如果查找的值小于当前节点，向左子树递归查找
			// 判断左子节点是否为空
			if (this.left == null) {
				return null;
			}
			return this.left.search(value);
		} else { // 如果查找的值大于当前节点，向右子树递归查找
			// 判断右子节点是否为空
			if (this.right == null) {
				return null;
			}
			return this.right.search(value);
		}
	}

	// 查找要删除节点的父节点
	/**
	 * 
	 * @param value 要找到的节点的值
	 * @return 返回的是要删除的节点的父节点；否则返回null
	 */
	public BSTNode searchParent(int value) {
		// 如果当前节点就是要删除的节点的父节点，则返回
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			// 如果查找的值小于当前节点，并且当前节点的子节点不为空
			if (value < this.value && this.left != null) {
				return this.left.searchParent(value); // 向左子树递归查找
			} else if (value >= this.value && this.right != null) {
				return this.right.searchParent(value); // 向右子树递归查找
			} else {
				return null; // 没有找到父节点
			}
		}
	}

	// 删除节点
	// 1. 删除叶子节点
	// 2. 删除只有一个子树的节点
	// 3. 删除有两个子树的节点
	public void delete(BSTNode parent, BSTNode target) {

	}

	// 中序遍历
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}

		System.out.println(this);

		if (this.right != null) {
			this.right.infixOrder();
		}
	}
}
