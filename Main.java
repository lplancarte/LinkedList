/**
Programmer: Lucio Plancarte
Created: 09 SEP 2024
Modified: 10 SEP 2024
			Added Analyzer object to hold analyzed data
Modified: 20 Oct 2024
			Removed the use of ArrayList<HurricaneRowData> in favor
			of a doubly linked list. Removed the use of the Analyzer
			class.
Description: Reads in file 'ace.csv' to create ArrayList of HurricaneRowData
			 objects. Returns to terminal the year with MAX ACE VALUE. Also
			 creates text file named 'results.txt'.
Main.java
*/
import java.util.*;
import java.io.*;
import java.nio.file.*;
/**
Program creates HurricaneRowData objects by reading in data from a csv file.
These objects are then used to populate a Doubly Linked List using the
DoublyLinkedSortedList class. These objects are sorted when inserted.
A maximum index value is found and reported alongside the year.
These results are also printed to an output text file.

NOTE
A default file is provided as ace.csv but alternative files can be used by
providing the name of the file as an argument.

Utilized Classes:
HurricaneRowData - Objects of Hurricane Data
LLP removed: Analyzer - Holds Analyzed Data from ArrayList of Hurricane Row Data 
objects

@hurricaneList -ArrayList holds HurricaneRowData objects
@maxAceIndexYear - Analyzer object that holds analyzed data. uses hurricaneList
					for constructor.
@inputFileDefault - String name of input file to use if none is provided
					as argument in terminal
@outputFile - String name of file to use to output results.
*/

class Main{
	static private ArrayList<HurricaneRowData> hurricaneList = null;
	static private DoublyLinkedSortedList data = new DoublyLinkedSortedList();

	//static private Analyzer maxAceIndexYear = null;
	static private String inputFileDefault = "ace.csv";
	static private String outputFile = "results.txt";
	
	static public void main(String[] args){
		File inputFile = checkFile(args);
		if(inputFile == null){
			return;
		}else{
			processFile(inputFile);
		}
		//int year = findMaxAceIndexYear(hurricaneList);
		int year = findMaxAceIndexYear();
		System.out.println("Year with Maximum Ace Index via method:" + year);
		displayMaxYearToTerminal();
		System.out.println("Results saved as " +outputFile);
		printOutputFile();

		
		System.out.println(data.toString());
		System.out.println("Thank you. Goodbye\n");
		/**TESTING
		HurricaneRowData test1 = new HurricaneRowData(1970,100,1,1,1);
		HurricaneRowData test2 = new HurricaneRowData(1980,200,2,2,2);
		HurricaneRowData test3 = new HurricaneRowData(1990,300,3,3,3);
		HurricaneRowData test4 = new HurricaneRowData(2000,400,4,4,4);

		HurricaneRowData test5 = new HurricaneRowData(1975,150,1,1,1);
		HurricaneRowData test6 = new HurricaneRowData(1985,270,2,2,2);
		HurricaneRowData test7 = new HurricaneRowData(1996,350,3,3,3);
		HurricaneRowData test8 = new HurricaneRowData(2008,600,4,4,4);

		data.insert(test2);
		data.insert(test4);
		data.insert(test3);
		data.insert(test1);
		data.insert(test6);
		data.insert(test8);
		data.insert(test5);
		data.insert(test7);
		System.out.println(data.toString());*/
		

	}//end main()


	/**		checkFile(String[] args)
	*Checks that the file to analyze exists. The default is named 'ace.csv'
	*Has the capability of accepting user input for different csv files
	*if argument is detected in the terminal.
	*These custom csv files must have the same format as 'ace.csv'.
	@param args - Array of strings from the terminal used to search for file
				  can be empty
	@return inputFile - input file to be processed. Returns null for error.
	*/
	static private File checkFile(String[] args){
		File inputFile = null;
		Path inputFilePath =  null;
		try{
			if(args.length > 0){
				inputFilePath = Paths.get(args[0]);
				inputFile = new File(inputFilePath.toString());
				System.out.println("Checking "+inputFilePath.toString());
			}else{
				inputFile = new File(inputFileDefault); //default csv file
				System.out.println("No Arguments detected."+
 				"Analyzing default file: " + inputFile.getName());
			}
			//Check if the file exists
			if(!inputFile.canRead()){
				throw new FileNotFoundException("404: File not found."+
				"The file you are looking for, isn't here.\n");
			}
		}catch(Exception e){
			System.out.print("Something went wrong. Error "+
			e.getMessage()+"\nExiting.\n");
			return null;
		}
		return inputFile;
	}//end checkFile()

	/** 	processFile(File inputFile)
	@param inputFile -File to be processed; returned by checkFile()
	*It is assumed that this file has both:
	*1)A header line. 2)At least one row of data.
	*Iterates through the lines of a csv to create HurricaneRowData objects
	*These objects are then placed into a Doubly Linked Sorted List. A maximum
	*Ace index position is also found and held by the class' Analyzer object.
	*/
	static private void processFile(File inputFile){
		String line = "";
		//hurricaneList = new ArrayList<HurricaneRowData>();
		HurricaneRowData hrd = null;
		try{
			Scanner reader = new Scanner(inputFile);
			line = reader.nextLine(); //throw away first line (header)
			while(reader.hasNextLine()){
				line = reader.nextLine();

				//split row into col
				if(line.isEmpty()){continue;};
				String[] lineData = line.split(",");
				//Once split, construct HRD object and place into ArrayList
				hrd = new HurricaneRowData(
						Integer.parseInt(lineData[0]),
						Integer.parseInt(lineData[1]),
						Integer.parseInt(lineData[2]),
						Integer.parseInt(lineData[3]),
						Integer.parseInt(lineData[4])
					);
				//System.out.println(hrd.toString()); //Debug line
				//hurricaneList.add(hrd); //add object to list
				data.insert(hrd); //add object to DoublyLinkedSortedList
			}
			//maxAceIndexYear = new Analyzer(hurricaneList); //Analyze data
		}catch(Exception e){};

	}//end processFile()

	/** 	displayMaxYearToTerminal()
	*displays the Maximum Ace Index and corresponding year.
	*Returns the first item in sorted list from DoublySortedList class
	*display results of maximum ace index and corresponding year.
	*/
	static private void displayMaxYearToTerminal(){
		DoublyLinkedSortedList pointerLink = data.getFirst();
		HurricaneRowData maxData = pointerLink.getValue();
		int maxYear = maxData.getYear();
		int maxAceIndex = maxData.getAceIndex();

		System.out.println("\nResults:\nMax Ace Value Year: "+
			maxYear
		);
		System.out.println("Max Ace Value: "+
			maxAceIndex
		);
	}

	/**		printOutputFile()
	*prints the results of the maximum ace index and associated year to the
	*output file. Uses PrintWriter and FileWriter and DoublyLinkedSortedList 
	*toString() method.
	*/
	static private void printOutputFile(){
		try{
			PrintWriter out = new PrintWriter(new FileWriter(outputFile));
			out.print("Year with Maximum Ace Index: " +
				data.getFirst().getValue().getYear()+ "\n"
			);
			//out.print(
			//	hurricaneList.
			//	get(maxAceIndexYear.getMaxAceIndexPo1sition()).
			//	toString()
			//	);
			out.print(data.toString());
			out.close();
		}catch(Exception e){}
	}

	/**REQUIRED		findMaxAceIndexYear()
	*Uses DoublyLinkedSortedList class method getFirst() to find max ace index
	*Does not use class member object maxAceIndexYear.
	@returns - the year of the HurricaneRowData object with the highest
			  ace index in an arraylist of HRD objects. Creates a new Analyzer
			 object.
	*/
	static private int findMaxAceIndexYear(){
		//Analyzer maxYear = new Analyzer(hrd);
		DoublyLinkedSortedList link = data.getFirst();
		return link.getValue().getYear();
	}//end findMaxAceIndexYear()



}//end class

