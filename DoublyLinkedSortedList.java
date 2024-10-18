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
		//Create new link
			DoublyLinkedSortedList link = new DoublyLinkedSortedList();
			link.data = newValue;
		//}

		//Looking at a solution on geeksforgeeks.org o
		/** check for when a copy of the head's next is null
			this would indicate that we have reached a tail.
			The inverse would be true in finding a head where its
			prev is null.
		*/
		//check if head is null
		if(head.data == null){
			head = link;
			System.out.println("Head Added");
			System.out.println(head.data.toString());
		}else{
			//Scroll to the end of the list to add
			DoublyLinkedSortedList current = head;
			while(current.next != null){
				current = current.next;
			}
			//head sets itself as the next node 
			//if head was by itself

			//we are now at the tail
			current.next = link; //start setting up our 1st link
			link.prev = current; //end setting up our 2nd link
			System.out.println("Inserted");
			System.out.println(link.data.toString());
			/**	recap: we check to see if we have a list, by checking
				for a head. if one is not found, set it as a new object.
				(this lives on the heap?)
				we then iterate using a temporary variable named current
				(lives on the stack? and points to our object in the list)
				which will check the next variable for null, indicating the
				tail. This is then  set as our new tail, by setting a link to
				the object in the list
			*/
			
		}
		/*
		if(head.data == null){
			head.data = newValue;
			head.next = tail;
			System.out.println("head added");
			System.out.println(head.data.toString());
		}

		if(tail.data == null){
			tail.data = newValue;
			tail.prev = head;
			System.out.println("tail added");
			System.out.println(tail.data.toString());
		}else
		{
			//Doublylinkedsortedlist link = new Doublylinkedsortedlist();
			//put in back; new tail
			link.data = newValue; //set data value
			link.prev = tail;
			tail.next = link;

			tail = link; //set new tail;

			System.out.println("inserted back");
			System.out.println(tail.data.toString());
		}

		*/
	}
	
	//Return the entire list as a multi-line String
	@Override 
	public String toString(){
		String toConsole= "";
		String newLine;
		String header = "All data in order of Ace:\n";
		DoublyLinkedSortedList iteratorFwd = head;
		toConsole = toConsole.concat(header);
		while(iteratorFwd != null){
			newLine = iteratorFwd.data.toString() + "\n";
			toConsole = toConsole.concat(newLine);
			iteratorFwd = iteratorFwd.next;
		}

		return toConsole;//iteratorFwd.data.toString();
	}

}
