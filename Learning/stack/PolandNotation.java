package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {

		// 将一个中缀表达式转换成后缀表达式
		// 1. 1+((2+3)*4)-5 => 1 2 3 + 4 * 5 -
		// 2. 因为直接面对str 进行操作，不方便，因此先将中缀表达式对应的List 进行遍历
		// 即 1+((2+3)*4)-5 => ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
		// 3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
		// 即 ArrayList[1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList[1，2，3，+，4，*，5，-]
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("中缀表达式对应的List" + infixExpressionList);
		List<String> SuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
		System.out.println("后缀表达式对应的List" + SuffixExpressionList);
		
		System.out.printf("expression=%d", calculate(SuffixExpressionList));
		
		/*
		 * // 定义逆波兰表达式 // (3+4)*5-6 = (3 4 + 5 * 6 -) = 29 String suffixExpression =
		 * "3 4 + 5 * 6 -";
		 * 
		 * // 1. 先将表达式放到ArrayList 中 // 2. 将ArrayList 传递给一个方法，遍历ArrayList，配合Stack 完成计算
		 * List<String> list = getListString(suffixExpression);
		 * System.out.println("rpnList=" + list);
		 * 
		 * int res = calculate(list); System.out.println("计算的结果是=" + res);
		 */
	}

	// 将中缀表达式转换成对应的List
	public static List<String> toInfixExpressionList(String s) {
		// 定义一个List，存放中缀表达式对应的内容
		List<String> ls = new ArrayList<String>();
		int i = 0; // 一个指针，用于遍历中缀表达式字符串
		String str; // 对多位数的拼接
		char c; // 每遍历到一个字符，就放入c中
		do {
			// 如果c是一个非数字，则加入到ls
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				ls.add("" + c);
				i++;
			} else { // 如果是一个数，需要考虑多位数的问题
				str = ""; // 给str置成""(空串)
				while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
					str += c; // 拼接
					i++;
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;
	}

	// 让 ArrayList[1,+,(,(,2,+,3,),*,4,-,5] => ArrayList[1，2，3，+，4，*，5，-]
	public static List<String> parseSuffixExpressionList(List<String> ls) {
		// 定义两个栈
		Stack<String> s1 = new Stack<String>(); // 符号栈
		// s2在转换过程中没有pop操作，且还需逆序输出
		// 使用List<String> s2 代替
		// Stack<String> s2 = new Stack<String>(); 储存中间结果的栈
		List<String> s2 = new ArrayList<String>();

		// 遍历
		for (String item : ls) {
			if (item.matches("\\d+")) {
				s2.add(item);
			} else if (item.equals("(")) {
				s1.push(item);
			} else if (item.equals(")")) {
				// 如果是右括号")",则依次弹出s1栈顶的operand并压入s2，直到遇到左括号为止，然后将这一对括号丢弃
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
					
				}
				s1.pop(); // 将 ( 弹出s1栈,消除小括号
			} else {
				// 当item的优先级小于等于s1栈顶的operand，将s1栈顶的operand弹出并加入到s2中，再次和新的operand比较
				while (s1.size() != 0 && operation.getValue(s1.peek()) >= operation.getValue(item)) {
					s2.add(s1.pop());
				}
				// 需要将item压入栈
				s1.push(item);
			}
		}

		// 将s1中剩余的operand依次弹出并加入s2
		while (s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2; // 因为是存放到List中，因此按顺序输出就是对应的后缀的表达式的List
	}

	// 将一个逆波兰表达式，依次将num 和operand 放到ArrayList
	public static List<String> getListString(String suffixExpression) {
		// 将suffixExpression 分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for (String ele : split) {
			list.add(ele);
		}
		return list;
	}

	// 完成对逆波兰表达式的运算
	public static int calculate(List<String> ls) {
		// 创建个栈，只需一个栈即可
		Stack<String> stack = new Stack<String>();
		// 遍历 ls
		for (String item : ls) {
			// 使用正则表达式来取出数
			if (item.matches("\\d+")) { // 匹配的是多位数
				// 入栈
				stack.push(item);
			} else {
				// pop出两个数，并运算
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;

				if (item.equals("+")) {
					res = num1 + num2;
				} else if (item.equals("-")) {
					res = num1 - num2;
				} else if (item.equals("*")) {
					res = num1 * num2;
				} else if (item.equals("/")) {
					res = num1 / num2;
				} else {
					throw new RuntimeException("运算符有误");
				}
				// 把res 入栈
				stack.push("" + res);
			}
		}
		// 最后留在stack中的数据就是结果
		return Integer.parseInt(stack.pop());
	}
}

// 创建一个类，可以返回operand对应的优先级
class operation {
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;

	// 写一个方法，返回对应的优先级数字
	public static int getValue(String opreation) {
		int res = 0;
		switch (opreation) {
		case "+":
			res = ADD;
			break;
		case "-":
			res = SUB;
			break;
		case "*":
			res = MUL;
			break;
		case "/":
			res = DIV;
			break;
		default:
			System.out.println("不存在该运算符");
			break;
		}
		return res;
	}
}
