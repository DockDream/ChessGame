import java.util.ArrayList;

public class Bishop extends Piece {

	Bishop(boolean sentTeam) {
		super(sentTeam);
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
	
	public ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, 
			int rowIncrement, int columnIncrement, Piece[][] currentBoard) {

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		
		int[] currentArray = new int[2];
		
		currentArray[0] = startRow;
		currentArray[1] = startColumn;

		for (int i = 0; i < 8; i++){

			currentArray[0] = currentArray[0] + rowIncrement;
			currentArray[1] = currentArray[1] + columnIncrement;
			
			if (currentArray[0] <= 7 && currentArray[1] <= 7 && 
					currentArray[0] >= 0 && currentArray[1] >= 0) {
				
				if (currentBoard[currentArray[0]][currentArray[1]] == null) {
					tempList.add(new int[] {currentArray[0],currentArray[1]});
				} else if (currentBoard[currentArray[0]][currentArray[1]].team != 
						currentBoard[startRow][startColumn].team) {
					tempList.add(new int[] {currentArray[0],currentArray[1]});
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
