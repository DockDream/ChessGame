
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
}//end King Class
		
	
	//how the king moves
	
	/*boolean kingValid = true;
	
	public boolean kingMoveValid (int startRow, int startColumn, int destRow, int destColumn){
		
		if (notInCheck() && piceceNotBlocked()){ //still need to figure out these methods
	
			if (startRow+1 == destRow && startColumn == destColumn) {
				//move down by one
				kingValid = true;
			}else if(startRow == destRow && startColumn+1 == destColumn){
				//move right by one
				kingValid = true;
			}else if(startRow-1 == destRow && startColumn == destColumn){
				//move up by one
				kingValid = true;
			}else if (startRow == destRow && startColumn-1 == destColumn){
				//move left by one
				kingValid = true;
			}else if(startRow+1 == destRow && startColumn+1 == destColumn){
				//move diagonal down and to the right by one
				kingValid = true;
			}else if(startRow+1 == destRow && startColumn-1 == destColumn){
				//move diagonal down and to the left by one
				kingValid = true;
			}else if (startRow-1 == destRow && startColumn+1 == destColumn){
				//move diagonal up and to the right by one
				kingValid = true;
			}else if (startRow-1 == destRow && startColumn-1 == destColumn){
				//move diagonal up and to the left by one
				kingValid = true;
			}else{
				//move is not valid
				kingValid = false;
			}	
		} else{
			kingValid = false;
		}
		return kingValid;
	}
	
	still need to work on this
	public boolean castleValid(){

	}*/

