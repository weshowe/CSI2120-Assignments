/**
 * Wesley Howe 300146506
 */
public class Node {
	private String name;
	private int weight;
	private int value;
	private int depthLevel;
	
	private Node left;
	private Node right;
	private Node root;
	
	public Node(String _name, int _value, int _weight, int _depthLevel) {
		name = _name;
		value = _value;
		weight = _weight;
		depthLevel = _depthLevel;
		
		left = null;
		right = null;
		root = null;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getValue() {
		return value;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setLeft(Node l) {
		left = l;
	}
	
	public void setRight(Node r) {
		right = r;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node groot) {
		root = groot;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDepthLevel() {
		return depthLevel;
	}
	
	public void print() {
		System.out.println(name + " " + Integer.toString(value) + " " + Integer.toString(weight) + "\n");	
	}
	
	public String toString() {
		return Integer.toString(value) + "\n" + name.trim(); 
	}
}
