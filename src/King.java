
import java.util.ArrayList;

public class King extends Piece {

	boolean castleValid;

	public King() {

	}

	/*
	 * public boolean castleValid(){ TODO: by Eric }
	 */

	public boolean KingCheckClass(Piece[][] currentBoard, Object q1, Object q2, 
			int rowIncrement, int columnIncrement, int currentRow, int currentColumn){
		//Moving down: rowIncrement=1 columnIncrement = 0
		
		int c1 = currentRow;
		int c2 = currentColumn;
		
		for(int i=0; i < 8; i++){
			currentRow = currentRow+rowIncrement;
			currentColumn = currentColumn+columnIncrement;
			
			if (currentRow <= 7 && currentColumn <= 7 && 
					currentRow >= 0 && currentColumn >= 0){
				break;
			}
			if (currentBoard[currentRow][currentColumn] == null){
				continue;
			}
			
			if(currentBoard[currentRow][currentColumn].team == currentBoard[c1][c2].team){
				return false;
			}else{
				if (currentBoard[currentRow][currentColumn].getClass().equals(q1.getClass())||
						currentBoard[currentRow][currentColumn].getClass().equals(q2.getClass())){
					return true;
				}else{
					return false;
				}
			}
		}
		
		return false;
	}

	public ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, int newRow, int newColumn,
			Piece[][] currentBoard) {

		// make sure cell being moved to is in bounds and can be taken

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		int[] newLocation = new int[2];

		newLocation[0] = newRow;
		newLocation[1] = newColumn;

		if (newLocation[0] <= 7 && newLocation[1] <= 7 && newLocation[0] >= 0 && newLocation[1] >= 0) {
			if (currentBoard[newLocation[0]][newLocation[1]] == null) {
				tempList.add(newLocation);
			} else if (currentBoard[newLocation[0]][newLocation[1]].team != currentBoard[startRow][startColumn].team) {
				tempList.add(newLocation);
			}
		}
		return tempList;
	}

	public void ReturnPossibleMoves(int startRow, int startColumn,
			Piece[][] currentBoard) {
		if (possibleMoves != null) {
			possibleMoves.clear();
		}

		// move down by 1
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, startRow + 1, startColumn, currentBoard);
		// move up by 1
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow - 1, startColumn, currentBoard));
		// move right by 1
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow, startColumn + 1, currentBoard));
		// move left by 1
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow, startColumn - 1, currentBoard));
		// move down and right by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow + 1, startColumn + 1, currentBoard));
		// move down and left by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow + 1, startColumn - 1, currentBoard));
		// move up and right by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow - 1, startColumn + 1, currentBoard));
		// move up and left by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow - 1, startColumn - 1, currentBoard));

		//Only add 2 moves to the left or 2 moves to the right is castle is valid
//		if (castleValid && !currentBoard[startRow][startColumn].isKingInCheck()){
//			if (currentBoard[startRow][startColumn+1] == null && currentBoard[startRow][startColumn+2] == null){
//				currentBoard[startRow][startColumn+1] = currentBoard[startRow][startColumn];
//				if (!currentBoard[startRow][startColumn+1].isKingInCheck()){
//					currentBoard[startRow][startColumn+2] = currentBoard[startRow][startColumn+1];
//					if (!currentBoard[startRow][startColumn+2].isKingInCheck()){
//						possibleMoves.add(new int[] {currentRow,currentColumn+2});
//					}
//				}
//			}
//			
//			if (currentBoard[startRow][startColumn-1] == null && currentBoard[startRow][startColumn-2] == null){
//				currentBoard[startRow][startColumn-1] = currentBoard[startRow][startColumn];
//				if (!currentBoard[startRow][startColumn-1].isKingInCheck()){
//					currentBoard[startRow][startColumn-2] = currentBoard[startRow][startColumn-1];
//					if (!currentBoard[startRow][startColumn-2].isKingInCheck()){
//						possibleMoves.add(new int[] {currentRow,currentColumn-2});
//					}
//				}
//			}	
//		}
	}
}
