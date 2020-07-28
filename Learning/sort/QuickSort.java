package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

	public static void main(String[] args) {

		int[] arr = { 4, 3, 6, 5, 7, 2, 1, 8 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		// 测试快速排序
		quickSort(arr, 0, arr.length - 1);
		System.out.println("排序后");
		System.out.println(Arrays.toString(arr));

		// 测试时间
		System.out.println("测试时间~~");
		int[] arr2 = new int[80000000];
		for (int i = 0; i < 80000000; i++) {
			arr2[i] = (int) (Math.random() * 8000000); // 生成一个[0，8000000）数
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是:" + date1Str);

		// 测试快速排序
		quickSort(arr2, 0, arr2.length - 1);
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是:" + date2Str);
	}

	/**
	 * 快速排序算法 O(nlogn)
	 * 
	 * @param arr
	 * @param left  Index of first entry of subarray
	 * @param right Index of last entry of subarray
	 */
	public static void quickSort(int[] arr, int low, int hi) {

		int left = low; // 左下标
		int right = hi; // 右下标
		// pivot 中轴
		int pivot = arr[(left + right) / 2];
		int temp = 0;

		// while循环的目的是让比pivot 小的放到左边
		// 比pivot 大的放到右边
		while (left < right) {
			// 在pivot的左边一直找，找到大于等于pivot值，才退出
			while (arr[left] < pivot) {
				left += 1;
			}
			// 在pivot的右边一直找，找到小于等于pivot值，才退出
			while (arr[right] > pivot) {
				right -= 1;
			}
			// 如果left >= right 说明pivot的左右两边的值
			// 已经按照左边全部是小于等于pivot值
			// 而右边全部是大于等于pivot的值
			if (left >= right) {
				break;
			}
			// 未满足，则交换
			temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;

			// 如果交换完后，发现这个arr[left] == pivot，则right--
			if (arr[left] == pivot) {
				right -= 1;
			}
			// 如果交换完后，发现这个arr[right] == pivot，则left++
			if (arr[right] == pivot) {
				left += 1;
			}
		}

		// 如果 left == right, 必须left++， right--，否则会出现栈溢出
		if (left == right) {
			left += 1;
			right -= 1;
		}

		// 向左递归
		if (low < right) {
			quickSort(arr, low, right);
		}

		// 向右递归
		if (hi > left) {
			quickSort(arr, left, hi);
		}
	}
}
