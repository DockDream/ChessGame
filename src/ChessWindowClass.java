import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ChessWindowClass implements ActionListener{
	
	private JFrame frmJavaholicsChess;
	private JButton[][] chessSquares = new JButton[8][8];
	public int startLocROW;	//Used for piece chosen, ROW.
	public int startLocCOL;	//Used for piece chosen, COLUMN.
	public int endLocROW;	//Used for place chosen, ROW.
	public int endLocCOL; 	//Used for place chosen, COLUMN.
	private int count = 0;

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
	 */
	
	private void initialize() throws IOException {
		frmJavaholicsChess = new JFrame();
		frmJavaholicsChess.getContentPane().setLayout(new GridLayout(8, 8, 0, 0));
		
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
				
				// piece initialization
				// back row black setup
				if(i == 0){
					switch(ii){
						case 0: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BRookWBoard.png")));
						break;
						case 1: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BKnightBBoard.png")));
						break;
						case 2: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BBishopWBoard.png")));
						break;
						case 3: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BQueenBBoard.png")));
						break;
						case 4: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BKingWBoard.png")));
						break;
						case 5: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BBishopBBoard.png")));
						break;
						case 6: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BKnightWBoard.png")));
						break;
						case 7: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BRookBBoard.png")));
						break;
						} // end switch
						} // end back row black setup
				
				// front row black setup
				if(i == 1){
					if(ii % 2 == 0){
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BPawnBBoard.png")));
					} // end black pawn black square
					else{
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BPawnWBoard.png")));
					} // end black pawn white square
				} // end front row black setup
				
				// front row white setup
				if(i == 6){
					if(ii % 2 == 0){
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WPawnWBoard.png")));
					} // end white pawn white square
					else{
						chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WPawnBBoard.png")));
					} // end white pawn black square
				} // end front row white setup
				
				// back row white setup
				if(i == 7){
					switch(ii){
						case 0: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WRookBBoard.png")));
						break;
						case 1: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WKnightWBoard.png")));
						break;
						case 2: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WBishopBBoard.png")));
						break;
						case 3: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WQueenWBoard.png")));
						break;
						case 4: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WKingBBoard.png")));
						break;
						case 5: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WBishopWBoard.png")));
						break;
						case 6: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WKnightBBoard.png")));
						break;
						case 7: chessSquares[i][ii].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WRookWBoard.png")));
						break;
						} // end switch
						} // end back row white setup
						
				
				
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
	} // end initialize method
	
	/**
	 * Move piece method - will be called from Action Listener
	 */
	public void movePiece(int sLR, int sLC, int eLR, int eLC, String piece){
		/**
		 * This block will set starting square to unoccupied and correct color
		 */
		if((startLocROW + startLocCOL) % 2 == 0){
			chessSquares[startLocROW][startLocCOL].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/WBoard.png")));
		}
		else{
			chessSquares[startLocROW][startLocCOL].setIcon(new ImageIcon(ChessWindowClass.class.getResource("/Images/BBoard.png")));
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
		if((endLocROW + endLocCOL) % 2 == 0 ){
			piece = piece + "WBoard.png";
		} // end if even square
		else if((endLocROW + endLocCOL) % 2 == 1 ){
			piece = piece + "BBoard.png";
		} // end else if odd square
		chessSquares[endLocROW][endLocCOL].setIcon(new ImageIcon(ChessWindowClass.class.getResource(piece)));
	} // end movePiece method
	
	public void actionPerformed(ActionEvent e) {		//Used for picking which piece to move
	    if (count == 0) {                               //IF first click
		    Object pick = e.getSource();	
		
		    for (int i = 0; i < 8; i ++) {
			    for (int ii = 0; i < 8; i++) {
				    if (pick == chessSquares[i][ii]) {
					    startLocROW = i;	//Stores clicked piece ROW location
					    startLocCOL = ii;	//Stores clicked piece COLUMN location

					    //getInitial(startLocROW,startLocCOL);    /Sends location of chosen piece to backend
					    count++;
				    }
			    }
		    }
	    }
	}
	
	public void actionPerformed1(ActionEvent e1) {		//Used for placing piece to desired location
	    if (count == 1) {                               //IF second click
	    	Object place = e1.getSource();
		
	    	for (int i = 0; i < 8; i ++) {
		    	for (int ii = 0; i < 8; i++) {
		      		if (place == chessSquares[i][ii]) {
		    			endLocROW = i;		//Stores second-clicked place ROW location
			    		endLocCOL = ii;		//Stores second-clicked place COLUMN location
					
			    		//getInitial(endLocROW, endLocCOL);    /Sends location to place piece to backend
			    		count--;
			    		// String 'piece' needed for movePiece method
			    		String piece = chessSquares[startLocROW][startLocCOL].getIcon().toString();
			    		// call to movePiece method
			    		movePiece(startLocROW, startLocCOL, endLocROW, endLocCOL, piece);
			    		
			    		
			    	} // end if place == 
		    	} // end for ii
		    } // end for i
		} // end if count = 1
	} // end Action e1
} // end ChessWindowClass