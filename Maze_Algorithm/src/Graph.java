/*
 * Description: This class represents the graph, which is an adjacency matrix 
 * */

// Import required modules 
import java.util.Iterator;
import java.util.ArrayList;

public class Graph implements GraphADT// implement GraphADT interface 
{
	private int totalNodes;// stores the total number of nodes in the graph
	private GraphEdge[][] adjMatrix;// adjacency matrix (2d array) representing edges between nodes
	private GraphNode[] nodes;// array to store the graph nodes 

	/*
	 * this is the constructor method which initializes an empty graph with n nodes and no edges 
	 * */
	Graph(int n)
	{
		this.totalNodes = n;// set the number of nodes to the value passed within the function
		this.adjMatrix = new GraphEdge[n][n];// create an adjacency matrix with dimensions n x n
		this.nodes = new GraphNode[n];// store the graph's nodes 

		for(int i = 0; i<n; i++)// loop through all the nodes (0 to n-1), where i represents the nodes name
		{
			nodes[i] = new GraphNode(i);// create a new graph node at specified index and store it in the graph's nodes 
		}
	}

	/*
	 * this method adds an edge between two nodes (nodeu and nodev) with a label and type specified
	 * */
	@Override
	public void insertEdge(GraphNode nodeu, GraphNode nodev, int type, String label) throws GraphException
	{
		// if nodeu is valid (if the name is valid) and nodev is valid and nodeu's name isnt equal to nodev's name and the edge doesnt already exist
		if(nodeValid(nodeu) && nodeValid(nodev) && nodeu.getName()!=nodev.getName()&& !edgeValid(nodeu, nodev))
		{
			adjMatrix[nodeu.getName()][nodev.getName()] = new GraphEdge(nodeu, nodev, type, label);// create an edge between nodeu and nodev 
			adjMatrix[nodev.getName()][nodeu.getName()] = new GraphEdge(nodev, nodeu, type, label);// create an edge between ndev and nodeu (two times since undirected graph)
		}

		else// if the edge already exists or cannot be inserted, throw a graph exception
		{
			throw new GraphException("error: cannot insert edge"); 
		}
	}

	/*
	 * this method returns the graph node with the name passed within the fuction (u)
	 * */
	@Override
	public GraphNode getNode(int u) throws GraphException 
	{
		if(u >=0 && u < totalNodes)// if the name passed is valid; the name is an integer from 0 to n-1
		{
			return nodes[u];// return the graph node with index u in the graph nodes array
		}
		
		else // if node with this name doesn't exist, throw a graph exception
		{
			throw new GraphException("errror: cannot get node");
		}
	}

	/*
	 * this method returns a Java iterator storing the edges incident on the node passed within the function (u)
	 * */
	@Override
	public Iterator incidentEdges(GraphNode u) throws GraphException
	{
		// TODO Auto-generated method stub
		if(nodeValid(u))// if the node is valid, run the following condition
		{
			ArrayList<GraphEdge> incidentEdgesList = new ArrayList<>();// create an array list which will store the incident edges 
			
			for(int i = 0; i < adjMatrix[u.getName()].length; i++)// traverse through the adjacency matrix to look for the incident edges 
			{
				if(adjMatrix[u.getName()][i]!=null)// if theres an edge from node u and the node at index i
				{
					incidentEdgesList.add(adjMatrix[u.getName()][i]);// add the edge between u and i to the list 
				}
			}
			return incidentEdgesList.iterator();// return the iterator for the incident edges
		}
		
		else// else if the node name provided isnt in the graph, throw a graph exception
		{
			throw new GraphException("errror: cannot get iterator");
		}
	}

	/*
	 * this method returns the edge between node u and node v
	 * */
	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException 
	{
		if(nodeValid(u) && nodeValid(v) && edgeValid(u,v) && areAdjacent(u,v))// if both nodes are valid and adjacent to eachother, run the following statement
		{
			return adjMatrix[u.getName()][v.getName()];// return the edge between u and v
		}
		
		else// else if none of the above conditions are true, throw a graph exception
		{
			throw new GraphException("errror: cannot get edge ");
		}
	}

	/*
	 * this method returns true if nodes u and v are adjacent to each other 
	 * */
	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException 
	{
		if(nodeValid(u) && nodeValid(v))// if both nodes are valid, run the following
		{
			if (!edgeValid(u,v))// if there doesnt exist an edge between the two nodes, return false
			{
				return false;
			}
			
			else// else if there exists an edge between the two nodes, return true 
			{
				return true;
			}
		}
		
		else// else if any of the nodes arent valid, throw a graph exception
		{
			throw new GraphException("errror: cannot check if adjacent");
		}

	}


	// Helper functions

	/*
	 * this is a helper function which returns true if a node's name is valid or not 
	 * */
	private boolean nodeValid(GraphNode n) 
	{
		int nameOfNode = n.getName();// store the name of the node within a variable
		if(nameOfNode >=0 && nameOfNode < totalNodes)// if the name of the node is between 0 to n-1, return true 
		{
			return true;
		}
		else // else if the node's name is less than 0 or greater than or equal to n-1, return false
		{
			return false;
		}
	}

	/*
	 * this is a helper function which returns true if there's a valid edge between u and v
	 * */
	private boolean edgeValid(GraphNode u, GraphNode v) 
	{
		if(adjMatrix[u.getName()][v.getName()]!=null || adjMatrix[v.getName()][u.getName()] != null)// 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}


}
