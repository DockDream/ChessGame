import java.util.ArrayList;

public class Bishop extends Piece {

	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard) {
		possibleMoves.clear();
		
		//Moving towards the right down corner
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, 1, 1, currentBoard);
		
		//Moving toward the left down corner
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 1, -1, currentBoard));
		
		//Moving toward top 
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, -1, 1, currentBoard));
		
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, -1, -1, currentBoard));
		
//		// Just bottom right
//		for (int i = 0; i < 8; i++) {
//			currentArray[0]++;
//			currentArray[1]++;
//
//			if (currentArray[0] <= 7 && currentArray[1] <= 7) {
//				if (currentBoard[currentArray[0]][currentArray[1]] == null) {
//					possibleMoves.add(currentArray);
//				} else if (currentBoard[currentArray[0]][currentArray[1]].team != currentBoard[startRow][startColumn].team) {
//					possibleMoves.add(currentArray);
//					break;
//				} else {
//					break;
//				}
//			} else{
//				break;
//			}
//		}
//		
//		// Just bottom right
//		for (int i = 0; i < 8; i++) {
//			currentArray[0]--;
//			currentArray[1]--;
//
//			if (currentArray[0] <= 7 && currentArray[1] <= 7) {
//				if (currentBoard[currentArray[0]][currentArray[1]] == null) {
//					possibleMoves.add(currentArray);
//				} else if (currentBoard[currentArray[0]][currentArray[1]].team != currentBoard[startRow][startColumn].team) {
//					possibleMoves.add(currentArray);
//					break;
//				} else {
//					break;
//				}
//			} else{
//				break;
//			}
//		}
//
//		// Decrementing both row and column
//		for (int i = 7; i > 0; i++) {
//
//		}
//
	}

}
