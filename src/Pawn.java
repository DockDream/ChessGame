
import java.util.ArrayList;


public class Pawn extends Piece {
	
	public Pawn(){
		
	}
	
	public ArrayList<int[]> PawnReturnMovesAddon(int newRow, int newColumn, Piece[][] currentBoard) {
		//make sure cell being moved to is in bounds and clear 
		ArrayList<int[]> tempList = new ArrayList<int[]>();
		int[] newLocation = new int[2];

			newLocation[0] = newRow;
			newLocation[1] = newColumn;

			if (newLocation[0] <= 7 && newLocation[1] <= 7 && newLocation[0] >= 0 && newLocation[1] >= 0) {
				if (currentBoard[newLocation[0]][newLocation[1]] == null) {
					tempList.add(newLocation);
				}
			}
			return tempList;
	}
					
	
	public ArrayList<int[]> ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard) {
		possibleMoves.clear();
		
		if(team){ //if white pieces
			if(startRow>0) possibleMoves = this.PawnReturnMovesAddon(startRow-1, startColumn, currentBoard); //move 1 up
			if(startRow==6) { //if hasn't moved yet, piece has option to move up by 2 if not blocked
				if(currentBoard[startRow-1][startColumn] == null)
				{
					possibleMoves.addAll(this.PawnReturnMovesAddon(startRow-2, startColumn, currentBoard));
				}
			}
		}else{ //if black pieces
			if(startRow<6) possibleMoves = this.PawnReturnMovesAddon(startRow+1, startColumn, currentBoard); //move down by 1
			if(startRow==1){//if hasn't moved yet, piece has option to move down by 2 if not blocked
				if(currentBoard[startRow+1][startColumn] == null)
				{
					possibleMoves.addAll(this.PawnReturnMovesAddon(startRow+2, startColumn, currentBoard));
				}
			}
		}
		return possibleMoves;
	}
}// end Pawn Class

	/*boolean pawnValid = true;
	
	public boolean pawnMoveValid (int startRow, int startColumn, int destRow, int destColumn){	

		//white piece movements
		if (team = true && pieceNotBlocked()){
			//if piece is still on front line and hasn't been moved
			if(startRow == 1){ 
				if (startRow+2 == destRow && startColumn == destColumn) {
					//move down by two is possible
					pawnValid = true;
				}
			}else if (startRow+1 == destRow && startColumn == destColumn) {
					//move down by one
					pawnValid = true;
			}else{
				pawnValid = false;
			}
			
		//white diagonal kill moves
		}else if (team = true && canKillDiagonally()){
			if (startRow+1 == destRow && startColumn+1 == destColumn){
				//kill diagonally moving down and to the right
				pawnValid = true;
			}else if (startRow+1 == destRow && startColumn-1 == destColumn){
				//kill diagonally moving down and to the left
				pawnValid = true;
			}
		
		//black movements only
		}else if(team = false && pieceNotBlocked()){ 
			//if piece is still on front line and hasn't been moved
			if (startRow == 7){
				if (startRow-2 == destRow && startColumn == destColumn) {
					//move up by two
					pawnValid = true;
				}
			}else if (startRow-1 == destRow && startColumn == destColumn) {
					//move up by one
					pawnValid = true;			
			}else{
				pawnValid = false;
			}
			
		//black diagonal kill moves
		}else if (team = false && canKillDiagonally()){
				if (startRow-1 == destRow && startColumn+1 == destColumn){
					//kill diagonally moving up and to the right
					pawnValid = true;
				}else if (startRow-1 == destRow && startColumn-1 == destColumn){
					//kill diagonally moving up and to the left
					pawnValid = true;
				}
		}else{
			pawnValid = false;
		}
		
		return pawnValid;
	}*/
	


			
		
		
