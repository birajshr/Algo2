/*
 * Name: <your name>
 * EID: <your EID>
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/* Your solution goes in this file.
 *
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */

public class Program2 extends VertexNetwork {
    /* DO NOT FORGET to add a graph representation and 
       any other fields and/or methods that you think 
       will be useful. 
       DO NOT FORGET to modify the constructors when you 
       add new fields to the Program2 class. */
    
    Program2() {
        super();
    }
    
    Program2(String locationFile) {
        super(locationFile);
    }
    
    Program2(String locationFile, double transmissionRange) {
        super(locationFile, transmissionRange);
    }
    
    Program2(double transmissionRange, String locationFile) {
        super(transmissionRange, locationFile);
    }

    public ArrayList<Vertex> gpsrPath(int sourceIndex, int sinkIndex) {
        /* This method returns a path from a source at location sourceIndex 
           and a sink at location sinkIndex using the GPSR algorithm. An empty 
           path is returned if the GPSR algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements the GPSR algorithm. */
    	ArrayList<Vertex> pathToSink = new ArrayList<Vertex>(0);
    	Vertex source = this.location.get(sourceIndex);
    	Vertex sink = this.location.get(sinkIndex);
    	Vertex nextHop = source;    	
    	while(nextHop!=null)
    	{
    		if (nextHop.equals(sink)) {break;}
	
    		pathToSink.add(nextHop);
    		ArrayList<Vertex> neighbor = neighborList(nextHop);
    		
    		nextHop = greedyFoward(nextHop, sink, neighbor);
    		if (nextHop == null) {return new ArrayList<Vertex>(0);}
    	}
    	
    	if (nextHop == null) {return new ArrayList<Vertex>(0);}
    	else {return pathToSink;}

    }
    
    public ArrayList<Vertex> neighborList(Vertex source){
    	ArrayList<Vertex> neighborlist = new ArrayList<Vertex>();
    	
    	for(Vertex v : this.location)
    	{
    		if(source.distance(v) <= this.transmissionRange && source.distance(v) !=0) { neighborlist.add(v); }
    	}
    	
    	
    	return neighborlist;
    }
    
    public Vertex greedyFoward(Vertex source, Vertex sink, ArrayList<Vertex> neighbors ){
    	Vertex nextHop = null;
    	double maximumProgress = Double.POSITIVE_INFINITY;
    	double distance_SS = sink.distance(source);
    	double distance_VS;
    	
    	for(Vertex v : neighbors)
    	{
    		distance_VS = sink.distance(v);
    		if (distance_VS < distance_SS){
    			if(distance_VS < maximumProgress){
    				maximumProgress = distance_VS;
    				nextHop = v;
    			}
    		}
    	}
    	
    	if(maximumProgress != Double.POSITIVE_INFINITY) { return nextHop; }
       	else { return null; }
    }
    
    public ArrayList<Vertex> dijkstraPathLatency(int sourceIndex, int sinkIndex) {
        /* This method returns a path (shortest in terms of latency) from a source at
           location sourceIndex and a sink at location sinkIndex using Dijkstra's algorithm.
           An empty path is returned if Dijkstra's algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements Dijkstra's algorithm. */
    	
    	int size = location.size();
    	double alt;
    	Double[] dist = new Double[size];
    	Vertex[] previous = new Vertex[size];
    	ArrayList<Vertex> output = new ArrayList<Vertex>();
    	//Queue<Vertex> Q= new PriorityQueue<Vertex>(location);
    	
    	ArrayList <Vertex> neighbor;
    	Vertex source = location.get(sourceIndex);
    	Vertex sink = location.get(sinkIndex);
    	
    	for (Vertex v : location)
    	{
    		int index = location.indexOf(v);
    		dist[index] = Double.POSITIVE_INFINITY;
    		previous[index] = null;
    		
    	}
    	
    	dist[sourceIndex] = 0.0;
    	ArrayList<Vertex> Q = new ArrayList<Vertex>(location);
    	
    	while(!Q.isEmpty()){
    		double uDistance = Double.POSITIVE_INFINITY;
    		Vertex u = null;		
    		
    		for(Vertex v : Q)
    		{
    			double vDistance = dist[location.indexOf(v)];
    			if(vDistance < uDistance){
    				uDistance = vDistance;
    				u = v;
    			}
    		}
    		
    		//System.out.println("Min : " + u);
    		if(u==null) { return new ArrayList<Vertex>();}
    		if (dist[location.indexOf(u)].isInfinite()) {break;}
    		Q.remove(u);
    		if (u.equals(sink)) {break;}
    		
    		neighbor = neighborList(u);
    		
    		ArrayList<Vertex> neighborNotInQ = new ArrayList<Vertex>();
    		
    		for(Vertex n : neighbor)
    		{
    			if(!Q.contains(n))
    			{
    				neighborNotInQ.add(n);
    			}
    		}
    		
    		neighbor.removeAll(neighborNotInQ);
    		
    		for (Vertex v : neighbor)
    		{
    			int indexOfV = location.indexOf(v);
    			
    			alt = dist[location.indexOf(u)] + getEdgeWeight(u,v);
    			if (alt < dist[indexOfV])
    			{
    				dist[indexOfV] = alt;
    				previous[indexOfV] =  u;
    				//Q.peek().
    			}
    		}
    		//output.add(u);
    	}
    	
    	
    	int indexCurrent = sinkIndex;
    	while (previous[indexCurrent] !=  source)
    	{
    		output.add(previous[indexCurrent]);
    		indexCurrent = location.indexOf(previous[indexCurrent]);
    		
    		if(previous[indexCurrent] ==  source)
    	}
    	
    	
    	//if(previous[sinkIndex] ==null) {return output;}
    	
//    	for(int i = (sourceIndex); i<= sinkIndex;i++)
//    	{
//
//    		
//    		if(!output.contains(previous[i]) && previous[i]!=null)
//    		{
//    		output.add(previous[i]);
//    		}
//    	}
    	
//    	for(Vertex v : previous)
//    	{
//    		System.out.println(v);
//    	}
    	
    	
//    	for(Double d : dist)
//    	{
//    		System.out.println(d);
//    	}
    	
    	
        return output;
    }
    

    
    public double getEdgeWeight(Vertex u, Vertex v)
    {
    	int indexU = location.indexOf(u);
    	int indexV = location.indexOf(v);
    	
    	double weight = 0;
    	
    	if(u.distance(v) > this.transmissionRange) { return Double.POSITIVE_INFINITY;}
    	
    	for(Edge e : this.edges)
    	{
    		if(e.getU() == indexU && e.getV() == indexV)
    		{
    			weight = e.getW();
    		}
    	}
    	
    	return weight;
    }
    
    
    
    public ArrayList<Vertex> dijkstraPathHops(int sourceIndex, int sinkIndex) {
        /* This method returns a path (shortest in terms of hops) from a source at
           location sourceIndex and a sink at location sinkIndex using Dijkstra's algorithm.
           An empty path is returned if Dijkstra's algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements Dijkstra's algorithm. */
        return new ArrayList<Vertex>(0);
    }
    
}

