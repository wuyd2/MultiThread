package leetCode;
/**
 * @author Administrator 合并两个有序数组
 */

public class MergeKArray {
	public static int[]merge(int[]a,int[]b){
		int lena = a.length;
		int lenb = b.length;
		int []c = new int[lena + lenb];
		int i=0,j=0,k=0;
		while(i<lena && j<lenb){
			if(a[i]<b[j]){
				c[k++] = a[i++];
			}else{
				c[k++] = b[j++];
			}
		}
		while(i<lena){
			c[k++] = a[i++];
		}
		while(j<lenb){
			c[k++] = b[j++];
		}
		return c;
	}
	public static void main(String[] args) {
		int[] c = merge(new int[] { 1, 2, 3, 4 }, new int[] { 2, 2, 4, 5,
				6, 7, 8 });
		for (int i = 0; i < c.length; i++)
			System.out.println(c[i]);
	}
}