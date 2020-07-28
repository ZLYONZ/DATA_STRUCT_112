package linkedlist;

public class SparseArray {

	public static void main(String[] args) {
		int chessArr1[][] = new int [11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		
		for(int[] row : chessArr1) {
			for(int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}	
	}
}


