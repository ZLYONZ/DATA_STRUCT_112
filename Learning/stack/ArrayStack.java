package stack;

import java.util.Scanner;

public class ArrayStack {

	public static void main(String[] args) {

		Stack stack = new Stack(4);
		String key = "";
		boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		
		while (loop) {
			System.out.println("s(how)");
			System.out.println("e(xit)");
			System.out.println("p(ush)");
			System.out.println("p(op)");
			key = scanner.next();
			
			switch (key) {
			case "show":
				stack.show();
				break;
			case "push":
				System.out.println("请输入一个数");
				int value = scanner.nextInt();
				stack.push(value);
				break;
			case "pop":
				try {
					int res = stack.pop();
					System.out.printf("出栈的数据是%d\n", res);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "exit":
				scanner.close();
				loop = false;
				break;
			default:
				break;
			}
		}
		System.err.println("程序退出~");
	}

}

class Stack {
	private int maxSize; // 栈的大小
	private int[] stack; // 数组模拟栈，数据放在数组里
	private int top = -1; // top表示栈顶，初始化为-1

	// 构造器
	public Stack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}

	// 栈满
	public boolean isFull() {
		return top == maxSize - 1;
	}

	// 栈空
	public boolean isEmpty() {
		return top == -1;
	}

	// 入栈-push
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}

	// 出栈-pop
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空");
		}
		int value = stack[top];
		top--;
		return value;
	}

	// 显示栈-show
	// 遍历时，需要从栈顶开始显示数据
	public void show() {
		if (isEmpty()) {
			System.out.println("栈空，没有数据~");
			return;
		}
		for (int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i, stack[i]);
		}
	}
}
