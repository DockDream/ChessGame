import java.util.ArrayList;

public class Bishop extends Piece {

	/**
	 * Constructor used to set the right team
	 */
	Bishop(boolean sentTeam) {
		super(sentTeam);
	}

	public Bishop() {
		
	}

	/**
	 * Used for calculating the right moves this piece can make
	 */
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard) {
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		//Moving towards the right down corner
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, 1, 1, currentBoard);
		
		//Moving toward the left down corner
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 1, -1, currentBoard));
		
		//Moving toward top right corner
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, -1, 1, currentBoard));
		
		//Move towards top left corner
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, -1, -1, currentBoard));
		
	}
	
	/**
	 * Used as a supplement to return possibleMoves
	 */
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
				} else if (currentBoard[cArray[0]][cArray[1]].team != 
						this.team) {
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
