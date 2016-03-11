import java.util.ArrayList;

public abstract class Piece {

	boolean team;
	ArrayList<int[]> possibleMoves = null; // store all possible moves for each
											// piece

	// team = true means white
	// team = false means black
	
	
	//Sets the team of the piece
	public void setTeam(boolean sentTeam){
		this.team = sentTeam;
		
	}

	//returning a boolean saying whether the move is valid or not.
	public boolean ValidMove(int destRow, int destColumn) {
		for (int i = 0; i < possibleMoves.size(); i++) {
			if (possibleMoves.get(i)[0] == destRow && possibleMoves.get(i)[1] == destColumn) {
				return true;
			}
		}

		return false;
	}

	//Just making a list of all the possible moves that the respective piece can perform
	public void ReturnPossibleMoves(int startRow, int startColumn, int destRow, int destColumn, Piece[][] currentBoard) {
		
		// Array = [row, column]
	}

	//Checking to see if the king of the opposite team is in check
	public boolean KingCheck(int startRow, int startColumn, int destRow,int destColumn, Piece[][] currentBoard) {
		this.ReturnPossibleMoves(startRow, startColumn, destRow,destColumn,currentBoard);
		for (int i = 0; i < possibleMoves.size(); i++) {
			if (currentBoard[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]] instanceof King
					&& currentBoard[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]].team == this.team) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, 
			int rowIncrement, int columnIncrement, int destRow, int destColumn, Piece[][] currentBoard) {

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		
		int[] currentArray = new int[2];
		
		currentArray[0] = startRow;
		currentArray[1] = startColumn;
		
		int[] tempArray = new int[2];

		while(currentArray[0] != destRow && currentArray[1] != destColumn) {

			currentArray[0] = currentArray[0] + rowIncrement;
			currentArray[1] = currentArray[1] + columnIncrement;

			
			
			if (currentArray[0] <= 7 && currentArray[1] <= 7 && 
					currentArray[0] >= 0 && currentArray[1] >= 0) {
				
				if (currentBoard[currentArray[0]][currentArray[1]] == null) {
					System.out.println("Adding empty spaces");
					System.out.println("Row: "+currentArray[0]+" Column: "+currentArray[1]);
					tempArray[0] = currentArray[0];
					tempArray[1] = currentArray[1];
					tempList.add(tempArray); 
				} else if (currentBoard[currentArray[0]][currentArray[1]].team != 
						currentBoard[startRow][startColumn].team) {
					tempArray[0] = currentArray[0];
					tempArray[1] = currentArray[1];
					tempList.add(tempArray);
					break;
				} else {
					
					break;
				}
			} else {
				break;
			}
		}
		this.PrintPossibleMovesList(tempList);
		return tempList;
		
	}
	
	//Method to printPossibleMoves for testing purposes
	public void PrintPossibleMovesList(ArrayList<int[]> ListofItems){
		System.out.println("");
		if (ListofItems == null){
			System.out.println("There is nothing in possibleMoves");
			return;
		}
		
		for (int i =0; i < ListofItems.size();i++){
			System.out.println("Row: "+ListofItems.get(i)[0]+" Column: "+ListofItems.get(i)[1]);
		}
		System.out.println("");
	}
}
