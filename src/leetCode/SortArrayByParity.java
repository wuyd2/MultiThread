/**
 * 
 */
package leetCode;

import java.util.Arrays;

/**
 * 给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
 * 
 * 你可以返回满足此条件的任何数组作为答案。
 * 
 * 示例：
 * 
 * 输入：[3,1,2,4] 
 * 输出：[2,4,3,1] 
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 *
 * 
 */
public class SortArrayByParity {

	public static int[] sortArrayByParity(int[] arr) {
		int i=0,j=arr.length-1;
		while(i<j){
			while((i<j)&&(arr[j] % 2 == 1)) {
				--j;
			}
			while((i<j) && (arr[i] % 2 == 0)){  
				i++;
			}
//			swap(arr[i],arr[j]);
			int val = arr[i];
			arr[i] = arr[j];
			arr[j] = val;
		}
		return arr;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = {5,1,1,4};
		sortArrayByParity(arr);
		long start = System.nanoTime();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		long end = System.nanoTime();
		System.out.println(end - start);
	}

}
