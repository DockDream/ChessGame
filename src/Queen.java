import java.util.ArrayList;

public class Queen extends Piece{
	
	public ArrayList<int[]> ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		possibleMoves.clear();
//		ArrayList<int[]> bishopMoves;
//		
		Rook queenRook = new Rook();
		Bishop queenBishop = new Bishop();
		
		possibleMoves = queenRook.ReturnPossibleMoves(startRow, startColumn, currentBoard);
		
		possibleMoves.addAll(queenBishop.ReturnPossibleMoves(startRow,startColumn,currentBoard));
		
		
//		bishopMoves = queenBishop.ReturnPossibleMoves(startRow,startColumn,currentBoard);
//		
//		for (int i = 0; i < bishopMoves.size(); i++){
//			possibleMoves.add(bishopMoves.get(i));
//		}
//		
		return possibleMoves;
	}
	
	
}