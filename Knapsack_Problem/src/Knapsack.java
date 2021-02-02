/**
 * Wesley Howe 300146506
 */
public class Knapsack {
	private int itemNumber;
	private int backpackCapacity;
	private Item[] itemList;
	
	public Knapsack() {
		itemNumber = 0;
		backpackCapacity = 0;
		itemList = new Item[1];
		itemList[0] = new Item();
	}
	public Knapsack(int _itemNumber, int _backpackCapacity, Item[] _itemList) {
		itemNumber = _itemNumber;
		backpackCapacity = _backpackCapacity;
		itemList = _itemList;	
	}
	
	public int getItemNumber() {
		return itemNumber;
	}
	
	public int getBackPackCapacity() {
		return backpackCapacity;
	}
	
	public Item[] getItemList() {
		return itemList;
	}
	
	public void print() {
		System.out.println("Number of Items: " + Integer.toString(itemNumber));
		System.out.println("Backpack Capacity: " + Integer.toString(backpackCapacity) + "\n");
	    
		if(itemNumber==0)
			System.out.println("Error: Uninitialized problem.");
		
		else {
		    for(int i = 0; i < itemNumber; i++) {
	    	    itemList[i].print();
	        }
		}
		
		System.out.println(); //adds extra newline for formatting.
	}
	
	public String bruteForce() {
		BruteForceTree Zaqqum = new BruteForceTree(this);
		
		Zaqqum.generateTree(Zaqqum.getRoot());
		
		System.out.println("\nThe solution is:\n");
		
		return Zaqqum.getSolution();

	}
	
	public String dynamicProgramming() {
		KTable chair = new KTable(this);
		
		System.out.println("Solution Table:\n");
		
		String solution = chair.generateTable();
		
		chair.print();
		
		return solution;
	}
}
