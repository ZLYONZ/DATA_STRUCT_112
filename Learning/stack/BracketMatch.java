package stack;

public class BracketMatch {

	public static void main(String[] args) {

		String str = "(上海(长安)())";
		boolean match = isMatch(str);
		System.out.println(str + " 中的括号是否匹配：" + match);
	}

	/**
	 * 判断str中的括号是否成对
	 * 
	 * @param str 括号组成的字符串
	 * @return 如果匹配，返回true；否则返回false
	 */
	public static boolean isMatch(String str) {

		// 创建栈，存放左括号
		Stack1<String> chars = new Stack1<String>();

		// 从左到右遍历字符串
		for (int i = 0; i < str.length(); i++) {
			String curr = str.charAt(i) + "";

			// 判断是否为左括号，如有则压入栈
			if (curr.equals("(")) {
				chars.push(curr);
			} 
			
			// 继续判断是否为右括号，如有则将左括号弹出栈
			else if (curr.equals(")")) {
				String pop = chars.pop();
				
				// 判断弹出结果
				if (pop == null) {
					return false;
				}
			}
		}
		
		// 判断栈中是否有剩余的左括号
		if (chars.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
