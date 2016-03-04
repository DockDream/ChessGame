
public class Game {
	
	Piece [][] ChessBoard = new Piece[8][8];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Game FirstGame = new Game();
		int ClickedRow = 0;
		int ClickedColumn = 0;
		
		FirstGame.InitializeGame();
		
		
		if (ClickedRow<=8 && ClickedRow>=0 && ClickedColumn<=8 && ClickedColumn >=0){
			//Check if first click is the same as the second click then just return a true;
		}else{
			System.out.println("ClickedRow and ClickedColumn Values sent are invalid");
		}
		
		
		
	}
	
	//Starts the Board at Initial Configuration
	public void InitializeGame(){
		ChessBoard[0][0] = new Rook();
		ChessBoard[0][1] = new Rook();
		ChessBoard[0][2] = new Horse();
		ChessBoard[0][3] = new Bishop();
		ChessBoard[0][4] = new Queen();
		ChessBoard[0][5] = new King();
		ChessBoard[0][6] = new Bishop();
		ChessBoard[0][7] = new Horse();
		
		for (int i = 0; i < 8; i++){
			ChessBoard[1][i] = new Pawn();
		}
	}
	
	
	
	

}
