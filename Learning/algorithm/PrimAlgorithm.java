package algorithm;

import java.util.Arrays;

public class PrimAlgorithm {

	public static void main(String[] args) {

		char[] data = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int vertex = data.length;
		// 使用二维数组，表示邻接矩阵的关系，用10000这个大数表示两个点不联通
		int[][] weight = new int[][] { 
			{ 10000, 5, 7, 10000, 10000, 10000, 2 }, // A
			{ 5, 10000, 10000, 9, 10000, 10000, 3 }, // B
			{ 7, 10000, 10000, 10000, 8, 10000, 10000 }, // C
			{ 10000, 9, 10000, 10000, 10000, 4, 10000 }, // D
			{ 10000, 10000, 8, 10000, 10000, 5, 4 }, // E
			{ 10000, 10000, 10000, 4, 5, 10000, 6 }, // F
			{ 2, 3, 10000, 10000, 4, 6, 10000 } }; // G

		// 创建MGraph对象
		MGraph graph = new MGraph(vertex);
		
		// 创建MinTree对象
		MinTree minTree = new MinTree();
		minTree.createGraph(graph, vertex, data, weight);
		
		// show
		minTree.showGraph(graph);
		
		// Prim Algorithm
		minTree.prim(graph, 0);
		
	}
}

class MGraph {
	int vertex; // 表示图的节点个数
	char[] data; // 存放节点数据
	int[][] weight; // 存放边，就是邻接矩阵

	public MGraph(int vertex) {
		this.vertex = vertex;
		data = new char[vertex];
		weight = new int[vertex][vertex];
	}
}

// 创建最小生成树 -> 村庄的图
class MinTree {
	// 创建图的邻接矩阵
	/**
	 * 
	 * @param graph  图对象
	 * @param vertex 图对应的顶点个数
	 * @param data   图的各个顶点的值
	 * @param weight 图的邻接矩阵
	 */
	public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
		for (int i = 0; i < vertex; i++) { // 顶点
			graph.data[i] = data[i];
			for (int j = 0; j < vertex; j++) {
				graph.weight[i][j] = weight[i][j];
			}
		}
	}

	// 显示图的邻接矩阵
	public void showGraph(MGraph graph) {
		for (int[] link : graph.weight) {
			System.out.println(Arrays.toString(link));
		}
	}
	
	// 编写Prim算法，得到最小生成树
	/**
	 * 
	 * @param graph 图
	 * @param v 表示从图的第几个顶点开始生成'A'->0 'B'->1 ...
	 */
	public void prim(MGraph graph, int v) {
		
		// visited[]标记节点（顶点）是否被访问过
		int visited[] = new int[graph.vertex];
		
		// visited[]默认元素的值都是0，表示没有访问过
//		for (int i = 0; i < graph.vertex; i++) {
//			visited[i] = 0;
//		}
		
		// 把当前这个节点标记为以访问
		visited[v] = 1;
		
		// h1 和 h2  记录两个顶点的下标
		int h1 = -1;
		int h2 = -1;
		
		int minWeight = 10000; // 将minWeight初始值为大数，后面在遍历过程中会被替换
		for (int k = 1; k < graph.vertex; k++) { // 因为有graph.vertex顶点，prim结束后，会有graph.vertex-1边
			
			// 确定每一次生成的子图，和哪个节点的距离最近
			for (int i = 0; i < graph.vertex; i++) { // i节点表示被访问过的节点
				for (int j = 0; j < graph.vertex; j++) { // j节点表示为被访问过的节点
					if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
						// 替换minWeight（寻找以访问过的节点和未访问过的节点间的权值最小的边）
						minWeight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			// 找到了一条最小的边
			System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值：" + minWeight);
			// 将当前节点标记为已访问
			visited[h2] = 1;
			// minWeight重新设置为10000
			minWeight = 10000;
		}
	}
}
