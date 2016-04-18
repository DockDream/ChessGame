
import java.util.ArrayList;

public class King extends Piece {

	boolean castleValid;
	
	King(boolean sentTeam) {
		super(sentTeam);
		this.castleValid = true;
	}

	public boolean KingCheck(Piece[][] chessBoard, int currentRow, int currentCol, boolean kingTeam){

		ArrayList<int[]> kingMoves = null;		
		
		boolean check = false;
		int kingRow = currentRow;
		int kingCol = currentCol;		
		int opponentRow;
		int opponentCol;
		Piece opponent;
		
		//System.out.println("Checking Rook: 1 of 5");
		Rook kingRook = new Rook(kingTeam);
		kingRook.ReturnPossibleMoves(kingRow, kingCol, chessBoard);
		kingMoves = kingRook.possibleMoves;
		for(int i = 0; i < kingMoves.size(); i++){
			opponentRow = kingMoves.get(i)[0];
			opponentCol = kingMoves.get(i)[1];
			opponent = chessBoard[opponentRow][opponentCol];
			if (opponent != null){
				if(opponent.team != kingTeam){
					if(opponent instanceof Rook || opponent instanceof Queen){
						check = true;
					}
				}
			}
		}
		
		
		//System.out.println("Checking Bishop: 2 of 5");
		Bishop kingBishop = new Bishop(kingTeam);
		kingBishop.ReturnPossibleMoves(kingRow, kingCol, chessBoard);
		kingMoves = kingBishop.possibleMoves;
		for(int i = 0; i < kingMoves.size(); i++){
			opponentRow = kingMoves.get(i)[0];
			opponentCol = kingMoves.get(i)[1];
			opponent = chessBoard[opponentRow][opponentCol];
			if (opponent != null){
				if(opponent.team != kingTeam){
					if(opponent instanceof Bishop || opponent instanceof Queen){
						check = true;
					}
				}
			}
		}
		
		//System.out.println("Checking Pawns: 4 of 5");
		//Direction does not matter since we are only using it for the killCheck command
		Pawn kingPawn = new Pawn(kingTeam,false);
		if(kingPawn.KillCheck(kingRow, kingCol, kingRow+1, kingCol+1, chessBoard)){
			opponent = chessBoard[kingRow+1][kingCol+1];
			if (opponent != null){
				if(opponent.team != kingTeam){
					if(opponent instanceof Pawn){
						if(!kingTeam){
							check = true;
						}
					}
				}
			}
		}else if(kingPawn.KillCheck(kingRow, kingCol, kingRow+1, kingCol-1, chessBoard)){
			opponent = chessBoard[kingRow+1][kingCol-1];
			if (opponent != null){
				if(opponent.team != kingTeam){
					if(opponent instanceof Pawn){
						if(!kingTeam){
							check = true;
						}
					}
				}
			}
		}else if(kingPawn.KillCheck(kingRow, kingCol, kingRow-1, kingCol+1, chessBoard)){
			opponent = chessBoard[kingRow-1][kingCol+1];
			if (opponent != null){
				if(opponent.team != kingTeam){
					if(opponent instanceof Pawn){
						if(kingTeam){
							check = true;
						}
					}
				}
			}	
		}else if(kingPawn.KillCheck(kingRow, kingCol, kingRow-1, kingCol-1, chessBoard)){
			opponent = chessBoard[kingRow-1][kingCol-1];
			if (opponent != null){
				if(opponent.team != kingTeam){
					if(opponent instanceof Pawn){
						if(kingTeam){
							check = true;
						}
					}
				}
			}
		}
		
		//System.out.println("Checking Knight: 5 of 5");
		Knight kingKnight = new Knight(kingTeam);
		kingKnight.ReturnPossibleMoves(kingRow, kingCol, chessBoard);
		kingMoves = kingKnight.possibleMoves;
		for(int i = 0; i < kingMoves.size(); i++){
			opponentRow = kingMoves.get(i)[0];
			opponentCol = kingMoves.get(i)[1];
			opponent = chessBoard[opponentRow][opponentCol];
			if (opponent != null){
				if(opponent.team != kingTeam){
					if(opponent instanceof Knight){
						check = true;
					}
				}
			}
		}

		kingMoves = null;
		return check;
	}	
	
	private ArrayList<int[]> ReturnMovesAddon(int startRow, int startCol, int newRow, int newCol,
			Piece[][] chessBoard) {

		// make sure cell being moved to is in bounds and can be taken

		ArrayList<int[]> tempList = new ArrayList<int[]>();
		int destRow = newRow;
		int destCol = newCol;
		int[] cell = new int[2];
		cell[0] = destRow;
		cell[1] = destCol;
		int kingRow = startRow;
		int kingCol = startCol;
		Piece king = chessBoard[kingRow][kingCol];
		
		if(this.inBounds(destRow, destCol)){
			Piece destCell = chessBoard[destRow][destCol]; 
			if (destCell == null) {
				//makes sure that the moves doesn't show up if there is another king in its radius
				if (!this.isKinginRadius(new Click(cell[0],cell[1]), chessBoard, !this.team)){
					tempList.add(cell);
				}
			} else if (destCell.team != king.team) {
				if (!(destCell instanceof King)) {
					tempList.add(cell);
				}
			}
		}
		return tempList;
	}

	public void ReturnPossibleMoves(int startRow, int startColumn,
			Piece[][] currentBoard) {
		
		possibleMoves = null;
		
		if (possibleMoves != null) {
			possibleMoves.clear();
		}

		// move down by 1
		possibleMoves = this.ReturnMovesAddon(startRow, startColumn, startRow + 1, startColumn, currentBoard);
		// move up by 1
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow - 1, startColumn, currentBoard));
		// move right by 1
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow, startColumn + 1, currentBoard));
		// move left by 1
		possibleMoves.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow, startColumn - 1, currentBoard));
		// move down and right by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow + 1, startColumn + 1, currentBoard));
		// move down and left by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow + 1, startColumn - 1, currentBoard));
		// move up and right by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow - 1, startColumn + 1, currentBoard));
		// move up and left by 1
		possibleMoves
				.addAll(this.ReturnMovesAddon(startRow, startColumn, startRow - 1, startColumn - 1, currentBoard));

		//Only add 2 moves to the left or 2 moves to the right is castle is valid
		if (castleValid && !this.KingCheck(currentBoard,startRow,startColumn,this.team)){
			if (currentBoard[startRow][startColumn+1] == null && currentBoard[startRow][startColumn+2] == null){
				currentBoard[startRow][startColumn+1] = currentBoard[startRow][startColumn];
				if (!this.KingCheck(currentBoard,startRow,startColumn+1,this.team)){
					currentBoard[startRow][startColumn+2] = currentBoard[startRow][startColumn+1];
					if (!this.KingCheck(currentBoard,startRow,startColumn+2,this.team)){
						possibleMoves.add(new int[] { startRow, startColumn + 2 });
					}
					currentBoard[startRow][startColumn+2] = null;
				}
				currentBoard[startRow][startColumn+1] = null;
			}

			if (currentBoard[startRow][startColumn - 1] == null && currentBoard[startRow][startColumn - 2] == null
					&& currentBoard[startRow][startColumn - 3] == null) {
				 currentBoard[startRow][startColumn-1] = currentBoard[startRow][startColumn];
				if (!this.KingCheck(currentBoard, startRow, startColumn - 1, this.team)) {
					 currentBoard[startRow][startColumn-2] = currentBoard[startRow][startColumn-1];
					 if (!this.KingCheck(currentBoard, startRow, startColumn - 2, this.team)){
						 possibleMoves.add(new int[] { startRow, startColumn - 2 });
					 }
					currentBoard[startRow][startColumn-2] = null;
				}
				currentBoard[startRow][startColumn-3] = null;
				currentBoard[startRow][startColumn-1] = null;
			}
		}
	}
	
	//Checks if there is a king of the sentTeam boolean in radius
	private boolean isKinginRadius(Click sentC,  Piece[][] currentBoard, boolean sentTeam){
		if (this.inBounds(sentC.row+1, sentC.col) &&
				currentBoard[sentC.row+1][sentC.col] instanceof King && 
				currentBoard[sentC.row+1][sentC.col].team  == sentTeam){
			return true;
		}else if (this.inBounds(sentC.row+1, sentC.col+1) &&
				currentBoard[sentC.row+1][sentC.col+1] instanceof King && 
				currentBoard[sentC.row+1][sentC.col+1].team  == sentTeam){
			return true;
		}else if (this.inBounds(sentC.row, sentC.col+1) &&
				currentBoard[sentC.row][sentC.col+1] instanceof King && 
				currentBoard[sentC.row][sentC.col+1].team  == sentTeam){
			return true;
		}else if (this.inBounds(sentC.row, sentC.col-1) &&
				currentBoard[sentC.row][sentC.col-1] instanceof King && 
				currentBoard[sentC.row][sentC.col-1].team  == sentTeam){
			return true;
		}else if (this.inBounds(sentC.row-1, sentC.col) &&
				currentBoard[sentC.row-1][sentC.col] instanceof King && 
				currentBoard[sentC.row-1][sentC.col].team  == sentTeam){
			return true;
		}else if (this.inBounds(sentC.row-1, sentC.col-1) &&
				currentBoard[sentC.row-1][sentC.col-1] instanceof King && 
				currentBoard[sentC.row-1][sentC.col-1].team  == sentTeam){
			return true;
		}else if (this.inBounds(sentC.row-1, sentC.col+1) &&
				currentBoard[sentC.row-1][sentC.col+1] instanceof King && 
				currentBoard[sentC.row-1][sentC.col+1].team  == sentTeam){
			return true;
		}else if (this.inBounds(sentC.row+1, sentC.col-1) &&
				currentBoard[sentC.row+1][sentC.col-1] instanceof King && 
				currentBoard[sentC.row+1][sentC.col-1].team  == sentTeam){
			return true;
		}else{
			return false;
		}
	}
	
}
