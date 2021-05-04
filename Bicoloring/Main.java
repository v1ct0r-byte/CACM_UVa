import java.util.Scanner;
import java.util.*;
import java.io.PrintStream;
import java.util.Vector;

public class Main {
    
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(scanner.hasNextLine()){
            int n = scanner.nextInt();
            if(n == 0){break;}
            scanner.nextLine();
            int l = scanner.nextInt();
            scanner.nextLine();

            //Constructing the graph
            Graph g = new Graph(false);
            for(int i = 0;i < n; i++){
                Vertex v = new Vertex(i, "");
                g.insert(v);
            }
            for(int i = 0;i < n; i++){
                String line[] = scanner.nextLine().split(" ");
                int o = Integer.parseInt(line[0]);
                int d = Integer.parseInt(line[1]);  
            }

            //Strategy
        }
    }
}

class Graph
{
    protected Vector<Vertex>  vertices;
    protected Vector<Edge>    edges;
    protected boolean         directed;

    protected int totalOutDegree;
    protected int totalInDegree;

    protected Edge  adjacency[][]; // Not always used. Must be generated ad-hoc for those algorithms that need it.

    public Graph( boolean directed )
    {
        vertices = new Vector<Vertex>();
           edges = new Vector<Edge>();

        totalOutDegree = 0;
        totalInDegree = 0;

        this.directed = directed;

        adjacency = null;
    }

    public int getNumVertices() { return vertices.size(); }
    public int getNumEdges()    { return edges.size(); }
    public int getDegree()      { return totalOutDegree; }
    public int getOutDegree()   { return totalOutDegree; }
    public int getInDegree()    { return totalInDegree; }

    public Vertex getVertex( int index ) { return vertices.get(index); }

    public boolean isDirected() { return directed; }


    public void generateAdjacencyMatrix()
    {
        adjacency = new Edge [ vertices.size() ][ vertices.size() ];

        for( Vertex v : vertices ) {
            for( Edge e : v.getEdges() ) {
                
                adjacency[ v.getId() ][ e.getDestination().getId() ] = e;
                // adjacency[ e.getDestination().getId() ][ v.getId() ] = e;
            }
        }
        // It not necessary to add the edge from 'dest' to 'origin' because if the graph was
        // generated as non-directed the edge from 'dest' to 'origin' must exist as an edge inside
        // the destination vertex. This implementation forces to allocate more memory but it 
        // give us the chance of use it for different purposes.
    }

    public Edge findEdge( Vertex from, Vertex to )
    {
        if ( adjacency != null ) return adjacency[ from.getId() ][ to.getId() ];

        for( Edge e : from.getEdges() ) if ( e.getDestination() == to ) return e;

        return null;
    }

    public void reset() // InitializeGraph
    {
        vertices.clear();
        edges.clear();

        totalOutDegree = 0;
        totalInDegree = 0;
    }

    public void insert( Vertex v )
    {
        v.setId( vertices.size() );
        vertices.add( v );
    }
    public void insert( Edge e, boolean directed )
    {
        edges.add( e );

        if ( !directed ) {
            Edge other = new Edge( e.getDestination(), e.getOrigin() );
            other.setCapacity( e.getCapacity() );
            other.setFlow( e.getFlow() );
            other.setResidual( e.getResidual() );
            other.setWeight( e.getWeight() );
            edges.add( other );
            e.getDestination().add( other );
        }
    }
    public void connect( Vertex o, Vertex d, boolean directed )
    {
        Edge e = new Edge( o, d );
        edges.add( e );
        o.add( e );

        if ( ! directed ) {
            e = new Edge( d, o );
            edges.add( e );
            d.add( e );
        }
    }

    public void read( Scanner sf, boolean directed )
    {
    }

    public void print( PrintStream ps )
    {
    }
}

class Vertex
{
    protected int             id; // Used as index inside the graph.
    protected String          label;
    protected Vector<Edge>    edges;
    protected int             inDegree;

    public Vertex( int id, String label )
    {
        this.id = id;
        this.label = label;
        this.edges = new Vector<Edge>();
        this.inDegree = 0;
    }

    public int getId() { return id; }
    public String getLabel() { return label; }

    public void setId( int id ) { this.id = id; }

    public Edge getEdge( int i ) { return edges.get(i); }

    public Vector<Edge> getEdges() { return edges; }

    public void add( Edge e )
    {
        edges.add( e );
        e.getDestination().incrInDegree();
    }

    public void incrInDegree() { inDegree++; }

    public int getOutDegree() { return edges.size(); }
    public int getInDegree() { return inDegree; }

    public String toString()
    {
        return String.format( "(%d) <%s>", id, label!=null ? label : "" );
    }
}

class Edge
{
    protected Vertex      origin;
    protected Vertex      destination;

    protected String      label;

    protected double      weight;

    protected double      capacity;
    protected double      flow;
    protected double      residual;

    public Edge( Vertex origin, Vertex destination )
    {
        this.origin = origin;
        this.destination = destination;
        this.label = null;
        reset();
    }
    public Edge( Vertex origin, Vertex destination, String label )
    {
        this.origin = origin;
        this.destination = destination;
        this.label = label;
        reset();
    }

    public void reset()
    {
        weight   = 1.0;
        capacity = 1.0;
        flow     = 0.0;
        residual = 0.0;
    }

    public Vertex getOrigin() { return origin; }
    public Vertex getDestination() { return destination; }
    public String getLabel() { return label; }

    public double getWeight() { return weight; }

    public void setWeight( double w ) { weight=w; }

    public double getCapacity() { return capacity; }
    public double getFlow()     { return flow; }
    public double getResidual() { return residual; }


    public void setCapacity( double c ) { capacity=c; }
    public void setFlow(     double f ) { flow=f; }
    public void setResidual( double r ) { residual=r; }


    public String toString()
    {
        return origin.toString() + " --> " + destination.toString();
    }
}
