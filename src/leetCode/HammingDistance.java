/**
 * 
 */
package leetCode;

/**
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 
 * 注意： 0 ≤ x, y < 231.
 * 
 * 示例:
 * 
 * 输入: x = 1, y = 4
 * 
 * 输出: 2
 * 
 * 解释: 1 (0 0 0 1) 4 (0 1 0 0) ↑ ↑
 * 
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 
 */
public class HammingDistance {

	public static int hammingDistance(int x, int y) {
		int distance = 0;
		x = x ^ y; //异常操作，算出X中1的个数就是汉明距离
		while (x != 0) {
			if ((x & 0x01) > 0) {
				++distance;
			}
			x = x >> 1;
		}
		return distance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
       System.out.println(hammingDistance(8, 9));
	}

}
