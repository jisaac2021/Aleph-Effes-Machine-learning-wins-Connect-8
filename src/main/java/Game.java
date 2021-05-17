
import java.io.IOException;
import java.util.ArrayList;

public class Game {

    final public int ROWS;
    final public int COLS;
    final public int CONNECT;
    
    public Board board;
    
    public Player currentPlayer;
    
    public Player human, computer;
    
    private int turns;
    
    boolean win;

    public Game(int rows, int cols, int conn, Player human, Player computer){
        ROWS = rows;
        COLS = cols;
        CONNECT = conn;
        
        board = new Board(ROWS, COLS, CONNECT);
        
        this.human = human;
        this.computer = computer;
        
        currentPlayer = human;
        
        turns = 0;
        
        win = false;
    }
        
    
        
    public boolean play(boolean loopMode) throws IOException {
        
            boolean isWin = false;
            
            int c = currentPlayer.getMove(this);
            
            if (c == -1) return true;
            
            if (board.placeChip(c, currentPlayer.playerNum)) {
                isWin = true;
                win = true;
            }
                
                            
            System.out.println("Player " + currentPlayer.playerNum + " has moved");
            
            if (!isWin) nextTurn();
            
            board.printBoard();
            
            return isWin;
                        
    }
    
    
    
    
    
    
    
    public void nextTurn() {
        if (currentPlayer == human)
            currentPlayer = computer;
                        
        else currentPlayer = human;
        
        turns++;
        
    }

    
    public void setTurns(int turns) {
        
        this.turns = turns;
    }
    
    public int getTurns() {
        
        return turns;
    }
}
