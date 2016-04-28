import java.util.ArrayList;

public class Queen extends Piece{
	
	Queen(boolean sentTeam) {
		super(sentTeam);
	}

	public Queen() {
		
	}

	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard, boolean boardFlipped){
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		Rook queenRook = new Rook(currentBoard[startRow][startColumn].team);
		Bishop queenBishop = new Bishop(currentBoard[startRow][startColumn].team);
		
		queenRook.ReturnPossibleMoves(startRow, startColumn, currentBoard, boardFlipped);
		
		queenBishop.ReturnPossibleMoves(startRow,startColumn, currentBoard, boardFlipped);
		
		possibleMoves = queenRook.possibleMoves;
		possibleMoves.addAll(queenBishop.possibleMoves);
		
	}
	
	
}