import util.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static javax.swing.SwingConstants.CENTER;

/**
 * A login frame for choosing user type.
 * @author Matt Phillips
 */
public class LoginGUI extends JFrame {


    private static final Connection conn = new DatabaseUtils(
            "jinitokazama",
            "Ialmostgothaxed111111",
            "localhost:3306"
    ).createConnection();



    private JPanel pnlAdd;
    private JLabel[] txfLabel = new JLabel[7];
    private JTextField[] txfField = new JTextField[7];
    private JButton btnAddTrail;

    private UserType userType;

    private JLabel nameLabel;
    private JTextField nameEntry;

    private JLabel couldNotFindLabel;

    public LoginGUI() {
        setSize(new Dimension(500, 300));
        setLayout(new GridLayout());
        userType = UserType.NONE;

        addComponents();

        setVisible(true);
    }

    private void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));

        JLabel welcome = new JLabel("Welcome to WA State Trails Database!", JLabel.CENTER);
        welcome.setForeground(Color.BLUE);
        welcome.setFont(new Font("WelcomeFont", Font.ITALIC, 18));
        panel.add(welcome);

        nameLabel = new JLabel("Please enter your name.", JLabel.CENTER);
        nameEntry = new JTextField(50);
        panel.add(nameLabel);
        panel.add(nameEntry);

        JLabel select = new JLabel("Are you a Hiker or a PathFinder?", JLabel.CENTER);
        panel.add(select);

        JButton hiker = new JButton("Hiker");
        JButton pathfinder = new JButton("Pathfinder");
        panel.add(pathfinder);
        panel.add(hiker);

        couldNotFindLabel = new JLabel("Could not find user. Please try again.", JLabel.CENTER);
        couldNotFindLabel.setVisible(false);
        couldNotFindLabel.setForeground(Color.RED);
        panel.add(couldNotFindLabel);

        pnlAdd = new JPanel();
        pnlAdd.setLayout(new GridLayout(8, 0));
        String labelNames[] = {"Enter HikerId", "Enter PathfinderId: ", "Enter HikerCategoryId: ", "Enter Name: ",
                "Enter Gender: ", "Enter Age: ", "Enter Experience(yrs): "};

        for (int j = 0; j < labelNames.length; j++){
            txfLabel[j] = new JLabel(labelNames[j], JLabel.LEFT);
            txfField[j] = new JTextField(25);
            pnlAdd.add(txfLabel[j]);
            pnlAdd.add(txfField[j]);
        }

        JPanel panel2 = new JPanel();
        btnAddTrail = new JButton("Create User");

        btnAddTrail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String database = "USE trails";
                String insertUser = "INSERT INTO Hiker (hikerId, pathFinderId, hikerCategoryId, name, gender, age, yearsExperience)"
                        + " values (?, ?, ?, ?, ?, ?, ?)";

                    try {


                    PreparedStatement useDatabase = conn.prepareStatement(database);
                        useDatabase.execute();
                        PreparedStatement preparedStatement = conn.prepareStatement(insertUser);
                    preparedStatement.setString(1, txfField[0].getText());
                    preparedStatement.setString(2, txfField[1].getText());
                    preparedStatement.setString(3, txfField[2].getText());
                    preparedStatement.setString(4, txfField[3].getText());
                    preparedStatement.setString(5, txfField[4].getText());
                    preparedStatement.setString(6, txfField[5].getText());
                        preparedStatement.setString(7, txfField[6].getText());
                    preparedStatement.execute();
                    System.out.println("New Hiker Added!");
                } catch (SQLException error) {
                    System.out.println(error);
                }

            }
        });

        panel2.add(btnAddTrail);
        pnlAdd.add(panel2);

        add(pnlAdd, BorderLayout.CENTER);




        hiker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = UserType.HIKER;
                if (verifyUser(userType)) {
                    firePropertyChange("UserType", 0, userType);
                    couldNotFindLabel.setVisible(false);
                } else {
                    // Add login failed message.
                    couldNotFindLabel.setVisible(true);
                }
            }
        });

        pathfinder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userType = UserType.PATHFINDER;
                if (verifyUser(userType)) {
                    firePropertyChange("UserType", 0, userType);
                    couldNotFindLabel.setVisible(false);
                } else {
                    // Add login failed message.
                    couldNotFindLabel.setVisible(true);
                }
            }
        });

        add(panel);
    }

    /**
     * Should verify a user exists to log them in.
     */
    public boolean verifyUser(UserType userType) {
        Statement stmt = null;

        String query = null;
        if (userType.equals(UserType.PATHFINDER)) {
            query = "select p.pathFinderId from trails.PathFinder p"
                    + " join trails.Hiker h on p.hikerId = h.hikerId"
                    + " where name = ?";
        } else {
            query = "select name from trails.Hiker where name = ?";
        }

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, nameEntry.getText());
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("LoginGUI: Caught SQL error.");
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException s) {
                    System.out.println("LoginGUI: Closing statement failed.");
                    System.out.println(s.getMessage());
                }
            }
        }

        return false;
    }
    /**
     * Representatives of user types. Decides what view is shown in the main frame.
     */
    public enum UserType {
        NONE(""),

        HIKER("Hiker"),

        PATHFINDER("PathFinder");

        private String table;

        /**
         * @param table name to store entity in.
         */
        private UserType(String table) {
            this.table = table;
        }

        public String getTable() {
            return table;
        }
    }
}
