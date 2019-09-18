package geek;

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
		long a = System.currentTimeMillis();
		insertionSort(new int[] { 6, 5, 4, 3, 2, 1 }, 6);
		print(System.currentTimeMillis() - a);
		long b = System.currentTimeMillis();
		bubbleSort(new int[] { 6, 5, 4, 3, 2, 1 }, 6);
		print(System.currentTimeMillis() - b);
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
		for (int i = 1; i < n; ++i) {
			int value = a[i];
			int j = i - 1;
			// 查找插入的位置
			for (; j >= 0; --j) {
				if (a[j] > value) {
					// 数据移动
					a[j + 1] = a[j];
				} else {
					break;
				}
			}
			// 插入数据
			a[j + 1] = value;
		}
		print(Arrays.toString(a));
	}

}
