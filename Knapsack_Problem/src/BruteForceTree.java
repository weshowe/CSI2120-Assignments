/**
 * Wesley Howe 300146506
 */
public class BruteForceTree {
	private Node root;
	private int depth;
	public Item[] itemList;
	public Node solution;
	
	public BruteForceTree(Knapsack p) {
		root = new Node("", 0, p.getBackPackCapacity(), 0);
		depth = p.getItemNumber();
		itemList = p.getItemList();
		solution = root;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public String getSolution( ) {
		solution.print();
		
		return solution.toString();
	}
	
	public void generateTree(Node n) {
		
		// Base case for when we reach the last level of the tree, indicating a solution.
		if(n.getDepthLevel() >= depth) {
			n.print();
			
			//If the node has a valid weight combination, and has a higher value than the current solution, we update the solution.
			if(n.getWeight() > -1) {
				if(n.getValue() > solution.getValue()) {
					solution = n;
				}
			}
			
			return;
		}
		
		Item current = itemList[n.getDepthLevel()];
		
		//Generate the next node that includes the next item.
		Node l = new Node(n.getName() + " " + current.getName(), n.getValue() + current.getValue(), n.getWeight() - current.getWeight(), n.getDepthLevel() + 1);
		n.setLeft(l);
		
		//Generate the next node that does not include the next item. Lack of inclusion denoted with prefix !.
		Node r = new Node(n.getName(), n.getValue(), n.getWeight(), n.getDepthLevel() + 1);
		n.setRight(r);
		
		generateTree(l);
		generateTree(r);
		
		return;
		
	}
}
