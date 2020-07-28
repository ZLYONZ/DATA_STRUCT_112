package search;

import java.util.Arrays;

public class FibonacciSearch {

	public static int maxSize = 20;

	public static void main(String[] args) {

		int[] arr = { 1, 8, 10, 89, 499, 820, 1000, 1234, 2378 };
		System.out.println("index=" + fibSearch(arr, 1234));
	}

	// 非递归方法得到一个斐波那契数列
	public static int[] fib() {
		int[] f = new int[maxSize];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < maxSize; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f;
	}

	// 斐波那契查找算法（非递归）
	// O（log2 n）
	/**
	 * 
	 * @param arr 数组
	 * @param key 需查找的关键值
	 * @return 返回对应下标，如果没有则-1
	 */
	public static int fibSearch(int[] arr, int key) {
		int low = 0;
		int high = arr.length - 1;
		int k = 0; // 表示斐波那契分割数值的下标
		int mid = 0; // 存放mid值
		int f[] = fib(); // 获取到Fibonacci

		// 获取Fibonacci下标
		while (high > f[k] - 1) {
			k++;
		}
		// 因为f[k]的值可能大于arr的长度，需要使用Arrays类，构造一个新的数组，并指向arr
		// 不足的部分会使用0填充
		int[] temp = Arrays.copyOf(arr, f[k]);
		// 实际上需要使用arr数组最后的数填充temp数组
		for (int i = high + 1; i < temp.length; i++) {
			temp[i] = arr[high];
		}

		// 使用while循环，找到key
		while (low <= high) {
			mid = low + f[k - 1] - 1;
			if (key < temp[mid]) { // 说明应该向前查找（左边）
				high = mid - 1;
				k--;
			} else if (key > temp[mid]) { // 说明应该向后查找（右边）
				low = mid + 1;
				k -= 2;
			} else {
				if (mid <= high) {
					return mid;
				} else {
					return high;
				}
			}
		}
		return -1;
	}
}
