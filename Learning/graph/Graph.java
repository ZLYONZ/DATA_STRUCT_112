package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	private ArrayList<String> vertexList; // 存储顶点的集合
	private int[][] edges; // 存储图对应的邻接矩阵
	private int numOfEdges; // 表示边的数目

	// 定义给数组Boolean[]，记录某个节点是否被访问
	private boolean[] isVisited;

	// 构造器
	public Graph(int n) {
		// 初始化矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;
	}

	// 添加节点
	public void addVertex(String vertex) {
		vertexList.add(vertex);
	}

	// 添加边
	/**
	 * 
	 * @param v1     表示点的下标，即第几个顶点 "A"-"B" "A"=>0 "B"=>0
	 * @param v2     表示第二个顶点对应的下标
	 * @param weight 权值：表示
	 */
	public void addEdges(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}

	// * 图中常用的方法

	// 返回节点的个数
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// 得到的边的数目
	public int getNumOfEdges() {
		return numOfEdges;
	}

	// 返回节点i（下标）对应的数据 0=>"A" 1=>"B" 2=>"C"
	public String getValByIndex(int i) {
		return vertexList.get(i);
	}

	// 返回v1，v2的权值
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}

	// 显示图对应的矩阵
	public void showGraph() {
		for (int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}

	// 得到第一个邻接节点的下标 w
	/**
	 * 
	 * @param index
	 * @return 如果存在，就返回对应的下标；否则返回-1
	 */
	public int getFirstNeighbor(int index) {
		for (int i = 0; i < vertexList.size(); i++) {
			if (edges[index][i] > 0) {
				return i;
			}
		}
		return -1;
	}

	// 根据前一个邻接节点的下标，来获取下一个邻接节点.
	public int getNextNeighbor(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (edges[v1][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	// 深度优先遍历算法
	// i 第一次就是 0
	private void DFS(boolean[] isVisited, int i) {

		// 1）访问该节点
		System.out.print(getValByIndex(i) + "->");

		// 将该节点设置为以访问
		isVisited[i] = true;

		// 查找节点i的第一个邻接节点w
		int w = getFirstNeighbor(i);
		while (w != -1) { // 说明有
			if (!isVisited[w]) { // 判断是否被访问
				DFS(isVisited, w);
			}
			// 如果w节点已被访问
			w = getNextNeighbor(i, w);
		}
	}

	// 对DFS重载，遍历所有节点，再次进行DFS
	public void DFS() {
		isVisited = new boolean[vertexList.size()];
		// 遍历所有的节点（回溯）
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				DFS(isVisited, i);
			}
		}
	}

	// 广度优先遍历算法
	private void BFS(boolean[] isVisited, int i) {
		int u; // 表示队列的头节点对应的下标
		int w; // 表示邻接节点的下标
		LinkedList<Integer> queue = new LinkedList<Integer>(); // 队列，记录节点访问的顺序

		// 访问节点，输出节点信息
		System.out.print(getValByIndex(i) + "=>");

		// 标记为以访问
		isVisited[i] = true;

		// 将节点加入队列
		queue.addLast(i);

		while (!queue.isEmpty()) {
			// 取出队列的头节点
			u = queue.removeFirst();
			// 得到第一个邻接节点的下标
			w = getFirstNeighbor(u);
			while (w != -1) { // 找到
				// 是否访问过
				if (!isVisited[w]) {
					System.out.print(getValByIndex(w) + "=>");
					// 标记为以访问
					isVisited[w] = true;
					// 入队
					queue.addLast(w);
				}
				// 以u为前驱节点，找w后面的下一个邻接节点
				w = getNextNeighbor(u, w); // 体现出广度优先
			}
		}
	}

	// 对DFS重载，遍历所有节点，再次进行DFS
	public void BFS() {
		isVisited = new boolean[vertexList.size()];
		// 遍历所有的节点（回溯）
		for (int i = 0; i < getNumOfVertex(); i++) {
			if (!isVisited[i]) {
				BFS(isVisited, i);
			}
		}
	}
}