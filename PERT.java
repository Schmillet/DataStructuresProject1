/* Starter code for PERT algorithm (Project 4)
 * @author
 */

// change dsa to your netid
package dsa;

import  dsa.Graph;
import  dsa.Graph.Vertex;
import  dsa.Graph.Edge;
import  dsa.Graph.GraphAlgorithm;
import  dsa.Graph.Factory;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class PERT extends GraphAlgorithm<PERT.PERTVertex> {
    LinkedList<Vertex> finishList;
    boolean isDAG;
    int CPL; //critical path
    
	
    public static class PERTVertex implements Factory {
	// Add fields to represent attributes of vertices here
        int duration;
        int ec;
        int lc;
        int slack;
        boolean crit;
        Vertex parent;
	enum Status{visited, visiting, notVisited};
        Status status;
        
	
	public PERTVertex(Vertex u) {
            
            duration = 0;
            ec =0;
            lc = 0;
            slack = 0;
            crit = false;
            parent = null;
	    status = status.notVisited;
          
            
	}
	public PERTVertex make(Vertex u) { return new PERTVertex(u); }
    }

    // Constructor for PERT is private. Create PERT instances with static method pert().
    private PERT(Graph g) {
  	super(g, new PERTVertex(null));
        CPL = 0;
        
    }

    public void setDuration(Vertex u, int d) {
        get(u).duration = d;
        
    }

    // Implement the PERT algorithm. Returns false if the graph g is not a DAG.
    public boolean pert() {
  	return false;
    }

    // Find a topological order of g using DFS
    LinkedList<Vertex> topologicalOrder() {
        if(!g.isDirected()){
            return null;
        }
        

        
  	return finishList;
    }

    void dfsVisit(Vertex u) {
	//if visiting a node that is already being visited, it is cyclic and NOT a DAG
        if (get(u).status == PERTVertex.Status.visiting) 
        {
            isDAG = false;  
        }
        else{
            //visit neighbors of node u
            get(u).status = PERTVertex.Status.visiting;
            for (Edge e : g.outEdges(u)){
                Vertex v = e.otherEnd(u);
                
                //recursive call for visiting nodes
                if (get(v).status == PERTVertex.Status.notVisited){
                    get(v).parent = u;
                    dfsVisit(v);
                }
            }
            //change status to visited
            get(u).status = PERTVertex.Status.visited;
        }
    }

    // The following methods are called after calling pert().

    // Earliest time at which task u can be completed
    public int ec(Vertex u) {
	     return get(u).ec;
    }

    // Latest completion time of u
    public int lc(Vertex u) {
	    return get(u).lc;
    }

    // Slack of u
    public int slack(Vertex u) {
    	return get(u).slack;
    }

    // Length of a critical path (time taken to complete project)
    public int criticalPath() {
	    return CPL;
    }

    // Is u a critical vertex?
    public boolean critical(Vertex u) {
        if(get(u).slack ==0) return true; // nodes with slack = 0 are in the critical path
        else return false;
    }

    // Number of critical vertices of g
    public int numCritical() {
        int critCount = 0;
        for (Vertex v : g) // traverse graph, count criticals
            if (critical(v)) critCount++;
       return critCount;
    }

    /* Create a PERT instance on g, runs the algorithm.
     * Returns PERT instance if successful. Returns null if G is not a DAG.
     */
    public static PERT pert(Graph g, int[] duration) {
	PERT p = new PERT(g);
	for(Vertex u: g) {
	    p.setDuration(u, duration[u.getIndex()]);
	}
	// Run PERT algorithm.  Returns false if g is not a DAG
	if(p.pert()) {
	    return p;
	} else {
	    return null;
	}
    }
    
    public static void main(String[] args) throws Exception {
	String graph = "10 13   1 2 1   2 4 1   2 5 1   3 5 1   3 6 1   4 7 1   5 7 1   5 8 1   6 8 1   6 9 1   7 10 1   8 10 1   9 10 1      0 3 2 3 2 1 3 2 4 1";
	Scanner in;
	// If there is a command line argument, use it as file from which
	// input is read, otherwise use input from string.
	in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(graph);
	Graph g = Graph.readDirectedGraph(in);
	g.printGraph(false);

	int[] duration = new int[g.size()];
	for(int i=0; i<g.size(); i++) {
	    duration[i] = in.nextInt();
	}
	PERT p = pert(g, duration);
	if(p == null) {
	    System.out.println("Invalid graph: not a DAG");
	} else {
	    System.out.println("Number of critical vertices: " + p.numCritical());
	    System.out.println("u\tEC\tLC\tSlack\tCritical");
	    for(Vertex u: g) {
		System.out.println(u + "\t" + p.ec(u) + "\t" + p.lc(u) + "\t" + p.slack(u) + "\t" + p.critical(u));
	    }
	}
    }
}
