package algorithm;

import java.util.Arrays;

public class Dijkstra { // 迪杰斯特拉算法

	public static void main(String[] args) {

		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

		// 迪杰斯特拉算法的邻接矩阵
		int[][] matrix = new int[vertex.length][vertex.length];

		final int N = 65535; // 表示不可连接
		matrix[0] = new int[] { N, 5, 7, N, N, N, 2 }; // A
		matrix[1] = new int[] { 5, N, N, 9, N, N, 3 }; // B
		matrix[2] = new int[] { 7, N, N, N, 8, N, N }; // C
		matrix[3] = new int[] { N, 9, N, N, N, 4, N }; // D
		matrix[4] = new int[] { N, N, 8, N, N, 5, 4 }; // E
		matrix[5] = new int[] { N, N, N, 4, 5, N, 6 }; // F
		matrix[6] = new int[] { 2, 3, N, N, 4, 6, N }; // G 

		// 创建Graph对象
		D_Graph graph = new D_Graph(vertex, matrix);
		// showGraph
		graph.showGraph();
		// 测试迪杰斯特拉算法
		graph.dijkstra(6);
		// show
		graph.showDijkstra();
	}

}

class D_Graph {

	private char[] vertex; // 顶点数组
	private int[][] matrix; // 邻接矩阵
	private VisitedVertex vv; // 已访问的顶点的集合
	// 构造器

	public D_Graph(char[] vertex, int[][] matrix) {
		this.vertex = vertex;
		this.matrix = matrix;
	}

	// 显示图
	public void showGraph() {
		for (int i = 0; i < vertex.length; i++) {
			for (int j = 0; j < vertex.length; j++) {
				System.out.printf("%7d", matrix[i][j]);
			}
			System.out.println(); // 换行
		}
	}

	// 迪杰斯特拉算法
	public void dijkstra(int index) {
		vv = new VisitedVertex(vertex.length, index);
		update(index); // 更新index顶点到周围顶点的距离和前驱顶点

		for (int i = 1; i < vertex.length; i++) {
			index = vv.updateArr(); // 选择并返回
			update(index); // 更新index顶点到周围顶点的距离和前驱顶点
		}
	}

	// 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
	private void update(int index) {
		int length = 0;
		// 根据遍历邻接矩阵的matrix[index]行
		for (int j = 0; j < matrix[index].length; j++) {
			// length表示：出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
			length = vv.getDis(index) + matrix[index][j];
			// 如果j节点没有被访问，且length小于出发顶点到j顶点的距离，就需要更新
			if (!vv.index(j) && length < vv.getDis(j)) {
				vv.updatePre(j, index); // 更新j顶点的前驱为index顶点
				vv.updateDis(j, length); // 更新出发顶点到j顶点的距离
			}
		}
	}

	// 显示结果
	public void showDijkstra() {
		vv.show();
	}
}

class VisitedVertex {

	// 记录各个顶点是否访问过；1表示访问过，0表示未访问；会动态更新
	public int[] already_arr;

	// 每个下标对应的值为前一个顶点下标；会动态更新
	public int[] pre_visited;

	// 记录出发顶点到其他所有顶点的距离；会动态更新
	public int[] dis;

	// 构造器
	/**
	 * 
	 * @param length 表示顶点的个数
	 * @param index  表示出发顶点对应的下标；比如G顶点，下标是6
	 */
	public VisitedVertex(int length, int index) {
		this.already_arr = new int[length];
		this.pre_visited = new int[length];
		this.dis = new int[length];

		// 初始化dis数组
		Arrays.fill(dis, 65535);
		this.already_arr[index] = 1; // 设置出发顶点被访问过为1
		this.dis[index] = 0; // 设置出发顶点的访问距离为0
	}

	/**
	 * func：判断index顶点是否被访问过
	 * 
	 * @param index
	 * @return 如果访问过，返回true；否则返回false
	 */
	public boolean index(int index) {
		return already_arr[index] == 1;
	}

	/**
	 * func：更新出发顶点到index顶点的距离
	 * 
	 * @param index
	 * @param length
	 */
	public void updateDis(int index, int length) {
		dis[index] = length;
	}

	/**
	 * func：更新顶点的前驱节点为index的顶点
	 * 
	 * @param pre
	 * @param index
	 */
	public void updatePre(int pre, int index) {
		pre_visited[pre] = index;
	}

	/**
	 * func：返回出发顶点到index顶点的距离
	 * 
	 * @param index
	 * @return
	 */
	public int getDis(int index) {
		return dis[index];
	}

	/**
	 * 继续先择并返回新的访问顶点；比如G完后，就让A作为新的访问顶点（注意不是出发顶点）
	 * 
	 * @return
	 */
	public int updateArr() {
		int min = 65535, index = 0;
		for (int i = 0; i < already_arr.length; i++) {
			if (already_arr[i] == 0 && dis[i] < min) {
				min = dis[i];
				index = i;
			}
		}
		// 更新index顶点被访问过
		already_arr[index] = 1;
		return index;
	}

	// show final result
	// 即将三个数组的情况输出
	public void show() {

		System.out.println("===========================");
		// 输出already_arr
		for (int i : already_arr) {
			System.out.print(i + " ");
		}

		System.out.println();
		// 输出pre_visited
		for (int i : pre_visited) {
			System.out.print(i + " ");
		}

		System.out.println();
		// 输出dis
		for (int i : dis) {
			System.out.print(i + " ");
		}

		System.out.println();
		// 处理输出
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		int count = 0;
		for (int i : dis) {
			if (i != 65535) {
				System.out.print(vertex[count] + "(" + i + ") ");
			} else {
				System.out.print("N");
			}
			count++;
		}
		System.out.println();
	}
}
