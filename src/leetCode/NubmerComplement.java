/**
 * 
 */
package leetCode;

/**
 * @author Administrator
 *
 * 
 */
public class NubmerComplement {
//	public static int nubmerComplent(int num) {
//		int com = 0;
//		int i = 0;
//		while (num > 0) {
//			int val = num % 2;
//			val = Math.abs(val - 1);
//			if (val == 0) {
//				num = num >> 1;
//				i++;
//				continue;
//			}else{
//				if (i == 0) {
//					com += val;
//					num = num >> 1;
//					i++;
//					continue;
//				}else{
//				    val = (int)Math.pow(2, i);
//					com += val;
//					num = num >> 1;
//					i++;
//				}
//			}
//		}
//		return com;
//	}
	public static int nubmerComplent(int num) {
		int com = 0;
		int i = 0;
		while (num > 0) {
			double val = 1 - num % 2;
			if (val > 0) {
				if (i>0) {
					val =  Math.pow(2, i);	
				}
			}
			com += val;
			num = num >> 1;
			++i;
		}
		return com;
	}
	public static int nubmerComplent1(int num) {
		 int sum=0,n=0,Div=-1,Rem=-1;
	        while(Div!=0)
	        {
	            Div=num/2;
	            Rem=num%2;
	            num=Div;
	            sum+=(1-Rem)*Math.pow(2,n);//1-Rem相当于取反
	            n++;
	        }
	        return sum;
	}
	public static void main(String[] args) {
		System.out.println(nubmerComplent(85));
	}

}
