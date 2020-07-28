package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort { // distribution sort / bucket sort

	public static void main(String[] args) {

		int[] arr = { 53, 3, 542, 748, 14, 214 };

		System.out.println("排序前");
		System.out.println(Arrays.toString(arr));
		// 测试基数排序
		radixSort(arr);
		System.out.println("排序后");
		System.out.println(Arrays.toString(arr));

		// 测试时间
		System.out.println("测试时间~~");
		int[] arr2 = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr2[i] = (int) (Math.random() * 80000); // 生成一个[0，8000000）数
		}

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是:" + date1Str);
		
		// 测试基数排序
		radixSort(arr2);
		Date date2 = new Date();
		String date2Str = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是:" + date2Str);
	}

	// 基数排序算法
	// O(n*k)
	public static void radixSort(int[] arr) {

		// 1. 得到数组中最大的数的位数
		int max = arr[0]; // Suppose 第1个数就是最大的数
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		// 得到最大数时几位数
		int maxLength = (max + "").length();

		// 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
		// 1. 二维数组包含10个一维数组
		// 2. 为了防止数据溢出，只能让每个一维数组（桶）的大小定为arr.length
		int[][] bucket = new int[10][arr.length];

		// 为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录各个桶每次放入的数据个数
		// 比如，bucketElementCounts[0]，记录的就是 bucket[0] 桶的放入数据个数
		int[] bucketElementCounts = new int[10];

		// 使用for循环
		for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
			// 针对每个元素对应的位数进行排序处理
			for (int j = 0; j < arr.length; j++) {
				// 取出每个元素的个位值
				int digitOfElement = arr[j] / n % 10;
				// 放入到对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
				bucketElementCounts[digitOfElement]++;
			}
			// 按照这个桶的顺序（一维数组的下标），依次取出数据，放入原来数组
			int index = 0;
			// 遍历每一个桶，并将桶中的数据，放入到原数组
			for (int k = 0; k < bucketElementCounts.length; k++) {
				// 如果桶中有数据，才放入到原数组
				if (bucketElementCounts[k] != 0) {
					// 循环该桶，即第k个桶
					for (int l = 0; l < bucketElementCounts[k]; l++) {
						// 取出元素放入到arr
						arr[index] = bucket[k][l];
						index++;
					}
				}
				// 需要将每个bucketElementCounts[k] = 0
				bucketElementCounts[k] = 0;
			} 
		}
	}
}
