package sort;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {

	public static void main(String[] args) {

		int[] arr = { 101, 34, 119, 1, -1, 56 }; 
		System.out.println("排序前");
		System.out.println(Arrays.toString(arr)); 
		//测试插入排序
		insertSort(arr);
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

		// 测试插入排序
		insertSort(arr2);
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是=" + date2Str);
	}

	// 插入排序算法
	// O(n^2)
	public static void insertSort(int[] arr) {

		int insertVal = 0;
		int insertIndex = 0;
		
		for (int i = 1; i < arr.length; i++) {

			// 定义待插入的数
			insertVal = arr[i];
			insertIndex = i - 1; // 即arr[1]的前面这个数的下标

			// 给insertVal 找到插入的位置
			// 1.insertIndex >= 0 保证在给insertVal 找插入位置时不越界
			// 2.insertVal < arr[insertIndex] 待插入的数，还未找到插入位置
			// 3.将arr[insertIndex]后移
			while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
				arr[insertIndex + 1] = arr[insertIndex];
				insertIndex--;
			}
			// 当退出while循环时，说明插入的位置找到，insertIndex+1
			// 判断是否需要赋值
			if (insertIndex + 1 != i) {
				arr[insertIndex + 1] = insertVal;
			}
		}
	}
}
