package view;

import actions.WstAction;
import config.WstConfiguration;
import view.action_panels.AbstractActionPanel;
import view.action_panels.CreatePanel;
import view.action_panels.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The frontend framework for WA state trails database.
 * @author Matthew Phillips
 */
public class WstFrame extends JFrame implements PropertyChangeListener {

    /**
     * The menu bar attached to this frame.
     */
    private final JMenuBar menu;

    /**
     * A map to store the panels that can be accessed.
     * To add more later as more actions get used.
     */
    private final AbstractActionPanel[] actionPanels;

    /**
     * Constructs and returns a new GUI frame.
     */
    WstFrame() {
        super(WstAction.WELCOME.value());
        menu = new WstMenuBar();

        actionPanels = new AbstractActionPanel[WstAction.values().length];
        actionPanels[0] = new WelcomePanel();
        actionPanels[1] = new CreatePanel();

        init();
    }

    /**
     * Displays the initialized GUI frame.
     */
    void start() {
        setVisible(true);
    }

    /**
     * Initializes the frame with dimensions and attributes.
     */
    private void init() {
        setLayout(new CardLayout());

        setSize(WstConfiguration.FRAME_DEF_DIM);
        setResizable(WstConfiguration.FRAME_RESIZE);
        add(actionPanels[0]);

        setJMenuBar(menu);
        menu.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        getContentPane().removeAll();
        AbstractActionPanel panel;
        if (evt.getPropertyName().equals(WstAction.CREATE.toString())) {
            panel = actionPanels[1];
            getContentPane().add(panel);
        } else if (evt.getPropertyName().equals(WstAction.READ.toString())) {
            panel = actionPanels[2];
            getContentPane().add(panel);
        } else if (evt.getPropertyName().equals(WstAction.UPDATE.toString())) {
            panel = actionPanels[3];
            getContentPane().add(panel);
        } else if (evt.getPropertyName().equals(WstAction.DELETE.toString())) {
            panel = actionPanels[4];
            getContentPane().add(panel);
        } else {
            panel = actionPanels[0];
            getContentPane().add(panel);
        }
        getContentPane().add(panel);
        panel.repaint();
        repaint();
    }
}
