import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
public class TrailGUI extends JFrame implements ActionListener, TableModelListener
{
	
	private static final long serialVersionUID = 1779520078061383929L;
	private JButton btnList, btnSearch, btnAdd, btnDelete;
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
	
	
	private JPanel pnlSearch;
	private JLabel lblTitle;;
	private JTextField txfTitle;
	private JButton btnTitleSearch;
	
	private JPanel pnlAdd;
	private JLabel[] txfLabel = new JLabel[7];
	private JTextField[] txfField = new JTextField[7];
	private JButton btnAddTrail;
	
	private JPanel pnlDelete;
	private JLabel lblTitleDelete;
	private JTextField txfTitleDelete;
	private JButton btnTitleDelete;
	
	
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public TrailGUI() {
		super("WA Trails");
		
		db = new TrailDB();
		try
		{
			list = db.getTrail();
			
			data = new Object[list.size()][columnNames.length];
			for (int i=0; i<list.size(); i++) {
				data[i][0] = list.get(i).getName();
				data[i][1] = list.get(i).getLocation();
				data[i][2] = list.get(i).getLength();
				data[i][3] = list.get(i).getElevation();
				data[i][4] = list.get(i).getDog();
				data[i][5] = list.get(i).getKid();
				data[i][6] = list.get(i).getCamp();
				
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		setSize(1000, 700);
		createComponents();
		setVisible(true);
	}
    
	/**
	 * Creates panels for Movie list, search, add and adds the corresponding 
	 * components to each panel.
	 */
	private void createComponents()
	{
		pnlButtons = new JPanel();
		btnList = new JButton("Trail List");
		btnList.addActionListener(this);
		
		btnSearch = new JButton("Trail Search");
		btnSearch.addActionListener(this);
		
		btnAdd = new JButton("Add Trail");
		btnAdd.addActionListener(this);
		
		btnDelete = new JButton("Delete Trail");
		btnDelete.addActionListener(this);
		
		pnlButtons.add(btnList);
		pnlButtons.add(btnSearch);
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnDelete);
		add(pnlButtons, BorderLayout.NORTH);
		
		//List Panel
		pnlContent = new JPanel();
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane(table);
		setTableSize();
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
		lblTitleDelete = new JLabel("Enter Name: ");
		txfTitleDelete = new JTextField(25);
		btnTitleDelete = new JButton("Delete");
		pnlDelete.add(lblTitleDelete);
		pnlDelete.add(txfTitleDelete);
		pnlDelete.add(btnTitleDelete);
		
		//Add Panel
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(8, 0));
		String labelNames[] = {"Enter Name: ", "Enter Location: ", "Enter Length: ", "Enter Elevation: ", 
							   "Dog Friendly: ", "Kid Friendly: ", "Established Campsites: "};
		
		for (int j = 0; j < labelNames.length; j++){
			JPanel panel = new JPanel();
			txfLabel[j] = new JLabel(labelNames[j]);
			txfField[j] = new JTextField(25);
			panel.add(txfLabel[j]);
			panel.add(txfField[j]);
			pnlAdd.add(panel);
		}
		
		JPanel panel = new JPanel();
		btnAddTrail = new JButton("Add");
		btnAddTrail.addActionListener(this);
		panel.add(btnAddTrail);
		pnlAdd.add(panel);
		
		add(pnlContent, BorderLayout.CENTER);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TrailGUI trailGUI = new TrailGUI();
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			data = new Object[list.size()][columnNames.length];
			for (int i=0; i<list.size(); i++) {
				data[i][0] = list.get(i).getName();
				data[i][1] = list.get(i).getLocation();
				data[i][2] = list.get(i).getLength();
				data[i][3] = list.get(i).getElevation();
				data[i][4] = list.get(i).getDog();
				data[i][5] = list.get(i).getKid();
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
			
		} else if (e.getSource() == btnAdd) {
			pnlContent.removeAll();
			pnlContent.add(pnlAdd);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnTitleDelete) {
			//add action for btnTitleDelete
			
			System.out.println("btnTitleDelete clicked");

			
		} else if (e.getSource() == btnTitleSearch) {
			String name = txfTitle.getText();
			if (name.length() > 0) {
				list = db.getTrail(name);
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getName();
					data[i][1] = list.get(i).getLocation();
					data[i][2] = list.get(i).getLength();
					data[i][3] = list.get(i).getElevation();
					data[i][4] = list.get(i).getDog();
					data[i][5] = list.get(i).getKid();
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
			Trail trail = new Trail(txfField[0].getText(), txfField[1].getText()
					,txfField[2].getText(), txfField[3].getText(), txfField[4].getText(), 
					txfField[5].getText(), txfField[6].getText() );
			db.addTrail(trail);
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i=0; i<txfField.length; i++) {
				txfField[i].setText("");
			}
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

}
