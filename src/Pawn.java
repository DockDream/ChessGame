import java.util.ArrayList;


public class Pawn extends Piece {
	
	/**
	 * Constructor used to set the correct team
	 */
	Pawn(boolean sentTeam) {
		super(sentTeam);
	}


	private boolean canKill = false;
	
	/**
	 * Used as a supplement to return possibleMoves
	 */
	public ArrayList<int[]> ReturnMovesAddon(int newRow, int newColumn, Piece[][] currentBoard) {
		//make sure cell being moved to is in bounds and clear 
		ArrayList<int[]> tempList = new ArrayList<int[]>();
		int[] newLocation = new int[2];

			newLocation[0] = newRow;
			newLocation[1] = newColumn;
			//make sure in bounds
			if (newLocation[0] <= 7 && newLocation[1] <= 7 && newLocation[0] >= 0 && newLocation[1] >= 0) {
				//check if destination cell is empty
				if (canKill){
					tempList.add(newLocation);
				}
				if (currentBoard[newLocation[0]][newLocation[1]] == null) {
					tempList.add(newLocation);
				}
			}
			return tempList;
	}
	
	/**
	 * Checks if there is a piece in the intended place with the right team
	 */
	public boolean KillCheck (int startRow, int startColumn, int killRow, int killColumn, Piece[][] currentBoard){
		boolean kill = false;	
		if(killRow <= 7 && killRow >= 0 && killColumn >= 0 && killColumn <= 7){
				Piece killer = currentBoard[startRow][startColumn];
				Piece victim = currentBoard[killRow][killColumn];
				//if cell is occupied
				if (currentBoard[killRow][killColumn] != null){
					//if it is the opposite team
					if (this.team != victim.team){
						kill = true;
					}
				}
			}
			return kill;			
		}
	
	/**
	 * Used for calculating the right moves this piece can make
	 */
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard) {
		
		if (possibleMoves != null){
			possibleMoves.clear();
		}
		
		if(team){ //if white pieces
			if(startRow>0) possibleMoves = this.ReturnMovesAddon(startRow-1, startColumn, currentBoard); //move 1 up
			if(startRow==6) { //if hasn't moved yet, piece has option to move up by 2 if not blocked
				if(currentBoard[startRow-1][startColumn] == null)
				{
					possibleMoves.addAll(this.ReturnMovesAddon(startRow-2, startColumn, currentBoard));
				}
			}
			//kill opposite team 1 up to the right
			if(KillCheck(startRow, startColumn, startRow-1, startColumn+1, currentBoard)){
				canKill = true;
				possibleMoves.addAll(this.ReturnMovesAddon(startRow-1, startColumn+1, currentBoard));
			}
			//kill opposite team 1 up to the left
			if(KillCheck(startRow, startColumn, startRow-1, startColumn-1, currentBoard)){
				canKill = true;
				possibleMoves.addAll(this.ReturnMovesAddon(startRow-1, startColumn-1, currentBoard));
			}
		}else{ //if black pieces
			if(startRow<7) possibleMoves = this.ReturnMovesAddon(startRow+1, startColumn, currentBoard); //move down by 1
			if(startRow==1){//if hasn't moved yet, piece has option to move down by 2 if not blocked
				if(currentBoard[startRow+1][startColumn] == null)
				{
					possibleMoves.addAll(this.ReturnMovesAddon(startRow+2, startColumn, currentBoard));
				}
			}
			//kill opposite team 1 down to the right
			if(KillCheck(startRow, startColumn, startRow+1, startColumn+1, currentBoard)){
				canKill = true;
				possibleMoves.addAll(this.ReturnMovesAddon(startRow+1, startColumn+1, currentBoard));
			}
			//kill opposite team 1 down to the left
			if(KillCheck(startRow, startColumn, startRow+1, startColumn-1, currentBoard)){
				canKill = true;
				possibleMoves.addAll(this.ReturnMovesAddon(startRow+1, startColumn-1, currentBoard));
			}
		}
		canKill = false;
		
	}
}// end Pawn Class

	


			
		
		
