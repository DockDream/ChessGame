import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ChessWindowClass implements ActionListener{
	
	private JFrame frmJavaholicsChess;
	private JButton[][] chessSquares = new JButton[8][8];
	public int [] start = new int [2]; //Used for piece chosen [ROW, COL]
	public int [] end = new int [2]; //Used for place chosen [ROW, COL]
	private int count;
	private int turn = 0;			//Used for showing player's turn
	public boolean pieceColor = true;
	private boolean check = false; 
	private boolean leavingInCheck = false;
	ArrayList <int[]> possibleMoves = new ArrayList<int[]>();
    Game fClick = new Game();
    Label label = new Label("White Team");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessWindowClass window = new ChessWindowClass();
					window.frmJavaholicsChess.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ChessWindowClass() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * (Requirement 1.0.0)
	 */
	
	private void initialize() throws IOException {
		frmJavaholicsChess = new JFrame();
		frmJavaholicsChess.getContentPane().setLayout(new GridLayout(8, 8, 0, 0));
		
		//Initialize Backend Code
		fClick.InitializeGame();
		
		for (int i = 0; i < 8; i++) {
			for (int ii = 0; ii < 8; ii++) {
				
				if ((((ii % 2) == 0) && ((i % 2) == 0)) || ((ii % 2) == 1) && ((i % 2) == 1)) {
				
					chessSquares[i][ii] = new JButton(); 			//Creates JButtons representing squares on board
					chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WBoard.png")));
				}
				else {
					chessSquares[i][ii] = new JButton();
					chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BBoard.png")));
				}	
				
				//chessSquares[i][ii].setToolTipText("" + alphabet[i] + ii);		//Creates tooltip of position
				frmJavaholicsChess.getContentPane().add(chessSquares[i][ii]); 	//Adds each created JButton to grid				
				chessSquares[i][ii].addActionListener(this);					//Adds ActionListeners to All JButtons on grid
         } // end for ii
		} // end for i
		
		frmJavaholicsChess.setTitle("Javaholics Chess"); //Sets title for window
		frmJavaholicsChess.setAlwaysOnTop(false);		//Makes window on top of all windows
		frmJavaholicsChess.setBounds(100, 100, 800, 798);	//Sets size of window
		frmJavaholicsChess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends program on close of window
      
		JMenuBar menuBar = new JMenuBar(); //Creates Menu Bar to top of window
		frmJavaholicsChess.setJMenuBar(menuBar); //Adds MenuBar
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);			//Adds Options Menu
		
		JMenuItem mntmBlackWhite = new JMenuItem("Black & White Pieces");
		mnOptions.add(mntmBlackWhite);	//Adds B&W Choice
		mntmBlackWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeColor(false);
				
				if (label.getText() == "Blue Team") {
					 label.setText("Black Team");
				}
				else if (label.getText() == "Red Team") {
					label.setText("White Team");
				}
			}
		});
		
		JMenuItem mntmRedBlue = new JMenuItem("Red & Blue Pieces");
		mnOptions.add(mntmRedBlue);		//Adds R&B Choice
		mntmRedBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeColor(true);
				
				 if (label.getText() == "Black Team") {
						 label.setText("Blue Team");
				 }
				 else if (label.getText() == "White Team"){
					 label.setText("Red Team");
				 }
			}
		});
		
		menuBar.add(label);				//Adds label for player turn
		
		setPieces(pieceColor);	// piece initialization method call
				
	} // end initialize method
	
	public void changeColor(boolean pieceColor)
	{
		String shade = "";
		String[] splitSh;
		
		if (pieceColor == true) {
			for(int i = 0; i < 8; i++) { 	//Searches all squares
				for(int ii = 0; ii < 8; ii++) {
					shade = chessSquares[i][ii].getIcon().toString();	//Finds location of icon
					splitSh = shade.split("/");		//Gets simpler end of icon name
					
					/*
					 * ROOK
					 */
				
					if (splitSh[8].compareTo("BRookWBoard.png") == 0) {  //If Black ROOK on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/URookWBoard.png")));
					}
					else if (splitSh[8].compareTo("BRookBBoard.png") == 0) {	//If Black ROOK on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/URookBBoard.png")));
					}
				
					if (splitSh[8].compareTo("WRookWBoard.png") == 0) {  //If White ROOK on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RRookWBoard.png")));
					}
					else if (splitSh[8].compareTo("WRookBBoard.png") == 0) {	//If White ROOK on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RRookBBoard.png")));
					}
					
					/*
					 * BISHOP
					 */
					
					if (splitSh[8].compareTo("BBishopWBoard.png") == 0) {  //If Black BISHOP on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UBishopWBoard.png")));
					}
					else if (splitSh[8].compareTo("BBishopBBoard.png") == 0) {	//If Black BISHOP on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UBishopBBoard.png")));
					}
				
					if (splitSh[8].compareTo("WBishopWBoard.png") == 0) {  //If White BISHOP on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RBishopWBoard.png")));
					}
					else if (splitSh[8].compareTo("WBishopBBoard.png") == 0) {	//If White BISHOP on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RBishopBBoard.png")));
					}
					
					/*
					 * KNIGHT
					 */
					
					if (splitSh[8].compareTo("BKnightWBoard.png") == 0) {  //If Black KNIGHT on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UKnightWBoard.png")));
					}
					else if (splitSh[8].compareTo("BKnightBBoard.png") == 0) {	//If Black KNIGHT on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UKnightBBoard.png")));
					}
				
					if (splitSh[8].compareTo("WKnightWBoard.png") == 0) {  //If White KNIGHT on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RKnightWBoard.png")));
					}
					else if (splitSh[8].compareTo("WKnightBBoard.png") == 0) {	//If White KNIGHT on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RKnightBBoard.png")));
					}
					
					/*
					 * QUEEN
					 */
					
					if (splitSh[8].compareTo("BQueenWBoard.png") == 0) {  //If Black QUEEN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UQueenWBoard.png")));
					}
					else if (splitSh[8].compareTo("BQueenBBoard.png") == 0) {	//If Black QUEEN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UQueenBBoard.png")));
					}
				
					if (splitSh[8].compareTo("WQueenWBoard.png") == 0) {  //If White QUEEN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RQueenWBoard.png")));
					}
					else if (splitSh[8].compareTo("WQueenBBoard.png") == 0) {	//If White QUEEN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RQueenBBoard.png")));
					}
					
					/*
					 * KING
					 */
					
					if (splitSh[8].compareTo("BKingWBoard.png") == 0) {  //If Black KING on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UKingWBoard.png")));
					}
					else if (splitSh[8].compareTo("BKingBBoard.png") == 0) {	//If Black KING on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UKingBBoard.png")));
					}
				
					if (splitSh[8].compareTo("WKingWBoard.png") == 0) {  //If White KING on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RKingWBoard.png")));
					}
					else if (splitSh[8].compareTo("WKingBBoard.png") == 0) {	//If White KING on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RKingBBoard.png")));
					}
					
					/*
					 * PAWN
					 */
					
					if (splitSh[8].compareTo("BPawnWBoard.png") == 0) {  //If Black PAWN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UPawnWBoard.png")));
					}
					else if (splitSh[8].compareTo("BPawnBBoard.png") == 0) {	//If Black PAWN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/UPawnBBoard.png")));
					}
				
					if (splitSh[8].compareTo("WPawnWBoard.png") == 0) {  //If White PAWN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RPawnWBoard.png")));
					}
					else if (splitSh[8].compareTo("WPawnBBoard.png") == 0) {	//If White PAWN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/RPawnBBoard.png")));
					}
				}
			}
		}
		
		else {		//Resets colors to B&W
			for(int i = 0; i < 8; i++) { 	//Searches all squares
				for(int ii = 0; ii < 8; ii++) {
					shade = chessSquares[i][ii].getIcon().toString();	//Finds location of icon
					splitSh = shade.split("/");		//Gets simpler end of icon name
					
					/*
					 * ROOK
					 */
				
					if (splitSh[8].compareTo("URookWBoard.png") == 0) {  //If Black ROOK on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BRookWBoard.png")));
					}
					else if (splitSh[8].compareTo("URookBBoard.png") == 0) {	//If Black ROOK on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BRookWBoard.png")));
					}
				
					if (splitSh[8].compareTo("RRookWBoard.png") == 0) {  //If White ROOK on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WRookWBoard.png")));
					}
					else if (splitSh[8].compareTo("RRookBBoard.png") == 0) {	//If White ROOK on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WRookWBoard.png")));
					}
					
					/*
					 * BISHOP
					 */
					
					if (splitSh[8].compareTo("UBishopWBoard.png") == 0) {  //If Black BISHOP on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BBishopWBoard.png")));
					}
					else if (splitSh[8].compareTo("UBishopBBoard.png") == 0) {	//If Black BISHOP on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BBishopBBoard.png")));
					}
				
					if (splitSh[8].compareTo("RBishopWBoard.png") == 0) {  //If White BISHOP on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WBishopWBoard.png")));
					}
					else if (splitSh[8].compareTo("RBishopBBoard.png") == 0) {	//If White BISHOP on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WBishopBBoard.png")));
					}
					
					/*
					 * KNIGHT
					 */
					
					if (splitSh[8].compareTo("UKnightWBoard.png") == 0) {  //If Black KNIGHT on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BKnightWBoard.png")));
					}
					else if (splitSh[8].compareTo("UKnightBBoard.png") == 0) {	//If Black KNIGHT on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BKnightBBoard.png")));
					}
				
					if (splitSh[8].compareTo("RKnightWBoard.png") == 0) {  //If White KNIGHT on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WKnightWBoard.png")));
					}
					else if (splitSh[8].compareTo("RKnightBBoard.png") == 0) {	//If White KNIGHT on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WKnightBBoard.png")));
					}
					
					/*
					 * QUEEN
					 */
					
					if (splitSh[8].compareTo("UQueenWBoard.png") == 0) {  //If Black QUEEN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BQueenWBoard.png")));
					}
					else if (splitSh[8].compareTo("UQueenBBoard.png") == 0) {	//If Black QUEEN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BQueenBBoard.png")));
					}
				
					if (splitSh[8].compareTo("RQueenWBoard.png") == 0) {  //If White QUEEN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WQueenWBoard.png")));
					}
					else if (splitSh[8].compareTo("RQueenBBoard.png") == 0) {	//If White QUEEN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WQueenBBoard.png")));
					}
					
					/*
					 * KING
					 */
					
					if (splitSh[8].compareTo("UKingWBoard.png") == 0) {  //If Black KING on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BKingWBoard.png")));
					}
					else if (splitSh[8].compareTo("UKingBBoard.png") == 0) {	//If Black KING on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BKingBBoard.png")));
					}
				
					if (splitSh[8].compareTo("RKingWBoard.png") == 0) {  //If White KING on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WKingWBoard.png")));
					}
					else if (splitSh[8].compareTo("RKingBBoard.png") == 0) {	//If White KING on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WKingBBoard.png")));
					}
					
					/*
					 * PAWN
					 */
					
					if (splitSh[8].compareTo("UPawnWBoard.png") == 0) {  //If Black PAWN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BPawnWBoard.png")));
					}
					else if (splitSh[8].compareTo("UPawnBBoard.png") == 0) {	//If Black PAWN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BPawnBBoard.png")));
					}
				
					if (splitSh[8].compareTo("RPawnWBoard.png") == 0) {  //If White PAWN on White Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WPawnWBoard.png")));
					}
					else if (splitSh[8].compareTo("RPawnBBoard.png") == 0) {	//If White PAWN on Black Square
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WPawnBBoard.png")));
					}
				}
			}
		}
	}
	
	/**
	 * Set pieces method - will be called from the initialize method
	 * initializes board with pieces of chosen color
	 * (Requirement 1.0.0)
	 */
	public void setPieces(boolean pieceColor){
		char B;	// B for Black pieces or P for Blue pieces
		char W;	// W for White pieces or R for Red pieces
		if(pieceColor){
			B = 'B';
			W = 'W';
		} // end if
		else{
			B = 'U';
			W = 'R';
		}
		for(int i = 0; i < 8; i++){
			for(int ii = 0; ii < 8; ii++){
				// back row black setup
				if(i == 0){
					switch(ii){
						case 0: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"RookWBoard.png")));
						break;
						case 1: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"KnightBBoard.png")));
						break;
						case 2: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"BishopWBoard.png")));
						break;
						case 3: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"QueenBBoard.png")));
						break;
						case 4: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"KingWBoard.png")));
						break;
						case 5: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"BishopBBoard.png")));
						break;
						case 6: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"KnightWBoard.png")));
						break;
						case 7: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"RookBBoard.png")));
						break;
						} // end switch
						} // end back row black setup
				
				// front row black setup
				if(i == 1){
					if(ii % 2 == 0){
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"PawnBBoard.png")));
					} // end black pawn black square
					else{
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+B+"PawnWBoard.png")));
					} // end black pawn white square
				} // end front row black setup
				
				// front row white setup
				if(i == 6){
					if(ii % 2 == 0){
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"PawnWBoard.png")));
					} // end white pawn white square
					else{
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"PawnBBoard.png")));
					} // end white pawn black square
				} // end front row white setup
				
				// back row white setup
				if(i == 7){
					switch(ii){
						case 0: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"RookBBoard.png")));
						break;
						case 1: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"KnightWBoard.png")));
						break;
						case 2: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"BishopBBoard.png")));
						break;
						case 3: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"QueenWBoard.png")));
						break;
						case 4: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"KingBBoard.png")));
						break;
						case 5: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"BishopWBoard.png")));
						break;
						case 6: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"KnightBBoard.png")));
						break;
						case 7: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/"+W+"RookWBoard.png")));
						break;
						} // end switch
						} // end back row white setup
				
			} // end for ii
		} // end for i
	} // end setPieces method
	
	
	/**
	 * Move piece method - will be called from Action Listener
	 * sets origin square to proper board color and sets destination square to correct piece/board combo
	 * (Requirement 1.1.0)
	 */
	public void movePiece(int [] start, int [] end, String piece){
		/**
		 * This block will set starting square to unoccupied and correct color
		 */
		if((start[0] + start[1]) % 2 == 0){
			chessSquares[start[0]][start[1]].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WBoard.png")));
		}
		else{
			chessSquares[start[0]][start[1]].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BBoard.png")));
		}
		
		/**
		 * This block will set end square to correct piece and color
		 */
		piece = new StringBuilder(piece).reverse().toString();
		Scanner s = new Scanner(piece).useDelimiter("\\W");
		StringBuilder sb = new StringBuilder();
		sb.append(s.next());
		sb.append(".");
		sb.append(s.next());
		sb.append("/");
		sb.append(s.next());
		sb.append("/");
		piece = sb.reverse().toString();
		piece = piece.substring(0, piece.length()-10);
		if((end[0] + end[1]) % 2 == 0 ){
			piece = piece + "WBoard.png";
		} // end if even square
		else if((end[0] + end[1]) % 2 == 1 ){
			piece = piece + "BBoard.png";
		} // end else if odd square
		chessSquares[end[0]][end[1]].setIcon(new ImageIcon(ChessWindowClass.class.getResource(piece)));
		
		
		/*
		 * Decides and Shows Whose Turn it is
		*/
		
		if (turn == 0)
		{
			if (pieceColor == false)
				label.setText("Black Team");
			else if (pieceColor == true)
				label.setText("Blue Team");
			turn++;
		}
		else if (turn == 1)
		{
			if (pieceColor == false)
				label.setText("White Team");
			else if (pieceColor == true)
				label.setText("Red Team");
			turn--;
		}
	} // end movePiece method
	
	/**
	 * highlightMoves method - called after first click
	 * will highlight sqaures where selected piece can move
	 * (Requirement 2.2.0)
	 */
   public void highlightMoves(ArrayList<int[]> moves){
	   possibleMoves = moves;
	   String highPiece = new String();
	   int [] coordinates = new int [2];
	   int row;
	   int col;
	   
	   for(int i = 0; i < moves.size(); i++){
		   coordinates = moves.get(i);
		   row = coordinates[0];
		   col = coordinates[1];
		   highPiece = chessSquares[row][col].getIcon().toString();
		   highPiece = new StringBuilder(highPiece).reverse().toString();
		   Scanner s = new Scanner(highPiece).useDelimiter("\\W");
		   StringBuilder sb = new StringBuilder();
		   sb.append(s.next());
		   sb.append(".");
		   sb.append(s.next());
		   sb.append("/");
		   sb.append(s.next());
		   sb.append("/");
		   highPiece = sb.reverse().toString();
		   highPiece = highPiece.substring(0, (highPiece.length()-4));
		   highPiece = highPiece + "High.png";
		   chessSquares[row][col].setIcon(new ImageIcon(ChessWindowClass.class.getResource(highPiece)));
	   } // end for i loop
   } // end highlightMoves method
   
   /**
    * clearHighlight method - called after second click
    * clears possible moves highlight from squares
    * (Requirement 2.2.0)
    */
   
   public void clearHighlight(ArrayList<int[]> moves){
	   String highPiece = new String();
	   int [] coordinates = new int [2];
	   int row;
	   int col;
	   
	   for(int i = 0; i < moves.size(); i++){
		   coordinates = moves.get(i);
		   row = coordinates[0];
		   col = coordinates[1];
		   highPiece = chessSquares[row][col].getIcon().toString();
		   highPiece = new StringBuilder(highPiece).reverse().toString();
		   Scanner s = new Scanner(highPiece).useDelimiter("\\W");
		   StringBuilder sb = new StringBuilder();
		   sb.append(s.next());
		   sb.append(".");
		   sb.append(s.next());
		   sb.append("/");
		   sb.append(s.next());
		   sb.append("/");
		   highPiece = sb.reverse().toString();
		   highPiece = highPiece.substring(0, (highPiece.length()-8));
		   highPiece = highPiece + ".png";
		   chessSquares[row][col].setIcon(new ImageIcon(ChessWindowClass.class.getResource(highPiece)));
	   } // end for i loop
   } // end clearHighlight method
   
   /**
    * pawnPromotionMove method - called from Action Listener
    * prompts user to select a piece to replace a pawn which has advanced to opponent's back row
    * (Requirement 2.4.0)
    */
   
public void pawnPromotionMove(int row, int col, int turn, boolean pieceColor){
	   StringBuilder newIcon = new StringBuilder("/Images/");
	   boolean team;
		// This block pops up player piece choice
		String[] pieces = {"Queen", "Bishop", "Rook", "Knight"};

		Object choice = JOptionPane.showInputDialog(null, "Choose your new piece:", "Selection", JOptionPane.DEFAULT_OPTION, null, pieces, "0");
		String selection = choice.toString();

		//adds piece color to image file path
		if(turn == 1){
			if(pieceColor){
				newIcon.append("W");
			}
			else{
				newIcon.append("R");
			}
		}
		else if (turn == 0){
			if(pieceColor){
				newIcon.append("B");
			}
			else{
				newIcon.append("U");
			}

		}
		//adds piece type to image file path
		newIcon.append(selection);

		//adds board color to image file path
		if((row + col)%2 == 0){
			newIcon.append("WBoard.png");
		}
		else{
			newIcon.append("BBoard.png");
		}
		
		chessSquares[row][col].setIcon(new ImageIcon(ChessWindowClass.class.getResource(newIcon.toString())));
		if(turn == 0){
			team = true;
		}
		else{
			team = false;
		}
		
		if(selection == "Queen"){
			fClick.pawnPromoted(new Queen());
		} // end if Queen
		else if(selection == "Bishop"){
			fClick.pawnPromoted(new Bishop());
		}
		else if(selection == "Rook"){
			fClick.pawnPromoted(new Rook());
		}
		else if(selection == "Knight"){
			fClick.pawnPromoted(new Knight());
		}
		
		} // end pawnPromotionMethod
   
 
   
   /**
    * Action Event Listener
    * records player "clicks" indication piece/square selection
    * (Requirements 1.1.0 and 2.3.0)
    */
	
   public void actionPerformed(ActionEvent e) { //Used for picking which piece to move
        Object pick = e.getSource();
        
        if (count == 0) {
            for (int i = 0; i < 8; i ++) {
                for (int ii = 0; ii < 8; ii++) {
                    if (pick == chessSquares[i][ii]) {
                        //chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BQueenBBoard.png"))); //FOR TESTING
                        start[0] = i;     //Stores first-clicked place ROW location
                        start[1] = ii;    //Stores first-clicked place ROW location                     
                        
                        //validates the first click and makes sure second click is only reached when first is valid
                        if (fClick.FirstClick(new Click(i,ii))){
                        	highlightMoves(fClick.returnPossibleMoves());
                        	count++; //Used for keeping track of first and second click, converts to next action for second click
                        }else{                         
                        	count = 0; //keeps everything within the first click;
                        }
                    }
                }
            }
        }
        
        else {    
            for (int i = 0; i < 8; i ++) {
                for (int ii = 0; ii < 8; ii++) {
                        if (pick == chessSquares[i][ii]) {
                            //chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BRookBBoard.png"))); //FOR TESTING
                            
                        	end[0] = i;		//Stores second-clicked place ROW location
			    		    end[1] = ii;		//Stores second-clicked place COLUMN location
                            
			    		    if (fClick.SecondClick(new Click(i,ii))){
			    		    	String piece = chessSquares[start[0]][start[1]].getIcon().toString();   // String 'piece' needed for movePiece method
			    		    	clearHighlight(possibleMoves);
			    		    	movePiece(start, end, piece);    // call to movePiece method
			    		    	// check for castling move
			    		    	if(fClick.isKingCastling()){
			    		    		// insert castling move method here
			    		    	} // end if isKingCastling
			    		    	// check for pawn promotion
			    		    	else if(fClick.pawnPromotion()){
			    		    		pawnPromotionMove(i, ii, turn, pieceColor);
			    		    	} // end else if pawnPromotion
			    		    	// else standard piece movement
			    		   
			    		    	//displays a message if the king is in check
			    		    	if (fClick.isKingInCheck()){
			    		    		fClick.ShowDialogBox("King is in Check", "Check");
			    		    	}
			    		    	
//			    		    	check = fClick.isKingInCheck(false, false);
//			    		    	leavingInCheck = fClick.isKingInCheck(true, true);
//			    		    	if(leavingInCheck){
//			    		    		fClick.reverseMove();
//			    		    		movePiece(end, start, piece);
//			    		    	}		   
			    		   	}
			    		    else {
			    		   		clearHighlight(possibleMoves);
			    		    	//not a valid move
			    		    }
                            
                            count = 0; //Used for keeping track of first and second click, converts to next action for first click
                        }
                }
            }
        }
     }
    
} // end ChessWindowClass