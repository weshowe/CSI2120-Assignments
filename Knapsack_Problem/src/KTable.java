/**
 * Wesley Howe 300146506
 */
public class KTable {

	private int[][] table;
	private String[][] shadowTable;
	private Item[] itemList;
	private int capacity;
	private int rows;
	private int columns;
	private int itemNumber;
	
	public KTable(Knapsack k) {
		//Pull capacity and item number from the knapsack, and generate row and column sizes for array.
		capacity = k.getBackPackCapacity();	
		columns = capacity + 1;
		
		itemNumber = k.getItemNumber();
		rows = itemNumber + 1;
		
        //Build and initialize array elements.
		table = new int[rows][columns];
		shadowTable = new String[rows][columns];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				table[i][j] = 0;
				shadowTable[i][j] = "X";
			}
		}
		
		//Pull list of items for the problem.
		itemList = k.getItemList();
	}
	
	/**
	 * This generates the solution table using the formula given in the instructions.
	 * 
	 * I also generate the "shadow table", which lists what each value is comprised of.
	 * It decomposes the formula into steps, and iteratively builds the shadow table 
	 * depending on each case.
	 */
	public String generateTable() {
		for(int i = 1; i < rows; i++) {
			
			for(int j = 0; j < columns; j++) {
				
				if(itemList[i-1].getWeight() > j) {
					table[i][j] = table[i-1][j];
					
					if(table[i-1][j] == 0)
						shadowTable[i][j] = "X";
					
					else
						shadowTable[i][j] = shadowTable[i-1][j];
				}
				
				else {
					int max1 = table[i-1][j];
					int max2 = itemList[i-1].getValue() + table[i-1][j-itemList[i-1].getWeight()];
					
					if(max1 > max2) {
						table[i][j] = max1;
						shadowTable[i][j] = shadowTable[i-1][j];
					}
					
					else {
						table[i][j] = max2;
						
						if(table[i-1][j-itemList[i-1].getWeight()] == 0) {
							shadowTable[i][j] = itemList[i-1].getName();
						}
						
						else {
						    shadowTable[i][j] = shadowTable[i-1][j-itemList[i-1].getWeight()] + " " + itemList[i-1].getName();
						}
					}
					
					//Original formula which is no longer needed.
					
					//table[i][j] = Math.max(table[i-1][j], itemList[i-1].getValue() + table[i-1][j-itemList[i-1].getWeight()]);
				}
			}
		}
		
		return Integer.toString(table[rows-1][columns-1]) + "\n" + shadowTable[rows-1][columns-1];
	}
	
	/**
	 * This prints the solution table.
	 */
	public void print() {
		
		//Generate and initialize a String array to hold the names of all item combinations.
		String[] solutionNames = new String[rows];
		
		for(int m = 0; m < rows; m++)
			solutionNames[m] = "";
		
		/* Prints empty corner, and generates capacity headings. */
		
		System.out.printf("%s" + "%" + itemNumber + "s","-"," "); 
		
		for(int k = 0; k < columns; k++) {
			System.out.print(" " + Integer.toString(k) + " ");
		}
		
		System.out.println(); // We are finished printing capacity row.
		
		/* Used to determine item name and spacing for row heading. @ indicates no items. */
		for(int i = 0; i < rows; i++) {
			
			//No items on the first row.
			if (i == 0) {
				System.out.printf("%s" + "%" + itemNumber + "s","@"," "); 							
			}
			
			//Loads solution names and prints them for the first table heading.
			else {
				for(int l = 0; l < i; l++) 
					solutionNames[i] += itemList[l].getName(); 
				
				System.out.print(solutionNames[i]);
				
				//Adds required spacing.
				int numSpaces = rows - i;
				
				System.out.printf("%" + numSpaces + "s", " "); 
			    
			}
			
			//Adds an extra space for cells with 1 item values. Assumes max value of 99 for entries.
			for(int j = 0; j < columns; j++) {
				
				if(table[i][j] < 10)
					System.out.print(" ");
				
				System.out.print(Integer.toString(table[i][j]) + " ");
			}
			System.out.println(); // Adds newline after each row of table is printed.
		}			
		
		//Prints solution value for all items.
		
		int solution = table[rows-1][columns - 1];
		System.out.println("\nThe solution for " + solutionNames[rows-1] + " has a value of " + Integer.toString(solution) + ".\n");
		
		//This prints the shadow table. Mostly for testing purposes.
		
		System.out.println("Shadow Table (shows steps to get each solution):\n");
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				System.out.print(shadowTable[i][j] + " " + "|" + " ");
			}
			System.out.println();
		}
		
		String shadowSolution = shadowTable[rows-1][columns-1];
		
		System.out.println("\nThe combination of items for the value is: " + shadowSolution + "\n");
		
		return;
	}
}
