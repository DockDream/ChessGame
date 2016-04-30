import java.util.ArrayList;

/*
* The entire class below is used as a base for the other pieces
*/
public abstract class Piece {
	
	/**
	 * Constructor used to set the correct team
	 */
	Piece (boolean sentTeam){
		this.team = sentTeam;
	}
	
	Piece(){
		
	}

	protected boolean team;
	// team = true means white
	// team = false means black
	
	protected ArrayList<int[]> possibleMoves = null; // store all possible moves for each
											// piece

	/**
	 * Returns true if row and column sent is within 
	 * the already generated possiblemoves.
	 */
	public boolean ValidMove(int destRow, int destColumn) {
		for (int i = 0; i < possibleMoves.size(); i++) {
			if (possibleMoves.get(i)[0] == destRow && possibleMoves.get(i)[1] == destColumn) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Used to check if the tile being considered is inbounds
	 * of the board.
	 */
	protected boolean inBounds(int destRow, int destCol){
		if (destRow <= 7 && destCol <= 7 && destRow >= 0 && destCol >= 0){
			return true;
		}
		return false;
	}

	/**
	 * Used for calculating the right moves this piece can make
	 */
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard) {
		
		// Array = [row, column]
	}

	/**
	 * Used as a supplement to return possibleMoves
	 */
	protected ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, Piece[][] currentBoard) {

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		return tempList;
		
	}
	
	/**
	 * Used for troubleshooting problems
	 */
	protected void PrintPossibleMovesList(ArrayList<int[]> ListofItems){
		if (ListofItems == null){
			System.out.println("There is nothing in possibleMoves");
			return;
		}
		
		for (int i =0; i < ListofItems.size();i++){
			System.out.println("Row: "+ListofItems.get(i)[0]+" Column: "+ListofItems.get(i)[1]);
		}
	}
	
	public boolean getTeam(){
		return this.team;
	}
}
