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
	public void FirstClick(int[] click){
		saved1stClick = click;
		if (this.IsPieceAtLocation(saved1stClick)){
			if (this.ValidateRightTeam(saved1stClick)){
				selectedPiece = ChessBoard[saved1stClick[0]][saved1stClick[1]];
			}
		}else{
			//Show message saying that they didn't click the right piece
		}
	}
	
	
	//handling all the second click functions
	public void SecondClick(int[] click){
		saved2ndClick = click;
		
		//If the first click is the same as the second click
		if (saved2ndClick[0] == saved1stClick[0] && saved2ndClick[1] == saved1stClick[1]){
			//The piece did not move;
		}
		
		
		//First Set possible moves
		ArrayList<int[]> m = ChessBoard[saved1stClick[0]][saved1stClick[1]].ReturnPossibleMoves();
		
		
		
		if (ChessBoard[saved1stClick[0]][saved1stClick[1]].ValidMove(click[0], click[1])){
			//Move the respective piece to its destination
			
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
			
			if (ChessBoard[saved2ndClick[0]][saved2ndClick[1]].KingCheck(ChessBoard)){
				//Show a message saying that the king is in check;
			}	
		}else{
			//not a valid move and tell the user that it is not a valid move
		}
	}
	
	
}
