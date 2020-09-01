package linkedlist;

//Solution 2

public class JosephuTest {

	public static void main(String[] args) {

		Node<Integer> first = null;
		Node<Integer> pre = null;

		for (int i = 1; i <= 41; i++) {

			if (i == 1) {
				first = new Node<>(i, null);
				pre = first;
				continue;
			}

			Node<Integer> newNode = new Node<>(i, null);
			pre.next = newNode;
			pre = newNode;

			if (i == 41) {
				pre.next = first;
			}
		}

		int count = 0;
		Node<Integer> curr = first;
		Node<Integer> preNode = null;

		while (curr != curr.next) {

			count++;
			if (count == 3) {
				preNode.next = curr.next;
				System.out.print(curr.item + ", ");
				count = 0;
				curr = curr.next;
			} else {
				preNode = curr;
				curr = curr.next;
			}
		}
		System.out.println(curr.item);
	}

	private static class Node<T> {

		T item;
		Node<T> next;

		public Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
}