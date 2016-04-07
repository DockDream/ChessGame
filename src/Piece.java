import java.util.ArrayList;

public abstract class Piece {
	
	Piece (boolean sentTeam){
		this.team = sentTeam;
	}
	
	Piece(){
		
	}

	boolean team;
	ArrayList<int[]> possibleMoves = null; // store all possible moves for each
											// piece

	// team = true means white
	// team = false means black

	//returning a boolean saying whether the move is valid or not.
	public boolean ValidMove(int destRow, int destColumn) {
		for (int i = 0; i < possibleMoves.size(); i++) {
			if (possibleMoves.get(i)[0] == destRow && possibleMoves.get(i)[1] == destColumn) {
				return true;
			}
		}

		return false;
	}
	
	public boolean inBounds(int destRow, int destCol){
		if (destRow <= 7 && destCol <= 7 && destRow >= 0 && destRow >= 0){
			return true;
		}
		return false;
	}

	//Just making a list of all the possible moves that the respective piece can perform
	public void ReturnPossibleMoves(int startRow, int startColumn, Piece[][] currentBoard) {
		
		// Array = [row, column]
	}

	//Expanded and used by most pieces as a function to add all the moves
	public ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, Piece[][] currentBoard) {

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		return tempList;
		
	}
	
	//Method to printPossibleMoves for testing purposes
	public void PrintPossibleMovesList(ArrayList<int[]> ListofItems){
		if (ListofItems == null){
			System.out.println("There is nothing in possibleMoves");
			return;
		}
		
		for (int i =0; i < ListofItems.size();i++){
			System.out.println("Row: "+ListofItems.get(i)[0]+" Column: "+ListofItems.get(i)[1]);
		}
	}
}
