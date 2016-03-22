

public class Queen extends Piece{
	
	public void ReturnPossibleMoves(int startRow, int startColumn, int destRow, int destColumn, Piece[][] currentBoard){
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
//		ArrayList<int[]> bishopMoves;
//		
		Rook queenRook = new Rook();
		Bishop queenBishop = new Bishop();
		
		queenRook.ReturnPossibleMoves(startRow, startColumn, destRow, destColumn, currentBoard);
		
		queenBishop.ReturnPossibleMoves(startRow,startColumn,destRow, destColumn, currentBoard);
		
		possibleMoves = queenRook.possibleMoves;
		possibleMoves.addAll(queenBishop.possibleMoves);
		
		
//		bishopMoves = queenBishop.ReturnPossibleMoves(startRow,startColumn,currentBoard);
//		
//		for (int i = 0; i < bishopMoves.size(); i++){
//			possibleMoves.add(bishopMoves.get(i));
//		}
//		
	}
	
	
}