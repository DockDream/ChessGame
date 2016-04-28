
import java.util.ArrayList;


public class Knight extends Piece {
	
	Knight(boolean sentTeam) {
		super(sentTeam);
	}

	public Knight() {
		
	}

	private ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, 
			int newRow, int newColumn, Piece[][] currentBoard) {

			//make sure cell being moved to is in bounds and can be taken
			ArrayList<int[]> tempList = new ArrayList<int[]>();
			int[] newLocation = new int[2];

				newLocation[0] = newRow;
				newLocation[1] = newColumn;

				if (newLocation[0] <= 7 && newLocation[1] <= 7 && newLocation[0] >= 0 && newLocation[1] >= 0) {
					if (currentBoard[newLocation[0]][newLocation[1]] == null) {
						tempList.add(newLocation);					
					} else if (currentBoard[newLocation[0]][newLocation[1]].team != this.team) {
						tempList.add(newLocation);					
					}			
				}		
			return tempList;
		}
	
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard, boolean boardFlipped) {
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		//move up 2 and 1 to the left
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, startRow-2, startColumn-1, currentBoard);
		//move up 2 and 1 to the right
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow-2, startColumn+1, currentBoard));
		//move down 2 and 1 to the left
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow+2, startColumn-1, currentBoard));
		//move down 2 and 1 to the right
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow+2, startColumn+1, currentBoard));
		//move up 1 and 2 to the right
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow-1, startColumn+2, currentBoard));
		//move up 1 and 2 to the left
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow-1, startColumn-2, currentBoard));
		//move down 1 and 2 to the left
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow+1, startColumn-2, currentBoard));
		//move down 1 and 2 to the right
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow+1, startColumn+2, currentBoard));
		
	}
		

}//end Knight Class
