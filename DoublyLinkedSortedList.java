/**
Programmer: Lucio Plancarte
Created:  16 Oct 2024
Description: Doubly Linked List

*/

public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface
{
	HurricaneRowData data;
	DoublyLinkedSortedList next;
	DoublyLinkedSortedList prev;
	DoublyLinkedSortedList head;
	DoublyLinkedSortedList tail;
	//CONSTRUCTOR
	public DoublyLinkedSortedList(){
		this.data = null;
		this.head = null;
		this.tail = null;
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
		return this.tail;
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
		if(newValue == null)
			return;
		DoublyLinkedSortedList temp = new DoublyLinkedSortedList();
		temp.data = newValue;
		//check if newValue had lower ace index than head
		if(head.data.getAceIndex() > temp.data.getAceIndex()){
			temp.next = this.head;
			this.head.prev = temp;
			temp.prev = null;
			this.head = temp;
		}else{
			//ace value is higher than head; iterate up
			temp = this.head;
			while(temp.data != null){
				if(temp.next.data.getAceIndex() >= temp.data.getAceIndex()){
					//link to next link
					temp.next = temp.next.prev;
					temp.next.prev = temp;
					//link to prev link
					temp.prev = temp.prev.next;
					temp.prev.next = temp;
				}
				temp = temp.next;
			}
		}
	}
	
	//Return the entire list as a multi-line String
	public String toString(){
		String toConsole= null;
		String newLine;
		String header = "All data in order of Ace:\n";
		DoublyLinkedSortedList iteratorFwd = this.head;
		toConsole.concat(header);
		while(iteratorFwd.hasNext()){
			newLine = iteratorFwd.data.toString() + "\n";
			toConsole.concat(newLine);
		}
		return toConsole;
	}

}
