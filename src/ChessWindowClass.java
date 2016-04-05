import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
    Label label = new Label("Player 1 Turn");

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
		
		//String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
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
		frmJavaholicsChess.setAlwaysOnTop(true);		//Makes window on top of all windows
		frmJavaholicsChess.setBounds(100, 100, 800, 798);	//Sets size of window
		frmJavaholicsChess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends program on close of window
      
		JMenuBar menuBar = new JMenuBar(); //Creates Menu Bar to top of window
		frmJavaholicsChess.setJMenuBar(menuBar); //Adds MenuBar
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Options"); //Creates item Options to MenuBar
		menuBar.add(mntmNewMenuItem);	//Adds item to MenuBar
		menuBar.add(label);				//Adds label for player turn
		
		setPieces(pieceColor);	// piece initialization method call
				
	} // end initialize method
	
	
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
			label.setText("Player 2 Turn");
			turn++;
		}
		else if (turn == 1)
		{
			label.setText("Player 1 Turn");
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
			    		    	check = fClick.isKingInCheck(false, false);
			    		    	leavingInCheck = fClick.isKingInCheck(true, true);
			    		    	if(leavingInCheck){
			    		    		fClick.reverseMove();
			    		    		movePiece(end, start, piece);
			    		    	}
			    		   	}else{
			    		   		clearHighlight(possibleMoves);
			    		    	//not a valid move
			    		    }
			    		    
			    		    //Need to check if the king is in check here
			    		    
                            /* if (valid.ValidMove(i, ii) == true) {                //Sends location of chosen piece to backend
			    		            String piece = chessSquares[start[0]][start[1]].getIcon().toString();   // String 'piece' needed for movePiece method
			    		         
                              movePiece(start, end, piece);    // call to movePiece method
                              }
                              else { //ELSE reset click and send invalid move error
                                 count--;
                                    //SEND ERROR MESSAGE TO PICK ANOTHER SPOT - PSEUDO
                                }
                            } */
                            
                            count = 0; //Used for keeping track of first and second click, converts to next action for first click
                        }
                }
            }
        }
     }
    
} // end ChessWindowClass