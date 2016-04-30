import java.util.ArrayList;

public class Queen extends Piece{
	
	/**
	 * Constructor used to set the right team
	 */
	Queen(boolean sentTeam) {
		super(sentTeam);
	}

	public Queen() {
		
	}

	/**
	 * Used for calculating the right moves this piece can make
	 */
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		//The queen moves are the same as a combination of the bishops and rooks
		Rook queenRook = new Rook(currentBoard[startRow][startColumn].team);
		Bishop queenBishop = new Bishop(currentBoard[startRow][startColumn].team);
		
		queenRook.ReturnPossibleMoves(startRow, startColumn, currentBoard);
		
		queenBishop.ReturnPossibleMoves(startRow,startColumn, currentBoard);
		
		possibleMoves = queenRook.possibleMoves;
		possibleMoves.addAll(queenBishop.possibleMoves);
		
	}
	
	
}