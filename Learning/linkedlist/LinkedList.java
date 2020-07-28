package linkedlist;

import java.util.NoSuchElementException;

public class LinkedList {

	public static void main(String[] args) {

		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

		// create linked list
		SinglyLinkedList LL = new SinglyLinkedList();

		// add
		LL.addByOrder(hero1);
		LL.addByOrder(hero4);
		LL.addByOrder(hero3);
		LL.addByOrder(hero2);

		// 显示
		LL.list();

		// update
		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~");
		LL.update(newHeroNode);

		System.out.println("修改后的链表情况~");
		LL.list();

		// delete
		LL.delete(1);

		System.out.println("删除后的链表情况~");
		LL.list();

		// length of node
		System.out.println("有效的节点个数=" + getLength(LL.getHead()));

	}

	// 查找单链表中的 k-th节点
	// 1.接收head node，同时接收index
	// 2.index表示倒数第index个节点
	// 3.先把链表从头到尾遍历，得到链表总长度（getLength）
	// 4.得到size后，从链表第一个开始遍历（size - index）个
	// 5.如果找到返回该节点，否则返回null
	public static HeroNode findLastIndex(HeroNode head, int index) {
		if (head.next == null) {
			return null;
		}
		// 第一次遍历找到链表长度
		int size = getLength(head);
		// 第二次遍历 size-index 位置，就是倒数k-th
		// 先做一个index的校验
		if (index <= 0 && index > size) {
			return null;
		}
		// 定义一个辅助变量
		HeroNode cur = head.next;
		for (int i = 0; i < size - size; i++) {
			cur = cur.next;
		}
		return cur;
	}

	// 获取单链表的节点个数
	public static int getLength(HeroNode head) {
		if (head.next == null) { // 空链表
			return 0;
		}
		int length = 0;
		// 定义一个辅助变量cur
		HeroNode cur = head.next;
		while (cur != null) {
			length++;
			cur = cur.next; // 遍历
		}
		return length;
	}
}

//定义LinkedList去管理Hero
class SinglyLinkedList { // SinglyLinkedList
	// 初始化头节点
	private HeroNode head = new HeroNode(0, "", "");

	// 返回头节点
	public HeroNode getHead() {
		return head;
	}

	// 添加node
	// 当不考虑编号顺序时，
	// 1. 找到当前链表的last node
	// 2. 将最后节点的next指向new node
	public void add(HeroNode heroNode) {

		// 因为head node不能动，需要辅助遍历temp
		HeroNode temp = head;
		// 遍历列表，找到last node
		while (true) {
			// find last node
			if (temp.next == null) {
				break;
			}
			// otherwise,将temp后移
			temp = temp.next;
		}
		// temp指向last node
		// temp.next指向new node
		temp.next = heroNode;
	}

	// 添加node
	// 考虑编号顺序
	public void addByOrder(HeroNode heroNode) {
		// 因为head node不能动，需要辅助temp去找到新的node
		HeroNode temp = head;
		boolean flag = false; // 标识添加的编号是否存在
		while (true) {
			if (temp.next == null) { // temp在列表最后
				break;
			}
			if (temp.next.num > heroNode.num) { // 位置找到，在temp后面插入
				break;
			} else if (temp.next.num == heroNode.num) { // 说明编号存在
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 判断flag的值
		if (flag) { // 不能添加，编号存在
			System.out.printf("英雄的编号 %d 已经存在，不能添加\n", heroNode.num);
		} else {
			// 插入到temp后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

	// 根据no修改节点信息
	public void update(HeroNode newHeroNode) {
		// 判断是否为空
		if (head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		// 根据no找到修改的节点
		// 定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false; // 表示是否找到
		while (true) {
			if (temp == null) {
				break; // 已经遍历完链表
			}
			if (temp.num == newHeroNode.num) {
				// 找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 根据flag判断是否找到要修改的node
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickName = newHeroNode.nickName;
		} else { // 没有找到
			System.out.printf("没有找到编号  %d 的节点，不能修改\n", newHeroNode.num);
		}
	}

	// 删除节点
	// 1.head不能动，需要temp辅助节点去找到待删除节点的前一个节点
	// 2.让 temp.next.num和需要删除的节点的num比较
	public void delete(int num) {
		HeroNode temp = head;
		boolean flag = false; // 表示是否找到待删除节点
		while (true) {
			if (temp.next == null) { // 已经遍历到链表最后
				break;
			}
			if (temp.next.num == num) { // 找到待删除节点的前一个节点
				flag = true;
				break;
			}
			temp = temp.next;
		}
		if (flag) { // 找到，可以删除
			temp.next = temp.next.next;
		} else {
			System.out.printf("要删除的节点 %d 不存在\n", num);
		}
	}

	// 显示链表
	public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			throw new NoSuchElementException();
		}
		// 因为head node不能动，需要辅助遍历temp
		HeroNode temp = head.next;
		while (true) {
			// 判断是否到链表最后
			if (temp == null) {
				break;
			}
			// 输出node信息
			System.out.println(temp);
			temp = temp.next;
		}
	}
}
