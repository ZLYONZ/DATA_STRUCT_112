package algorithm;

public class DivideAndConquer {

	public static void main(String[] args) { // Tower of Hanoi 

		DAC(5, 'A', 'B', 'C');
	}

	// 汉诺塔的移动方案
	// 分治算法
	public static void DAC(int num, char a, char b, char c) {
		// 如果只有一个盘
		if (num == 1) {
			System.out.println("第1个盘从 " + a + "->" + c);
		} else {
			// 假如有num>2个盘，可以看作是 1）最下面的一个盘 2) 上面的所有盘
			
			// 1. 先把上面的所有盘 A->B
			DAC(num - 1, a, c, b);
			
			// 2. 再把最下面的盘 A->C
			System.out.println("第" + num + "个盘从 " + a + "->" + c);
			
			// 3. 再把B塔的所有盘 B->C
			DAC(num - 1, b, a, c);
		}
	}
}
