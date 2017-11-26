package view.action_panels;

import config.WstConfiguration;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Parent panel for each action panel. Should hold components that
 * are shared by each action panel, and reduce the code we need overall.
 * @author Matthew Phillips
 */
public abstract class AbstractActionPanel extends JPanel {

    /**
     * Can disable showing user inputs.
     */
    private final boolean showInputs;

    private JTable table;

    private JTextField trailId;
    private JTextField trailName;
    private JTextField trailLocation;
    private JTextField trailElevation;
    private JTextField dogFriendly;
    private JTextField kidFriendly;
    private JTextField hasCampsites;

    public AbstractActionPanel(boolean showInputs) {
        this.showInputs = showInputs;
    }

    /**
     * Executes a query defined for the action subclass.
     *
     * Example: in CreatePanel, we should override this method
     * and create a query inside it to be executed. We can get
     * user input by calling the methods in this class, such as
     * getUserTrailName(), and use that data in the queries.
     *
     * @return whether the query was successful, potentially
     * for error message viewing purposes.
     */
    protected abstract boolean executeQuery();

    /**
     * Populates the JTable with a TableModel.
     */
    protected void populate(TableModel data) {
        table = new JTable(data);
        table.updateUI();
    }

    /**
     * Initializes the action panel.
     */
    public void init() {
        setBackground(WstConfiguration.BG_COLOR);
        setLayout(new GridLayout(10, 1));

        addFields();
        addSubmitButton();
        addGrid();
        repaint();
    }

    /**
     * Adds a JLabel with the specified header.
     */
    protected void addHeader(final String header) {
        final JLabel label = new JLabel(header);
        add(label);
    }

    /**
     * Adds a JLabel with the specified subheader.
     */
    protected void addSubheader(final String header) {
        final JLabel label = new JLabel(header);
        label.setFont(WstConfiguration.FONT_SMALL);
        add(label);
    }

    /**
     * Adds user input fields to the panel.
     */
    private void addFields() {
        if (showInputs) {
            trailId = new JTextField("Enter trail ID here");
            trailName = new JTextField("Enter trail name here");
            trailLocation = new JTextField("Enter trail location here");
            trailElevation = new JTextField("Enter trail elevation here (ft)");
            dogFriendly = new JTextField("Is it dog friendly? (yes/no)");
            kidFriendly = new JTextField("Is it kid friendly? (yes/no)");
            hasCampsites = new JTextField("Does it have campsites? (yes/no)");

            add(trailName);
            add(trailId);
            add(trailLocation);
            add(trailElevation);
            add(dogFriendly);
            add(kidFriendly);
            add(hasCampsites);
        }
    }

    /**
     * Creates a submit button and adds a listener to execute
     * when the user clicks on the button. What happens when the
     * user clicks on the button depends on the subclass implementation,
     * which is where the executed query should reside.
     */
    private void addSubmitButton() {
        if (showInputs) {
            final JButton submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    executeQuery();
                }
            });
            add(submit);
        }
    }

    /**
     * Creates a grid for displaying data.
     */
    private void addGrid() {
        if (showInputs) {
            // Placeholder 6's.
            table = new JTable(6, 6);
            table.setGridColor(Color.BLACK);
            add(table);
        }
    }

    // Use these protected methods to get the data needed for queries.

    /**
     * @return the contents of the trail name text box.
     */
    protected final String getUserTrailName() {
        return trailName.getText();
    }

    /**
     * @return the Integer contents of the trail id text box.
     */
    protected final int getUserTrailId() {
        return Integer.parseInt(trailId.getText());
    }

    /**
     * @return the Integer contents of the trail location text box.
     */
    protected final String getUserTrailLocation() {
        return trailLocation.getText();
    }

    /**
     * @return the Integer contents of the trail elevation text box.
     */
    protected final String getUserTrailElevation() {
        return trailElevation.getText();
    }

    /**
     * @return the Integer contents of the trail elevation text box.
     */
    protected final String getUserDogFriendly() {
        return dogFriendly.getText();
    }

    /**
     * @return the Integer contents of the trail elevation text box.
     */
    protected final String getUserKidFriendly() {
        return kidFriendly.getText();
    }

    /**
     * @return the Integer contents of the trail elevation text box.
     */
    protected final String getUserHasCampsites() {
        return hasCampsites.getText();
    }

}
