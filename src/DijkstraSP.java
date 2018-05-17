
public class DijkstraSP {
	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;


	public DijkstraSP(EdgeWeightedGraph G, int s) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<>(G.V());

		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq.insert(s, 0.0);

		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (Edge e : G.adj(v))
			{
				relax(e, v);
				
			
			}
		}
		
		
	}

	private void relax(Edge e, int v) {
		int w = e.other(v);
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w))
				pq.decreaseKey(w, distTo[w]);
			else
				pq.insert(w, distTo[w]);
		}
	}

	public double distTo(int v) {
		return distTo[v];
	}

	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<Edge> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Edge> path = new Stack<Edge>();
		int x = v;
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
			path.push(e);
			x = e.other(x);
		}
		return path;
	}
}
