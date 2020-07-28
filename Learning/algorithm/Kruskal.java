package algorithm;

import java.util.Arrays;

public class Kruskal {

	private int edgeNum; // 边的个数
	private char[] vertex; // 顶点数组
	private int[][] matrix; // 邻接矩阵
	// 使用INF，表示两个顶点不能连通
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
		// 克鲁斯卡尔算法的邻接矩阵
		int[][] matrix = { 
				{ 0, 12, INF, INF, INF, 16, 14 }, // A
				{ 12, 0, 10, INF, INF, 7, INF }, // B
				{ INF, 10, 0, 3, 5, 6, INF }, // C
				{ INF, INF, 3, 0, 4, INF, INF }, // D
				{ INF, INF, 5, 4, 0, 2, 8 }, // E
				{ 16, 7, 6, INF, 2, 0, 9 }, // F
				{ 14, INF, INF, INF, 8, 9, 0 } // G
		};

		// 创建Kruskal Case 对象实例
		Kruskal kruskal = new Kruskal(vertex, matrix);
		// 输出
		kruskal.print();
		EData[] edges = kruskal.getEdges();
		System.out.println("排序前= " + Arrays.toString(edges)); // 没有排序
		kruskal.sortEdges(edges);
		System.out.println("排序后= " + Arrays.toString(edges));
		
		kruskal.kruskalMethod();
	}

	// 构造器
	public Kruskal(char[] vertex, int[][] matrix) {

		// 初始化顶点数和边的个数
		int vlen = vertex.length;

		// 使用复制拷贝的方式，初始化顶点
		this.vertex = new char[vlen];
		for (int i = 0; i < vertex.length; i++) {
			this.vertex[i] = vertex[i];
		}

//		this.vertex = vertex;

		// 使用复制拷贝的方式，初始化边
		this.matrix = new int[vlen][vlen];
		for (int i = 0; i < vlen; i++) {
			for (int j = 0; j < vlen; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}

		// 统计边的条数
		for (int i = 0; i < vlen; i++) {
			for (int j = i + 1; j < vlen; j++) {
				if (this.matrix[i][j] != INF) {
					edgeNum++;
				}
			}
		}
	}

	// 打印邻接矩阵
	public void print() {
		System.out.print("邻接矩阵为：\n");
		for (int i = 0; i < vertex.length; i++) {
			for (int j = 0; j < vertex.length; j++) {
				System.out.printf("%12d", matrix[i][j]);
			}
			System.out.println(); // 换行
		}
	}

	/**
	 * func: 使用冒泡排序，对边进行排序处理
	 * @param edges 边的集合
	 */
	private void sortEdges(EData[] edges) {
		for (int i = 0; i < edges.length - 1; i++) {
			for (int j = 0; j < edges.length - 1 - i; j++) {
				if (edges[j].weight > edges[j + 1].weight) {
					EData temp = edges[j];
					edges[j] = edges[j + 1];
					edges[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * @param ch 顶点的值，比如'A','B'
	 * @return 返回ch顶点对应的下标；如未找到，则返回-1
	 */
	private int getPosition(char ch) {
		for (int i = 0; i < vertex.length; i++) {
			if (vertex[i] == ch) { // 找到
				return i;
			}
		}
		return -1;
	}

	/**
	 * func: 获取图中的边，放到Edata[]数组中，之后需要遍历数组 是通过matrix邻接矩阵来获取 存放形式：['A','B', 12] ,
	 * ['B','F', 7] ...
	 * @return
	 */
	private EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[edgeNum];
		for (int i = 0; i < vertex.length; i++) {
			for (int j = i + 1; j < vertex.length; j++) {
				if (matrix[i][j] != INF) {
					edges[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}

	/**
	 * func: 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
	 * @param end 数组在遍历过程中逐步形成，记录了各个顶点对应的终点是哪个
	 * @param i   表示传入的顶点对应的下标
	 * @return 返回下标为i的顶点对应的终点的下标
	 */
	private int getEnd(int[] end, int i) {
		while (end[i] != 0) {
			i = end[i];
		}
		return i;
	}
	
	public void kruskalMethod() {
		int index = 0; // 表示最后结果数组的索引
		int[] end = new int[edgeNum]; // 用于保存"已有最小生成树"中的每个顶点，在最小生成树中的终点
		
		// 创建结果数组，保存最后的最小生成树
		EData[] result = new EData[edgeNum];
		
		// 获取图中所有的边的集合，一共有12条边
		EData[] edges = getEdges();
		System.out.println("获取图的边的集合= " + Arrays.toString(edges) + "共" + edges.length); // 12
		
		// 按照边的全职大小进行排序（从小到大）
		sortEdges(edges);
		
		// 遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，如果没有就加入到结果数组中
		for (int i = 0; i < edges.length; i++) {
			// 获取到第i条边的第一个顶点（起点）
			int p1 = getPosition(edges[i].start);
			// 获取到第i条边的第二个顶点（终点）
			int p2 = getPosition(edges[i].end);
			
			// 获取p1顶点在已有最小生成树中的终点
			int m = getEnd(end, p1);
			// 获取p2顶点在已有最小生成树中的终点
			int n = getEnd(end, p2);
			
			// 判断是否构成回路
			if (m != n) {
				end[m] = n; // 设置 m 在"已有最小生成树"中的终点
				result[index++] = edges[i];
			}
		}
		
		// 统计并打印"最小生成树"，输出result
		System.out.println("最小生成树为");
		for (int i = 0; i < index; i++) {
			System.out.println(result[i]);
		}
	}
}

// 创建一个类，EData，它的对象实例就表示一条边
class EData {
	char start; // 边的一个点
	char end; // 边的另一个点
	int weight; // 边的权值

	// 构造器
	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	// 重写toString方法，便于输出这条边的信息
	@Override
	public String toString() {
		return "EData[<" + start + ", " + end + "> = " + weight + "]";
	}
}
