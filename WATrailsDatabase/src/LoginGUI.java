import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A login frame for choosing user type.
 * @author Matt Phillips
 */
public class LoginGUI extends JFrame {

    private UserType userType;
    private TrailDB db;

    public LoginGUI() {
        db = new TrailDB();
        setSize(new Dimension(400, 300));
        userType = UserType.NONE;
        addElements();
        setVisible(true);
    }

    public void addElements() {
        this.setLayout(new BorderLayout());
        JPanel pnlNorth = new JPanel();
        JPanel pnlSouth = new JPanel();

        pnlNorth.add(new JLabel("Username"));
        JTextField name = new JTextField("name here");
        pnlNorth.add(name);

        pnlSouth.add(new JLabel("Please select a login type"), JLabel.CENTER);
        pnlSouth.setLayout(new GridLayout(3,0));
        JButton hiker = new JButton("Hiker");
        pnlSouth.add(hiker);
        JButton pathfinder = new JButton("Pathfinder");
        pnlSouth.add(pathfinder);



        hiker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = UserType.HIKER;
                firePropertyChange("UserType", 0, userType);
                db.checkUserNames(name.getText());
            }
        });

        pathfinder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = UserType.PATHFINDER;
                firePropertyChange("UserType", 0, userType);
                db.checkUserNames(name.getText());
            }
        });
        add(pnlNorth, BorderLayout.NORTH);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    /**
     * Representatives of user types. Decides what view is shown in the main frame.
     */
    public enum UserType {
        NONE,

        HIKER,

        PATHFINDER
    }
}
