/*
 * Description: This class represents the maze. An object of the class Graph will be used to store the maze and to find a solution for it
 * */

// Import required modules 
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;

public class Maze 
{
	// Variables
	private Graph graph; // stores the graph 						
	private int width, length, k, u, v;// width and length are the dimensions of the graph, k will store the number of coins program has available to open maze doors
										//	and u and v represent the start and end node within maze
	private Stack<GraphNode> p;// stores the path of the maze 

	/*
	 * this method is the costructor which constructs the maze though reading and processing the input file 
	 * */
	public Maze(String inputFile) throws MazeException 
	{
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));// read the input file 

			input.readLine();// read the first like which is the scale factor for the maze (not used in program)
			width = Integer.parseInt(input.readLine());// store the width of the maze, which is the 2nd line within input file 
			length = Integer.parseInt(input.readLine());// store the length of the maze, which is the 3rd line within input file
			k = Integer.parseInt(input.readLine()); // store the number coins avaiable, which is the 4th line within input file

			graph = new Graph(width * length);// create a graph with dimensions width and length

			String currentLine;// stores the String representation line we're reading within the input file which will form the maze
			int counterH=0;// represents the counter for horizontal position in the file
			int counterV=0;// represents the counter for vertical position in the file
			int index = 0;// represents the counter for the current line index in the maze
			
			currentLine= input.readLine();// read the first line of the maze (after the first 4 lines)

			while(currentLine!=null)// traverse through each line in the input file to construct the graph
			{
				if(currentLine.length() != 2*width-1)// checks if each line in the file (except the first 4) have the same length 
				{
					throw new MazeException("error: invalid format");// if not, throw an error
				}
				// Horizontal Lines 
				if (index%2==0)// if the index of the current line is even, run the following conditions
				{
					for (int i = 0; i < currentLine.length(); i++)// traverse through each character within the line
					{
						char character = currentLine.charAt(i);// store the current character within the variable

						if (character == 's')// if the character is s, indicating the entrance of the maze
						{
							u = counterH;// set the starting position in the horizontal direction
							counterH++;// increment the horizontal counter
						} 
						
						else if (character== 'x')// else if the character is x, indicating the exit of the maze
						{
							v = counterH;// set the ending position in the horizontal direction
							counterH++;// increment the horizontal counter
						} 
						
						else if (character == 'o')// else if the character is o, indicating a room in the maze
						{
							counterH++;// increment the horizontal counter
						} 
						
						else if (character== 'c')// else if the character is c, indicating a corridoor in the maze
						{
							graph.insertEdge(graph.getNode(counterH - 1), graph.getNode(counterH), 0, "corridor");// insert a corridor edge between the current and next horizontal nodes
						}
						
						else if (character == 'w')// else if the character is w, indicating a wall in the maze
						{
							// dont do anything
						}
						
						else if (Character.isDigit(character))// else if the character is a digit, indicating a door in the maze
						{
							
							graph.insertEdge(graph.getNode(counterH - 1), graph.getNode(counterH), Character.getNumericValue(character), "door");// insert a door edge between the current and next horizontal nodes with a value specified by the digit
						}
					}
				}

				// Vertical Lines
				if(index%2 ==1)// if the index of the current line is odd, run the following conditions
				{
					for (int j = 0; j< currentLine.length(); j++)// traverse through each character within the line
					{
						char character = currentLine.charAt(j);// store the current character within the variable

						if (character == 'c')// if the character is c, indicating a corridoor in the maze
						{
							graph.insertEdge(graph.getNode(counterV), graph.getNode(counterV +width), 0, "corridor");// insert a corridor edge between the current vertical and next vertical nodes
						} 
						
						else if (character== 'w')// else if the character is w, indicating a wall in the maze
						{
							// dont do anything
						} 
						
						else if (Character.isDigit(character))// else if the character is a digit, indicating a door in the maze
						{
							graph.insertEdge(graph.getNode(counterV), graph.getNode(counterV +width), Character.getNumericValue(character), "door");// Insert a door edge between the current vertical and next vertical nodes with a value specified by the digit
						} 

						if(j%2==0)// if the current character's index is even, increment the vertical counter to ensure vertical counter is only incremented every other character in the line
						{
							counterV++;
						}
					}
				}
				
				index++;// increment the lines index
				currentLine= input.readLine();// read the next line fo the maze
			}

		} 

		catch (Exception e)// if file does not exist or teh format of the input file is incorrect, throw a maze exception
		{
			throw new MazeException("error: cannot create maze");
		}
	}
	
	/*
	 * this getter method returns a reference to the Graph object representing the maze
	 * */
	public Graph getGraph() throws MazeException
	{
		if(graph!=null)// if graph is not null, return the reference to the Graph object representing the maze
		{
			return graph;
		}
		
		else// else if the graph is null, throw a maze exception error 
		{
			throw new MazeException("error: cannot return graph");
		}
	}
	
	/*
	 * this method returns a java Iterator containing the nodes of the path from the entrance to the exit of the maze, if such a path exists
	 * */
	public Iterator solve() throws GraphException
	{
		p = new Stack<>();// intialize  the path 
		GraphNode startNode = graph.getNode(u);// store the starting node of the graph based on u
		GraphNode endNode = graph.getNode(v);// store the ending node of the graph based on v

		dfs(startNode, endNode, k);// perform a depth first search to look for a path from the starting node to the ending node with k number of coins (which is the number of coins avaiable)

		if (!p.isEmpty())// if the path stack is not empty, return the iterator over the path
		{
			return p.iterator();
		} 

		else// else if the path is empty (no valid path), return null
		{
			return null; 
		}
	}

	// Helper Functions
	
	/*
	 * this helper function performs recursion to find a path in the maze from s to e with coinsUsed number of coins
	 * */
	private boolean dfs(GraphNode s, GraphNode e ,  int coinsUsed) throws GraphException 
	{
		p.push(s);// add the current node to the path 

		if (s.getName() == e.getName())// the path is found if if the current node is the destination, and thus return true 
		{
			return true;
		}

		s.mark(true);// mark the node

		Iterator<GraphEdge> incidentEdges = graph.incidentEdges(s);// get the incident edges to the node 

		while (incidentEdges.hasNext())// traverse through the incident edges 
		{
			GraphEdge nextEdge = incidentEdges.next();// get the next edge 
			GraphNode nextNode = nextEdge.secondEndpoint();// along the edge, get the next node 

			if (!nextNode.isMarked())// if the next node isn't mark, run the following function
			{
				int updatedCoinsUsed = coinsUsed;// store a temporary variable with the current number coins avaiable 
				
				if (nextEdge.getLabel().equals("door")&& (updatedCoinsUsed  = updatedCoinsUsed - nextEdge.getType())< 0)// if the edge is a door and there aren't sufficient number of coins to open the door 
				{
					continue;// skip this path 
				}
				
				if (dfs(nextNode, e, updatedCoinsUsed ) == true)// call the function recursively and pass the nextNode as the start, pass the end node and pass the updated number of coins avaiable and if the path is found, return true
				{
					return true;// return true
				}
			}
		}
		
		p.pop();// remove the node from the path
		return false;// if no valid path is found from node, return false

	}

}
