
// first brute force --> alpha beta pruning
// heuristic (average win rate per tree)


// monte carlo

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
/*
import javax.swing.JSlider;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
 */

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 *
 *
 *   We will keep things NOT too flexible here,
 *   so as to make the design and code simpler.
 *
 *  Things will be in fixed-location on screen.
 *  This is called Absolute-positioning (as compared to using layouts)
 *  http://docs.oracle.com/javase/tutorial/uiswing/layout/none.html
 *  All the definition are grouped below
 *  The graphic tries to follow the look of:
 *  http://www.bitstorm.org/gameoflife/
 *
 *   Display is responsible for drawing the game.
 *   Display :
 *   	  Manages interactions with the game object
 *      Creates and manages the items display (getting the Frame from main)
 *      Handles all non-button events. That means, getting mouse clicks,
 *      determining which cell they hit, and managing response.
 *
 *
 */
@SuppressWarnings("serial")
public class Display extends JComponent {

    int frameWidth, frameHeight;
    boolean loopMode = false;

    private final static long STEP_TIME_MILLIS = 150; // time in milliseconds


    private final int ROWS;  // This are just copies of the ones in Game. Easier to write here.
    private final int COLS;
    private final int CONNECT;

    private final JButton resetButton = new JButton();
    private final JButton stepButton = new JButton();
    private final JButton runButton = new JButton();
    private final JButton avgButton = new JButton();
    private final JLabel avgLabel = new JLabel();
    private final JLabel winLabel = new JLabel();
    

    private final JLabel stepsLabel = new JLabel();


    private final int CELL_TOP_X = 50;
    private final int CELL_TOP_Y = 50;
    private final int CELL_SIDE_PIXELS;


    private final static Color COLOR_EMPTY = Color.BLUE;
    private final static Color COLOR_GRID = Color.WHITE;

    // Graphics locations
    // Should be as a function of frameWidth and frameHeight
    // and created in the constructor.
    // Let's save trouble for now and just put these based on:
    // (Width, Height) = (600,800)
    private final static Rectangle RESET_RECT = new Rectangle(50, 500, 50, 40);
    private final static Rectangle STEP_RECT = new Rectangle(50, 550, 50, 40);
    private final static Rectangle RUN_RECT = new Rectangle(130, 550, 50, 40);
    private final static Rectangle AVG_RECT = new Rectangle(210, 550, 100, 40);
    private final static Rectangle AVG_LABEL_RECT = new Rectangle(320, 550, 40, 40);
    private final static Rectangle WIN_LABEL_RECT = new Rectangle(200, 500, 200, 40);
    private final static Rectangle STEPS_RECT = new Rectangle(500, 550, 40, 40);

    private Game game;

    public Display(int frameWidth, int frameHeight, Game game) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.game = game;

        ROWS = game.ROWS;
        COLS = game.COLS;
        CONNECT = game.CONNECT;

        CELL_SIDE_PIXELS = Math.round( (this.frameWidth - 2*CELL_TOP_X)/COLS);

        putButtons();
        init();
    }

    private void init() {
        
        game = new Game(game.ROWS, game.COLS, game.CONNECT, game.human, game.computer);
        
        makeButtonsVisible(true);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        drawCells(g2);
        // drawGrid(g2);
        drawButtons();
        
        if (loopMode) {
            try {
                Thread.sleep(STEP_TIME_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (game.play(loopMode)) displayWin();
                    
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            repaint();
        }

    }

    private void drawGrid(Graphics2D g2) {
        g2.setColor(COLOR_GRID);

        int x1 = CELL_TOP_X;
        int x2 = CELL_TOP_X + COLS * CELL_SIDE_PIXELS;
        int y1, y2;

        for (int ii = 0; ii <= ROWS; ii++) {
            y1 = CELL_TOP_Y + ii * CELL_SIDE_PIXELS;
            g2.drawLine(x1, y1, x2, y1);
        }

        y1 = CELL_TOP_Y;
        y2 = CELL_TOP_Y + ROWS * CELL_SIDE_PIXELS;
        for (int jj = 0; jj <= COLS; jj++) {
            x1 = CELL_TOP_X + jj * CELL_SIDE_PIXELS;
            g2.drawLine(x1, y1, x1, y2);
        }

    }
    

    private void drawCells(Graphics2D g2) {

        for (int ii = 0; ii < ROWS; ii++) {
            int ytop = CELL_TOP_Y + ii * CELL_SIDE_PIXELS;
            int ybot = ytop + CELL_SIDE_PIXELS;

            for (int jj = 0; jj < COLS; jj++) {

                int xleft = CELL_TOP_X + jj * CELL_SIDE_PIXELS;
                int xright = xleft + CELL_SIDE_PIXELS;

                // if (game.isCellEmpty(ii,jj))
                // We'll do it as default
                Color c = COLOR_EMPTY;
                g2.setColor(c);
                g2.fillRect(xleft, ytop, CELL_SIDE_PIXELS, CELL_SIDE_PIXELS);
                
                g2.setColor(Color.WHITE);
                Ellipse2D.Double ecircle = new Ellipse2D.Double(xleft, ytop, CELL_SIDE_PIXELS - 5, CELL_SIDE_PIXELS - 5);
                g2.fill(ecircle);

                Color cs = null;
                if (game.board.sayColor1(ii, jj))
                    cs = Color.RED;
                else if (game.board.sayColor2(ii, jj))
                    cs = Color.YELLOW;
                
                    
                g2.setColor(cs);
                Ellipse2D.Double circle = new Ellipse2D.Double(xleft, ytop, CELL_SIDE_PIXELS - 5, CELL_SIDE_PIXELS - 5);
                g2.fill(circle);

                }
                /*
                if (game.sayColor3(ii, jj)) {
                    // draw an X
                    g2.setColor(Color.BLACK);
                    g2.drawLine(xleft, ytop, xright, ybot);
                    g2.drawLine(xright, ytop, xleft, ybot);
                }
                */
            

        }

    }

    private void drawButtons() {
        stepsLabel.setText(Integer.toString(game.getTurns()));
    }

    private void putButtons() {

        resetButton.setText("Reset");
        resetButton.setBounds(RESET_RECT);
        class ResetListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                init();
                repaint();
            }
        }
        resetButton.addActionListener(new ResetListener());
        resetButton.setVisible(true);
        add(resetButton);



        stepButton.setText("Step");
        stepButton.setBounds(STEP_RECT);
        class StepListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                loopMode = false;
                try {
                    if (game.play(loopMode)) {
                        displayWin();
                    }
                        
                            
                } catch (IOException ex) {
                    Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                }
                repaint();
            }
        }
        stepButton.addActionListener(new StepListener());
        stepButton.setVisible(true);
        add(stepButton);

        runButton.setText("Run");
        runButton.setBounds(RUN_RECT);
        class RunListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                loopMode = !loopMode;
                String str = (loopMode) ? "Stop" : "Run";
                runButton.setText(str);
                repaint();
            }
        }
        runButton.addActionListener(new RunListener());
        runButton.setVisible(true);
        add(runButton);

        avgButton.setText("Calc. Avg.");
        avgButton.setBounds(AVG_RECT);

        avgLabel.setBounds(AVG_LABEL_RECT);
        avgLabel.setText("--");
        avgLabel.setVisible(true);
        add(avgLabel);

        class AvgListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                final int NUM_OF_ITERATIONS = 1000;
                double sumTurns = 0;

                for (int ii = 0; ii < NUM_OF_ITERATIONS; ++ii) {
                    // Game gg = new Game(ROWS, COLS, CONNECT);
                    // gg.play(true);
                    // sumTurns += gg.getTurns();
                }

                avgLabel.setText( Integer.toString((int)(sumTurns / NUM_OF_ITERATIONS)) );

                repaint();
            }
        }

        avgButton.addActionListener(new AvgListener());
        avgButton.setVisible(true);
        add(avgButton);


        //stepLabel.setText(Integer.toString(g.getStepCounter()));
        stepsLabel.setBounds(STEPS_RECT);
        stepsLabel.setText("0");
        stepsLabel.setVisible(true);
        add(stepsLabel);

    }
    
    private void makeButtonsVisible(boolean val) {
        stepButton.setVisible(val);
        runButton.setVisible(val);
        runButton.setVisible(val);
        
        winLabel.setVisible(!val);
    }
    
    private void displayWin() {
        
        
        makeButtonsVisible(false);
        
        winLabel.setBounds(WIN_LABEL_RECT);
        winLabel.setText("Player " + game.currentPlayer.playerNum  + " has won the game!!!");
        winLabel.setForeground(game.currentPlayer.color);

        add(winLabel);
        
        System.out.println("Player " + game.currentPlayer.playerNum  + " has won the game!!!");
        
    }

}
