package sort;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {

	public static void main(String[] args) {

		int[] arr = { 101, 34, 119, 1, 56 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		//测试选择排序
		selectSort(arr);
		System.out.println("排序后");
		System.out.println(Arrays.toString(arr));

		// 测试时间
		int[] arr2 = new int[80000];
 		for (int i = 0; i < 80000; i++) {
			arr2[i] = (int) (Math.random() * 8000000); // 生成一个[0，8000000）数
		}
		System.out.println("测试时间~~"); 
		
		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是=" + date1Str);

		// 测试选择排序
		selectSort(arr2);
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是=" + date2Str);
	}

	// 选择排序算法
	// O(n^2)
	public static void selectSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			int min = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) { // 说明假定的最小值，并不是最小的
					min = arr[j]; // 重置min
					minIndex = j; // 重置minIndex
				}
			}
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	}
}
