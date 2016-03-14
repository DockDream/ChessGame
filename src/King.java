
import java.util.ArrayList;


public class King extends Piece {


	public King(){
	
	}
	
	/*public boolean castleValid(){
		TODO: by Eric
	}*/
	
	public ArrayList<int[]> KingReturnMovesAddon(int startRow, int startColumn, 
		int newRow, int newColumn, Piece[][] currentBoard) {
		
		//make sure cell being moved to is in bounds and can be taken

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		int[] newLocation = new int[2];

			newLocation[0] = newRow;
			newLocation[1] = newColumn;

			if (newLocation[0] <= 7 && newLocation[1] <= 7 && newLocation[0] >= 0 && newLocation[1] >= 0) {
				if (currentBoard[newLocation[0]][newLocation[1]] == null) {
					tempList.add(newLocation);					
				} else if (currentBoard[newLocation[0]][newLocation[1]].team !=currentBoard[startRow][startColumn].team) {
					tempList.add(newLocation);					
				}			
			}		
		return tempList;
	}
	
	public void ReturnPossibleMoves(int startRow, int startColumn, int destRow, int destColumn, Piece[][] currentBoard) {
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		//move down by 1
		possibleMoves = this.KingReturnMovesAddon(startRow, startColumn, startRow+1, startColumn, currentBoard);
		//move up by 1
		possibleMoves.addAll(this.KingReturnMovesAddon(startRow, startColumn, startRow-1, startColumn, currentBoard));
		//move right by 1
		possibleMoves.addAll(this.KingReturnMovesAddon(startRow, startColumn, startRow, startColumn+1, currentBoard));
		//move left by 1
		possibleMoves.addAll(this.KingReturnMovesAddon(startRow, startColumn, startRow, startColumn-1, currentBoard));
		//move down and right by 1
		possibleMoves.addAll(this.KingReturnMovesAddon(startRow, startColumn, startRow+1, startColumn+1, currentBoard));
		//move down and left by 1
		possibleMoves.addAll(this.KingReturnMovesAddon(startRow, startColumn, startRow+1, startColumn-1, currentBoard));
		//move up and right by 1
		possibleMoves.addAll(this.KingReturnMovesAddon(startRow, startColumn, startRow-1, startColumn+1, currentBoard));
		//move up and left by 1
		possibleMoves.addAll(this.KingReturnMovesAddon(startRow, startColumn, startRow-1, startColumn-1, currentBoard));
		
	}
}