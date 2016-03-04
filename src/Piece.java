import java.util.ArrayList;


public abstract class Piece {
	
	boolean team;
	ArrayList<int[]> possibleMoves; //store all possible moves for each piece
	
	
	//team = true means white
	//team = false means black
	
	
	public boolean ValidMove(int destRow, int destColumn){
		for (int i = 0; i < possibleMoves.size(); i++){
			if (possibleMoves.get(i)[0] == destRow && possibleMoves.get(i)[1] == destColumn){
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<int[]> PossibleMoves(){
		return null;
		//Array = [row, column]
	}
	
	public boolean KingCheck(Piece[][] currentBoard){
		
		for (int i = 0; i < possibleMoves.size(); i++){
			if (currentBoard[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]] instanceof King
					&& currentBoard[possibleMoves.get(i)[0]][possibleMoves.get(i)[1]].team == this.team){
				return true;
			}
		}
		
		return false;
	}
}
