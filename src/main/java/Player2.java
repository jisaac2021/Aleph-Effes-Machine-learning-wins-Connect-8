/**
 * Created by Zachi_Mac13in_1 on 3/28/21.
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Player2 extends Player {
    
    
    public Player2(Color color) {
        playerNum = 2;
        this.color = color;
    }
    
    @Override
    public int getMove(Game game) {
        
        // solveBFS(game);
        
        // return (int) (Math.random() * game.COLS);
        
        int[] decision = minimax(game.board, 4, true);
     
        System.out.println("Bestcol for 2 = " + decision[0]);
        
        return decision[0];
        
        
    }
    
    class OurComparator implements Comparator<Board> {
        @Override
        public int compare(Board b1, Board b2) {
            return (int) (scoreBoard(b1) - scoreBoard(b2));
        }
    }
    
    public void solveBFS(Game game) {
        Queue<Board> q = new LinkedList<>();
        
        q.add(game.board); // enqueue
        
        while (!game.win) {
            Board b = q.remove(); // dequeue
            // check neighbors
            
            ArrayList<Integer> legalMoves = b.allLegalMoves();
  
            
            for (int col : legalMoves) {
                Board b2 = game.board.clone();
                b2.placeChip(col, 2);
                q.add(b2);
                b2.printBoard();
                
            }
            
            
            
            
            System.out.println(this);
            
            
            
        }
        
    }
    
    public boolean isTerminalNode(Board board) {
        boolean ISTERMINAl = (board.isConnectN(4, 1) >= 1 || board.isConnectN(4, 2) >= 1 || board.allLegalMoves().isEmpty());
        System.out.println("ISTERMINAl" + ISTERMINAl);
        
                return ISTERMINAl;
    }
    
    
    public int[] minimax(Board board, int depth, boolean maximizingPlayer) {
        
        // change to breath first!!!!
        
        ArrayList<Integer> legalMoves = board.allLegalMoves();
        // array = {column, score}
        
        if ((depth == 0) || isTerminalNode(board)) {
            if (isTerminalNode(board)) {
                if (board.isConnectN(4, 1) >= 1) return new int[] {0, Integer.MIN_VALUE}; 
                else if (board.isConnectN(4, 2) >= 1) return new int[] {0, Integer.MAX_VALUE}; 
                else return new int[] {0, 0}; 
            }
            else return new int[] {0, scoreBoard(board)}; 
        }
            
        if (maximizingPlayer) {
            int val = Integer.MIN_VALUE;
            int column = legalMoves.get(0);
            for (int col : legalMoves) {
                Board bCopy = board.clone();
                
                // System.out.println("Legalmove: " + col);
                bCopy.placeChip(col, 2);
                
                // board.printBoard();
                
                
                int newScore = minimax(bCopy, depth-1, false)[1];
                
                System.out.println("col = " + col + "val = " + newScore);
                
                if (newScore > val) {
                    val = newScore;
                    column = col;
                }
                
            }
            
            return new int[] {column, val};
            
        }
        
        else {
            int val = Integer.MAX_VALUE;
            int column = legalMoves.get(0);
            
            for (int col : legalMoves) {
                Board bCopy = board.clone();
                
                // System.out.println("Legalmove: " + col);
                bCopy.placeChip(col, 1);
                
                
                // board.printBoard();
                
                int newScore = minimax(bCopy, depth-1, true)[1];
                
                if (newScore < val) {
                    val = newScore;
                    column = col;
                }
                
            }
            
            return new int[] {column, val};
            
        }
                
    }
    
    public int scoreBoard(Board board) {
        
        int con4AI = board.isConnectN(4, 2);
        System.out.println("con4AI" + con4AI);
        int con3AI = board.isConnectN(3, 2);
        System.out.println("con3AI" + con3AI);
        int con2AI = board.isConnectN(2, 2);
        System.out.println("con2AI" + con2AI);
        
        int con4human = board.isConnectN(4, 1);
        System.out.println("con4human" + con4human);
        int con3human = board.isConnectN(3, 1);
        System.out.println("con3human" + con3human);
        int con2human = board.isConnectN(2, 1);
        System.out.println("con2human" + con2human);
        
        
        return (con4AI * 1000000 + con3AI * 100 + con3AI * 5)
                - (con4human * 1000000 + con3human * 100 + con2human * 5);
    }
    
    
    
    
    
    
    
}
