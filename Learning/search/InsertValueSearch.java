package search;

import java.util.Arrays;

public class InsertValueSearch {

	public static void main(String[] args) {

		int[] arr = new int[100];
		for (int i = 0; i < 100; i++) {
			arr[i] = i + 1;
		}
		System.out.println(Arrays.toString(arr));
		
		int resIndex = insValSearch(arr, 0, arr.length - 1, 49);
		System.out.println("resIndex=" + resIndex);
	}

	// 插值查找算法
	/**
	 * 
	 * @param arr     数组
	 * @param lo      左边索引
	 * @param hi      右边索引
	 * @param findVal 查找的值
	 * @return 如果找到，返回对应下标，否则返回-1
	 */
	public static int insValSearch(int[] arr, int lo, int hi, int findVal) {
		if (lo > hi || findVal < arr[0] || findVal > arr[arr.length - 1]) {
			return -1;
		}
		// 求mid
		int mid = lo + (hi - lo) * (findVal - arr[lo]) / (arr[hi] - arr[lo]);
		int midVal = arr[mid];
		if (findVal > midVal) { // 向右递归
			return insValSearch(arr, mid + 1, hi, findVal);
		} else if (findVal < midVal) { // 向左递归
			return insValSearch(arr, lo, mid - 1, findVal);
		} else {
			return mid;
		}

	}
}
