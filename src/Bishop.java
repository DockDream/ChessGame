import java.util.ArrayList;

public class Bishop extends Piece{

	
	public ArrayList<int[]> PossibleMoves(int startRow, int startColumn, Piece[][] currentBoard){
		possibleMoves.clear();
		
		int[] currentArray = new int[2];
		
		currentArray[0] = startRow;
		currentArray[1] = startColumn;
		
		//Incrementing both row and column
		for (int i = 0; i < 8; i++){
			currentArray[0]++;
			currentArray[1]++;
			
			if (currentArray[0] >= 8 && currentArray[1] >= 8){
				break;
			}
			
			
			
			
		}
		
		//Decrementing both row and column
		for (int i = 7; i > 0; i++){
			
		}
		
		
		return possibleMoves;
	}
	
}
