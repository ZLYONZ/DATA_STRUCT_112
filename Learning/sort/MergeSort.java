package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

	public static void main(String[] args) {

		int[] arr = { 8, 4, 5, 7, 1, 3, 6, 2 };
		int[] temp = new int[arr.length];

		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		// 测试归并排序
		mergeSort(arr, 0, arr.length - 1, temp);
		System.out.println("排序后");
		System.out.println(Arrays.toString(arr));

		// 测试时间
		System.out.println("测试时间~~");
		int[] arr2 = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr2[i] = (int) (Math.random() * 80000); // 生成一个[0，8000000）数
		}
		int[] temp2 = new int[arr2.length];

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是:" + date1Str);

		// 测试归并排序
		mergeSort(arr2, 0, arr2.length - 1, temp2);
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是:" + date2Str);
	}

	
	// 归并排序算法
	// O(nlogn)
	
	/**
	 * 合并的方法
	 * @param arr  排序的原始数组
	 * @param lo   左边有序序列的初始索引
	 * @param mid  中间索引
	 * @param hi   右边索引
	 * @param temp 中转数组
	 */
	public static void merge(int[] arr, int lo, int mid, int hi, int[] temp) {

		int left = lo; // 初始化left，右边有序序列的初始索引
		int right = mid + 1; // 初始化right，左边有序序列的初始索引
		int t = 0; // 指向temp数组的当前索引

		// （一）
		// 先把左右两边（有序）的数据按照规则填充到temp数组
		// 直到左右两边的有序序列，有一边处理完毕为止
		while (left <= mid && right <= hi) {
			// 如果left有序序列的当前元素，小于等于right有序序列的当前元素
			// 即将left的当前元素，拷贝到temp数组
			// left 和 right 后移
			if (arr[left] <= arr[right]) {
				temp[t] = arr[left];
				t += 1;
				left += 1;
			} else {
				temp[t] = arr[right];
				t += 1;
				right += 1;
			}
		}
		
		// （二）
		// 把有剩余数据的一边的数据依次全部填充到temp中
		while (left <= mid) { // 说明left有序序列还有剩余元素，全部填充到temp
			temp[t] = arr[left];
			t += 1;
			left += 1;
		}
		while (right <= hi) { // 说明right有序序列还有剩余元素，全部填充到temp
			temp[t] = arr[right];
			t += 1;
			right += 1;
		}

		// （三）
		// 将temp数组的元素拷贝到arr
		// 注意，并不是每次都拷贝所有
		t = 0;
		int tempLeft = lo;
		while (tempLeft <= hi) { // 第一次合并时，tempLeft = 0，hi = 1
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
	}

	// 分+合的方法
	public static void mergeSort(int[] arr, int lo, int hi, int[] temp) {
		if (lo < hi) {
			int mid = (lo + hi) / 2; // 中间索引
			// 向左递归进行分解
			mergeSort(arr, lo, mid, temp);
			// 向右递归进行分解
			mergeSort(arr, mid + 1, hi, temp);
			// 合并
			merge(arr, lo, mid, hi, temp);
		}

	}
}
