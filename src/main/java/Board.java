
import java.util.ArrayList;


public class Board {
    
    public int[][] board;
    
    int ROWS, COLS;
    
    int CONNECT = 4;
    
    public Board(int ROWS, int COLS, int CONNECT) {
        
        this.ROWS = ROWS;
        this.COLS = COLS;
        
        this.CONNECT = CONNECT;
        
        board = new int[ROWS][COLS];
        
    }
    
    public Board(int[][] board)
    {
        this.board = board;
        
        ROWS = board.length;
        COLS = board[0].length;
    }
    
    public boolean placeChip(int c, int playerNum) {
        
        int i = ROWS - 1;
        
        for (i = ROWS - 1; i >= 0; i--) {
            if (board[i][c] == 0) {
                board[i][c] = playerNum;
                break;
            }
        }
        
        return isConnectFour(i, c, CONNECT);
        
    }
    

    public int getRow(int c) {
        
        int i, r = 0;
        
        for (i = ROWS - 1; i >= 0; i--) {
            if (board[i][c] == 0) {
                r = i;
                break;
            }
        }
        
        return r;
        
    }
    
    public boolean isLegalMove(int c) {
        
        if (!(c >= 0 && c < COLS)) return false;
        if (board[0][c] == 0) return true;
        
        return false;
    }
    
    public ArrayList<Integer> allLegalMoves() {
        
        ArrayList<Integer> legalCols = new ArrayList<>();
        for (int c = 0; c < COLS; c++) {
            if (isLegalMove(c))
                legalCols.add(c);
        }
        
        return legalCols;
        
    }
    
    public boolean isConnectFour(int i, int j, int CONNECT) {
                
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
                
        // descending diagonals
                
        for (int rs = 0; rs <= ROWS - 4; rs++){
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
        
        for (int cs = 1; cs <= COLS - 4; cs++){
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
        
        for (int rs = ROWS - 1; rs >= 3; rs--){
            count = 0;
            int row, col;
            for (row = ROWS - 1, col = 0; row >= 0 && col < COLS; row--, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) return true;
                }
                else {
                    count = 0;
                }
            }
        }

        for (int cs = 1; cs <= COLS - 4; cs++){
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
    
    public int isConnectN(int CONNECT, int player) {
                
        // horizantal
        
        int countfinal = 0;
        
        int count = 0;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
            int current = board[r][c];
            if (current == player) count++;
            else count = 0;
            if (count == CONNECT) countfinal++;
            
            }
        }
        
        
        // vertical
        
        count = 0;
        
        for (int c = 0; c < COLS; c++) {
            for (int r = 0; r < ROWS; r++) {
            int current = board[r][c];
            if (current == player) {
                count++;
                if (count == CONNECT) countfinal++;
            }
            else count = 0;
            
            }
            
        }
        
        // descending diagonals
                
        for (int rs = 0; rs <= ROWS - 4; rs++){
            count = 0;
            int row, col;
            for (row = rs, col = 0; row < ROWS && col < COLS; row++, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) countfinal++;
                }
                else {
                    count = 0;
                }
            }
        }
        
        for (int cs = 1; cs <= COLS - 4; cs++){
            count = 0;
            int row, col;
            for (row = 0, col = cs; row < ROWS && col < COLS; row++, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) countfinal++;
                }
                else {
                    count = 0;
                }
            }
        }
        
        // ascending diagonals
        
        for (int rs = ROWS - 1; rs >= 3; rs--){
            count = 0;
            int row, col;
            for (row = ROWS - 1, col = 0; row >= 0 && col < COLS; row--, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) countfinal++;
                }
                else {
                    count = 0;
                }
            }
        }

        for (int cs = 1; cs <= COLS - 4; cs++){
            count = 0;
            int row, col;
            for (row = ROWS - 1, col = cs; row >= 0 && col < COLS; row--, col++) {
                if(board[row][col] == player){
                    count++;
                    if (count == CONNECT) countfinal++;
                }
                else {
                    count = 0;
                }
            }
        }

        
        return countfinal;
    }
    
    @Override
    public Board clone() {
        int [][] clonedBoard = new int[board.length][];
        for (int i = 0; i < board.length; i++)
            clonedBoard[i] = board[i].clone();
        
        return new Board(clonedBoard);
        
    }
    
}
