import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Game {

	//Contains the current ChessBoard
	private Piece[][] ChessBoard = new Piece[8][8];
	
	private boolean whoseTurn; // true = white, false = black //being called for checking whose turn
	private boolean justCastled = false; //being called to check if we castled
	
	private Click fstClick;
	private Click secClick;
	private Click blackKing;
	private Click whiteKing;
	
	private int drawWith75Moves;
	
	//Variables used temporarily in various methods
	private King selectedKing;
	private Click kingLoc;
	private Piece tempPiece;

	/**
	 * InitializeGame method - Sets all the board
	 * pieces in the right places
	 * (Requirement 1.0.0)
	 */
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
			
			//Making all the empty spaces null
			ChessBoard[2][i] = null;
			ChessBoard[3][i] = null;
			ChessBoard[4][i] = null;
			ChessBoard[5][i] = null;
		}
		
		// Initialized king locations
		whiteKing = new Click(7, 4);
		blackKing = new Click(0, 4);
		
		//Below values are needed to check for Stale mate
		drawWith75Moves = 0;
	}

	/**
	 * FirstClick method - It is called when first square
	 * is clicked. 
	 * (Requirement 1.1.0)
	 */
	public boolean FirstClick(Click cSent) {
		fstClick = null;

		//Helpful for debugging purposes
		if (ChessBoard[cSent.row][cSent.col] != null){
			System.out.println("1st Click: "+whoseTurn+"'s Turn"+", "
					+ChessBoard[cSent.row][cSent.col].getClass().toString()+", Class's Team: "+ChessBoard[cSent.row][cSent.col].team);
		}else{
			System.out.println("2nd Click: " + whoseTurn + "'s Turn" + ", Empty Spot Clicked");
		}
		
		if (ChessBoard[cSent.row][cSent.col] != null) {
			if (ChessBoard[cSent.row][cSent.col].team == whoseTurn) {
				
				fstClick = new Click(cSent.row,cSent.col);
				
				// First get all possible moves
				this.eliminatePossibleMoves(fstClick);
				
				if (ChessBoard[fstClick.row][fstClick.col].possibleMoves.size() == 0){
					this.ShowDialogBox("No moves left", "Invalid Move");
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

	/**
	 * SecondClick method - Called when second square is clicked
	 * Validates Moves or deselects piece based on click
	 * (Requirements 1.1.0, 1.2.0, 2.0.0, 2.2.0)
	 */
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

	/**
	 * isKingCastling method - Method used by SecondClick method
	 * to check if the king is castling
	 */
	private boolean isKingCastling(){

		justCastled = false;
		
		// Deactivate respective future castling
		if (ChessBoard[fstClick.row][fstClick.col] instanceof Rook) {
			this.selectCurrentKing();
			selectedKing.castleValid = false;
		}
		
		if (ChessBoard[fstClick.row][fstClick.col] instanceof King) {
			
			selectedKing = (King) ChessBoard[fstClick.row][fstClick.col];
			
			if (selectedKing.castleValid) {

				if (secClick.col - fstClick.col == 2 && (fstClick.row == secClick.row)) {
					ChessBoard[secClick.row][secClick.col] = ChessBoard[fstClick.row][fstClick.col];
					ChessBoard[fstClick.row][5] = ChessBoard[fstClick.row][7];
					ChessBoard[fstClick.row][7] = null;
					ChessBoard[fstClick.row][fstClick.col] = null;

					
					selectedKing.castleValid = false;
					justCastled = true;
					return true;
				}

				if (secClick.col - fstClick.col == -2 && (fstClick.row == secClick.row)) {
					ChessBoard[secClick.row][secClick.col] = ChessBoard[fstClick.row][fstClick.col];
					ChessBoard[fstClick.row][3] = ChessBoard[fstClick.row][0];
					ChessBoard[fstClick.row][0] = null;
					ChessBoard[fstClick.row][fstClick.col] = null;

					selectedKing.castleValid = false;
					justCastled = true;
					return true;
				}
			}
			selectedKing.castleValid = false;
		}

		return false;
	}

	/**
	 * ShowDialogBox method - Used to display a message
	 * to the user. it is used in multiple methods
	 */
	public void ShowDialogBox(String message, String title) {
		JOptionPane optionPane = new JOptionPane(message);

		JDialog dialog = optionPane.createDialog(title);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	/**
	 * eliminatePossibleMoves method - Makes sure that all moves
	 * that are not legal are eliminated. Used highly when moving
	 * a piece places the king in check
	 * (Requirements 2.0.0, 2.1.0)
	 */
	private void eliminatePossibleMoves(Click sentC){
		
		int kingRow;
		int kingCol;

		
		// select the right king to check against
		this.selectCurrentKing();
		kingRow = kingLoc.row;
		kingCol = kingLoc.col;
		
		// First we need to get all possible moves for that piece.
		ChessBoard[sentC.row][sentC.col].ReturnPossibleMoves(sentC.row, sentC.col, ChessBoard);
		
		if (ChessBoard[sentC.row][sentC.col].possibleMoves.size() == 0){
			return;
		}
		
		// Is storing the address of the possibleMoves
		ArrayList<int[]> tempList = ChessBoard[sentC.row][sentC.col].possibleMoves;

		for (int i = 0; i < tempList.size(); i++) {
			
			// Move piece to possibleplace && firstclick is wrong for stalemate
			tempPiece = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
			ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = ChessBoard[sentC.row][sentC.col];
			ChessBoard[sentC.row][sentC.col] = null;
			
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
				ChessBoard[sentC.row][sentC.col] = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
				ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = tempPiece;
//				System.out.println("Remrow: " + tempList.get(i)[0] + " Remcol: " + tempList.get(i)[1]);
				tempList.remove(i);
				i = i - 1; // this is so it doesn't skip over a spot.
			} else {
				ChessBoard[sentC.row][sentC.col] = ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]];
				ChessBoard[tempList.get(i)[0]][tempList.get(i)[1]] = tempPiece;
			}
		}
		ChessBoard[sentC.row][sentC.col].possibleMoves = tempList;

	}
	
	/**
	 * isDraw method - Checks for a draw or stalemate
	 */
	public boolean isDraw(){
		
		if (drawWith75Moves >= 75){
				this.ShowDialogBox("No piece has been killed or Pawn has been moves since 75 moves", "Draw");
			return true;
		}
		//Below is checking for Stalemate
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (ChessBoard[i][j] != null && ChessBoard[i][j].team == whoseTurn){
//					System.out.println("Stalemate row: "+i+" Stalemate col: "+j);
					this.eliminatePossibleMoves(new Click(i,j));
					
					if (ChessBoard[i][j].possibleMoves.size() > 0){
						return false;
					}
				}
			}
		}
		
		System.out.println("Done with draw");
		
		return true;
	}
	
	/**
	 * isCheckMate method - Checks for a checkMate
	 */
	public boolean isCheckMate(){
		
		this.selectCurrentKing();
		
		this.eliminatePossibleMoves(kingLoc);
		
		//If the king can't move then check other piece for possible blocks
		if (selectedKing.possibleMoves != null && selectedKing.possibleMoves.size() == 0){
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if (ChessBoard[i][j] != null && ChessBoard[i][j].team == whoseTurn){
						this.eliminatePossibleMoves(new Click(i,j));
						
						if (ChessBoard[i][j].possibleMoves.size() > 0){
							return false;
						}
					}
				}
			}
		}else{
			return false;
		}
		
		return true;
	}
	

	/**
	 * returnPossibleMoves method - returns the generated moves array
	 * to the ChessWindowClass to be displayed 
	 * (Requirements 2.1.0)
	 */
	public ArrayList<int[]> returnPossibleMoves(){
		
		if (fstClick == null){
			return null;
		}
		
		return ChessBoard[fstClick.row][fstClick.col].possibleMoves;
	}

	/**
	 * pawnPromotion method - informs the chessWindowClass
	 * if the current move is a pawn promotion
	 */
	public boolean pawnPromotion(){
		if ((ChessBoard[secClick.row][secClick.col] instanceof Pawn) && 
				((secClick.row == 0) || secClick.row == 7)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * pawnPromoted method - sets the pawn to be promoted to the piece sent
	 * (Requirements 2.3.0)
	 */
	public void pawnPromoted(Piece sent){
		sent.team = whoseTurn? false:true;
		ChessBoard[secClick.row][secClick.col] = sent;
	}
	
	//Prints what the board contains
	@SuppressWarnings("unused")
	private void printBoard(){
		
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
	
	/**
	 * isKingInCheck method - informs the ChessWindowClass if the 
	 * king is in Check
	 */
	public boolean isKingInCheck(){
		
		this.selectCurrentKing();
		
		return selectedKing.KingCheck(ChessBoard, kingLoc.row, kingLoc.col , selectedKing.team);
	}
	
	/**
	 * selectCurrentKing method - used to update the current kings location
	 * needed to check for Stalemate, Checkmate and check.
	 */
	private void selectCurrentKing(){
		if (whoseTurn){
			selectedKing = (King) ChessBoard[whiteKing.row][whiteKing.col];
			kingLoc  = new Click(whiteKing.row,whiteKing.col);
		}else{
			selectedKing = (King) ChessBoard[blackKing.row][blackKing.col];
			kingLoc = new Click(blackKing.row,blackKing.col);
		}
		
		return;
	}
	
	/**
	 * getjustCastled method - used to inform the chessWindowClass that
	 * that last move was a castle
	 */
	public boolean getjustCastled(){
		return this.justCastled;
	}
	
	/**
	 * getTurn method - used by the ChessWindowClass to show the
	 * current players turn
	 * (Requirements 1.3.0)
	 */
	public boolean getTurn(){
		return this.whoseTurn;
	}
}