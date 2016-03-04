import java.util.ArrayList;

public class Rook extends Piece{
	
	public ArrayList<int[]> ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		possibleMoves.clear();
		
		//The four lines below might throw an error
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, 1, 0, currentBoard);
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, -1, 0, currentBoard));
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 0, 1, currentBoard));
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 0, -1, currentBoard));
		
//		
//		//Cover everything to the left
//		for (int i = startRow - 1; i < 8; i++){
//			currentArray[0] = i;
//			currentArray[1] = startColumn;
//			
//			if (currentBoard[i][startColumn] == null){
//				possibleMoves.add(currentArray);
//			}else{
//				//adding if the piece is from a different team
//				if (currentBoard[i][startColumn].team != currentBoard[startRow][startColumn].team){
//					possibleMoves.add(currentArray);
//				}
//				break;
//			}
//		}
//		
//		//Cover everything to the right
//		for (int i = startRow - 1; i > 0; i--){
//			
//			currentArray[0] = i;
//			currentArray[1] = startColumn;
//			
//			if (currentBoard[i][startColumn] == null){
//				possibleMoves.add(currentArray);
//			}else{
//				//adding if the piece is from a different team
//				if (currentBoard[i][startColumn].team != currentBoard[startRow][startColumn].team){
//					possibleMoves.add(currentArray);
//				}
//				break;
//			}
//		}
//		
//		//Cover everything to the top
//		for (int i = startColumn - 1; i < 8; i++){
//			
//			currentArray[0] = startRow;
//			currentArray[1] = i;
//			
//			if (currentBoard[startRow][i] == null){
//				possibleMoves.add(currentArray);
//			}else{
//				//adding if the piece is from a different team
//				if (currentBoard[startRow][i].team != currentBoard[startRow][startColumn].team){
//					possibleMoves.add(currentArray);
//				}
//				break;
//			}
//		}
//		
//		//Cover everything to the top
//		for (int i = startColumn - 1; i > 0; i--){
//			
//			currentArray[0] = startRow;
//			currentArray[1] = i;
//			
//			if (currentBoard[startRow][i] == null){
//				possibleMoves.add(currentArray);
//			}else{
//				//adding if the piece is from a different team
//				if (currentBoard[startRow][i].team != currentBoard[startRow][startColumn].team){
//					currentArray[0] = i;
//					currentArray[1] = startColumn;
//					possibleMoves.add(currentArray);
//				}
//				break;
//			}
//		}
//		
		return possibleMoves;
	}
}
