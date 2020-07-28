package graph;

public class DFS { // Depth First Search

	public static void main(String[] args) {
		
		int n = 5; // 节点的个数
		String Vertexs[] = { "A", "B", "C", "D", "E" };

		// 创建图对象
		Graph graph = new Graph(n);

		// 循环添加顶点
		for (String vertex : Vertexs) {
			graph.addVertex(vertex);
		}
		
		// 添加边
		// A-B A-C B-C B-D B-E
		graph.addEdges(0, 1, 1); // A-B
		graph.addEdges(0, 2, 1); // A-C
		graph.addEdges(1, 2, 1); // B-C
		graph.addEdges(1, 3, 1); // B-D
		graph.addEdges(1, 4, 1); // B-E

		// 显示
		graph.showGraph();
		
		// DFS
		System.out.println("深度遍历");
		graph.DFS();
	}
}
