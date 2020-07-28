package algorithm;

import java.util.Arrays;

public class KMP { // Knuth-Morris-Pratt Algoritm

	public static void main(String[] args) {

		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABD";

		int[] next = kmpNext("ABCDABD");
		System.out.println("next = " + Arrays.toString(next));

		int index = kmpSearch(str1, str2, next);
		System.out.println("index = " + index);

	}

	// 获取到一个字符串（子串）的部分匹配值表
	public static int[] kmpNext(String dest) {

		// 创建一个next数组保存部分匹配值
		int[] next = new int[dest.length()];

		// 如果字符串长度为1，部分匹配值为0
		next[0] = 0;

		for (int i = 1, j = 0; i < dest.length(); i++) {

			// 当(dest.charAt(i) != dest.charAt(j)，需从next[j-1]获取新的j
			// 直到发现相等后才退出
			// KMP核心点
			while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
				j = next[j - 1];
			}

			// 条件满足时，部分匹配值+1
			if (dest.charAt(i) == dest.charAt(j)) {
				j++;
			}
			next[i] = j;
		}
		return next;
	}

	// KMP搜索算法
	/**
	 * 
	 * @param str1 源字符串
	 * @param str2 子串
	 * @param next 是子串对应的部分匹配表
	 * @return 返回第一个匹配的位置；如没匹配到，则返回-1
	 */
	public static int kmpSearch(String str1, String str2, int[] next) {

		// 遍历
		for (int i = 0, j = 0; i < str1.length(); i++) {

			// 需处理不相等的情况
			while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
				j = next[j - 1];
			}

			if (str1.charAt(i) == str2.charAt(j)) {
				j++;
			}
			if (j == str2.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}
}
