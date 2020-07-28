package search;

import java.util.ArrayList;

public class BinarySearch { // 注意：二分查找的前提是，该数组是有序的

	public static void main(String[] args) {

		int[] arr = { 1, 8, 10, 89, 1000, 1000, 1000, 1234 };

		int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
		System.out.println("resIndex=" + resIndex);

		ArrayList<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
		System.out.println("resIndex=" + resIndexList);
	}

	/**
	 * 
	 * @param arr     数组
	 * @param lo      左边的索引
	 * @param hi      右边的索引
	 * @param findVal 要查找的值
	 * @return 如果找到，返回对应下标，否则返回-1
	 */
	public static int binarySearch(int[] arr, int lo, int hi, int findVal) {

		if (lo > hi) {
			return -1;
		}
		int mid = (lo + hi) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) { // 向右递归
			return binarySearch(arr, mid + 1, hi, findVal);
		} else if (findVal < midVal) { // 向左递归
			return binarySearch(arr, lo, mid - 1, findVal);
		} else {
			return mid;
		}
	}

	public static ArrayList<Integer> binarySearch2(int[] arr, int lo, int hi, int findVal) {

		if (lo > hi) {
			return new ArrayList<Integer>();
		}
		int mid = (lo + hi) / 2;
		int midVal = arr[mid];

		if (findVal > midVal) { // 向右递归
			return binarySearch2(arr, mid + 1, hi, findVal);
		} else if (findVal < midVal) { // 向左递归
			return binarySearch2(arr, lo, mid - 1, findVal);
		} else {
			// 1. 找到mid索引值后不要直接返回
			// 2. 向mid索引值的左右两边扫描，将所有满足条件的元素的下标，加入到集合ArrayList
			// 3. 返回ArrayLsit
			ArrayList<Integer> resIndexList = new ArrayList<Integer>();
			// 向左扫描
			int temp = mid - 1;
			while (true) {
				if (temp < 0 || arr[temp] != findVal) {
					break;
				}
				// 否则,就把temp放入到resIndexList
				resIndexList.add(temp);
				temp -= 1; // temp左移
			}
			resIndexList.add(mid);

			// 向右扫描
			temp = mid + 1;
			while (true) {
				if (temp > arr.length - 1 || arr[temp] != findVal) {
					break;
				}
				// 否则,就把temp放入到resIndexList
				resIndexList.add(temp);
				temp += 1; // temp右移
			}
			return resIndexList;
		}
	}
}