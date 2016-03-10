import java.util.ArrayList;

public class Rook extends Piece{
	
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		//The four lines below might throw an error
		
		System.out.println("startRow: "+startRow+" startColumn: "+startColumn);
		
		//Moving to the up
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, -1, 0, currentBoard);
		
		
		//Move Down
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 1, 0, currentBoard));
		
		
		//Moving to the right
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 0, 1, currentBoard));
		
		
		//Moving to the left
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 0, -1, currentBoard));

	}
}
