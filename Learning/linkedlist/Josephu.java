package linkedlist;

import java.util.NoSuchElementException;

public class Josephu {

	public static void main(String[] args) {

		CircularSingleLinkedList CSLL = new CircularSingleLinkedList();

		// add
		CSLL.add(50);
		// show
		CSLL.show();
		// get out
		CSLL.count(1, 5, 50);
	}
}

// 创建一个环形的单项链表
class CircularSingleLinkedList { // CircularSingleLinkedList

	// 创建一个first node
	private Boy first = null;

	// 添加小孩节点，构造成环形链表
	public void add(int num) {

		// num 做一个数据校验
		if (num < 1) {
			System.out.println("数据不正确");
			return;
		}

		// 辅助指针，帮助构建环形链表
		Boy curr = null;
		// 使用for来创建环形链表
		for (int i = 1; i <= num; i++) {
			// 根据编号，创建小孩节点
			Boy boy = new Boy(i);
			// 如果是第一个小孩
			if (i == 1) {
				first = boy;
				first.setNext(first); // 构成环
				curr = first;
			} else {
				curr.setNext(boy);
				boy.setNext(first);
				curr = boy;
			}
		}
	}

	// 遍历当前环形链表
	public void show() {
		
		if (first == null) {
			throw new NoSuchElementException();
		}
		
		// 因为first不能动，因此需要辅助指针
		Boy curr = first;
		while (true) {
			System.out.printf("小孩的编号%d \n", curr.getNum());
			if (curr.getNext() == first) { // 说明遍历完毕
				break;
			}
			curr = curr.getNext();
		}
	}

	// 根据用户的输入，计算出小孩出圈的顺序
	/**
	 * 
	 * @param statNum  表示从第几个小孩开始数数
	 * @param countNum 表示数几下
	 * @param num      表示最初有多少小孩在圈中
	 */
	public void count(int startNum, int countNum, int num) {
		
		if (first == null || startNum < 0 || startNum > num) {
			throw new NoSuchElementException();
		}
		Boy helper = first;
		while (true) {
			if (helper.getNext() == first) { // 说明helper指向指向最后节点
				break;
			}
			helper = helper.getNext();
		}
		// 小孩报数前，先让helper和first移动到k-1的位置
		for (int i = 0; i < startNum - 1; i++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		
		// 小孩报数时，先让helper和first移动到m-1的位置，然后出圈
		// whil循环，直到圈中只有一个节点
		while (true) {
			if (helper == first) { // 说明圈中只有一个node
				break;
			}
			
			// 让helper和first移动countNum-1
			for (int j = 0; j < countNum - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			
			// 这时first指向要出圈的小孩
			System.out.printf("小孩%d出圈\n", first.getNum());
			first = first.getNext();
			helper.setNext(first); // helper.next = first;
		}
		System.out.printf("最后留在圈中的小孩编号%d \n", first.getNum());
	}
}

// 创建一个Boy类，表示一个节点
class Boy {
	
	private int num;
	private Boy next; // 指向next node，默认为null

	public Boy(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}
}