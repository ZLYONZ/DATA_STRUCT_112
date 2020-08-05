package BST;

public class BST {

	public static void main(String[] args) {

		int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };

		BinarySortTree BST = new BinarySortTree();
		// 循环添加节点到二叉排序树
		for (int i = 0; i < arr.length; i++) {
			BST.add(new BSTNode(arr[i]));
		}

		// 中序遍历二叉排序树
		System.out.println("中序遍历二叉排序树");
		BST.infixOrder();

		BST.delete(7);
		BST.delete(3);
		BST.delete(10);
		BST.delete(12);
		BST.delete(5);
		BST.delete(1);
		BST.delete(9);
		BST.delete(2);
		System.out.println("删除节点后");
		BST.infixOrder();
	}
}

//创建二叉排序树
class BinarySortTree {

	private BSTNode root;

	// 添加节点
	public void add(BSTNode node) {
		if (root == null) {
			root = node; // 如果root为空，则直接让root指向node
		} else {
			root.add(node);
		}
	}

	// 查找要删除的节点
	public BSTNode search(int value) {
		if (root == null) {
			return null;
		} else {
			return root.search(value);
		}
	}

	// 查找父节点
	public BSTNode searchParent(int value) {
		if (root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	/**
	 * 
	 * @param node 传入的节点（当作二叉排序树的根节点）
	 * @return 返回的以 node 为根节点的二叉排序树的最小节点值
	 */
	public int delRightTreeMin(BSTNode node) {
		BSTNode target = node;
		// 循环的查找左子节点，就会找到最小值
		while (target.left != null) {
			target = target.left;
		}
		// then，target指向了最小节点
		// 删除最小节点
		delete(target.value);
		return target.value;
	}

	public int delLeftTreeMax(BSTNode node) {
		BSTNode target = node;
		// 循环的查找右子节点，就会找到最大值
		while (target.right != null) {
			target = target.right;
		}
		// then，target指向了最大节点
		// 删除最大节点
		delete(target.value);
		return target.value;
	}

	// 删除节点
	public void delete(int value) {
		if (root == null) {
			return;
		} else {
			// 1. 需要先找到要删除的节点 target
			BSTNode target = search(value);
			// 如果没有找到要删除的节点
			if (target == null) {
				return;
			}
			// 如果发现 target 没有父节点，即 target 为 root，二叉排序树只有一个节点
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}

			// 找到target的父节点
			BSTNode parent = searchParent(value);

			// 如果要删除的节点是叶子节点
			if (target.left == null && target.right == null) {
				// 判断要删除节点是父节点的左节点或右节点
				if (parent.left != null && parent.left.value == value) {
					parent.left = null;
				} else if (parent.right != null && parent.right.value == value) {
					parent.right = null;
				}
			}
			// target有左右子树
			else if (target.left != null && target.right != null) {
//				int minVal = delRightTreeMin(target.right);
//				target.value = minVal;

				int maxVal = delLeftTreeMax(target.left);
				target.value = maxVal;
			}
			// 删除只有一颗子树的节点
			else {
				// 如果是左子节点
				if (target.left != null) {
					// 判断target是否为root节点
					if (parent != null) {
						// 如果target是parent的左子节点
						if (parent.left.value == value) {
							parent.left = target.left;
						}
						// target是parent的右子节点
						else {
							parent.right = target.left;
						}
					} else {
						root = target.left;
					}

				}
				// 如果是右子节点
				else {
					// 判断target是否为root节点
					if (parent != null) {
						// 如果target是parent的左子节点
						if (parent.left.value == value) {
							parent.left = target.right;
						}
						// target是parent的右子节点
						else {
							parent.right = target.right;
						}
					} else {
						root = target.right;
					}

				}
			}
		}
	}

	// 中序遍历
	public void infixOrder() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("二叉排序树为空，不能遍历");
		}
	}
}
