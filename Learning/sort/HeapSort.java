 package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {

		// 要求将数组进行升序排序
		int[] arr = { 12, 19, 10, 4, 23, 7, 45, 8, 15 };
		System.out.println("排序前的数组=" + Arrays.toString(arr));
		heapSort(arr);
		System.out.println("排序后的数组=" + Arrays.toString(arr));
		
		// 测试时间
		int[] arr2 = new int[8000000];
		for (int i = 0; i < 8000000; i++) {
			arr2[i] = (int) (Math.random() * 8000000); // 生成一个[0，8000000）数
		}
		System.out.println("测试时间~~");

		Date date1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1String = simpleDateFormat.format(date1);
		System.out.println("排序前的时间是:" + date1String);

		// 测试冒泡排序
		heapSort(arr2);

		Date date2 = new Date();
		String date2String = simpleDateFormat.format(date2);
		System.out.println("排序后的时间是:" + date2String);
	}

	// 编写堆排序
	public static void heapSort(int[] arr) {

		int temp = 0;

		// 1.将无序序列构建成一个堆，根据升序降序需求选择大顶堆or小顶堆
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}

		/*
		 * 2. 将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端 
		 * 3. 重新调整结构，使其满足堆定义，
		 *    然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
		 */
		for (int j = arr.length - 1; j >= 0; j--) {
			// 交换
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
	}

	/**
	 * 将一个数组（二叉树）调整成一个大顶堆（升序） 功能：将以 i 对应的非叶子节点的数调整成大顶堆
	 * 举例 int[] arr = { 4, 6, 8, 5, 9 } => i=1 => adjustHeap => { 4, 9, 8, 5, 6 }; 
	 * 如果再次调用 adjustHeap 传入的是 i=0 => { 4, 9, 8, 5, 6 } => { 9, 6, 8, 5, 4 };
	 * 
	 * @param arr    待调整的数组
	 * @param i      表示非叶子节点在数组中的索引
	 * @param length 表示对多少个元素进行调整，length是在逐渐减少
	 */
	public static void adjustHeap(int[] arr, int i, int length) {

		int temp = arr[i]; // 先取出当前元素的值，保存在临时变量temp
		// 开始调整
		// 1. k = k * 2 + 1，k是i节点的左子节点
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) { // 说明左子节点的值小于右子节点的值
				k++; // k指向右子节点
			}
			if (arr[k] > temp) { // 如果子节点大于父节点
				arr[i] = arr[k]; // 把较大的值赋给当前节点
				i = k; // !!! 将i指向k，继续循环比较
			} else {
				break; // !
			}
		}
		// 当for循环结束后，已经将以 i 为父节点的树的最大值，放在了最顶（局部）
		arr[i] = temp; // 将temp赋值到调整后的位置
	}
}