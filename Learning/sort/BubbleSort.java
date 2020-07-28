package sort;

import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

	public static void main(String[] args) {

		int arr[] = { 9, 3, -1, 10, -2 };
		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		//测试冒泡排序
		bubbleSort(arr);
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
		String date1String = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是:" + date1String);

		// 测试冒泡排序
		bubbleSort(arr2);

		Date date2 = new Date();
		String date2String = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是:" + date2String);

	}

	// 冒泡排序算法
	// O(n^2)
	public static void bubbleSort(int arr[]) {

		int temp = 0; // 临时变量
		boolean flag = false; // 标识变量，表示是否进行过交换

		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = 0; j < arr.length - 1 - i; j++) {
				// 如果前面的数比后面的数大，则交换
				if (arr[j] > arr[j + 1]) {
					flag = true;
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}

			if (!flag) { // 在一趟排序中，一次交换都没发生过
				break;
			} else {
				flag = false; // 重置flag，进行下次的判断
			}
		}
	}
}
