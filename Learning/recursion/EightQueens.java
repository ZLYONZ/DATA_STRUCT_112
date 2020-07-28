package recursion;

public class EightQueens {

	// 定义一个max，表示共有多少个皇后
	int max = 8;
	// 定义数组array，保存皇后放置位置的结果，比如arr={0，4，7，5，2，6，1，3}
	int[] array = new int[max];
	
	static int count = 0;
	static int judgeCount = 0;

	public static void main(String[] args) {
		
		EightQueens Queue = new EightQueens(); 
		Queue.check(0);
		System.out.printf("一共有%d解法\n",count);
		System.out.printf("一共判断冲突的次数是%d次",judgeCount);
	}

	// 放置第n个皇后
	private void check(int n) {
		if (n == max) { // n=8,前8个皇后已放好
			print();
			return;
		}
		// 依次放入皇后，判断是否冲突
		for (int i = 0; i < max; i++) {
			// 先把当前这个皇后n，放到该行的第1列
			array[n] = i;
			// 判断当放置第n个皇后后到i列时，是否冲突
			if (judge(n)) { // 不冲突
				// 接着放n+1个皇后，开始递归
				check(n+1);;
			} 
			// 如果冲突，就继续执行array[n] = i; 即将第n个皇后在本行后移
		}
	}

	// 查看当我们放置第n哥皇后，就去检测该皇后是否和前面已摆放的皇后冲突
	/**
	 * 
	 * @param n 表示第n个皇后
	 * @return
	 */
	private boolean judge(int n) {
		judgeCount++;
		for (int i = 0; i < n; i++) {
			// 1. 表示第n个皇后是否和前面的n-1个皇后在同一列
			// 2. 表示第n个皇后是否和第i个皇后在同一斜线
			if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
				return false;
			}
		}
		return true;
	}

	// 写一个方法,将皇后摆放的位置输出
	private void print() {
		count++; 
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
