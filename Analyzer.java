/**
Programmer: Lucio Plancarte
Created: 10 SEP 2024
Description: Analyzes HurricaneRowData objects in an Arraylist. Looks for
			the maximum ace value and the year it happened.
*/
import java.util.*;


/**
Analyzes data from ArrayList of HurricanRowData objects. 
Constructor sets
	 Maximum Ace Index ArrayList Position
	 Associated Year, and Ace Index
Public getters allow for quick access to analyzed data.


*/
class Analyzer{

	ArrayList<HurricaneRowData> hrd = null;
	int maxAceIndexPosition = 0;
	int maxAceIndexYear = 0;
	int maxAceIndex = 0;

	public Analyzer(ArrayList<HurricaneRowData> hurricanerowdata){
		hrd = hurricanerowdata;
		setMaxAceIndexPosition();
		setMaxAceIndexYear();
		setMaxAceIndex();
	}//End Constructor


	//SETTERS
	private void setMaxAceIndexPosition(){
		//To find max, iteratate through arraylist and compare adjacent +1
		//Assume first is the largest
		int largestIndex = 0;
		for(int i=1; i< hrd.size()-1; i++){
			if(hrd.get(largestIndex).getAceIndex() < hrd.get(i).getAceIndex()){
				largestIndex = i;
			}
		}
		maxAceIndexPosition = largestIndex;
	}

	private void setMaxAceIndexYear(){
		maxAceIndexYear = hrd.get(maxAceIndexPosition).getYear();
	}

	private void setMaxAceIndex(){
		maxAceIndex = hrd.get(maxAceIndexPosition).getAceIndex();
	}

	//GETTERS
	public int getMaxAceIndexPosition(){
		return maxAceIndexPosition;
	}

	public int getMaxAceIndexYear(){
		return maxAceIndexYear;
	}

	public int getMaxAceIndex(){
		return maxAceIndex;
	}


}
