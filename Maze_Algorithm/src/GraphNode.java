/*
 * Description: This class represent a node of the graph.
 * */

public class GraphNode 
{
	// Variables
	private int name;// node's name with values 0 to n-1
	private boolean mark;// stores boolean value if node is marked or not (true or false)
	
	
	// Methods 
	
	/*
	 * this method is the constructor which initializes the name and mark of the node
	 * */
	GraphNode(int name)
	{
		this.name = name;// set the nodes name to the name passed within the function
		this.mark = false;// mark the node initially to false
	}
	
	/*
	 * this method marks the node with the specified value
	 * */
	void mark(boolean mark)
	{
		this.mark = mark;// set the value of mark by the value passed within the function
	}
	
	/*
	 * this method returns the value with which the node has been marked
	 * */
	boolean isMarked() 
	{
		return mark;
	}
	
	/*
	 * this getter method returns the name of the node
	 * */
	int getName() 
	{
		return name;
	}

}
