/**
 * 
 */
package leetCode;

/**
 * 给定一个二叉树，找出其最大深度。
 * 
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例： 给定二叉树 [3,9,20,null,null,15,7]，
 * 
 * 3 / \ 9 20 / \ 15 7 返回它的最大深度 3 。
 *
 * 
 */
public class MaxmiumDepthOfBinaryTree {
	public static int getDepth(Node root) {
		if (root == null) {
			return 0;
		} else if (root.left == null && root.right == null) {
			return 1;
		} else {
			return 1 + Math.max(getDepth(root.left), getDepth(root.right));
		}
	}

	public static void main(String[] args) throws Exception {
//		Node[] nodes = { new Node(3), new Node(9), new Node(20), null, null, new Node(15), new Node(7) };
		Integer nodes[] = {3, 9, 20, null, null, 15, 7};
		int length = nodes.length;
		BinaryTree<Integer> tree = new BinaryTree(3);
		tree.addNode(tree.root(), nodes[1], true);
		tree.addNode(tree.root(), 20, false);
		tree.addNode(tree.root().left, null, true);
		tree.addNode(tree.root().left, null, false);
		tree.addNode(tree.root().right,15, true);
		tree.addNode(tree.root().right, 7, false);
		tree.addNode(tree.root().left.left, 7, false);
//		int i = 0;
//		while (i < length-1) {
//			Node r = nodes[i];
//			if (++i < length - 1) {
//				r.left = nodes[i];
//			}
//			if (++i<length - 1) {
//				r.right = nodes[i];
//			}
//			r = r.left;
//		}	
		System.out.println(getDepth(tree.root()));

	}
}
class BinaryTree<T>{
	private Node root;//根结点
    public BinaryTree(T data){
    	this.root = new Node(data);
    }
    public BinaryTree(){
    	this.root = new Node();
    }
    public Node addNode(Node parent,T val,boolean isLeft) throws Exception{
    	if (parent == null) {
			throw new Exception("parent is null;");
		}
    	if (isLeft && parent.left != null) {
			throw new Exception("left node is exist.");
		}
    	if (!isLeft && parent.right != null) {
			throw new Exception("right node is exist.");
		}
    	if(val == null){
    		return null;
    	}
    	Node newNode =  new Node(val);
    	if (isLeft) {
			parent.left = newNode;
		}else{
			parent.right = newNode;
		}
    	return newNode;
    }
    public Node leftChild(Node parent) throws Exception{
    	if (parent == null) {
			throw new Exception("parent is null,no leftChild.");
		}
    	return parent.left;
    }
    public Node  rightChild(Node parent) throws Exception{
    	if (parent == null) {
			throw new Exception("parent is null,no  rightChild.");
		}
    	return parent.right;
    }
    public Node root(){
    	return this.root;
    }
}
class Node<T> {
	T val;
	Node left;
	Node right;
	public Node(){
		
	}
	public Node(T val) {
		this.val = val;
	}
	public Node(T val,Node left,Node right){
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
}