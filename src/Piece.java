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

	//Just making a list of all the possible moves that the respectiv piece can perform
	public void ReturnPossibleMoves() {
		
		// Array = [row, column]
	}

	//Checking to see if the king of the opposite team is in check
	public boolean KingCheck(Piece[][] currentBoard) {
		this.ReturnPossibleMoves();
		for (int i = 0; i < possibleMoves.size(); i++) {
			if (currentBoard[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]] instanceof King
					&& currentBoard[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]].team == this.team) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<int[]> ReturnMovesAddon(int startRow, int startColumn, 
			int rowIncrement, int columnIncrement, Piece[][] currentBoard) {

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		tempList.clear();
		
		int[] currentArray = { startRow, startColumn };

		for (int i = 0; i < 8; i++) {

			currentArray[0] = currentArray[0] + rowIncrement;
			currentArray[1] = currentArray[1] + columnIncrement;

			
			if (currentArray[0] <= 7 && currentArray[1] <= 7 && 
					currentArray[0] >= 0 && currentArray[1] >= 0) {
				
				if (currentBoard[currentArray[0]][currentArray[1]] == null) {
					tempList.add(currentArray);
				} else if (currentBoard[currentArray[0]][currentArray[1]].team != 
						currentBoard[startRow][startColumn].team) {
					tempList.add(currentArray);
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
