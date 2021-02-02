/**
 * Wesley Howe 300146506
 */
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.FileWriter;

/* TODO
 * verification to ensure file has correct structure. (not required)
 */
public class Main {

	public static void main(String[] args) {

		/* Used to test command line inputs without building program. */
		
		/*
		args = new String[2];
		args[0] = "p1.txt";
		args[1] = "F";
		*/
		
		String solution = "";
		
		//Verifies correct input to command line.
		if (args.length !=2 || !args[1].toUpperCase().equals("F") && !args[1].toUpperCase().equals("D"))
		{
			System.out.println("Usage: You must enter a filename (ie: p1.txt), followed by 'D' for dynamic programming, or 'F' for brute force.");
			return;
		}
		
		//Initializes variables that will be read from file and used in the problem.
		int itemNumber = 0;
		int backpackCapacity = 0;
		
		//We don't know how many items there are, so initially put them in an ArrayList.
		ArrayList<Item> arr = new ArrayList<Item>();
		
		// Initialize the object that will hold all of the needed data.
		Knapsack currentSet = new Knapsack();
		
		// Try to load file, prints error and returns if this fails.
		 try {
		      File inputFile = new File(args[0]);
		      Scanner inputScan = new Scanner(inputFile);
		      
		      int line = 0; // used to keep track of where we are in the read.
		      while (inputScan.hasNextLine()) {
		        String lineRead = inputScan.nextLine();
		        
		        /* This is used for cases where we don't read an object. If it's
		         * the first line, we know it's the number of items. If it's another
		         * line that isn't trailing whitespace, we know it's the backpack
		         * capacity.
		         */
		        if(lineRead.length() <= 2) {
		        	if(line == 0) {
		        		itemNumber = Integer.parseInt(lineRead.trim());
		        	}
		        	
		        	else if(lineRead.trim().equals("") == false)
		        		backpackCapacity = Integer.parseInt(lineRead);
		        }
		        
		        /*
		         * This is used to pull tokens out of the object lines. They are read
		         * in order, with the first token being the item name, the second token
		         * being the value, and the third being the weight. Spaces used as
		         * delimiters, as per the default behaviour.
		         */
		        else {
		        	int tokenCounter = 0; // Keeps track of position in read tokens.
		        	Item curItem = new Item(); // Item is generated on the fly.
		            StringTokenizer st = new StringTokenizer(lineRead);
		            while (st.hasMoreTokens()) {
		                if(tokenCounter == 0)
		                	curItem.setName(st.nextToken());
		                
		                else if(tokenCounter == 1)
		                	curItem.setValue(Integer.parseInt(st.nextToken()));
		                
		                else
		                	curItem.setWeight(Integer.parseInt(st.nextToken()));
		                
		                tokenCounter++;
		            }
		            
		            arr.add(curItem); // Adds finished item to the ArrayLisy.
		            line++;
		        }
		      
		      /* Converts ArrayList to array so it can be loaded and accessed easily. */
		      Item[] arr2 = new Item[arr.size()];
		      arr2 = arr.toArray(arr2);
		      
		      /* Needed data to solve the problem is organized into Knapsack class. */
		      currentSet = new Knapsack(itemNumber, backpackCapacity, arr2);
		     } 
		      
		     inputScan.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("Error: Input file not found.");
			      e.printStackTrace();
			      return;
			    }
		 
		 /* Show the loaded problem on the console. I overloaded the print methods in
		  * the Item and Knapsack classes to make this easier. */
		 System.out.println("Problem Loaded. We are trying to solve a problem with:\n");
		 currentSet.print();
		 
		 if(args[1].toUpperCase().equals("F")) {
		 System.out.println("Attempting to brute force solution...\n\nResults:\n");		 
		 solution = currentSet.bruteForce();
		 }
		 
		 else {
			 System.out.println("Attempting to solve with dynamic programming...\n");
			 solution = currentSet.dynamicProgramming();			 
		 }
		 
		 /* This section writes the output file. */
		    try {
		    	//strips the extension off the input file name and adds .sol extension
		    	String newName = args[0].substring(0, args[0].length()-3) + "sol";
		    	File outFile = new File(newName);
		    	
		        FileWriter outWrite = new FileWriter(outFile);
		        
		        outWrite.write(solution);
		        outWrite.close();
		        
		        System.out.println("Solution file " + newName + " written. Exiting.");
		        
		      } 
		    
		    catch (IOException e) {
		        System.out.println("Could not write output file.");
		        e.printStackTrace();
		        return;
		      }
		    
		 return;
	}

}
