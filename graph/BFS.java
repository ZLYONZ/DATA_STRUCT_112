package graph;

public class BFS { // Breadth First Search

	public static void main(String[] args) {

		int n = 8; // 节点的个数
		String Vertexs[] = { "A", "B", "C", "D", "E", "F", "G", "H" };

		// 创建图对象
		Graph graph = new Graph(n);

		// 循环添加顶点
		for (String vertex : Vertexs) {
			graph.addVertex(vertex);
		}
		
		// 添加边
		graph.addEdges(0, 1, 1);
		graph.addEdges(0, 2, 1);
		graph.addEdges(1, 3, 1);
		graph.addEdges(1, 4, 1);
		graph.addEdges(3, 7, 1);
		graph.addEdges(4, 7, 1);
		graph.addEdges(2, 5, 1);
		graph.addEdges(2, 6, 1);
		graph.addEdges(5, 6, 1);
		
		// 显示
		graph.showGraph();
		
		// BFS
		System.out.println("广度遍历");
		graph.BFS();
	}
}
