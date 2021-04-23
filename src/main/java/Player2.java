/**
 * Created by Zachi_Mac13in_1 on 3/28/21.
 */
public class Player2 extends Player {
    
    
    public Player2(String color) {
        playerNum = 2;
        this.color = color;
    }
    
    @Override
    public int getMove(Game game) {
        return (int) (Math.random() * game.COLS);
     
        
    }
    
}
