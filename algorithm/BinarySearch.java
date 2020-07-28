package algorithm;

public class BinarySearch { // 非递归实现

	public static void main(String[] args) {

		int[] arr = { 1, 3, 8, 10, 11, 67, 100 };
		int index = binarySearch(arr, 100);
		System.out.println("index = " + index);
	}

	/**
	 * 
	 * @param arr    待查找的数组
	 * @param target 需要查找的树
	 * @return 返回对应标，-1表示没有找到
	 */
	public static int binarySearch(int[] arr, int target) {

		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] > target) {
				hi = mid - 1; // 需要向左边查找
			} else {
				lo = mid + 1; // 需要向右边查找
			}
		}
		return -1;
	}
}
