import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


/**
 * A login frame for choosing user type.
 * @author Matt Phillips
 */
public class LoginGUI extends JFrame {

    private UserType userType;

    public LoginGUI() {
        setSize(new Dimension(400, 300));
        userType = UserType.NONE;
        addButtons();
        setVisible(true);
    }

    public void addButtons() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Please select a login type"), JLabel.CENTER);
        panel.setLayout(new GridLayout(3,0));
        JButton hiker = new JButton("Hiker");

        panel.add(hiker);
        JButton pathfinder = new JButton("Pathfinder");
        panel.add(pathfinder);

        hiker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = UserType.HIKER;
                firePropertyChange("UserType", 0, userType);
            }
        });

        pathfinder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = UserType.PATHFINDER;
                firePropertyChange("UserType", 0, userType);
            }
        });

        add(panel);
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
