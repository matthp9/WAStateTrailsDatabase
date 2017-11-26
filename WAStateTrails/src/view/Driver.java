package view;

import java.awt.*;

/**
 * Entry point for the program.
 * @author Matthew Phillips
 */
public class Driver {
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WstFrame().start();
            }
        });
    }
}
