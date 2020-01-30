package geek.algorithm;

import java.util.Arrays;

/**
 * @author heyu
 * @date 2019/9/17
 */
public class geek {

	private static void print(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		insertionSort(new int[] { 6, 5, 4, 3, 2, 1 }, 6);
		bubbleSort(new int[] { 6, 5, 4, 3, 2, 1 }, 6);
		quickSort(new int[] { 6, 5, 4, 3, 2, 1 });
	}

	// 冒泡排序，a 表示数组，n 表示数组大小
	public static void bubbleSort(int[] a, int n) {
		if (n <= 1) {
			return;
		}
		for (int i = 0; i < n; ++i) {
			// 提前退出冒泡循环的标志位
			boolean flag = false;
			for (int j = 0; j < n - i - 1; ++j) {
				// 交换
				if (a[j] > a[j + 1]) {
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					// 表示有数据交换
					flag = true;
				}
			}
			if (!flag)
				// 没有数据交换，提前退出
				break;
		}
		print(Arrays.toString(a));
	}

	// 插入排序，a 表示数组，n 表示数组大小
	public static void insertionSort(int[] a, int n) {
		if (n <= 1) {
			return;
		}
		for (int i = 1; i < a.length; i++) {
			int value = a[i];
			int j = i - 1;
			for (; j >= 0; j--) {
				if (a[j] < value) {
					a[j] = a[j - 1];
				} else {
					break;
				}
			}
			a[j + 1] = value;
		}
		print(Arrays.toString(a));
	}

	/**
	 * 快速排序
	 * 
	 * @param a
	 * @param low
	 * @param hight
	 */
	public static void sort(int[] a, int low, int hight) {
		int i, j, index;
		if (low > hight) {
			return;
		}
		i = low;
		j = hight;
		index = a[i]; // 用子表的第一个记录做基准
		while (i < j) { // 从表的两端交替向中间扫描
			while (i < j && a[j] >= index)
				j--;
			if (i < j)
				a[i++] = a[j];// 用比基准小的记录替换低位记录
			while (i < j && a[i] < index)
				i++;
			if (i < j) // 用比基准大的记录替换高位记录
				a[j--] = a[i];
		}
		a[i] = index;// 将基准数值替换回 a[i]
		sort(a, low, i - 1); // 对低子表进行递归排序
		sort(a, i + 1, hight); // 对高子表进行递归排序
	}

	public static void quickSort(int[] a) {
		sort(a, 0, a.length - 1);
		print(Arrays.toString(a));
	}

}
