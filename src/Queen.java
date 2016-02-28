
public class Queen extends Piece{

	
	@SuppressWarnings("unused")
	public boolean validmove(int startrow, int startcolumn, int destrow, int destcolumn, Piece[][] CurrentBoard){
		
		int diffrow = destrow - startrow;
		int diffcolumn = destcolumn - startcolumn;
		
		//if the piece isn't moving
		if (diffcolumn == 0 && diffrow == 0){
			return true;
			
		//If the piece is moving to the right
		}else if(diffcolumn > 0 && diffrow  == 0) {
			
			//Checking all the columns in between except the destination and returns false if something is in between
			for (int i = startcolumn; i < diffcolumn-1; i++){
				//if there is no piece
				if (CurrentBoard[startrow][i] == null){
					
				} else{
						return false;
					
					}
				}
		
			if (CurrentBoard[destrow][destcolumn].team = CurrentBoard[startrow][startcolumn].team){
				return false;
			}
			
			return true;
		
			//	Moving to the left
		}else if (diffcolumn < 0 && diffrow  == 0) {
			
//			for (int i = startcolumn; i < diffcolumn; i--){
//				//if there is no piece
//				if (CurrentBoard[startrow][i] == null){
//					
//				} else{
//						return false;
//					
//					}
//			}
//			
//			if (CurrentBoard[destrow][destcolumn].team = CurrentBoard[startrow][startcolumn].team){
//				return false;
//			}
//			
//			return true;
			
				
		}else if (diffrow != 0 && diffcolumn == 0){
			
			
			
			
		}
		
		
		
		
		
		return false;
	}
	
	public boolean IsKingInCheck(){
		
		return true;
		
	}
	
}