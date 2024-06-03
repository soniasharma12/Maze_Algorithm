/*
 * Description: This class represents an edge of the graph
 * */

public class GraphEdge 
{
	// Variables 
	private GraphNode u, v;// these two nodes store the end points of the edge
	private int type;// stores the number of coins needed to open a door for type door 
	private String label;// stores the label of an edge (corridoor or door)
	
	// Methods 
	
	/*
	 * this method is the constructor which initializes the graph edge 
	 * */
	GraphEdge(GraphNode u, GraphNode v, int type, String label)
	{
		this.u = u;// set the first end point of the edge to the value passed within the method 
		this.v = v;// set the second end point to the value passed within the method 
		this.type = type;// set the type of the edge to the value passed within the method 
		this.label = label;// set the label of the edge to the value passed within the method 
	}
	
	/*
	 * this method returns the first end point of the edge 
	 * */
	GraphNode firstEndpoint() 
	{
		return u;
	}
	
	/*
	 * this method returns the second end point of the edge 
	 * */
	GraphNode secondEndpoint() 
	{
		return v;
	}
	
	/*
	 * this getter method returns the type of the edge
	 * */
	int getType() 
	{
		return type;
	}
	
	/*
	 * this setter method sets the type of the edge to the type value passed within the function
	 * */
	void setType(int newType) 
	{
		type = newType;
	}
	
	/*
	 * this getter method returns the label of the edge
	 * */
	String getLabel() 
	{
		return label;
	}
	
	/*
	 * this setter method sets the label of the edge to the label value passed within the function
	 * */
	void setLabel(String newLabel) 
	{
		label = newLabel;
	}
	
	

}
