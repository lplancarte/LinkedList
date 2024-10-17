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
		
		if(head.data == null){
			head.data = newValue;
			head.next = tail;
			System.out.println("HEAD ADDED");
		}
		
		if(tail.data == null){
			tail.data = newValue;
			tail.prev = head;
			System.out.println("TAIL ADDED");
		}else{
			
			DoublyLinkedSortedList link = new DoublyLinkedSortedList();
			//put in back; new tail
			link.data = newValue;

			tail.next = link;
			link.prev = tail;
			tail = link; //set new tail;
			System.out.println("INSERTED BACK");
		}
			
		
	/*
		//if list is empty set newValue as head and tail
		if(head.data == null){
			head.data = newValue;
			tail.data = newValue;
			head.next = tail;
			tail.prev = head;
			head.prev = null;
			tail.next = null;
			System.out.println("HEAD ADDED");
		}else{
	 		DoublyLinkedSortedList link = new DoublyLinkedSortedList();
			//check if newValue had lower ace index than head
			if(head.data.getAceIndex() > newValue.getAceIndex()){
				//set link as the new head 
				link.next = head;
				head.prev = link;
				link.prev = null;
				head = link;
			}else{
				//ace value is higher than head; iterate up starting at the head
				DoublyLinkedSortedList temp = head;
				while(temp.next!= null){
					if(temp.data.getAceIndex() < newValue.getAceIndex() ){
						//when our value is bigger we set to the previous

						link.next = temp;
						temp.prev = link;
						link.prev = temp.prev.next;
						temp.prev.next = link;
						link = temp;
						link.data = newValue;
					}
					temp = temp.next;
				}
				//add to end of list, new tail
				link.prev = tail;
				tail.next = link;

				link.next = null;
				tail = temp; //set new tail
			
				System.out.println("TAIL ADDED"); 
			}
			System.out.println("INSERTED"); 
		}*/
		
	}
	
	//Return the entire list as a multi-line String
	@Override 
	public String toString(){
		String toConsole= "";
		String newLine;
		String header = "All data in order of Ace:\n";
		DoublyLinkedSortedList iteratorFwd = head;
		toConsole = toConsole.concat(header);
		while(iteratorFwd.next != null){
			newLine = iteratorFwd.data.toString() + "\n";
			toConsole = toConsole.concat(newLine);
			iteratorFwd = iteratorFwd.next;
		}
		return toConsole;//iteratorFwd.data.toString();
	}

}
