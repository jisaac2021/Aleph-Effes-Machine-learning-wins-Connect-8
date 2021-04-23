

import java.io.IOException;
import javax.swing.JFrame;

public class Main {


    public static void main(String[] args) throws IOException
    {
        final int DISPLAY_WIDTH = 600 ;
        final int DISPLAY_HEIGHT = 680 ;

        boolean useDisplay = true; // Can be changed according to main run-time arguments.
        Player human = new Player1("blue");
        Player computer = new Player2("yellow");
        Game game = new Game(6,7,4,human,computer);

        if (useDisplay) {
            JFrame frame = new JFrame();

            frame.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
            frame.setTitle("Connect 4 variations");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setLayout(null); // we are using absolute location


            Display display = new Display(DISPLAY_WIDTH, DISPLAY_HEIGHT, game);

            frame.add(display);
            frame.setVisible(true);
        }
        else{
            // run without Display interface
            
            game.play(true);
            
        }

    }





}
