package leetcode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author heyu
 * @date 2019/9/10
 */
public class Solution {

	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>(100);
		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				map.put(nums[i], i);
				continue;
			}
			int x = target - nums[i];
			if (map.containsKey(x)) {
				return new int[] { map.get(x), i };
			}
			map.put(nums[i], i);
		}
		throw new RuntimeException();
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Map<Integer, Integer> map1 = new HashMap<>(5);
		int index1 = 0;
		map1.put(index1, l1.val);
		while (l1.next != null) {
			index1++;
			l1 = l1.next;
			map1.put(index1, l1.val);
		}
		Map<Integer, Integer> map2 = new HashMap<>(5);
		int index2 = 0;
		map2.put(index2, l2.val);
		while (l2.next != null) {
			index2++;
			l2 = l2.next;
			map2.put(index2, l2.val);
		}
		int size = Math.max(index1, index2);
		List<Integer> aaa = new ArrayList<>();
		boolean add = false;
		for (int i = 0; i <= size; i++) {
			Integer x1 = map1.get(i) == null ? 0 : map1.get(i);
			Integer x2 = map2.get(i) == null ? 0 : map2.get(i);
			int x = x1 + x2;
			if (add) {
				x = x + 1;
			}
			if (x > 9) {
				x = x - 10;
				add = true;
			} else {
				add = false;
			}
			aaa.add(x);
		}
		if (add) {
			aaa.add(1);
		}
		ListNode listNode = null;
		ListNode listNodeItem = null;
		for (Integer integer : aaa) {
			if (listNode == null) {
				listNode = new ListNode(integer);
				listNodeItem = listNode;
			} else {
				while (true) {
					if (listNodeItem.next != null) {
						listNodeItem = listNodeItem.next;
						continue;
					}
					listNodeItem.next = new ListNode(integer);
					break;
				}
			}

		}
		return listNode;
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		long l1 = System.currentTimeMillis();
		Arrays.sort(nums);
		List<List<Integer>> ls = new ArrayList<>();

		for (int i = 0; i < nums.length - 2; i++) {
			if (i == 0 || nums[i] != nums[i - 1]) { // 跳过可能重复的答案
				int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
				while (l < r) {
					if (nums[l] + nums[r] == sum) {
						ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
						while (l < r && nums[l] == nums[l + 1])
							l++;
						while (l < r && nums[r] == nums[r - 1])
							r--;
						l++;
						r--;
					} else if (nums[l] + nums[r] < sum) {
						while (l < r && nums[l] == nums[l + 1])
							l++; // 跳过重复值
						l++;
					} else {
						while (l < r && nums[r] == nums[r - 1])
							r--;
						r--;
					}
				}
			}
		}
		long l2 = System.currentTimeMillis();
		print(l2 - l1);
		return ls;
		// List<List<Integer>> listList = new ArrayList<>(nums.length);
		// Arrays.sort(nums);
		// int size = nums.length;
		// Set<Set<Integer>> setSet = new HashSet<>(nums.length);
		// Map<Integer, Integer> map = new HashMap<>();
		// for (int i = 0; i < nums.length; i++) {
		// map.put(nums[i], i);
		// }
		// for (int i = 0; i < size - 2; i++) {
		// int a = nums[i];
		// for (int j = i + 1; j < size; j++) {
		// int b = nums[j];
		// int x = 0 - a - b;
		// if (!map.containsKey(x)){
		// continue;
		// }
		// for (int k = j + 1; k < size; k++) {
		// int c = nums[size - 1];
		// int sum = a + b + c;
		// if (sum < 0) {
		// break;
		// }
		// c = nums[k];
		// sum = a + b + c;
		// if (sum > 0) {
		// break;
		// }
		// if (sum == 0) {
		// List<Integer> item = new ArrayList<>(3);
		// item.add(a);
		// item.add(b);
		// item.add(c);
		// listList.add(item);
		// break;
		// }
		// }
		// while (j != size - 1 && b == nums[j+1]){
		// j++;
		// }
		// }
		// while (i != size - 1 && a == nums[i+1]){
		// i++;
		// }
		// }
		// return listList;
	}

	// private static final String x1 = "abcabcbb";

	// private static final String x1 = "dvdf";

	// private static final String x1 = "ckilbkd";

	// private static final String x1 = "abba";

	private static final String x1 = "tmmzuxt";

	private static int lengthOfLongestSubstring(String s) {
		// int n = s.length();
		// Set<Character> set = new HashSet<>();
		// int ans = 0, i = 0, j = 0;
		// while (i < n && j < n) {
		// // try to extend the range [i, j]
		// if (!set.contains(s.charAt(j))){
		// set.add(s.charAt(j++));
		// ans = Math.max(ans, j - i);
		// }
		// else {
		// set.remove(s.charAt(i++));
		// }
		// }
		// return ans;
		//
		int length = 0;
		int lengthItem = 0;
		Map<Character, Integer> set = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);
			if (set.containsKey(x)) {
				length = Math.max(length, lengthItem);
				if (Objects.equals(x, s.charAt(i - 1))) {
					lengthItem = 1;
					set.put(x, i);
					continue;
				}
				if (lengthItem < i - set.get(x)) {
					lengthItem++;
				} else {
					lengthItem = i - set.get(x);
				}
				set.put(x, i);
				continue;
			}
			lengthItem++;
			set.put(x, i);
		}
		length = Math.max(length, lengthItem);
		return length;
	}

	private static String lll1 = "cccc";

	private static String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int length = s.length();
		Set<Integer> indexList = new TreeSet<>();
		indexList.add(0);
		for (int i = 1; i < length; i++) {
			if (i != length - 1 && Objects.equals(s.charAt(i - 1), s.charAt(i + 1))) {
				Set<Integer> itemSet = new TreeSet<>();
				itemSet.add(i - 1);
				itemSet.add(i);
				itemSet.add(i + 1);
				xxx(itemSet, s, length, i - 1, i + 1);
				indexList = indexList.size() > itemSet.size() ? indexList : itemSet;
			}
			if (Objects.equals(s.charAt(i - 1), s.charAt(i))) {
				Set<Integer> itemSet = new TreeSet<>();
				itemSet.add(i - 1);
				itemSet.add(i);
				xxx(itemSet, s, length, i - 1, i);
				indexList = indexList.size() > itemSet.size() ? indexList : itemSet;
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
		indexList.forEach(integer -> {
			stringBuilder.append(s.charAt(integer));
		});
		return stringBuilder.toString();
	}

	private static void xxx(Set<Integer> indexSet, String s, int length, int start, int end) {
		while (start >= 0 && end < length && Objects.equals(s.charAt(start), s.charAt(end))) {
			indexSet.add(start);
			indexSet.add(end);
			start--;
			end++;
		}
	}

	private static int reverse(int x) {
		boolean boo = false;
		if (x < 0) {
			x = x * -1;
			boo = true;
		}
		StringBuilder stringBuilder = new StringBuilder(String.valueOf(x));
		stringBuilder.reverse();
		try {
			if (boo) {
				return Integer.parseInt(stringBuilder.toString()) * -1;
			}
			return Integer.parseInt(stringBuilder.toString());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private static Pattern patternNumber = Pattern.compile("(\\d+)");
	private static String atoi = "+";

	private static int myAtoi(String str) {
		if (str == null) {
			return 0;
		}
		str = str.trim();
		if (str.length() == 0) {
			return 0;
		}
		boolean boo = str.startsWith("-");
		try {
			if (boo || str.startsWith("+")) {
				Integer.parseInt(String.valueOf(str.charAt(1)));
			} else {
				Integer.parseInt(String.valueOf(str.charAt(0)));
			}
			Matcher matcher = patternNumber.matcher(str);
			if (!matcher.find()) {
				return 0;
			}
			str = matcher.group();
		} catch (Exception e) {
			return 0;
		}
		try {
			if (boo) {
				return Integer.parseInt(str) * -1;
			}
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			if (boo) {
				return Integer.MIN_VALUE;
			}
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * 合并两个有序链表
	 * @param l1 有序链表
	 * @param l2 有序链表
	 * @return 有序链表
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode listNode = new ListNode(-1);
		ListNode prev = listNode;
		while (l1 != null && l2 != null){
			if (l1.val >= l2.val){
				prev.next = l2;
				l2 = l2.next;
			} else {
				prev.next = l1;
				l1 = l1.next;
			}
			prev = prev.next;
		}
		prev.next = l1 == null ? l2 : l1;
		return listNode.next;
	}

	public boolean isPalindrome(int x) {
		if (x < 0){
			return false;
		}
		StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(x)).reverse();
		return Objects.equals(String.valueOf(x), stringBuilder2.toString());
	}

	public int maxArea(int[] height) {
		int size = height.length;
		if (size == 0 || size == 1){
			return 0;
		}
		int area = 0;
		int i = 0;
		int j = size - 1;
		while (i < j){
			area = Math.max(area, (j - i) * Math.min(height[i], height[j]));
			if (height[i] < height[j]){
				i ++;
			} else {
				j--;
			}
		}
		return area;
	}

	private static String one = "I";
	private static String four = "IV";
	private static String five = "V";
	private static String nine = "IX";
	private static String ten = "X";
	private static String forty = "XL";
	private static String fifty = "L";
	private static String ninety = "XC";
	private static String hundred = "C";
	private static String four_hundred = "CD";
	private static String five_hundred = "D";
	private static String nine_hundred = "CM";
	private static String thousand = "M";

	public static String intToRoman(int num) {
//		// 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
//		// 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
//		int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
//		String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
//
//		StringBuilder stringBuilder = new StringBuilder();
//		int index = 0;
//		while (index < 13) {
//			// 特别注意：这里是等号
//			while (num >= nums[index]) {
//				// 注意：这里是等于号，表示尽量使用大的"面值"
//				stringBuilder.append(romans[index] + " ");
//				num -= nums[index];
//			}
//			index++;
//		}
//		return stringBuilder.toString();

		StringBuilder stringBuilder = new StringBuilder();
		if (num >= 1000) {
			int x = num / 1000;
			for (int i = 0; i < x; i++) {
				stringBuilder.append(thousand);
			}
			num = num % 1000;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num >= 900) {
			stringBuilder.append(hundred).append(thousand);
			num = num % 900;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num >= 500) {
			stringBuilder.append(five_hundred);
			num = num % 500;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num >= 400) {
			stringBuilder.append(hundred).append(five_hundred);
			num = num % 400;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num >= 100) {
			int x = num / 100;
			for (int i = 0; i < x; i++) {
				stringBuilder.append(hundred);
			}
			num = num % 100;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}

		if (num >= 90) {
			stringBuilder.append(ten).append(hundred);
			num = num % 90;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num >= 50) {
			stringBuilder.append(fifty);
			num = num % 50;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num >= 40) {
			stringBuilder.append(ten).append(fifty);
			num = num % 40;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num >= 10) {
			int x = num / 10;
			for (int i = 0; i < x; i++) {
				stringBuilder.append(ten);
			}
			num = num % 10;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num == 9) {
			stringBuilder.append(one).append(ten);
			return stringBuilder.toString();
		}
		if (num >= 5) {
			stringBuilder.append(five);
			num = num % 5;
			if (num == 0) {
				return stringBuilder.toString();
			}
		}
		if (num == 4) {
			stringBuilder.append(one).append(five);
			return stringBuilder.toString();
		}
		for (int i = 0; i < num; i++) {
			stringBuilder.append(one);
		}
		return stringBuilder.toString();

	}


	public static int romanToInt(String s) {
		Map<String, Integer> map = new HashMap<>();
		map.put("I", 1);
		map.put("IV", 4);
		map.put("V", 5);
		map.put("IX", 9);
		map.put("X", 10);
		map.put("XL", 40);
		map.put("L", 50);
		map.put("XC", 90);
		map.put("C", 100);
		map.put("CD", 400);
		map.put("D", 500);
		map.put("CM", 900);
		map.put("M", 1000);
		int total = 0;
		for (int i = 0; i < s.length();){
			if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))){
				total = total + map.get(s.substring(i, i + 2));
				i = i + 2;
			} else {
				total = total + map.get(s.substring(i, i + 1));
				i = i + 1;
			}
		}
		return total;
	}

	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int length = strs[0].length();
        for (String str : strs) {
            length = Math.min(length, str.length());
        }
        int low = 1;
        int high = length;
        while (low <= high){
            int middle = (low + high)/2;
            if (isPre(strs, middle)){
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
		return strs[0].substring(0, (low + high)/2);
	}

	private static boolean isPre(String[] strs, int middle){
	    String x = strs[0].substring(0, middle);
        for (String str : strs) {
            if (!str.startsWith(x)){
                return false;
            }
        }
	    return true;
    }

    public static int threeSumClosest(int[] nums, int target) {
	    Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            int low = i + 1;
            int high = nums.length - 1;
            while (high > low){
                int sum = a + nums[low] + nums[high];
                if (Math.abs(ans - target) > Math.abs(sum - target)){
                    ans = sum;
                }
                if (sum < target){
                    low++;
                } else if (sum > target){
                    high--;
                } else {
                    return sum;
                }
            }
        }
	    return ans;
    }

    public List<String> letterCombinations(String digits) {
        Map<String, List<String>> map = new HashMap<>(10);
        map.put("2", Arrays.asList("a", "b", "c"));
        map.put("3", Arrays.asList("d", "e", "f"));
        map.put("4", Arrays.asList("g", "h", "i"));
        map.put("5", Arrays.asList("j", "k", "l"));
        map.put("6", Arrays.asList("m", "n", "o"));
        map.put("7", Arrays.asList("p", "q", "r", "s"));
        map.put("8", Arrays.asList("t", "u", "v"));
        map.put("9", Arrays.asList("w", "x", "y", "z"));
        if (digits == null){
            return null;
        }
        List<String> a = new ArrayList<>();
		for (int i = 0; i < digits.length(); i++) {
            String item = String.valueOf(digits.charAt(i));
            List<String> x = map.get(item);
            if (a.isEmpty()){
                a = x;
                continue;
            }
            List<String> b = a;
            a = new ArrayList<>();
            for (String s : x) {
                for (String s1 : b) {
                    a.add(s1 + s);
                }
            }
		}
	    return a;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> listList = new ArrayList<>(nums.length);
        int sum1 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (sum1 == nums[i]){
                continue;
            }
            sum1 = nums[i];
            int sum2 = Integer.MIN_VALUE;
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                if (sum2 == nums[i1]){
                    continue;
                }
                sum2 = nums[i1];
                int a = i1 + 1;
                int b = nums.length - 1;
                int sum3 = Integer.MIN_VALUE;
                int sum4 = Integer.MIN_VALUE;
                while (b > a){
                    if (sum3 == nums[a]){
                        sum3 = nums[a];
                        a++;
                        continue;
                    }
                    if (sum4 == nums[b]){
                        sum4 = nums[b];
                        b--;
                        continue;
                    }
                    int sum = sum1 + sum2 + nums[a] + nums[b];
                    if (sum == target){
                        List<Integer> integerList = new ArrayList<>(4);
                        integerList.add(sum1);
                        integerList.add(sum2);
                        integerList.add(nums[a]);
                        integerList.add(nums[b]);
                        listList.add(integerList);
                        sum4 = nums[b];
                        b--;
                    } else if (sum > target){
                        sum4 = nums[b];
                        b--;
                    } else {
                        sum3 = nums[a];
                        a++;
                    }
                }
            }
        }
	    return listList;
    }

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode a = head;
		int length = 1;
		while (a.next != null){
			length++;
			a = a.next;
		}
		if (length == n){
			return head.next;
		}
		int x = 1;
		ListNode q = new ListNode(-1);
		ListNode b = head;
		q.next = b;
		while (b.next != null){
			if (length - x == n){
				b.next = b.next.next;
				break;
			}
			x++;
			b = b.next;
		}
		return q.next;
	}

	public static boolean isValid(String s) {
		Map<String, String> map = new HashMap<>(3);
		map.put("(", ")");
		map.put("[", "]");
		map.put("{", "}");
		LinkedList<String> stack = new LinkedList<>();
		for (int i = 0; i < s.length(); i ++){
			String a = String.valueOf(s.charAt(i));
			if (map.containsKey(a)){
				stack.push(a);
				continue;
			}
			if (stack.isEmpty()){
				return false;
			}
			String b = stack.pop();
			if (!Objects.equals(a, map.get(b))){
				return false;
			}
		}
		return stack.isEmpty();
	}

	public static List<String> generateParenthesis(int n) {

		return null;
	}

	public static void main(String[] args) {
		// int[] x = { 2, 11, 7, 15 };
		// int[] s = twoSum(x, 9);
		// print(s[0]);
		// print(s[1]);

		int[] x = { -1, 0, 1, 2, -1, -4 };
		int[] a = { -4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0 };
		int[] b = { 82597, -9243, 62390 };
		// print(threeSum(b).toString());
		// print(lengthOfLongestSubstring(x1));
		// print(longestPalindrome(lll1));
		// print(reverse(1534236469));
		// print(myAtoi(atoi));
		// print(intToRoman(20));
		// print(romanToInt("MCDLXXVI"));
		// print(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        // print(threeSumClosest(new int[]{1,1,-1,-1,3}, 3));
        // print(fourSum(new int[]{1,0,-1,0,-2,2}, 0));
        List<String> s = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<String> s1 = s;
        s1.remove(1);
        print(s);
		print(s1);
	}

	private static void print(Object obj) {
		System.out.println(obj);
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
