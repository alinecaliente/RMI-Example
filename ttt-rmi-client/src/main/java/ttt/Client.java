package ttt;
import java.rmi.*;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) throws Exception {
        Scanner keyboardSc= new Scanner(System.in);
        int winner = 0;
        int player = 1;
        
        TTTService ttt = null;
        
        try{
            
            ttt = (TTTService) Naming.lookup("//localhost:1099/TTT");
            System.out.println("Found Server.");    
            
            int play;
            boolean playAccepted;
            //do{
                do {
                    player = ++player % 2;
                    do {
                        System.out.println(ttt.currentBoard());
    
                        do {
                            System.out.printf("\nPlayer %d, please enter the number of the square ", player);
                            play = keyboardSc.nextInt();
                            
                            
                                 if(play== 10){
	                                String s = "Player " + player +", insert your new symbol: ";
	                                System.out.println(s);
	                                char simbolo = (char) System.in.read();
	                                ttt.trocaSimbolos(simbolo);
	                                playAccepted=true;
	                                continue;
	                              }
                                
                            
                        } while (play > 9 || play < 0);
                        
                        
                        if (play != 0) {
                            playAccepted = ttt.play( --play / 3, play % 3, player);
                            if (!playAccepted)
                                System.out.println("Invalid play! Try again.");
                        } else
                            playAccepted = false;
                    } while (!playAccepted);
                    winner = ttt.checkWinner();
                } while (winner == -1);
            
            if (winner == 2)
                System.out.printf("\nHow boring, it is a draw\n");
            else
                System.out.printf(
                        "\nCongratulations, player %d, YOU ARE THE WINNER!\n",
                        winner);

        }catch(RemoteException e){
            System.out.println("Winner: " + e.getMessage());    
        }
    }

}
