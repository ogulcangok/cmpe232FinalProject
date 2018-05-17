import org.gephi.*;
import org.gephi.project.io.GephiReader;
public class InteractionsGraph {

	private LinearProbingHashST<String, Integer> st; // String -> index
	private String[] keys; // index -> String
	private EdgeWeightedGraph G;
	private DijkstraSP dj;
	

	public InteractionsGraph(String stream, String sp) {
		st = new LinearProbingHashST<String, Integer>();
		In in = new In(stream); // First pass
		in.readLine();
		while (in.hasNextLine()) // builds the index
		{
			String[] a = in.readLine().split(sp); // by reading strings
			for (int i = 0; i < 2; i++) // to associate each
			{
				if (!st.contains(a[i])) // distinct string
				{
					st.put(a[i], st.size()); // with an index.
				}
			}
		}
		keys = new String[st.size()]; // Inverted index
		for (String name : st.keys()) // to get string keys
		{
			keys[st.get(name)] = name; // is an array.
		}
		G = new EdgeWeightedGraph(st.size());
		in = new In(stream); // Second pass
		in.readLine();

		while (in.hasNextLine()) // builds the graph
		{
			String[] a = in.readLine().split(sp); // by connecting the
			int v = st.get(a[0]); // first vertex

			G.addEdge(new Edge(v, st.get(a[1]), Double.parseDouble(a[3]))); // to all the others.

		}
	}

	public boolean contains(String s) {
		return st.contains(s);
	}

	public int index(String s) {
		return st.get(s);
	}

	public String name(int v) {
		return keys[v];
	}

	public EdgeWeightedGraph G() {
		return G;
	}

	public String toString() {
		
		
		String s = G.V() + " vertices, " + G.E() + " edges\n";
		for (int v = 0; v < G.V(); v++) {
			s += name(v) + ": ";
			for (Edge e : G.adj(v)) {
				
				s += name(e.other(v)) + " (weight " + e.weight() + "), ";
			}
			
			s += "\n---------------------------------------------------------------------------------------------\n";
		}
		return s;
	}

	public static void main(String[] args) {
		
		
		String filename = "/home/ogulcan/babel.csv";
		String delim = ",";

		InteractionsGraph sg = new InteractionsGraph(filename, delim);
		EdgeWeightedGraph G = sg.G();
		DijkstraSP dj = new DijkstraSP(G, 0);
		
		for(int v = 0; v < G.V(); v++) 
		{
			System.out.println(dj.pathTo(v));
			 
			
		}
		
		

		
		
	}
}
