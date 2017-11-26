package view;

import actions.WstAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The menu bar at the top of the WstFrame, primarily for choosing action panels.
 * @author Matthew Phillips
 */
public class WstMenuBar extends JMenuBar {

    /**
     * Constructs and returns a new menu bar.
     */
    public WstMenuBar() {
        super();
        init();
    }

    /**
     * Initializes the menu bar.
     */
    private void init() {
        final JMenu file = add(new JMenu("Action"));
        final JMenuItem create = new JMenuItem(WstAction.CREATE.value());
        file.add(create);
        final JMenuItem read = new JMenuItem(WstAction.READ.value());
        file.add(read);
        final JMenuItem update = new JMenuItem(WstAction.UPDATE.value());
        file.add(update);
        final JMenuItem delete = new JMenuItem(WstAction.DELETE.value());
        file.add(delete);

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(WstAction.CREATE.toString(), 0, 1);
            }
        });

        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(WstAction.READ.toString(), 0, 1);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(WstAction.UPDATE.toString(), 0, 1);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                firePropertyChange(WstAction.DELETE.toString(), 0, 1);
            }
        });
    }
}
