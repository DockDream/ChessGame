import java.util.ArrayList;

public class Rook extends Piece{
	
	
	Rook(boolean sentTeam) {
		super(sentTeam);
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
				
//				if (currentBoard[startRow][startColumn] != null){
//				System.out.println("Class: "+currentBoard[startRow][startColumn].getClass().toString());
//				}else{
//					System.out.println("startRow: "+startRow+" startColumn: "+startColumn);
//				}
				if (currentBoard[currentArray[0]][currentArray[1]] == null) {
					tempList.add(new int[] {currentArray[0],currentArray[1]});
				} else if (currentBoard[currentArray[0]][currentArray[1]].team != 
						this.team) {
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
