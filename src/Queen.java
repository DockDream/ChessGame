import java.util.ArrayList;

public class Queen extends Piece{
	
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		Rook queenRook = new Rook();
		Bishop queenBishop = new Bishop();
		
		queenRook.ReturnPossibleMoves(startRow, startColumn, currentBoard);
		
		queenBishop.ReturnPossibleMoves(startRow,startColumn, currentBoard);
		
		possibleMoves = queenRook.possibleMoves;
		possibleMoves.addAll(queenBishop.possibleMoves);
		
	}
	
	
}