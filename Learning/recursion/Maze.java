package recursion;

public class Maze {

	public static void main(String[] args) {

		// 先创建一个二维数组，模拟迷宫
		// 地图
		int[][] map = new int[8][7];
		// 使用1表示墙
		// 上下都置为一
		for (int i = 0; i < 7; i++) {
			map[0][i] = 1;
			map[7][i] = 1;
		}
		// 左右置为1
		for (int i = 0; i < 8; i++) {
			map[i][0] = 1;
			map[i][6] = 1;
		}
		// 设置挡板
		map[3][1] = 1;
		map[3][2] = 1;
		map[5][4] = 1;
		map[6][4] = 1;

		// 输出地图
		System.out.println("地图的情况~");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();	
		}
		
		// 找路
		setWay(map, 1, 1);
		// 输出新的地图
		System.out.println("小球走过，并标识后的地图的情况~");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();	
		}
	}

	// 使用递归回溯模拟小球的找路
	// [1][1] => [6][5]
	// 当map[i][j]为0时，表示该点为走过
	// 1为墙；2为路；3为不通的路
	// 走迷宫时需要策略：下 -> 右 -> 左 -> 上
	/**
	 * 
	 * @param map 表示地图
	 * @param i   从哪个位置开始找
	 * @param j
	 * @return 如果找到，返回true，否则返回false
	 */
	public static boolean setWay(int[][] map, int i, int j) {
		if (map[6][5] == 2) {
			return true;
		} else {
			if (map[i][j] == 0) {
				// 按照策略
				map[i][j] = 2; // 假定该点可以走通
				if (setWay(map, i + 1, j)) { // 向下走
					return true;
				} else if (setWay(map, i, j + 1)) { // 向右走
					return true;
				} else if (setWay(map, i - 1, j)) { // 向上走
					return true; 
				} else if (setWay(map, i, j - 1)) { // 向左走
					return true;
				} else {
					// 该点为死路
					map[i][j] = 3;
					return false;
				}
			} else { // 如果map[i][j] != 0,可能是1，2，3
				return false;
			}
		}
	}
	
	// 修改找路的策略：上 -> 右 -> 下 -> 左
	public static boolean setWay2(int[][] map, int i, int j) {
		if (map[6][5] == 2) {
			return true;
		} else {
			if (map[i][j] == 0) {
				// 按照策略
				map[i][j] = 2; // 假定该点可以走通
				if (setWay(map, i - 1, j)) { // 向上走
					return true;
				} else if (setWay2(map, i, j + 1)) { // 向右走
					return true;
				} else if (setWay2(map, i + 1, j)) { // 向下走
					return true; 
				} else if (setWay2(map, i, j - 1)) { // 向左走
					return true;
				} else {
					// 该点为死路
					map[i][j] = 3;
					return false;
				}
			} else { // 如果map[i][j] != 0,可能是1，2，3
				return false;
			}
		}
	}
}
