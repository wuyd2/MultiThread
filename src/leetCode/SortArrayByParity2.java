/**
 * 
 */
package leetCode;

import java.util.Arrays;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。
示例：
输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * 
 */
public class SortArrayByParity2 {
	public static int[] sortArrayByParity(int[] arr) {
		int odd = 1;//奇数位游标
		int even = 0;//偶数位游标
		while (odd < arr.length && even < arr.length) {
			while (odd<arr.length && arr[odd]%2 == 1) {
				odd += 2;
			}
			while (even<arr.length && arr[even] %2 == 0) {
				even += 2;
			}
			if (odd < arr.length && even < arr.length) {
				int tmp = arr[odd];
				arr[odd] = arr[even];
				arr[even] = tmp;
			}
		}
		return arr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = {4,2,5,7};
		sortArrayByParity(arr);
		long start = System.nanoTime();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		long end = System.nanoTime();
		System.out.println(end - start);
	}

}
