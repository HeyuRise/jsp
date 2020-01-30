package geek.algorithm;

import geek.pattern.Test;

import java.util.Arrays;

public class DynamicProgramming {

	public static void print(Object object) {
		System.out.println(object);
	}

	private static int minDist = Integer.MAX_VALUE; // 全局变量或者成员变量
	// 调用方式：minDistBacktrace(0, 0, 0, w, n);

	/**
	 * 矩阵最短路径 回溯
	 * 
	 * @param i
	 * @param j
	 * @param dist
	 * @param w
	 * @param n
	 */
	public static void minDistBT(int i, int j, int dist, int[][] w, int n) {
		// 到达了n-1, n-1这个位置了,这里看着有点奇怪哈,你自己举个例子看下
		if (i == n && j == n) {
			if (dist < minDist)
				minDist = dist;
			return;
		}
		if (i < n) { // 往下走,更新i=i+1, j=j
			minDistBT(i + 1, j, dist + w[i][j], w, n);
		}
		if (j < n) { // 往右走,更新i=i, j=j+1
			minDistBT(i, j + 1, dist + w[i][j], w, n);
		}
	}

	/**
	 * 矩阵最短路径 动态规划 状态转移表法
	 * 
	 * @param matrix
	 * @param n
	 * @return
	 */
	public static int minDistDP(int[][] matrix, int n) {
		int[][] states = new int[n][n];
		int sum = 0;
		for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
			sum += matrix[0][j];
			states[0][j] = sum;
		}
		sum = 0;
		for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
			sum += matrix[i][0];
			states[i][0] = sum;
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				states[i][j] = matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
			}
		}
		return states[n - 1][n - 1];
	}

	private static int[][] matrix = { { 1, 3, 5, 9 }, { 2, 1, 3, 4 }, { 5, 2, 6, 7 }, { 6, 8, 4, 3 } };
	private static int n = 4;
	private static int[][] mem = new int[4][4];

	/**
	 * 矩阵最短路径 动态规划 状态转移方程
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public static int minDist(int i, int j) { // 调用minDist(n-1, n-1);
		if (i == 0 && j == 0)
			return matrix[0][0];
		if (mem[i][j] > 0)
			return mem[i][j];
		int minLeft = Integer.MAX_VALUE;
		if (j - 1 >= 0) {
			minLeft = minDist(i, j - 1);
		}
		int minUp = Integer.MAX_VALUE;
		if (i - 1 >= 0) {
			minUp = minDist(i - 1, j);
		}

		int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
		mem[i][j] = currMinDist;
		return currMinDist;
	}

	public static int[] a = { 1, 2, 5, 10, 20, 50, 100 };
	public static int money = 999999;

	/**
	 * 找钱问题
	 * 
	 * @param i
	 * @param leaveMoney
	 * @return
	 */
	public static int getMoneyNumber(int i, int leaveMoney) {
		if (leaveMoney == 0) {
			return 0;
		}
		int x = a[i];
		if (leaveMoney < x) {
			return getMoneyNumber(i - 1, leaveMoney);
		}
		return leaveMoney / x + getMoneyNumber(i - 1, leaveMoney % x);
	}

	public static int[] l = { 2, 9, 3, 6, 5, 1, 7 };

	/**
	 * 找出以peaches[i]结尾的最长增长子序列
	 * @param peaches
	 * @return
	 */
	public static int getLongestInsArray(int[] peaches) {
		// 用于存储子序列的长度
		int[] subSeqLen = new int[peaches.length];
		for (int x = 0; x < peaches.length; x++) {
			// 初始化最长子序列长度
			subSeqLen[x] = 1;
			// 找出前x+1项最长的序列
			for (int y = 0; y < x; y++) {
				if (peaches[x] > peaches[y] && subSeqLen[y] + 1 > subSeqLen[x]) {
					subSeqLen[x] = subSeqLen[y] + 1;
				}
			}
		}
		Arrays.sort(subSeqLen);
		print(Arrays.toString(subSeqLen));
		return subSeqLen[subSeqLen.length - 1];
	}

	public static void main(String[] args) {
		// print(minDist(3, 3));
		// print(getMoneyNumber(a.length - 1, money));
		// print(getLongestInsArray(l));

		print(Test.x);
	}

}
