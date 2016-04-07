import java.util.ArrayList;

public class Queen extends Piece{
	
	Queen(boolean sentTeam) {
		super(sentTeam);
	}

	public Queen() {
		// TODO Auto-generated constructor stub
	}

	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		Rook queenRook = new Rook(currentBoard[startRow][startColumn].team);
		Bishop queenBishop = new Bishop(currentBoard[startRow][startColumn].team);
		
		queenRook.ReturnPossibleMoves(startRow, startColumn, currentBoard);
		
		queenBishop.ReturnPossibleMoves(startRow,startColumn, currentBoard);
		
		possibleMoves = queenRook.possibleMoves;
		possibleMoves.addAll(queenBishop.possibleMoves);
		
	}
	
	
}