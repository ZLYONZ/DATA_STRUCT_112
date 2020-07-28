package algorithm;

public class DynamicProgramming { // Knapsack Problem

	public static void main(String[] args) {

		int[] wt = { 1, 4, 3 }; // 物品的重量
		int[] val = { 1500, 3000, 2000 }; // 物品的价值
		int num = val.length; // 物品的个数
		int cap = 4; // 背包的容量

		// 创建二维数组
		// v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] table = new int[num + 1][cap + 1];
		
		// 为了记录放入商品的情况，定一个二维数组
		int[][] path  = new int[num + 1][cap + 1];
	
		// 初始化第一行和第一列，在本程序中可以不去处理，因为默认为0
		for (int i = 0; i < table.length; i++) {
			table[i][0] = 0; // 将第一列设置为0
		}
		for (int i = 0; i < table[0].length; i++) {
			table[0][i] = 0; // 将第一行设置为0
		}

		// 根据前面公式，动态规划处理
		for (int i = 1; i < table.length; i++) { // 不处理第一行
			for (int j = 1; j < table[0].length; j++) { // 不处理第一列
				// 公式
				if (wt[i - 1] > j) {
					table[i][j] = table[i - 1][j];
				} else {
					// table[i][j] = Math.max(table[i-1][j], val[i-1] + table[i-1][j-wt[i-1]]);
					// 为了体现存放情况，不能直接使用这个公式
					if (table[i-1][j] < val[i-1] + table[i-1][j-wt[i-1]]) {
						table[i][j] = val[i-1] + table[i-1][j-wt[i-1]];
						// 把当前情况记录到path
						path[i][j] = 1;
					} else {
						table[i][j] = table[i-1][j];
					}
				}
			}
		}

		// 输出
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("=========================================");
		// 输出最后放入的商品
		// 遍历path
		// 这样输出会把所有的放入情况都得到，但只需要最后的放入
//		for (int i = 0; i < path.length; i++) {
//			for (int j = 0; j < path[i].length; j++) {
//				if (path[i][j] == 1) {
//					System.out.printf("第%d个商品放入到背包\n", i);
//				}
//			}
//		}
		
		int i = path.length - 1; // 行的最大下标
		int j = path[0].length - 1; // 列的最大下标
		while (i > 0 && j > 0) { // 从path的最后开始找
			if (path[i][j] == 1) {
				System.out.printf("第%d个商品放入到背包\n", i);
				j -= wt[i-1];
			}
			i--;
		}
	}
}
