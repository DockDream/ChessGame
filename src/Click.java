/**
** Made as a more descriptive way to represent a click 
*/
public class Click {
	int row;
	int col;
	
	/**
	 ** Constructor to set the row and column 
	 */
	Click(int r, int c){
		this.row = r;
		this.col = c;
	}
	
	/**
	 ** Constructor to set the row and column 
	 */
	Click(Click sentC){
		this.row = sentC.row;
		this.col = sentC.col;
	}
	
	/*
	 * Method that compares the click sent to itself
	 */
	public boolean equals(Click sentC){
		if ((this.row == sentC.row) && (this.col == sentC.col)){
			return true;
		}else{
			return false;
		}
	}
}
