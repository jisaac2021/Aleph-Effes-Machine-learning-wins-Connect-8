
import java.io.IOException;

public class Game {

    final public int ROWS;
    final public int COLS;
    final public int CONNECT;
    
    public int[][] board;
    
    public Player currentPlayer;
    
    public Player human, computer;
    
    private int turns;

    public Game(int rows, int cols, int conn, Player human, Player computer){
        ROWS = rows;
        COLS = cols;
        CONNECT = conn;
        
        board = new int[ROWS][COLS];
        
        this.human = human;
        this.computer = computer;
        
        currentPlayer = human;
        
        turns = 0;
    }
    
    
        
    public void play(boolean loopMode) throws IOException {
        
            int c = currentPlayer.getMove(this);
            
            if (placeChip(c, currentPlayer.playerNum))
                System.out.println("Player " + currentPlayer.playerNum + " has moved");
            
            nextTurn();
            
            printBoard();
                        
    }
    
    
    public boolean isLegalMove(int[][] board, int c) {
        
        if (!(c >= 0 && c < COLS)) return false;
        if (board[0][c] == 0) return true;
        
        return false;
    }
    
    public void allLegalMoves() {
        
    }
    
    public boolean placeChip(int c, int playerNum) {
        
        int i = 0;
        
        for (i = ROWS - 1; i >= 0; i--) {
            if (board[i][c] == 0) {
                board[i][c] = playerNum;
                break;
            }
        }
        
        
        return isConnectFour(i, c);
        
    }
    
    public void nextTurn() {
        if (currentPlayer == human)
            currentPlayer = computer;

        else currentPlayer = human;
        
        turns++;
        
    }

    public boolean isConnectFour(int i, int j) {
        
        int player = board[i][j];
        
        // horizantal
        
        int count = 0;
        
        for (int c = 0; c < COLS; c++) {
            int current = board[i][c];
            if (current == player) count++;
            else count = 0;
            if (count == CONNECT) return true;
            
        }
        
        // vertical
        
        count = 0;
        
        for (int r = 0; r < ROWS; r++) {
            int current = board[r][j];
            if (current == player) {
                count++;
                if (count == CONNECT) return true;
            }
            else count = 0;
            
            
        }
        
        // top-left to bottom-right
        for (int rs = 0; rs < ROWS - 4; rs++){
            count = 0;
            int row, col;
            for (row = rs, col = 0; row < ROWS && col < COLS; row++, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) return true;
                }
                else {
                    count = 0;
                }
            }
        }
        
        for (int cs = 0; cs < COLS - 4; cs++){
            count = 0;
            int row, col;
            for (row = 0, col = cs; row < ROWS && col < COLS; row++, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) return true;
                }
                else {
                    count = 0;
                }
            }
        }
        
        // ascending diagonals
        
        for (int rs = ROWS - 1; rs < 4; rs--){
            count = 0;
            int row, col;
            for (row = ROWS - 1, col = 0; row < ROWS && col < COLS; row--, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) return true;
                }
                else {
                    count = 0;
                }
            }
        }

        for (int cs = 1; cs < COLS - 4; cs++){
            count = 0;
            int row, col;
            for (row = ROWS - 1, col = cs; row >= 0 && col < COLS; row--, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) return true;
                }
                else {
                    count = 0;
                }
            }
        }

        
        return false;
    }
    
    public boolean sayColor1(int ii, int jj) {
        
        if (board[ii][jj] == 1) return true;
        
        return false;
        
    }
    
    public boolean sayColor2(int ii, int jj) {
        
        if (board[ii][jj] == 2) return true;
        
        return false;
        
    }
    
    public void printBoard() {
        
        for (int i = 0; i <  ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if(board[i][j] == 0)
                    System.out.print(".  ");
                else
                    System.out.print(board[i][j]+"  ");
            }
            System.out.println("");
        }
        System.out.println("--------------------");
        System.out.println("0  1  2  3  4  5  6");
    }
    
    public int getTurns() {
        
        return turns;
    }
}
