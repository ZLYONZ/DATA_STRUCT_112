package BST;

import queue.Queue;

public class BT<Key extends Comparable<Key>, Value> {

	public static void main(String[] args) {

		// 创建二叉树
		BT<Integer, String> tree = new BT<>();

		// insert
		tree.add(1, "a");
		tree.add(2, "b");
		tree.add(3, "c");
		tree.add(4, "d");
		tree.add(5, "e");
		System.out.println("插入后，元素个数为" + tree.size());

		// get
		System.out.println("键2对应的元素为" + tree.get(2));

		// delete
		tree.delete(3);
		System.out.println("删除后，元素个数为" + tree.size());
		System.out.println("键2对应的元素为" + tree.get(3));
	}

	// 记录根节点
	private Node root;
	// 记录元素个数
	private int num;

	// 获取树中元素个数
	public int size() {
		return num;
	}

	// 添加元素 key-value
	public void add(Key k, Value val) {
		root = insert(root, k, val);
	}

	// 向指定的树x中添加，并返回新的树
	public Node insert(Node x, Key k, Value val) {

		// 如果x子树为空
		if (x == null) {
			num++;
			return new Node(k, val, null, null);
		}

		// 如果不为空
		// 比较新节点和当前节点的key
		int cmp = k.compareTo(x.key);

		if (cmp > 0) {
			x.right = insert(x.right, k, val);
		} else if (cmp < 0) {
			x.left = insert(x.left, k, val);
		} else {
			x.value = val;
		}
		return x;
	}

	// 查找树中指定key对应的value
	public Value get(Key k) {
		return get(root, k);
	}

	// 从指定的树x中，获取key对应的值
	public Value get(Node x, Key k) {

		// 判断x树是否为null
		if (x == null) {
			return null;
		}

		// 比较k和x.value
		int cmp = k.compareTo(x.key);

		if (cmp > 0) {
			return get(x.right, k);
		} else if (cmp < 0) {
			return get(x.left, k);
		} else {
			return x.value;
		}
	}

	// 删除树中key对应的Value
	public void delete(Key k) {
		delete(root, k);

	}

	// 从指定的树x中，删除key对应的值
	public Node delete(Node x, Key k) {

		// 判断x树是否为null
		if (x == null) {
			return null;
		}

		// 比较k和x.value
		int cmp = k.compareTo(x.key);

		if (cmp > 0) {
			x.right = delete(x.right, k);
		} else if (cmp < 0) {
			x.left = delete(x.left, k);
		} else {

			// 元素个数-1
			num--;

			// 判断是否为空
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}

			// 找到右子树中最小的节点
			Node minNode = x.right;
			while (minNode.left != null) {
				minNode = minNode.left;
			}

			// 删除右子树中最小节点
			Node n = x.right;
			while (n.left != null) {
				if (n.left.left == null) {
					n.left = null;
				} else {
					n = n.left;
				}
			}

			// minNode替换被删除节点
			minNode.left = x.left;
			minNode.right = x.right;
			x = minNode;
		}
		return x;
	}

	// 找出整个树中最小的键
	public Key minNode() {
		return minNode(root).key;
	}

	// 重载，找出指定树x中最小的键
	public Node minNode(Node x) {

		// 判断左子节点是否为空
		if (x.left != null) {
			return minNode(x.left);
		} else {
			return x;
		}
	}

	// 找出整个树中最大的键
	public Key maxNode() {
		return maxNode(root).key;
	}

	// 重载，找出指定树x中最大的键
	public Node maxNode(Node x) {

		// 判断右子节点是否为空
		if (x.right != null) {
			return maxNode(x.right);
		} else {
			return x;
		}
	}

	// 求出树的最大深度
	public int maxDepth() {
		return maxDepth(root);
	}

	// 重载
	private int maxDepth(Node x) {

		if (x == null) {
			return 0;
		}

		// x的最大深度
		int max = 0;
		// 左子树的最大深度
		int maxL = 0;
		// 右子树的最大深度
		int maxR = 0;

		// 求出左子树的最大深度
		if (x.left != null) {
			maxL = maxDepth(x.left);
		}
		// 求出右子树的最大深度
		if (x.right != null) {
			maxR = maxDepth(x.right);
		}

		// 比较并计算出最大深度
		max = maxL > maxR ? maxL + 1 : maxR + 1;
		
		return max;
	}

	// 前序遍历
	public Queue<Key> preErgodic() {
		Queue<Key> keys = new Queue<>();
		preErgodic(root, keys);
		return keys;
	}

	// 重载
	private void preErgodic(Node x, Queue<Key> keys) {

		if (x == null) {
			return;
		}

		// 把x节点的key放入到keys中
		keys.enqueue(x.key);

		// 递归遍历x.left
		if (x.left != null) {
			preErgodic(x.left, keys);
		}

		// 递归遍历x.right
		if (x.right != null) {
			preErgodic(x.right, keys);
		}
	}

	// 中序遍历
	public Queue<Key> infixErgodic() {
		Queue<Key> keys = new Queue<>();
		infixErgodic(root, keys);
		return keys;
	}

	// 重载
	private void infixErgodic(Node x, Queue<Key> keys) {

		if (x == null) {
			return;
		}

		// 递归遍历x.left
		if (x.left != null) {
			infixErgodic(x.left, keys);
		}

		// 把x节点的key放入到keys中
		keys.enqueue(x.key);

		// 递归遍历x.right
		if (x.right != null) {
			infixErgodic(x.right, keys);
		}
	}

	// 后序遍历
	public Queue<Key> postErgodic() {
		Queue<Key> keys = new Queue<>();
		postErgodic(root, keys);
		return keys;
	}

	// 重载
	private void postErgodic(Node x, Queue<Key> keys) {

		if (x == null) {
			return;
		}

		// 递归遍历x.left
		if (x.left != null) {
			postErgodic(x.left, keys);
		}

		// 递归遍历x.right
		if (x.right != null) {
			postErgodic(x.right, keys);
		}

		// 把x节点的key放入到keys中
		keys.enqueue(x.key);
	}

	// 层序遍历
	public Queue<Key> layerErgodic() {

		// 定义两个队列，分别储存树中的键和节点
		Queue<Key> keys = new Queue<>();
		Queue<Node> nodes = new Queue<>();

		// 默认，在队列中放入root
		nodes.enqueue(root);

		while (!nodes.isEmpty()) {

			// 从队列中弹出节点，把key放入到keys中
			Node n = nodes.dequeue();
			keys.enqueue(n.key);

			// 判断是否有无左节点，如有放入到nodes中
			if (n.left != null) {
				nodes.enqueue(n.left);
			}

			// 判断是否有无右节点，如有放入到nodes中
			if (n.right != null) {
				nodes.enqueue(n.right);
			}
		}
		return keys;
	}

	public class Node {

		// 储存键
		public Key key;
		// 储存值
		public Value value;
		// 记录左子节点
		public Node left;
		// 记录右子节点
		public Node right;

		Node(Key key, Value value, Node left, Node right) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
		}

	}

}
