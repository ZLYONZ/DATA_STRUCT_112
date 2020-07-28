package tree;

public class RedBlackTree<Key extends Comparable<Key>, Value> {

	public static void main(String[] args) {

		// 创建红黑树
		RedBlackTree<String, String> RBT = new RedBlackTree<>();

		// 插入元素
		RBT.insert("1", "A");
		RBT.insert("2", "B");
		RBT.insert("3", "C");
		RBT.insert("4", "D");
		RBT.insert("5", "E");

		// 获取元素
		String s1 = RBT.get("1");
		System.out.println(s1);
		String s2 = RBT.get("2");
		System.out.println(s2);
		String s3 = RBT.get("3");
		System.out.println(s3);
	}

	// 根节点
	private Node root;

	// 记录树中元素的个数
	private int num;

	// 红色链接标识
	private static final boolean RED = true;

	// 黑色链接标识
	private static final boolean BLACK = false;

	// 节点类
	private class Node {

		// 储存键
		public Key key;
		// 储存值
		public Value value;
		// 记录左子节点
		public Node left;
		// 记录右子节点
		public Node right;
		// 由其父节点指向它的链接的颜色
		public boolean color;

		public Node(Key key, Value value, Node left, Node right, boolean color) {
			this.key = key;
			this.value = value;
			this.left = left;
			this.right = right;
			this.color = color;
		}
	}

	// 获取树中元素的个数
	public int size() {
		return num;
	}

	// 判断当前节点的父指向链接是否为红色
	private boolean isRed(Node x) {
		if (x == null) {
			return false;
		}
		return x.color == RED;
	}

	// 左旋调整
	private Node rotateLeft(Node h) {

		// 获取h节点的右子节点，表示为x
		Node x = h.right;

		// 让x节点的左子节点成为h节点的右子节点
		h.right = x.left;

		// 让h节点成为x节点的左子节点
		x.left = h;

		// 让x节点的color属性等于h节点的color
		x.color = h.color;

		// 让h节点的color等于RED
		h.color = RED;

		return x;
	}

	// 右旋调整
	private Node rotateRight(Node h) {

		// 获取h节点的右子节点，表示为x
		Node x = h.left;

		// 让x节点的右子节点成为h节点的左子节点
		h.left = x.right;

		// 让h节点成为x节点的右子节点
		x.right = h;

		// 让x节点的color属性等于h节点的color
		x.color = h.color;

		// 让h节点的color等于RED
		h.color = RED;

		return x;
	}

	// 颜色反转
	private void flipColor(Node h) {

		// 把当前节点变为red
		h.color = RED;
		// 把左右子节点变成black
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	// 在整个树中完成插入
	public void insert(Key key, Value val) {

		root = insert(root, key, val);
		// 根节点的颜色总是black
		root.color = BLACK;
	}

	// 在指定树中完成插入操作，并返回添加元素后的新树
	private Node insert(Node h, Key key, Value val) {

		// 判断h是否为空
		if (h == null) {
			// 数量+1
			num++;
			return new Node(key, val, null, null, RED);
		}

		// 比较h节点的键和key的大小
		int cmp = key.compareTo(h.key);

		if (cmp < 0) {
			// 继续往左
			h.left = insert(h.left, key, val);

		} else if (cmp > 0) {
			// 继续往右
			h.right = insert(h.right, key, val);

		} else {
			// 发生值的替换
			h.value = val;
		}

		// 当当前节点h的左子节点为黑色，右子节点为红色，需要左旋
		if (!isRed(h.left) && isRed(h.right)) {
			h = rotateLeft(h);
		}

		// 当当前节点h的左子节点，和左子节点的左子节点都为红色，需要右旋
		if (isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}

		// 当前节点的左右子节点都为red时，需要颜色反转
		if (isRed(h.left) && isRed(h.right)) {
			flipColor(h);
		}
		return h;
	}

	// 根据key，获取对应的值
	public Value get(Key key) {
		return get(root, key);
	}

	// 从指定的树中，查找key对应的值
	public Value get(Node x, Key key) {

		// 判断是否为空
		if (x == null) {
			return null;
		}

		// 比较h节点的键和key的大小
		int cmp = key.compareTo(x.key);

		if (cmp < 0) {
			return get(x.left, key);

		} else if (cmp > 0) {
			return get(x.right, key);

		} else {
			return x.value;
		}
	}
}
