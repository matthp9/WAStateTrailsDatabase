import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A user interface to view the movies, add a new movie and to update an existing movie.
 * The list is a table with all the movie information in it. The TableModelListener listens to
 * any changes to the cells to modify the values for reach movie.
 * @author mmuppa
 *
 */
public class TrailGUI extends JFrame implements ActionListener, TableModelListener, PropertyChangeListener
{

	private static final long serialVersionUID = 1779520078061383929L;
	private JButton btnList, btnSearch, btnAdd, btnDelete, btnUpdate;
	private JPanel pnlButtons, pnlContent;
	private TrailDB db;
	private List<Trail> list;
	private String[] columnNames = {"trail_name",
			"trail_location",
			"trail_length",
			"trail_elevation",
			"dog_friendlly",
			"kid_friendlly",
			"established_campsites"};

	private Object[][] data;
	private JTable table;
	private JScrollPane scrollPane;

	private LoginGUI.UserType userType;

	private JPanel pnlSearch;
	private JLabel lblTitle;;
	private JTextField txfTitle;
	private JButton btnTitleSearch;

	private JPanel pnlUpdate;
	private JLabel txfLabelUpdateFeature;
	private JTextField txfUpdateFeature;
	private JLabel txfLabelUpdateName;
	private JTextField txfUpdateName;
	private JTextField txfUpdateUpdate;
	private JLabel txfLabelUpdateUpdate;
	private JButton btnTitleUpdate;

	private JPanel pnlAdd;
	private JLabel[] txfLabel = new JLabel[7];
	private JTextField[] txfField = new JTextField[7];
	private JButton btnAddTrail;

	private JPanel pnlDelete;
	private JLabel lblTitleDelete;
	private JTextField txfTitleDelete;
	private JButton btnTitleDelete;

	// Adding a trail
	private JLabel addTrailNameLabel;
	private JTextField addTrailNameField;
	private JLabel addTrailLocationLabel;
	private JTextField addTrailLocationField;
	private JLabel addTrailLengthLabel;
	private JTextField addTrailLengthField;
	private JLabel addTrailRatingLabel;
	private JTextField addTrailRatingField;
	private JLabel addTrailElevationLabel;
	private JTextField addTrailElevationField;
	private JLabel addTrailCampsitesLabel;
	private JTextField addTrailCampsitesField;

	// Adding a Policy
	private JLabel addPolicyNameLabel;
	private JTextField addPolicyNameField;

	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public TrailGUI() {
		super("WA Trails");

		db = new TrailDB();
		try
		{
			list = db.getTrail();
			if (list == null) {
				list = new ArrayList<>();
			}

			data = new Object[list.size()][columnNames.length];
			for (int i=0; i<list.size(); i++) {
				data[i][0] = list.get(i).getName();
				data[i][1] = list.get(i).getLoc();
				data[i][2] = list.get(i).getLen();
				data[i][3] = list.get(i).getElev();
				data[i][6] = list.get(i).getCamp();

			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(1000, 750));
		createComponents();
		pack();
		setLocationRelativeTo(null);
		setVisible(false);
	}

	/**
	 * Creates panels for Movie list, search, add and adds the corresponding
	 * components to each panel.
	 */
	private void createComponents()
	{
		Color listColor = new Color(100, 200, 10);
		Color searchColor = new Color(200, 200, 100);
		Color addColor = new Color(100, 100, 200);
		Color removeColor = new Color(200, 100, 100);
		Color updateColor = new Color(178, 102, 255);


		pnlButtons = new JPanel();
		btnList = createCustomButton("Trail List", listColor);
		btnList.addActionListener(this);

		btnSearch = createCustomButton("Trail Search", searchColor);
		btnSearch.addActionListener(this);

		btnAdd = createCustomButton("Add Trail", addColor);
		btnAdd.addActionListener(this);

		btnDelete = createCustomButton("Delete Trail", removeColor);
		btnDelete.addActionListener(this);

		btnUpdate = createCustomButton("Update Trail", updateColor);
		btnUpdate.addActionListener(this);

		pnlButtons.repaint();

		pnlButtons.add(btnList);
		pnlButtons.add(btnSearch);
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnDelete);
		pnlButtons.add(btnUpdate);

		add(pnlButtons, BorderLayout.NORTH);

		//List Panel
		pnlContent = new JPanel();
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane(table);
		pnlContent.add(scrollPane);
		table.getModel().addTableModelListener(this);

		//Search Panel
		pnlSearch = new JPanel();
		lblTitle = new JLabel("Enter Name: ");
		txfTitle = new JTextField(25);
		btnTitleSearch = new JButton("Search");
		btnTitleSearch.addActionListener(this);
		pnlSearch.add(lblTitle);
		pnlSearch.add(txfTitle);
		pnlSearch.add(btnTitleSearch);

		//Delete Panel
		pnlDelete = new JPanel();
		pnlDelete.add(new JLabel("Warning: you are about to permanently delete a trail."));
		lblTitleDelete = new JLabel("Enter Name: ");
		txfTitleDelete = new JTextField(25);
		btnTitleDelete = new JButton("Delete");
		pnlDelete.add(lblTitleDelete);
		pnlDelete.add(txfTitleDelete);
		pnlDelete.add(btnTitleDelete);
		pnlDelete.setBackground(removeColor);
		btnTitleDelete.addActionListener(this);

		//Update Panel
		pnlUpdate = new JPanel();
		pnlUpdate.setLayout(new GridLayout(5, 0));
		txfLabelUpdateFeature = new JLabel("Enter Column Name: ");
		txfUpdateFeature = new JTextField(25);
		txfLabelUpdateName = new JLabel("Enter Trail Name: ");
		txfUpdateName = new JTextField(25);
		txfLabelUpdateUpdate = new JLabel("Enter Update: ");
		txfUpdateUpdate = new JTextField(25);
		btnTitleUpdate = new JButton("Update");
		pnlUpdate.add(txfLabelUpdateFeature);
		pnlUpdate.add(txfUpdateFeature);
		pnlUpdate.add(txfLabelUpdateName);
		pnlUpdate.add(txfUpdateName);
		pnlUpdate.add(txfLabelUpdateUpdate);
		pnlUpdate.add(txfUpdateUpdate);

		JPanel updateBtnPanel = new JPanel();
		updateBtnPanel.add(btnTitleUpdate, BorderLayout.CENTER);
		pnlUpdate.add(updateBtnPanel);
		btnTitleUpdate.addActionListener(this);

		//Add Panel
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(18, 1));
		String labelNames[] = {"Enter Elevation (ft): ",
				"Dog Friendly: ", "Kid Friendly: ", "Established Campsites: "};

		// Trail name components.
		addTrailNameLabel = new JLabel("Trail Name: ", JLabel.LEFT);
		pnlAdd.add(addTrailNameLabel);
		addTrailNameField = new JTextField(25);
		pnlAdd.add(addTrailNameField);

		// Trail location components.
		addTrailLocationLabel = new JLabel("Enter Location: ", JLabel.LEFT);
		pnlAdd.add(addTrailLocationLabel);
		addTrailLocationField = new JTextField(25);
		pnlAdd.add(addTrailLocationField);

		// Trail length components.
		addTrailLengthLabel = new JLabel("Enter Length (miles): ", JLabel.LEFT);
		pnlAdd.add(addTrailLengthLabel);
		addTrailLengthField = new JTextField(25);
		pnlAdd.add(addTrailLengthField);

		// Trail rating components.
		addTrailRatingLabel = new JLabel("Enter Rating (stars): ", JLabel.LEFT);
		pnlAdd.add(addTrailRatingLabel);
		addTrailRatingField = new JTextField(25);
		pnlAdd.add(addTrailRatingField);

		// Trail elevation components.
		addTrailElevationLabel = new JLabel("Enter elevation gain (ft): ", JLabel.LEFT);
		pnlAdd.add(addTrailElevationLabel);
		addTrailElevationField = new JTextField(25);
		pnlAdd.add(addTrailElevationField);

		// Trail campsites components.
		addTrailCampsitesLabel = new JLabel("Does the trail have campsites? (y/n): ", JLabel.LEFT);
		pnlAdd.add(addTrailCampsitesLabel);
		addTrailCampsitesField = new JTextField(25);
		pnlAdd.add(addTrailCampsitesField);

		// Policy add or use.
		addPolicyNameLabel = new JLabel("Add policy or use existing: ", JLabel.LEFT);
		pnlAdd.add(addPolicyNameLabel);
		addPolicyNameField = new JTextField(40);
		pnlAdd.add(addPolicyNameField);

		JPanel panel = new JPanel();
		btnAddTrail = new JButton("Add");
		btnAddTrail.addActionListener(this);
		panel.add(btnAddTrail);
		pnlAdd.add(panel);

		add(pnlContent, BorderLayout.CENTER);
		setTableSize();
	}

	private JButton createCustomButton(String text, Color c) {
		JButton button = new JButton(text);
		button.setForeground(c);
		return button;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		LoginGUI loginGUI = new LoginGUI();
		TrailGUI trailGUI = new TrailGUI();
		loginGUI.addPropertyChangeListener(trailGUI);
		trailGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Event handling to change the panels when different tabs are clicked,
	 * add and search buttons are clicked on the corresponding add and search panels.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnList) {
			try {
				list = db.getTrail();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Defensive check.
			if (list == null) list = new ArrayList<>();

			data = new Object[list.size()][columnNames.length];
			for (int i=0; i<list.size(); i++) {
				data[i][0] = list.get(i).getName();
				data[i][1] = list.get(i).getLoc();
				data[i][2] = list.get(i).getLen();
				data[i][3] = list.get(i).getElev();
				data[i][6] = list.get(i).getCamp();
			}
			pnlContent.removeAll();
			table = new JTable(data, columnNames);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnSearch) {
			pnlContent.removeAll();
			pnlContent.add(pnlSearch);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnDelete) {
			pnlContent.removeAll();
			pnlContent.add(pnlDelete);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnUpdate) {

			pnlContent.removeAll();
			pnlContent.add(pnlUpdate);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnAdd) {
			pnlContent.removeAll();
			pnlContent.add(pnlAdd);
			pnlContent.revalidate();
			this.repaint();

		} else if (e.getSource() == btnTitleUpdate) {

			String update = txfUpdateFeature.getText();
			String name = txfUpdateName.getText();
			String columnName = txfUpdateUpdate.getText();

			if (name.length() > 0) {
				db.modifyTrail(update, columnName, name);
				JOptionPane.showMessageDialog(null, "Updated Successfully!");
			}

		} else if (e.getSource() == btnTitleDelete) {
			String name = txfTitleDelete.getText();

			if (name.length() > 0) {
				db.deleteTrail(name);
				JOptionPane.showMessageDialog(null, "Deleted Successfully!");
			}

		} else if (e.getSource() == btnTitleSearch) {
			String name = txfTitle.getText();
			if (name.length() > 0) {
				list = db.getTrail();
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getName();
					data[i][1] = list.get(i).getLoc();
					data[i][2] = list.get(i).getLen();
					data[i][3] = list.get(i).getElev();
					data[i][6] = list.get(i).getCamp();
				}
				pnlContent.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
			}
		} else if (e.getSource() == btnAddTrail) {
			int hasCampsites = (addTrailCampsitesField.getText().equals("y")) ? 1 : 0;
			Trail trail = new Trail( addTrailNameField.getText(),
					addTrailLocationField.getText(),
					Float.parseFloat(addTrailLengthField.getText()),
					Float.parseFloat(addTrailRatingField.getText()),
					Integer.parseInt(addTrailElevationField.getText()), hasCampsites,
					addPolicyNameField.getText());
			db.addTrail(trail);
			JOptionPane.showMessageDialog(null, "Added Successfully!");
		}

		setTableSize();
	}

	/**
	 * Event handling for any cell being changed in the table.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
		int column = e.getColumn();
		TableModel model = (TableModel)e.getSource();
		String columnName = model.getColumnName(column);
		Object data = model.getValueAt(row, column);

		db.updateTrail(row, columnName, data);

	}

	private void setTableSize() {
		scrollPane.setSize(new Dimension(900, 600));
		scrollPane.setPreferredSize(new Dimension(900, 600));
		pnlContent.setSize(new Dimension(900, 600));
		pnlContent.setPreferredSize(new Dimension(900, 600));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("UserType")) {
			userType = (LoginGUI.UserType) evt.getNewValue();

			if (userType.equals(LoginGUI.UserType.PATHFINDER)) {
				btnAdd.setVisible(true);
				btnDelete.setVisible(true);
			} else {
				btnAdd.setVisible(false);
				btnDelete.setVisible(false);
			}

			setVisible(true);
			repaint();
		}
	}
}