package tree;

// Binary Sort Tree
public class Node {

	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	// 返回以该节点为root的树的高度
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}

	// 返回左子树的高度
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	// 返回右子树的高度
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	// 添加节点
	// 递归，满足二叉排序树的要求
	public void add(Node node) {
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

		// 当添加完一个节点后，如果（右子树的高度-左子树的高度）> 1，左旋转
		if (rightHeight() - leftHeight() > 1) {
			// 如果它的右子树的左子树高度大于右子树高度
			if (right != null && right.leftHeight() > right.rightHeight()) {
				// 先对当前节点的右子树进行右旋转
				right.rightRotate();
				// 再对当前节点进行左旋转
				leftRotate();
			} else {
				// 直接进行左旋转
				leftRotate();
			}
			return;
		}

		// 当添加完一个节点后，如果（左子树的高度-右子树的高度）> 1，右旋转
		if (leftHeight() - rightHeight() > 1) {
			// 如果它的左子树的右子树高度大于左子树高度
			if (left != null && left.rightHeight() > left.leftHeight()) {
				// 先对当前节点的左子树进行左旋转
				left.leftRotate();
				// 再对当前节点进行右旋转
				rightRotate();
			} else {
				// 直接进行右旋转
				rightRotate();
			}
			return;
		}
	}

	// 查找要删除的节点
	/**
	 * 
	 * @param value 希望删除的节点的值
	 * @return 如果找到，返回该节点；否则返回null
	 */
	public Node search(int value) {
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
	public Node searchParent(int value) {
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
	public void delete(Node parent, Node target) {

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

	// 后序遍历
	public void postOrder() {

		if (this.left != null) {
			this.left.postOrder();
		}

		if (this.right != null) {
			this.right.postOrder();
		}
		
		System.out.println(this);

	}

	// 左旋转的方法
	private void leftRotate() {

		// 以当前根节点的值，创建新的节点
		Node newNode = new Node(value);
		// 把新的节点的左子树，设置成当前节点的左子树
		newNode.left = left;
		// 把新的节点的右子树，设置成当前节点的右子树的左子树
		newNode.right = right.left;
		// 把当前节点的值，替换成右子节点的值
		value = right.value;
		// 把当前节点的右子节点，设置成右子节点的右子节点
		right = right.right;
		// 把当前节点的左子节点，设置成新的节点
		left = newNode;
	}

	// 右旋转的方法
	private void rightRotate() {

		// 以当前根节点的值，创建新的节点
		Node newNode = new Node(value);
		// 把新的节点的右子树，设置成当前节点的右子树
		newNode.right = right;
		// 把新的节点的左子树，设置成当前节点的左子树的右子树
		newNode.left = left.right;
		// 把当前节点的值，替换成左子节点的值
		value = left.value;
		// 把当前节点的左子节点，设置成左子节点的左子节点
		left = left.left;
		// 把当前节点的右子节点，设置成新的节点
		right = newNode;
	}
}
