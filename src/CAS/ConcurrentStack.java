/**
 * 
 */
package CAS;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Administrator
 * 使用AtomicReference原子引用的CAS算法实现一个非阻塞栈
 * push把E元素送入栈顶
 * pop返回一个E元素
 * 使用Node来封装E作为栈的一个节点
 */
public class ConcurrentStack<E> {
	//使用原子引用来封装栈顶节点
	private AtomicReference<Node<E>> top = new AtomicReference<ConcurrentStack.Node<E>>();
	public E pop(){
		Node<E> oldHead;
		Node<E> newHead;
        do {
        	oldHead = top.get();
    		if (oldHead == null) {
    			return null;
    		}
    		newHead = oldHead.next;
		} while (!top.compareAndSet(oldHead, newHead));
		return oldHead.item;
		
	}
	public void push(E item){
		Node<E> newHead = new Node<E>(item);
		Node<E> oldHead;
		do {
			oldHead = top.get();//获取top封装的元素
			newHead.next = oldHead;
		} while (!top.compareAndSet(oldHead, newHead));
	}
	
	private static class Node<E> {//Node代表栈的一个节点，用来封装栈数据
		private final E item;
		private Node<E> next;

		public Node(E item) {
			this.item = item;
		}
	}
}
