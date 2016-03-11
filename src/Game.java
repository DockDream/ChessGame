import java.util.ArrayList;

public class Game {
	
	Piece [][] ChessBoard = new Piece[8][8];
	Piece selectedPiece;
	boolean whoseTurn; //true = white, false = black
	int[] saved1stClick;
	int[] saved2ndClick;
	
	//Starts the Board at Initial Configuration
	public void InitializeGame(){
		this.whoseTurn = true;
		ChessBoard[0][0] = new Rook();
		ChessBoard[0][1] = new Knight();
		ChessBoard[0][2] = new Bishop();
		ChessBoard[0][3] = new Queen();
		ChessBoard[0][4] = new King();
		ChessBoard[0][5] = new Bishop();
		ChessBoard[0][6] = new Knight();
		ChessBoard[0][7] = new Rook();
		
		for (int i = 0; i < 8; i++){
			//Below three lines are setting up the Black team
			ChessBoard[1][i] = new Pawn();
			ChessBoard[0][i].setTeam(false);
			ChessBoard[1][i].setTeam(false);
			
			//Below three lines are setting up the White team
			ChessBoard[6][i] = new Pawn();
			ChessBoard[7][i] = ChessBoard[0][i];
			ChessBoard[6][i].setTeam(true);
			ChessBoard[7][i].setTeam(true);
		}	
	}
	
	
	//Making sure that the team that is clicking the piece is
	public boolean ValidateRightTeam(int[] click){
		if (ChessBoard[click[0]][click[1]].team == whoseTurn){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	Checking to see if there are any pieces at the clicked location
	
	*/
	public boolean IsPieceAtLocation(int[] click){
		if (ChessBoard[click[0]][click[1]] == null){
			return false;
		}else{
			return true;
		}
	}
	
	//Handling all the first click functions 
	// and returning true if it is valid click otherwise returning false
	public boolean FirstClick(int[] click){
		saved1stClick = null;
		
		System.out.println("1st Click");
		
		if (this.IsPieceAtLocation(click)){
			if (ChessBoard[click[0]][click[1]].team == whoseTurn){
				//Turn and team evaluated
				saved1stClick = click;
				return true;
			}else{
				//TODO: Show a message that says not right team
				System.out.println("Not your turn");
				return false;
				
			}
		}else{
			//TODO: Show message saying that they didn't click the right piece
			System.out.println("Clicked an empty spot");
			
			return false;
		}
	}
	
	
	//handling all the second click functions
	// returning true if it is a valid click otherwise returning false
	public boolean SecondClick(int[] click){
		
		if (saved1stClick == null){
			System.out.println("Second click function is being called before first click is called");
			return false;
		}
		
		saved2ndClick = click;
		
		System.out.println("2nd Click");
		
		//If the first click is the same as the second click
		if (saved2ndClick[0] == saved1stClick[0] && saved2ndClick[1] == saved1stClick[1]){
			//The piece did not move;
		}
		
		
		//First Set possible moves
		ChessBoard[saved1stClick[0]][saved1stClick[1]].ReturnPossibleMoves(saved1stClick[0],saved1stClick[1], saved2ndClick[0], saved2ndClick[1], ChessBoard);
		
		
		if (ChessBoard[saved1stClick[0]][saved1stClick[1]].ValidMove(click[0], click[1])){
			//Move the respective piece to its destination
			//TODO: Add code for front end to move the piece
			
			System.out.println("Valid 2nd Click");
			
			//Make the piece in the first click place to the second click place so that our 2d array is up to date
			ChessBoard[saved2ndClick[0]][saved2ndClick[1]] = ChessBoard[saved1stClick[0]][saved1stClick[1]];
			
			//make the piece in our first click to be null
			ChessBoard[saved1stClick[0]][saved1stClick[1]] = null;
			
			//Change turns
			if (whoseTurn){
				whoseTurn = false;
			}else{
				whoseTurn = true;
			}
			
			return true;
//			if (ChessBoard[saved2ndClick[0]][saved2ndClick[1]].KingCheck(ChessBoard)){
//				//TODO: Show a message saying that the king is in check;
//			}	
		}else{
			//TODO: not a valid move and tell the user that it is not a valid move
			return false;
		}
	}
	
	//For now not doing anything if the king is in check
	public boolean IsKingInCheck(){
		return false;
	}
	

	
	
}
