package stack;

public class Calculator {

	public static void main(String[] args) {

		String expression = "30+22*6-20";

		// 创建两个栈，数栈，符号栈
		Stack2 numStack = new Stack2(10);
		Stack2 operStack = new Stack2(10);

		// 定义相关变量；
		int index = 0; // 用于扫描
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' '; // 将每次扫描得到的char保存到ch
		String keepNum = ""; // 用于拼接多位数

		// 开始while循环扫描expression
		while (true) {
			// 先得到expression里的每个字符
			ch = expression.substring(index, index + 1).charAt(0);
			// 判断ch是什么，做相应的处理
			if (operStack.isOper(ch)) {
				// 判断当前符号栈是否为空
				if (!operStack.isEmpty()) {
					// 1. 操作符的优先级小于或等于栈中的操作符
					if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						// 把运算结果入数栈
						numStack.push(res);
						operStack.push(ch);
					}
					// 2. 操作符的优先级大于栈中的操作符
					else {
						operStack.push(ch);
					}
				}
				// 如果为空直接入符号栈
				else {
					operStack.push(ch);
				}
			}
			// ch是数，则直接返回数栈
			else {

				// 1. 处理多位数时，不能发现是一个数就立即入栈，可能是多位数
				// 2. 在处理数时，需要向expression的表达式的index后再看一位，如果是数就继续扫描，如果是符号则入栈
				// 3. 定义一个变量字符串，用于拼接

				// 处理多位数
				keepNum += ch;

				// 判断ch是否为最后一位
				if (index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				} else {
					// 判断下一位字符是否为数字，如为数字继续扫描，否则入栈
					// 注意只是看后一位，index不++
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						// is operand
						numStack.push(Integer.parseInt(keepNum));
						// 重要！！！！！！！！
						// keepNum清空
						keepNum = "";
					}
				}
			}

			// index+1,判断是否扫描到expression最后
			index++;
			if (index >= expression.length()) {
				break;
			}
		}

		// 扫描表达式完毕，顺序的从数栈和符号栈pop出相应的数和符号
		while (true) {
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			// 把运算结果入数栈
			numStack.push(res);
		}
		// 将数栈的最后数pop出
		int res2 = numStack.pop();
		System.out.printf("表达式%s = %d", expression, res2);
	}
}

// 创建一个栈
class Stack2 {
	private int maxSize; // 栈的大小
	private int[] stack; // 数组模拟栈，数据放在数组里
	private int top = -1; // top表示栈顶，初始化为-1

	// 构造器
	public Stack2(int maxSize) {
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

	// 返回当前栈顶的值，不是pop - peek
	public int peek() {
		return stack[top];
	}

	// 返回运算符的优先级，优先级使用数字表示
	public int priority(int operand) {
		if (operand == '*' || operand == '/') {
			return 1;
		} else if (operand == '+' || operand == '-') {
			return 0;
		} else {
			return -1; // 假定目前的表达式只有+，-，*，/
		}
	}

	// 判断是否为运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}

	// 计算方法
	public int cal(int num1, int num2, int oper) {
		int res = 0; // 用于存放计算的结果
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num1 * num2;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;
		}
		return res;
	}
}