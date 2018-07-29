/**
 * 
 */
package SyncTools.EffectiveCache;

import java.math.BigInteger;

/**
 * @author Administrator
 *
 * 
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
	@Override
	public BigInteger compute(String arg) {
		return new BigInteger(arg);
	}
}
