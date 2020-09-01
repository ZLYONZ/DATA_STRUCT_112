package stack;

import java.util.Iterator;

public class Stack1<T> implements Iterable<T> {

	public static void main(String[] args) {

		// 创建栈
		Stack1<String> stack = new Stack1<String>();

		// push
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.push("d");
		stack.push("e");

		// travere
		for (String item : stack) {
			System.out.println(item);
		}
		System.out.println("------------------------------");

		// pop
		String result = stack.pop();
		System.out.println("弹出的元素是：" + result);
		System.out.println("剩余的元素个数为" + stack.size());
	}

	// 记录头节点
	private Node head;
	// 记录栈中元素个数
	private int num;

	// 构造器
	private class Node {

		public T item;
		public Node next;

		public Node(T item, Stack1<T>.Node next) {
			this.item = item;
			this.next = next;
		}
	}

	public Stack1() {
		this.head = new Node(null, null);
	}

	// 判断栈中元素是否为0
	public boolean isEmpty() {
		return num == 0;
	}

	// 获取栈中元素的个数
	public int size() {
		return num;
	}

	// 把item入栈(压栈)
	public void push(T item) {

		// 找到首节点指向的第一个节点
		Node oldFirst = head.next;

		// 创建新节点
		Node newNode = new Node(item, null);

		// 让首节点指向新节点
		head.next = newNode;

		// 让新节点指向原来第一个节点
		newNode.next = oldFirst;

		// 元素个数+1
		num++;
	}

	// 出栈(弹栈)
	public T pop() {

		// 找到首节点指向的第一个节点
		Node oldFirst = head.next;

		// 链表不为空
		if (oldFirst != null) {

			// 让首节点指向原来第一个节点的下一个节点
			head.next = oldFirst.next;
		}

		// 元素个数-1
		num--;

		return oldFirst.item;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new SIterator();
	}

	private class SIterator implements Iterator<T> {

		private Node node;

		public SIterator() {
			this.node = head;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return node.next != null;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			node = node.next;
			return node.item;
		}
	}
}
