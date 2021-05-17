/**
 * Created by Zachi_Mac13in_1 on 3/28/21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Color;

public class Player1 extends Player {

    public Player1(Color color) {
        playerNum = 1;
        this.color = color;
    }
    
    @Override
    public int getMove(Game game) throws IOException {
        
        boolean isLegal = false;
        
        int finalCol = 0;
        
        while (!isLegal) {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Player 1, please select your column:");
            int c = Integer.parseInt(br.readLine());
            
            if (game.board.isLegalMove(c)) {
                finalCol = c;
                isLegal = true;
               
            }
            
            else {
                System.out.println("Column "+ c +" is already full!!");
            }
            
        }

        return finalCol;  

     
        
    }

}
