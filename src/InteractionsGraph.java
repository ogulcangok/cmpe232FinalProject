import java.util.Hashtable;
public class InteractionsGraph {

	private LinearProbingHashST<String, Integer> st; // String -> index
	private String[] keys; // index -> String
	private EdgeWeightedDigraph G;

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
		G = new EdgeWeightedDigraph(st.size());
		in = new In(stream); // Second pass
		in.readLine();

		while (in.hasNextLine()) // builds the graph
		{
			String[] a = in.readLine().split(sp); // by connecting the
			int v = st.get(a[0]); // first vertex

			G.addEdge(new DirectedEdge(v, st.get(a[1]), Double.parseDouble(a[3]))); // to all the others.

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

	public EdgeWeightedDigraph G() {
		return G;
	}

	public String toString() {

		String s = G.V() + " vertices, " + G.E() + " edges\n";
		for (int v = 0; v < G.V(); v++) {
			s += name(v) + ": ";
			for (DirectedEdge e : G.adj(v)) {

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
		EdgeWeightedDigraph G = sg.G();
		DijkstraAllPairsSP dj = new DijkstraAllPairsSP(G);
		int[] ec = new int[G.V()];
		int[] edges = new int[G.V()];
		int max = 0;
		Hashtable<Integer, Integer> edge = new Hashtable<>();

		for (int i = 0; i < G.V(); i++) {
			int count = 0;

			System.out.println("Edge " + i + "\n");
			for (int v = 0; v < G.V(); v++) {

				if (dj.dist(i, v) != Double.POSITIVE_INFINITY) {

					System.out.println(dj.path(i, v));

					count++;
				}
				
			}
			ec[i] = count;
			edges[i] = i;
			System.out.println("edge " + i + " has " + ec[i]);
			
		}
		max = ec[0];
		for (int j = 0; j < ec.length; j++) {
			if (ec[j] > max) {
				max = ec[j];
				
			}
			edge.put(edges[j], ec[j]);
			
		}
		
		
		System.out.println("Biggest edge is " + edge.get(max) + " with value of " + max);
		
		
		
	}
}
