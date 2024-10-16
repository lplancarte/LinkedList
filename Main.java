/**
Programmer: Lucio Plancarte
Created: 09 SEP 2024
Modified: 10 SEP 2024
			Added Analyzer object to hold analyzed data
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
These objects are then used to populate an ArrayList. A maximum index value 
is found and reported alongside the year. These results are also printed to an 
output text file.
NOTE
A default file is provided as ace.csv but alternative files can be used by
providing the name of the file as an argument.

Utilized Classes:
HurricaneRowData - Objects of Hurricane Data
Analyzer - Holds Analyzed Data from ArrayList of Hurricane Row Data objects

@hurricaneList -ArrayList holds HurricaneRowData objects
@maxAceIndexYear - Analyzer object that holds analyzed data. uses hurricaneList
					for constructor.
@inputFileDefault - String name of input file to use if none is provided
					as argument in terminal
@outputFile - String name of file to use to output results.
*/

class Main{
	static private ArrayList<HurricaneRowData> hurricaneList = null;
	static private Analyzer maxAceIndexYear = null;
	static private String inputFileDefault = "ace.csv";
	static private String outputFile = "results.txt";
	
	static public void main(String[] args){
		File inputFile = checkFile(args);
		if(inputFile == null){
			return;
		}else{
			processFile(inputFile);
		}
		int year = findMaxAceIndexYear(hurricaneList);
		System.out.println("Year with Maximum Ace Index via method:" + year);
		displayMaxYearToTerminal();
		System.out.println("Results saved as " +outputFile);
		printOutputFile();
		System.out.println("Thank you. Goodbye\n");

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
	*These objects are then placed into an ArrayList. A maximum Ace index
	*position is also found and held by the class' Analyzer object.
	*/
	static private void processFile(File inputFile){
		String line = "";
		hurricaneList = new ArrayList<HurricaneRowData>();
		HurricaneRowData hrd = null;
		try{
			Scanner reader = new Scanner(inputFile);
			line = reader.nextLine(); //throw away first line (header)
			while(reader.hasNextLine()){
				line = reader.nextLine();

				//split row into col
				if(line.isEmpty()){continue;};
				String[] data = line.split(",");
				//Once split, construct HRD object and place into ArrayList
				hrd = new HurricaneRowData(
						Integer.parseInt(data[0]),
						Integer.parseInt(data[1]),
						Integer.parseInt(data[2]),
						Integer.parseInt(data[3]),
						Integer.parseInt(data[4])
					);
				//System.out.println(hrd.toString()); //Debug line
				hurricaneList.add(hrd); //add object to list
			}
			maxAceIndexYear = new Analyzer(hurricaneList); //Analyze data
		}catch(Exception e){};

	}//end processFile()

	/** 	displayMaxYearToTerminal()
	*displays the Maximum Ace Index and corresponding year. Uses class member
	*Analyzer object maxAceIndexYear to display analyzed data.
	*/
	static private void displayMaxYearToTerminal(){
		System.out.println("\nResults:\nMax Ace Value Year: "+
			maxAceIndexYear.getMaxAceIndexYear()
		);
		System.out.println("Max Ace Value: "+ maxAceIndexYear.getMaxAceIndex()
		);
	}

	/**		printOutputFile()
	*prints the results of the maximum ace index and associated year to the
	*output file. Uses PrintWriter and FileWriter and HurricaneRowData 
	*toString() method.
	*/
	static private void printOutputFile(){
		try{
			PrintWriter out = new PrintWriter(new FileWriter(outputFile));
			out.print("Year with Maximum Ace Index\n");
			out.print(
				hurricaneList.
				get(maxAceIndexYear.getMaxAceIndexPosition()).
				toString()
				);
			out.close();
		}catch(Exception e){}
	}

	/**REQUIRED		findMaxAceIndexYear(ArrayList<HurricaneRowData> hrd)
	*Takes in an arraylist of hurricane row data. Creates and uses a new
	*Analyzer object to find the maximum ace value year within that arraylist.
	*Does not use class member object maxAceIndexYear.
	@param hrd - ArrayList of HurricanRowData objects
	@returns - the year of the HurricaneRowData object with the highest
			  ace index in an arraylist of HRD objects. Creates a new Analyzer
			 object.
	*/
	static private int findMaxAceIndexYear(ArrayList<HurricaneRowData> hrd){
		Analyzer maxYear = new Analyzer(hrd);
		return maxYear.getMaxAceIndexYear();
	}//end findMaxAceIndexYear()



}//end class

