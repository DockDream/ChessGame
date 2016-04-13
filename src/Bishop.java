import java.util.ArrayList;

public class Bishop extends Piece {

	Bishop(boolean sentTeam) {
		super(sentTeam);
	}

	public Bishop() {
		// TODO Auto-generated constructor stub
	}

	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard) {
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		//Moving towards the right down corner
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, 1, 1, currentBoard);
		
		//Moving toward the left down corner
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, 1, -1, currentBoard));
		
		//Moving toward top 
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, -1, 1, currentBoard));
		
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, -1, -1, currentBoard));
		
		
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
