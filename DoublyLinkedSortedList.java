/**
Programmer: Lucio Plancarte
Created:  16 Oct 2024
Description: Doubly Linked List

*/

public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface
{
	private HurricaneRowData data;
	private DoublyLinkedSortedList next;
	private DoublyLinkedSortedList prev;
	private static DoublyLinkedSortedList head = new DoublyLinkedSortedList();
	private static DoublyLinkedSortedList tail = new DoublyLinkedSortedList();

	//CONSTRUCTOR
	public DoublyLinkedSortedList(){
		this.data = null;
		this.next = null;
		this.prev = null;
	}


	//Get the value of the current DoublyLinkedSortedList
	public HurricaneRowData getValue(){
		return this.data;
	}

	//Return true if next is not null
	public boolean hasNext(){
		if(this.next != null)
			return true;
		return false;
	}

	//Set next to be the given DoublyLinkedSortedList
	public void setNext(DoublyLinkedSortedList next){
		this.next = next;
	}

	//Return a reference to the next DoublyLinkedSortedList
	public DoublyLinkedSortedList getNext(){
		return this.next;
	}

	//Return true if previous is not null
	public boolean hasPrevious(){
		if(this.prev != null)
			return true;
		return false;
	}
	
	//Set previous to be the given DoublyLinkedSortedList
	public void setPrevious(DoublyLinkedSortedList previous){
		this.prev = previous;
	}

	//Return a reference to the previous DoublyLinkedSortedList
	public DoublyLinkedSortedList getPrevious(){
		return this.prev;
	}

	//Return a reference to the first DoublyLinkedSortedList element in the list
	public DoublyLinkedSortedList getFirst(){
		return this.head;
	}
	
	//Return a reference to the last DoublyLinkedSortedList element in the list
	public DoublyLinkedSortedList getLast(){
		DoublyLinkedSortedList current = head;
		while(current.next != null)
			current = current.next;
		return current;
	}
	
	//Remove the DoublyLinkedSortedList element that has toRemove as its value
	public DoublyLinkedSortedList remove(HurricaneRowData toRemove){
		if(toRemove == null)
			return null;
		DoublyLinkedSortedList checker = this.head;

		while(checker.next != null){
			if(checker.data == toRemove){
				checker.prev.next = checker.next;
				checker.next.prev = checker.prev;
				return checker;
			}
			checker = checker.next;
		}
		return null;
	}
	
	//Insert a new DoublyLinkedSortedList element that has the given newValue in order in the list.
	public void insert(HurricaneRowData newValue){
		//Create new link
			DoublyLinkedSortedList link = new DoublyLinkedSortedList();
			link.data = newValue;

			//check if head is null
		if(head.data == null){
			head = link;
			//System.out.println("Head Added");
			//System.out.println(head.data.toString());
		}else{
			//set up pointer to head; get Ace Index from head to compare
			//to newAce
			DoublyLinkedSortedList current = head;
			int newAce = link.data.getAceIndex();
			int curAce = current.data.getAceIndex();

			if(newAce > curAce){ //place at front
				while(current.prev != null){
					current = current.prev;
				}
				current.prev = link;
				link.next = current;
				//set new head
				head = link;

			}else if(newAce < getLast().data.getAceIndex()){ //place at end
				while(current.next != null){
					current = current.next;
				}
				current.next = link;
				link.prev = current;

			}else{
				//place in the middle
				while(current.data.getAceIndex() > newAce){
					current = current.next;
				}
				//set links
//				System.out.println("Curr: "+current.data.toString());
//				System.out.println("Prev: "+current.prev.data.toString());	
				current.prev.next = link;
				link.prev = current.prev;
				
				current.prev = link;
				link.next = current;

			}
		}
	}


	//Debugging tool, tracking what was inserted
	public void printInsert(int newAce){
		System.out.println("newAce: "+newAce);
		System.out.println("Inserted");
	}
	
	//Return the entire list as a multi-line String
	@Override 
	public String toString(){
		String toConsole= "";
		String newLine;
		String description = "All data in order of Ace:\n";
		DoublyLinkedSortedList iteratorFwd = head;
		toConsole = toConsole.concat(description);
		String header = String.format(
		"%8s %8s %9s %8s %9s",
		"YEAR",
		"ACE",
		"TSTM",
		"ALL",
		"MAJ\n"
		);
		String underline = String.format(
		"%8s %8s %9s %8s %9s",
		"----",
		"---",
		"----",
		"---",
		"---\n"
		);
		toConsole = toConsole.concat(header);
		toConsole = toConsole.concat(underline);
		while(iteratorFwd != null){
			newLine = iteratorFwd.data.toString() + "\n";
			toConsole = toConsole.concat(newLine);
			iteratorFwd = iteratorFwd.next;
		}

		return toConsole;//iteratorFwd.data.toString();
	}

}
