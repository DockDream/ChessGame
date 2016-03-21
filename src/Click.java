
public class Click {
	int row;
	int col;
	
	Click(int r, int c){
		this.row = r;
		this.col = c;
	}
	
	Click(Click sentC){
		this.row = sentC.row;
		this.col = sentC.col;
	}
	
	/*
	 * Method that compares values between clicks
	 */
	public boolean equals(Click sentC){
		if ((this.row == sentC.row) && (this.col == sentC.col)){
			return true;
		}else{
			return false;
		}
	}
}
