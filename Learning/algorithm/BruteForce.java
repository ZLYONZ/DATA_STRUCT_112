package algorithm;

public class BruteForce { // 暴力匹配算法

	public static void main(String[] args) {

		String str1 = "abc abcde abcdefca";
		String str2 = "abcdef";
		int index = BF(str1, str2);
		System.out.println("index = " + index);
	}

	// 暴力匹配算法
	public static int BF(String str1, String str2) {

		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();

		int s1Length = s1.length;
		int s2Length = s2.length;

		int i = 0; // i 索引指向 s1
		int j = 0; // j 索引指向 s2

		while (i < s1Length && j < s2Length) { // 保证匹配时不越界

			if (s1[i] == s2[j]) { // 匹配成功
				i++;
				j++;
			} else {
				i = i - j + 1; // i = i-(j-1)
				j = 0;
			}
		}

		// 判断是否匹配成功
		if (j == s2Length) {
			return i - j;
		} else {
			return -1;
		}
	}
}
