package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

	public static void main(String[] args) {

		int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		// 测试希尔排序
		shellSort2(arr);
		System.out.println("排序后");
		System.out.println(Arrays.toString(arr));

		// 测试时间
		System.out.println("测试时间~~");
		int[] arr2 = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr2[i] = (int) (Math.random() * 8000000); // 生成一个[0，8000000）数
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是:" + date1Str);

		// 测试希尔排序
		shellSort2(arr2);
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是:" + date2Str);
	}

	// 希尔排序算法
	// O(nlogn)
	public static void shellSort(int[] arr) {


		int temp = 0;
		for (int gap = arr.length / 2; gap > 0; gap /= 2) {

			// 将10个数分成5组
			for (int i = gap; i < arr.length; i++) {
				// 遍历各组中所有的元素（共gap组，每组有个元素），步长gap
				for (int j = i - gap; j >= 0; j -= gap) {
					// 如果当前元素大于加上步长后的那个元素，则需交换
					if (arr[j] > arr[j + gap]) {
						temp = arr[j];
						arr[j] = arr[j + gap];
						arr[j + gap] = temp;
					}
				}
			}
		}
	}

	// 对交换式的ShellSort进行优化 -> 移位法
	public static void shellSort2(int[] arr) {

		// 增量gap，并逐步地缩小增量
		int temp = 0;
		int j = 0;

		for (int gap = arr.length / 2; gap > 0; gap /= 2) {
			// 从第gap个元素，逐个对其所在的组进行直接插入
			for (int i = gap; i < arr.length; i++) {
				j = i;
				temp = arr[j];

				if (arr[j] < arr[j - gap]) {
					while (j - gap >= 0 && temp < arr[j - gap]) {
						// 移动
						arr[j] = arr[j - gap];
						j -= gap;
					}
					arr[j] = temp;
				}
			}
		}
	}
}
