import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Game {

	Piece[][] ChessBoard = new Piece[8][8];
	Piece selectedPiece;
	Piece tempPiece;
	
	boolean whoseTurn; // true = white, false = black
	boolean justCastled = false;
	
	Click fstClick;
	Click secClick;
	Click blackKing;
	Click whiteKing;
	Click tempKing;
	
	int drawWith75Moves;
	
	
	//Variables used temporarily in various methods
	King selectedKing;

	// Starts the Board at Initial Configuration
	public void InitializeGame() {
		this.whoseTurn = true;
		// set up black back row
		ChessBoard[0][0] = new Rook(false);
		ChessBoard[0][1] = new Knight(false);
		ChessBoard[0][2] = new Bishop(false);
		ChessBoard[0][3] = new Queen(false);
		ChessBoard[0][4] = new King(false);
		ChessBoard[0][5] = new Bishop(false);
		ChessBoard[0][6] = new Knight(false);
		ChessBoard[0][7] = new Rook(false);
		

		// set up white back row
		ChessBoard[7][0] = new Rook(true);
		ChessBoard[7][1] = new Knight(true);
		ChessBoard[7][2] = new Bishop(true);
		ChessBoard[7][3] = new Queen(true);
		ChessBoard[7][4] = new King(true);
		ChessBoard[7][5] = new Bishop(true);
		ChessBoard[7][6] = new Knight(true);
		ChessBoard[7][7] = new Rook(true);

		for (int i = 0; i < 8; i++) {
			// Below three lines are setting up the Black team
			ChessBoard[1][i] = new Pawn(false);

			// Below three lines are setting up the White team
			ChessBoard[6][i] = new Pawn(true);
		}
		
		// Initialized king locations
		whiteKing = new Click(7, 4);
		blackKing = new Click(0, 4);
		
		//Below values are needed to check for Stale mate
		drawWith75Moves = 0;
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
//				ChessBoard[fstClick.row][fstClick.col].ReturnPossibleMoves(fstClick.row, fstClick.col, ChessBoard);
				this.eliminatePossibleMoves(fstClick);
				
				if (ChessBoard[fstClick.row][fstClick.col].possibleMoves.size() == 0){
					this.ShowDialogBox("Can't move, if moved king will be placed in check", "Invalid Move");
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
			
			if (ChessBoard[fstClick.row][fstClick.col] instanceof King) {
				if (whoseTurn) {
					whiteKing = new Click(secClick.row, secClick.col);
				} else {
					blackKing = new Click(secClick.row, secClick.col);
				}
			}
			
			System.out.println("Valid 2nd Click");
			System.out.println("");
			
			//if King isn't castling run the normal move
			if (!this.isKingCastling()) {

				drawWith75Moves++;

				if (ChessBoard[fstClick.row][fstClick.col] instanceof Pawn) {
					drawWith75Moves = 0;
				}

				if (ChessBoard[secClick.row][secClick.col] != null) {
					//we are killing a piece
					drawWith75Moves = 0;
				}

				tempPiece = ChessBoard[secClick.row][secClick.col];
				ChessBoard[secClick.row][secClick.col] = ChessBoard[fstClick.row][fstClick.col];
				ChessBoard[fstClick.row][fstClick.col] = null;

				
			}		
			whoseTurn = whoseTurn ? false : true;	
			return true;
		}else{//Not a valid move and tell the user that it is not valid
			this.ShowDialogBox("That is not a legal move", "Invalid Move");
			return false;
		}
	}
	
	public void reverseMove(){
		ChessBoard[fstClick.row][fstClick.col] = ChessBoard[secClick.row][secClick.col];
		ChessBoard[secClick.row][secClick.col] = tempPiece;
		if(ChessBoard[fstClick.row][fstClick.col] instanceof King){
			if (!whoseTurn){
				whiteKing = tempKing;
				System.out.println("White king has moved to Row: " + whiteKing.row + " Col: " + whiteKing.col);
			}else{
				blackKing = tempKing;
				System.out.println("Black king has moved to Row: " + blackKing.row + " Col: " + blackKing.col);
			}
		}
		if (ChessBoard[secClick.row][secClick.col] != null){	//We are killing a piece
			/*if (whoseTurn){
				blackPiecesLeft++;
			}else{
				whitePiecesLeft++;
			}*/
		}
		whoseTurn = whoseTurn ? false : true;
	}
	
	public boolean isKingInCheck(boolean reCheck, boolean leaving){
		
		boolean check = false;
		
		if(!reCheck){		
			if(whoseTurn){
				selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
				check = selectedKing.KingCheck(ChessBoard, whiteKing.row, whiteKing.col,true);
				System.out.println("White's turn, White check is: " + check);
			}else{
				selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
				check = selectedKing.KingCheck(ChessBoard, blackKing.row, blackKing.col,false);
				System.out.println("Black's turn, Black check is: " + check);
			}
			if(check){
				this.ShowDialogBox("King is in check", "King in Check");
			}
		}else{
			if(whoseTurn){
				selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
				check = selectedKing.KingCheck(ChessBoard, blackKing.row, blackKing.col,false);
				System.out.println("White's turn, Black check is: " + check);
			}else{
				selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
				check = selectedKing.KingCheck(ChessBoard, whiteKing.row, whiteKing.col,true);
				System.out.println("Black's turn, White check is: " + check);
			}
			if(check && !leaving){
				this.ShowDialogBox("King is still in check", "Invalid Move");
			}else if(check && leaving){
				this.ShowDialogBox("Cannot Leave King In Check", "Invalid Move");
			}
		}
		return check;
	}

	//Check if king is castling & update king location
	public boolean isKingCastling(){

		justCastled = false;
		
		// Deactivate respective future castling
		if (ChessBoard[fstClick.row][fstClick.col] instanceof Rook) {
			if (whoseTurn == true) {
				selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
				selectedKing.castleValid = false;
			} else {
				selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
				selectedKing.castleValid = false;
			}
		}
		
		if (ChessBoard[fstClick.row][fstClick.col] instanceof King) {
			
			selectedKing = (King) ChessBoard[fstClick.row][fstClick.col];
			
			if (selectedKing.castleValid) {

				if (secClick.col - fstClick.col == 2 && (fstClick.row == secClick.row)) {
					ChessBoard[secClick.row][secClick.col] = ChessBoard[fstClick.row][fstClick.col];
					ChessBoard[fstClick.row][5] = ChessBoard[fstClick.row][7];
					ChessBoard[fstClick.row][7] = null;
					ChessBoard[fstClick.row][fstClick.col] = null;

					

					justCastled = true;
					return true;
				}

				if (secClick.col - fstClick.col == -2 && (fstClick.row == secClick.row)) {
					ChessBoard[secClick.row][secClick.col] = ChessBoard[fstClick.row][fstClick.col];
					ChessBoard[fstClick.row][3] = ChessBoard[fstClick.row][0];
					ChessBoard[fstClick.row][0] = null;
					ChessBoard[fstClick.row][fstClick.col] = null;

					justCastled = true;
					return true;
				}
			}
			selectedKing.castleValid = false;
		}

		return false;
	}

	//Call this to show a Dialog box
	private void ShowDialogBox(String message, String title) {
		JOptionPane optionPane = new JOptionPane(message);

		JDialog dialog = optionPane.createDialog(title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	//This is in case the king is in check, we need to eliminate the row and columns that show
	public void eliminatePossibleMoves(Click sentC){
		
		int kingRow;
		int kingCol;

		// select the right king to check against
		if (whoseTurn) {
			selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
			kingRow = whiteKing.row;
			kingCol = whiteKing.col;
		} else {
			selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
			kingRow = blackKing.row;
			kingCol = blackKing.col;
		}

		// First we need to get all possible moves for that piece.
		ChessBoard[sentC.row][sentC.col].ReturnPossibleMoves(sentC.row, sentC.col, ChessBoard);

		// Is storing the address of the possibleMoves
		ArrayList<int[]> tempList = ChessBoard[sentC.row][sentC.col].possibleMoves;
		Piece tempPiece;

		for (int i = 0; i < tempList.size(); i++) {

			// Move piece to possibleplace
			tempPiece = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
			ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = ChessBoard[fstClick.row][fstClick.col];
			ChessBoard[fstClick.row][fstClick.col] = null;
			
			if (ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] instanceof King){
				kingRow = tempList.get(i)[0];
				kingCol = tempList.get(i)[1];
				if (whoseTurn) {
					selectedKing = (King) ChessBoard[kingRow][kingCol];
				} else {
					
					selectedKing = (King) ChessBoard[kingRow][kingCol];
					
				}
			}

			// if king is still in check then remove the int[] as a possibility
			if (selectedKing.KingCheck(ChessBoard, kingRow, kingCol, selectedKing.team)) {
				ChessBoard[fstClick.row][fstClick.col] = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
				ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = tempPiece;
				System.out.println("Remrow: " + tempList.get(i)[0] + " Remcol: " + tempList.get(i)[1]);
				tempList.remove(i);
				i = i - 1; // this is so it doesn't skip over a spot.
			} else {
				ChessBoard[fstClick.row][fstClick.col] = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
				ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = tempPiece;
			}
		}
		ChessBoard[sentC.row][sentC.col].possibleMoves = tempList;

	}
	
	//Goes through the whole board and checks if there are any moves for the team playing
	//Checks Stalemate for the team currently playing
	public boolean isDraw(){
		if (drawWith75Moves >= 75){
				this.ShowDialogBox("No piece has been killed or Pawn has been moves since 75 moves", "Draw");
			return true;
		}
		//Below is checking for Stalemate
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (ChessBoard[i][j] != null && ChessBoard[i][j].team == whoseTurn){
					ChessBoard[i][j].ReturnPossibleMoves(i, j, ChessBoard);
					
					if (ChessBoard[i][j] != null && ChessBoard[i][j] instanceof King){
						this.eliminatePossibleMoves(new Click(i,j));
					}
					
					if (ChessBoard[i][j] != null && ChessBoard[i][j].possibleMoves.size() > 0){
						return false;
					}
				}
			}
		}
		this.ShowDialogBox("", "Stalemate");
		return true;
	}
	
	//returns true if it is a checkmate
	//TODO: get the convention right for isKingInCheck method
	public boolean isCheckMate(){
		Click currentC;
		
		//Making sure that the right king and kings location is selected
		if (whoseTurn){
			selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
			currentC = whiteKing;
		}else{
			selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
			currentC = blackKing;
			
		}
		
		//If we aren't in check we aren't in checkmate
		if (!selectedKing.KingCheck(ChessBoard, currentC.row, currentC.col,selectedKing.team)){
			return false;
		}
		
		ChessBoard[currentC.row][currentC.col].ReturnPossibleMoves(currentC.row,currentC.col,ChessBoard);
		
		//If the king can move then it is not a checkmate
		if (selectedKing.possibleMoves != null && selectedKing.possibleMoves.size() > 0){
			return false;
		}
		
		//Go through all the pieces of whoseTurn and see if any of them can block the checkMate
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (ChessBoard[i][j] != null && ChessBoard[i][j].team == whoseTurn){
					ChessBoard[i][j].ReturnPossibleMoves(i, j, ChessBoard);
					this.eliminatePossibleMoves(new Click(i,j));
					
					if (ChessBoard[i][j] != null && ChessBoard[i][j].possibleMoves.size() > 0){
						return false;
					}
				}
			}
		}		
		//if all else fails it is a checkmate
		this.ShowDialogBox("", "Checkmate");
		return true;
		//TODO: temporary need to remove
//		return false;
	}
	

	//Return possibleMoves
	public ArrayList<int[]> returnPossibleMoves(){
		
		if (fstClick == null){
			return null;
		}
		
		return ChessBoard[fstClick.row][fstClick.col].possibleMoves;
	}

	//Check if pawnPromotion is eligible
	public boolean pawnPromotion(){
		if ((ChessBoard[secClick.row][secClick.col] instanceof Pawn) && ((secClick.row == 0) || secClick.row == 7)){
			return true;
		}else{
			return false;
		}
	}

	//Replace the pawn promotion
	public void pawnPromoted(Piece sent){
		sent.team = whoseTurn? false:true;
		ChessBoard[secClick.row][secClick.col] = sent;
	}
	
	//Prints what the board contains
	public void printBoard(){
		
		System.out.println("");
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (ChessBoard[i][j] != null){
				System.out.print(ChessBoard[i][j].getClass().toString()+"  :");
				}else{
					System.out.print("null.");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public boolean isKingInCheck(){
		if (whoseTurn){
			selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
			return (selectedKing.KingCheck(ChessBoard, whiteKing.row, whiteKing.col , true));
		}else{
			selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
			return (selectedKing.KingCheck(ChessBoard, whiteKing.row, whiteKing.col , true));
		
		}
	}
}