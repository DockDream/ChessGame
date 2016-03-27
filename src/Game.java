import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Game {

	Piece[][] ChessBoard = new Piece[8][8];
	Piece selectedPiece;
	boolean whoseTurn; // true = white, false = black
	Click fstClick;
	Click secClick;
	Click blackKing;
	Click whiteKing;
	int whitePiecesLeft;
	int blackPiecesLeft;
	int movesToStaleMate;

	// Starts the Board at Initial Configuration
	public void InitializeGame() {
		this.whoseTurn = true;
		// set up black back row
		ChessBoard[0][0] = new Rook();
		ChessBoard[0][1] = new Knight();
		ChessBoard[0][2] = new Bishop();
		ChessBoard[0][3] = new Queen();
		ChessBoard[0][4] = new King();
		ChessBoard[0][5] = new Bishop();
		ChessBoard[0][6] = new Knight();
		ChessBoard[0][7] = new Rook();
		

		// set up white back row
		ChessBoard[7][0] = new Rook();
		ChessBoard[7][1] = new Knight();
		ChessBoard[7][2] = new Bishop();
		ChessBoard[7][3] = new Queen();
		ChessBoard[7][4] = new King();
		ChessBoard[7][5] = new Bishop();
		ChessBoard[7][6] = new Knight();
		ChessBoard[7][7] = new Rook();

		for (int i = 0; i < 8; i++) {
			// Below three lines are setting up the Black team
			ChessBoard[1][i] = new Pawn();
			ChessBoard[0][i].team = false;
			ChessBoard[1][i].team = false;

			// Below three lines are setting up the White team
			ChessBoard[6][i] = new Pawn();
			ChessBoard[6][i].team = true;
			ChessBoard[7][i].team = true;
		}
		
		//Below values are needed to check for Stale mate
		whitePiecesLeft = 16;
		blackPiecesLeft = 16;
		movesToStaleMate = 0;
	}

	/*
	 * Checking to see if there are any pieces at the clicked location
	 * 
	 */
	public boolean IsPieceAtLocation(Click cSent) {
		if (ChessBoard[cSent.row][cSent.col] == null) {
			return false;
		} else {
			return true;
		}
	}

	// and returning true if it is valid first click
	public boolean FirstClick(Click cSent) {
		fstClick = null;

		//Helpful for debugging purposes
		if (ChessBoard[cSent.row][cSent.col] != null){
			System.out.println("1st Click: "+whoseTurn+"'s Turn"+", "
					+ChessBoard[cSent.row][cSent.col].getClass().toString()+", Class's Team: "+ChessBoard[cSent.row][cSent.col].team);
		}else{
			System.out.println("2nd Click: " + whoseTurn + "'s Turn" + ", Empty Spot Clicked");
		}
		
		if (this.IsPieceAtLocation(cSent)) {
			if (ChessBoard[cSent.row][cSent.col].team == whoseTurn) {
				
				fstClick = new Click(cSent.row,cSent.col);
				
				// First get all possible moves
				ChessBoard[fstClick.row][fstClick.col].ReturnPossibleMoves(fstClick.row, fstClick.col, ChessBoard);
				
				
//				this.eliminatePossibleMoves();
				
				if (ChessBoard[fstClick.row][fstClick.col].possibleMoves.size() == 0){
					this.ShowDialogBox("Piece cannot move", "Invalid Move");
					return false;
				}else{
				
					return true;
				}
				
			} else {
				// Shows a message that says not right team
				this.ShowDialogBox("Not your Turn", "Invalid Turn");
				return false;

			}
		} else {
			// Shows message saying that they didn't click the right piece
			this.ShowDialogBox("Clicked an empty spot", "Invalid Click");
			return false;
		}
	}

	// Return true if it is a valid secondClick
	public boolean SecondClick(Click cSent) {

		if (fstClick == null) {
			System.out.println("Second click function is being called before first click is called");
			return false;
		}

		secClick = new Click(cSent.row,cSent.col);

		//Helpful for debugging purposes
		if (ChessBoard[cSent.row][cSent.col] != null) {
			System.out.println("2nd Click: " + whoseTurn + "'s Turn" + ", " + ChessBoard[cSent.row][cSent.col].getClass().toString()
				+ ", Class's Team: " + ChessBoard[cSent.row][cSent.col].team);
		}else{
			System.out.println("2nd Click: " + whoseTurn + "'s Turn" + " ,Empty Spot Clicked");
		}

		if (fstClick.equals(secClick)) {		// The piece did not move;
			return true;
		}
		

		//Checks for validity of the move
		if (ChessBoard[fstClick.row][fstClick.col].ValidMove(cSent.row, cSent.col)) {

			System.out.println("Valid 2nd Click");
			
			// Move Piece and Change Turns
			ChessBoard[secClick.row][secClick.col] = ChessBoard[fstClick.row][fstClick.col];
			ChessBoard[fstClick.row][fstClick.col] = null;
			whoseTurn = whoseTurn ? false:true;

			return true;
			
		} else {		//Not a valid move and tell the user that it is not valid
			this.ShowDialogBox("That is not a legal move", "Invalid Move");
			return false;
		}
	}

	
	public void isKingCastling(){
		
		if (ChessBoard[fstClick.row][fstClick.col] instanceof King){
			King selectedKing = (King) ChessBoard[fstClick.row][fstClick.col];
			
			
			if (Math.abs(secClick.col - fstClick.col) == 2 && (fstClick.row == secClick.row)){
				ChessBoard[secClick.row][secClick.col] = ChessBoard[fstClick.row][fstClick.col];
				ChessBoard[fstClick.row][fstClick.col] = null;
				whoseTurn = whoseTurn ? false:true;
			}
			
			//Making sure that the whiteKing & blackKing locations are up to date
			if (whoseTurn == true){
				whiteKing = new Click(secClick);
			}else{
				blackKing = new Click(secClick);
			}
			
		}
		
		//Deactivate respective future castling
		if (ChessBoard[fstClick.row][fstClick.col] instanceof King || ChessBoard[fstClick.row][fstClick.col] instanceof Rook){
			if (whoseTurn == true){
				King selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
				selectedKing.castleValid = false;
			}else{
				King selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
				selectedKing.castleValid = false;
			}
		}
	}

	//Call this to show a Dialog box
	private void ShowDialogBox(String message, String title) {
		JOptionPane optionPane = new JOptionPane(message);

		JDialog dialog = optionPane.createDialog(title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	//This is in case the king is in check, we need to eliminate the row and columns that show
	public void eliminatePossibleMoves(){
		
		//First we need to get all possible moves for that piece.
		ChessBoard[fstClick.row][fstClick.col].ReturnPossibleMoves(fstClick.row, fstClick.col, ChessBoard);
		
		//Is storing the address of the possibleMoves
		ArrayList<int[]> tempList = ChessBoard[fstClick.row][fstClick.col].possibleMoves;
		Piece tempPiece;
		
		//select the right king to check against
		if (whoseTurn){
			King selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
		}else{
			King selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
		}

//		for (int i = 0; i < tempList.size(); i++) {
//
//			tempPiece = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
//			ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = ChessBoard[fstClick.row][fstClick.col];
//			ChessBoard[fstClick.row][fstClick.col] = null;
//
//			if (selectedKing.isKingInCheck()) {
//				ChessBoard[fstClick.row][fstClick.col] = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
//				ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = tempPiece;
//				tempList.remove(i);
//				i = i - 1; // this is so it doesn't skip over a spot.
//			} else {
//				ChessBoard[fstClick.row][fstClick.col] = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
//				ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = tempPiece;
//			}	
//		}
		
	}

}
