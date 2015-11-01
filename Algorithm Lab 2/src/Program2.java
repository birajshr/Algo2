/*
 * Name: <your name>
 * EID: <your EID>
 */

import java.util.ArrayList;
import java.util.Collections;

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
    	
    	while(nextHop != sink && nextHop!=null)
    	{
    		pathToSink.add(nextHop);
    		ArrayList<Vertex> ne = neighborList(nextHop);
    		//nextHop = greedyFoward(nextHop, sink, neighborList(nextHop));
    		
    	}
    	
    	if (nextHop == null)
    		return null;
    	else 
    		return pathToSink;

    }
    
    public ArrayList<Vertex> neighborList(Vertex source){
    	ArrayList<Vertex> neighborlist = new ArrayList<Vertex>();
    	
    	for(Vertex v : this.location)
    	{
    		if(source.distance(v) <= this.transmissionRange && source.distance(v) !=0)
    		{
    			neighborlist.add(v);
    		}
    	}
    	
    	
    	return neighborlist;
    }
    
    public Vertex greedyFoward(Vertex source, Vertex sink, ArrayList<Vertex> neighbors ){
    	Vertex nextHop = null;
    	double maximumProgress = 0;
    	double distance_SS = sink.distance(source);
    	
    	for(Vertex v : neighbors)
    	{
    		double distance_VS = sink.distance(v);
    		if (distance_VS < distance_SS){
    			double progress_FI = (distance_SS - distance_VS)/distance_SS;
    			
    			if(maximumProgress < progress_FI){
    				maximumProgress = progress_FI;
    				nextHop = v;
    			}
    		}
    	}
    	
    	if(maximumProgress > 0){
    		return nextHop;
    	}
    	else{
    		return null;
    	}
    		
    	
    }
    
    public ArrayList<Vertex> dijkstraPathLatency(int sourceIndex, int sinkIndex) {
        /* This method returns a path (shortest in terms of latency) from a source at
           location sourceIndex and a sink at location sinkIndex using Dijkstra's algorithm.
           An empty path is returned if Dijkstra's algorithm fails to find a path. */
        /* The following code is meant to be a placeholder that simply 
           returns an empty path. Replace it with your own code that 
           implements Dijkstra's algorithm. */
        return new ArrayList<Vertex>(0);
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

