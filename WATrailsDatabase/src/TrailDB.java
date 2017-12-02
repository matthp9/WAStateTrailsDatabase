import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A class that consists of the database operations to insert and update the Movie information.
 * @author mmuppa
 *
 */

public class TrailDB {
	private static String userName = "USERNAME"; // CHANGE TO YOURS
	private static String password = "PASSWORD"; // CHANGE TO YOURS
	private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection conn;
	private List<Trail> list;


	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);

		System.out.println("Connected to database");
	}
	


	public List<Trail> getTrail() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select trail_name, trail_location, trail_length, trail_elevation, "
				+ "dog_friendly, kid_friendly, established_campsites "
				+ "from USERNAME.Trail ";

		list = new ArrayList<Trail>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String trail_name = rs.getString("trail_name");
				String trail_location = rs.getString("trail_location");
				String trail_length = rs.getString("trail_length");
				String trail_elevation = rs.getString("trail_elevation");
				String dog_friendly = rs.getString("dog_friendly");
				String kid_friendly = rs.getString("kid_friendly");
				String established_campsites = rs.getString("established_campsites");
				Trail trail = new Trail(trail_name, trail_location, trail_length, trail_elevation,
										dog_friendly, kid_friendly, established_campsites);
				list.add(trail);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}


	public List<Trail> getTrail(String trail_name) {
		
		
		List<Trail> filterList = new ArrayList<Trail>();
		try {
			list = getTrail();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Trail trail : list) {
			if (trail.getName().toLowerCase().contains(trail_name.toLowerCase())) {
				filterList.add(trail);
			}
		}
		return filterList;
	}


	public void addTrail(Trail trail) {
		String sql = "insert into USERNAME.Trail values " + "(?, ?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, trail.getName());
			preparedStatement.setString(2, trail.getLocation());
			preparedStatement.setString(3, trail.getLength());
			preparedStatement.setString(4, trail.getElevation());
			preparedStatement.setString(5, trail.getDog());
			preparedStatement.setString(6, trail.getKid());
			preparedStatement.setString(7, trail.getCamp());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	public void deleteTrail(String name) {
		String sql = "delete from USERNAME.Trail where trail_name = ? " ;
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, name);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void modifyTrail(String columnName, String update, String trailName) {
		
		String sql = "update USERNAME.Trail set " + columnName + " = ?  where trail_name = ?";
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, update);
			preparedStatement.setString(2, trailName);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	

	public void updateTrail(int row, String columnName, Object data) {
		Trail trail = list.get(row);
		String name = trail.getName();
		String location = trail.getLocation();
		String sql = "update USERNAME.Trail set " + columnName + " = ?  where trail_name = ?";
		System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, location);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
}
