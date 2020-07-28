package algorithm;

import java.util.Arrays;

public class Floyd {

	public static void main(String[] args) { // 佛洛依德算法

		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

		// 迪杰斯特拉算法的邻接矩阵
		int[][] matrix = new int[vertex.length][vertex.length];

		final int N = 65535; // 表示不可连接
		matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 }; // A
		matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 }; // B
		matrix[2] = new int[] { 7, N, 0, N, 8, N, N }; // C
		matrix[3] = new int[] { N, 9, N, 0, N, 4, N }; // D
		matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 }; // E
		matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 }; // F
		matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 }; // G

		// 创建Graph对象
		F_Graph graph = new F_Graph(vertex.length, vertex, matrix);
		// Floyd
		graph.floyd();
		// show
		graph.show();
	}
}

// 创建图
class F_Graph {
	@SuppressWarnings("unused")
	private char[] vertex; // 存放顶点的数组
	private int[][] dis; // 保存从各个顶点出发到其他顶点的距离，最后的结果保留在该数组中
	private int[][] pre; // 保存到达目标顶点的前驱顶点

	// 构造器
	/**
	 * 
	 * @param length 大小
	 * @param vertex 邻接矩阵
	 * @param matrix 顶点数组
	 */
	public F_Graph(int length, char[] vertex, int[][] matrix) {
		this.dis = matrix;
		this.pre = new int[length][length];

		// 对pre数组初始化；注意：存放的是前驱节点的下标
		for (int i = 0; i < length; i++) {
			Arrays.fill(pre[i], i);
		}
	}

	// 显示 dis and pre 数组
	public void show() {

		// 优化输出
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

		for (int i = 0; i < dis.length; i++) {

			// 先将pre数组输出
			for (int j = 0; j < dis.length; j++) {
				System.out.print(vertex[pre[i][j]] + " ");
			}
			System.out.println();

			// 再将dis数组输出
			for (int j = 0; j < dis.length; j++) {
				System.out.print("(" + vertex[i] + "-" + vertex[j] + "-" + dis[i][j] + ") ");
			}
			System.out.println();
		}
	}

	public void floyd() {

		// 变量保存距离
		int length = 0;

		// 对中间顶点的遍历，k为下标
		for (int k = 0; k < dis.length; k++) {

			// 从i顶点开始出发
			for (int i = 0; i < dis.length; i++) {

				// 到达j顶点
				for (int j = 0; j < dis.length; j++) {

					length = dis[i][k] + dis[k][j]; // => 求出从i顶点出发，经过中间顶点k，到达j顶点
					if (length < dis[i][j]) {
						dis[i][j] = length; // 更新距离
						pre[i][j] = pre[k][j]; // 更新前驱顶点 
					} 
				}
			}
		}
	}
}
