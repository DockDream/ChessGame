import java.util.ArrayList;

public class Rook extends Piece{
	
	
	Rook(boolean sentTeam) {
		super(sentTeam);
	}

	public Rook() {
		
	}

	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		//Moving to the up
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, -1, 0, currentBoard);
		
		//Move Down
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 1, 0, currentBoard));
		
		//Moving to the right
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 0, 1, currentBoard));
		
		//Moving to the left
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 0, -1, currentBoard));
	}
	
	
	private ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, 
			int rowIncrement, int columnIncrement, Piece[][] currentBoard) {

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		
		int[] cArray = new int[2];
		
		cArray[0] = startRow;
		cArray[1] = startColumn;

		for (int i = 0; i < 8; i++){

			cArray[0] = cArray[0] + rowIncrement;
			cArray[1] = cArray[1] + columnIncrement;
			
			if (this.inBounds(cArray[0], cArray[1])) {
				if (currentBoard[cArray[0]][cArray[1]] == null) {
					tempList.add(new int[] {cArray[0],cArray[1]});
				} else if (currentBoard[cArray[0]][cArray[1]].getTeam() != 
						this.getTeam()) {
					tempList.add(new int[] {cArray[0],cArray[1]});
					break;
				} else {
					
					break;
				}
			} else {
				break;
			}
		}
		return tempList;
		
	}
}
