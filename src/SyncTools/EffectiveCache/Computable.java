/**
 * 
 */
package SyncTools.EffectiveCache;

/**
 * @author Administrator
 *
 * 
 */
public interface Computable<A, V> {
	public V compute(A arg) throws Exception;
}
