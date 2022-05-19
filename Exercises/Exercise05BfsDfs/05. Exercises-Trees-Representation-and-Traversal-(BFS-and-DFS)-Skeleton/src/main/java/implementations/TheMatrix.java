// Recursive variant
//package implementations;
//
//public class TheMatrix {
//	private char[][] matrix;
//	private char fillChar;
//	private char toBeReplaced;
//	private int startRow;
//	private int startCol;
//
//
//	public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
//		this.matrix = matrix;
//		this.fillChar = fillChar;
//		this.startRow = startRow;
//		this.startCol = startCol;
//		this.toBeReplaced = this.matrix[this.startRow][this.startCol];
//	}
//
//	public void solve() {
//
//		fillMatrix(startRow, startCol);
//
//	}
//
//	private void fillMatrix(int rowIndex, int colIndex) {
//		
//		if(isOutOfBounds(rowIndex, colIndex) || this.matrix[rowIndex][colIndex] != this.toBeReplaced) {
//			return;
//		}
//
//		this.matrix[rowIndex][colIndex] = this.fillChar;
//		
//		this.fillMatrix(rowIndex + 1, colIndex);
//		this.fillMatrix(rowIndex , colIndex + 1);
//		this.fillMatrix(rowIndex - 1 , colIndex);
//		this.fillMatrix(rowIndex , colIndex - 1);
//
//		
//	}
//
//	private boolean isOutOfBounds(int rowIndex, int colIndex) {
//		
//		return rowIndex < 0 || rowIndex >= this.matrix.length || colIndex < 0 || colIndex >= this.matrix[rowIndex].length; 
//	}
//
//	public String toOutputString() {
//
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix[i].length; j++) {
//				sb.append(matrix[i][j]);
//			}
//			sb.append("\r\n");
//		}
//
//		return sb.toString().trim();
//	}
//}




// BFS Variant
//package implementations;
//
//import java.util.ArrayDeque;
//import java.util.Deque;
//
//public class TheMatrix {
//	private char[][] matrix;
//	private char fillChar;
//	private char toBeReplaced;
//	private int startRow;
//	private int startCol;
//
//
//	public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
//		this.matrix = matrix;
//		this.fillChar = fillChar;
//		this.startRow = startRow;
//		this.startCol = startCol;
//		this.toBeReplaced = this.matrix[this.startRow][this.startCol];
//	}
//
//	public void solve() {
//		
//		Deque<int[]> coordinates = new ArrayDeque<>();	
//		
//		coordinates.offer(new int[] {startRow,startCol});
//		
//		while(!coordinates.isEmpty()) {
//			int[] positions = coordinates.poll();
//			int row = positions[0];
//			int col = positions[1];
//			
//			this.matrix[row][col] = this.fillChar;
//			
//			if(isInBounds(row + 1, col) && this.matrix[row + 1][col] == this.toBeReplaced) {
//				coordinates.offer(new int[] {row+1, col});
//			}
//			
//			if(isInBounds(row - 1, col) && this.matrix[row - 1][col] == this.toBeReplaced) {
//				coordinates.offer(new int[] {row - 1, col});
//			}
//			
//			if(isInBounds(row, col + 1) && this.matrix[row][col + 1] == this.toBeReplaced) {
//				coordinates.offer(new int[] {row, col + 1 });
//			}
//			
//			if(isInBounds(row, col - 1) && this.matrix[row][col - 1] == this.toBeReplaced) {
//				coordinates.offer(new int[] {row, col - 1});
//			}
//			
//		}
//
//
//
//	}
//
//
//	private boolean isInBounds(int row, int col) { 
//		
//		return !isOutOfBounds(row, col); 
//	}
//	
//	
//
//	private boolean isOutOfBounds(int rowIndex, int colIndex) {
//		
//		return rowIndex < 0 || rowIndex >= this.matrix.length || colIndex < 0 || colIndex >= this.matrix[rowIndex].length; 
//	}
//
//	public String toOutputString() {
//
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix[i].length; j++) {
//				sb.append(matrix[i][j]);
//			}
//			sb.append("\r\n");
//		}
//
//		return sb.toString().trim();
//	}
//}



// with STACK
package implementations;

import java.util.ArrayDeque;
import java.util.Deque;

public class TheMatrix {
	private char[][] matrix;
	private char fillChar;
	private char toBeReplaced;
	private int startRow;
	private int startCol;


	public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
		this.matrix = matrix;
		this.fillChar = fillChar;
		this.startRow = startRow;
		this.startCol = startCol;
		this.toBeReplaced = this.matrix[this.startRow][this.startCol];
	}

	public void solve() {
		
		Deque<int[]> coordinates = new ArrayDeque<>();	
		
		coordinates.push(new int[] {startRow,startCol});
		
		while(!coordinates.isEmpty()) {
			int[] positions = coordinates.pop();
			int row = positions[0];
			int col = positions[1];
			
			this.matrix[row][col] = this.fillChar;
			
			
			System.out.println(this.toOutputString());
			System.out.println();
			
			
			if(isInBounds(row + 1, col) && this.matrix[row + 1][col] == this.toBeReplaced) {
				coordinates.push(new int[] {row+1, col});
			}
			
			if(isInBounds(row, col + 1) && this.matrix[row][col + 1] == this.toBeReplaced) {
				coordinates.push(new int[] {row, col + 1 });
			}
			
			if(isInBounds(row - 1, col) && this.matrix[row - 1][col] == this.toBeReplaced) {
				coordinates.push(new int[] {row - 1, col});
			}
			
			
			if(isInBounds(row, col - 1) && this.matrix[row][col - 1] == this.toBeReplaced) {
				coordinates.push(new int[] {row, col - 1});
			}
			
		}



	}


	private boolean isInBounds(int row, int col) { 
		
		return !isOutOfBounds(row, col); 
	}
	
	

	private boolean isOutOfBounds(int rowIndex, int colIndex) {
		
		return rowIndex < 0 || rowIndex >= this.matrix.length || colIndex < 0 || colIndex >= this.matrix[rowIndex].length; 
	}

	public String toOutputString() {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				sb.append(matrix[i][j]);
			}
			sb.append("\r\n");
		}

		return sb.toString().trim();
	}
}