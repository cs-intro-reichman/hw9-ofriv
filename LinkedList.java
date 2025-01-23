/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		Node current = this.first;
		for(int i = 0; i < index; i++)
		{
			current = current.next;
		}
		return current;
	}
	
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		Node n = new Node(block);
		Node current = this.first;
		if(this.size == 0)
		{
			this.first = n;
			this.last = n;
			this.size++;
		}
		else if(index == 0)
		{
			addFirst(block);
		}
		else if(index == this.size)
		{
			addLast(block);
		}
		else{
		for(int i = 0; i < index - 1; i++)
		{
			current = current.next;
		}
		n.next = current.next;
		current.next = n;
		this.size++;
		}
	}


	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node n = new Node(block);
		if(this.getSize() == 0)
		{
			this.first = n;
			this.last = n;
			this.size++;
		}
		else{
		this.last.next = n;
		this.last = n;
		this.size++;
		}
	}

	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node n = new Node(block);
		if(this.getSize() == 0)
		{
			this.first = n;
			this.last = n;
			this.size++;
		}
		else{
		n.next = this.getFirst();
		this.first = n;
		this.size++;
		}
	}


	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		MemoryBlock m = getNode(index).block;
		return m;
	}	


	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		int counter = 0;
		Node current = this.first;
		while(current != null)
		{
			if(current.block == block)
			{
				return counter;
			}
			current = current.next;
			counter++;
		}
		return -1;
	}


	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		Node current = this.first;
		if(this.getFirst().equals(node))
		{
			this.first = this.getFirst().next;
			if(this.getFirst() == null)
			{
				this.last = null;
			}
		}
		else if(node.equals(last))
		{
			for(int i = 0; i < this.getSize() - 2; i++)
			{
				current = current.next;
			}
			current.next = null;
			this.last = current;
		}
		else{
			while(current.next != node)
			{
				current = current.next;
			}
			current.next = current.next.next;
		}
		this.size--;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		Node current = this.getFirst();
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		else if(index == 0)
		{
			this.first = this.getFirst().next;
			if(this.getFirst() == null)
			{
				this.last = null;
			}
		}
		else if(index == this.getSize()-1)
		{
			for(int i = 0; i < index; i++)
			{
				current = current.next;
			}
			current.next = null;
			this.last = current;
		}
		else{
		int counter = 0;
		while(counter != index)
		{
			current = current.next;
			counter++;
		}
		current.next = current.next.next;
		}
		this.size--;
		
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		//// Write your code here
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		//// Replace the following statement with your code
		return "";
	}
}