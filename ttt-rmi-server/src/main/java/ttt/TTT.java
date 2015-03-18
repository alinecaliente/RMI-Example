package ttt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TTT extends UnicastRemoteObject implements TTTService{
	protected TTT() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    char board[][] = {
		  {'1','2','3'},          /* Initial values are reference numbers */
		  {'4','5','6'},          /* used to select a vacant square for   */
		  {'7','8','9'}           /* a turn.                              */
		};
	int nextPlayer = 0;
	int numPlays = 0;
	char simbolo0 = 'O';
	char simbolo1 = 'X';

    public String currentBoard() {
    	String s = "\n\n " + 
    				board[0][0]+" | " +
    				board[0][1]+" | " +
    				board[0][2]+" " +
    				"\n---+---+---\n " +
    				board[1][0]+" | " +
    				board[1][1]+" | " +
    				board[1][2]+" " +
    				"\n---+---+---\n " +
    				board[2][0]+" | " +
    				board[2][1]+" | " +
    				board[2][2] + " \n";
    	return s;
    }

    public boolean play(int row, int column, int player) {
		if (!(row >=0 && row <3 && column >= 0 && column < 3))
			return false;
		if (board[row][column] > '9')
			return false;
		if (player != nextPlayer) 
			return false;

		if (numPlays == 9) 
			return false;

		board[row][column] = (player == 1) ? simbolo1 : simbolo0;        /* Insert player symbol   */
		nextPlayer = (nextPlayer + 1) % 2;
		numPlays ++;
		return true;	
    }

    public int checkWinner() {
    	  int i;
    	  /* Check for a winning line - diagonals first */     
    	  if((board[0][0] == board[1][1] && board[0][0] == board[2][2]) ||
    	     (board[0][2] == board[1][1] && board[0][2] == board[2][0])) {
    		  if (board[1][1]==simbolo1)
    			  return 1;
    		  else 
    			  return 0;
    	  }
    	  else
    	    /* Check rows and columns for a winning line */
    	    for(i = 0; i <= 2; i ++){
    	      if((board[i][0] == board[i][1] && board[i][0] == board[i][2])) {
    	    	  if (board[i][0]==simbolo1)
    	    		  return 1;
    	    	  else 
    	    		  return 0;
    	      }

    	     if ((board[0][i] == board[1][i] && board[0][i] == board[2][i])) {
    	    	 if (board[0][i]==simbolo1) 
    	    		 return 1;
    	    	 else 
    	    		 return 0;
    	     }
    	    }
    	  	if (numPlays == 9)
    	  		return 2; /* A draw! */
    	  	else
    	  		return -1; /* Game is not over yet */
	}

	public void trocaSimbolos(char simbolo) {
		if(nextPlayer == 0){
			for(int i = 0; i <= 2; i ++){
				for (int j = 0; j <= 2; j++){
					if(board[i][j]==simbolo0)
						board[i][j] = simbolo;
				}
			}
			simbolo0 = simbolo;}
		else{
			if(nextPlayer == 1){
				for(int i = 0; i <= 2; i ++){
					for (int j = 0; j <= 2; j++){
						if(board[i][j]==simbolo1)
							board[i][j] = simbolo;
					}
				}
				simbolo1 = simbolo;		
			}
		}
	}
}
