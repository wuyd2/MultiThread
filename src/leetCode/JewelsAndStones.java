/**
 * 
 */
package leetCode;

import java.util.Iterator;

/**
 * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 
 * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 * 
 * 示例 1:
 * 
 * 输入: J = "aA", S = "aAAbbbb" 输出: 3 
 * 
 * 示例 2:
 * 
 * 输入: J = "z", S = "ZZ" 输出: 0 注意:
 * 
 * S 和 J 最多含有50个字母。 J 中的字符不重复。
 *
 * 
 */
public class JewelsAndStones {
	public static int howMany1(String jewels , String stones){//时间复杂度O(n*m)
		int total = 0;
		for(int i=0;i<jewels.length();i++){
			char jewel = jewels.charAt(i);
			for (int j = 0; j < stones.length(); j++) {
				if (jewel == stones.charAt(j)) {
					++total; 
				}
			}
		}
		return total;
	}
	/**
	 * 时间复杂度O(m+n),空间复杂度O(128)
	 * @param J
	 * @param S
	 * @return
	 */
	public static int numjewelsInStones(String J,String S){
		int f[] = new int[128];
		for (char c:J.toCharArray()) {
			f[c] = 1;
		}
		int num = 0;
		for(char c:S.toCharArray()){
			num += f[c];//如果c存在于J，则加1，否则加0
		}
		return num;
	}
	
   public static void main(String args[]){
	   String jewels = "aA";
	   String stones = "aAAbbbb";
	   long start = System.nanoTime();;
	   System.out.println(howMany1(jewels, stones));
	   long end = System.nanoTime();
	   System.out.println(end - start );
	   start = System.nanoTime();
	   System.out.println(numjewelsInStones("aA", "aAAbbbb"));
	   end = System.nanoTime();
	   System.out.println(end - start );
   }
}
