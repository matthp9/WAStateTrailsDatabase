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
 * Database functionality file for Trails database.
 * @author mmuppa (default)
 * @author Hasnah Said
 */

public class TrailDB {
	private static String userName = "root"; // CHANGE TO YOURS
	private static String password = "atbx-143-!$#-"; // CHANGE TO YOURS
	private static String serverName = "localhost:3306";
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

	public List<Trail> getTrail() {
		if (conn == null) {
			try {
				createConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Statement stmt = null;
		String query = "select trailName, description, rating, length,\n" +
				"elevationGain, hasCampsites, policyId from prod.Trail t LEFT JOIN prod.Location l " +
				"ON l.locationId = t.locationId";

		list = new ArrayList<Trail>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println(rs.getFetchSize());
			while (rs.next()) {
				String trailName = rs.getString("trailName");
				String trailLocation = rs.getString("description");
				float rating = Float.parseFloat(rs.getString("rating"));
				float length = Float.parseFloat(rs.getString("length"));
				int elevationGain = Integer.parseInt(rs.getString("elevationGain"));
				int hasCampsites = Integer.parseInt(rs.getString("hasCampsites"));
				String policyId = rs.getString("policyId");
				Trail trail = new Trail(trailName, trailLocation, length, rating,
						elevationGain, hasCampsites, policyId);
				list.add(trail);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}


	public void addTrail(Trail trail) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("insert into prod.Policy(description) values (?);");
			preparedStatement.setString(1, trail.getPolicy());
			preparedStatement.executeUpdate();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select LAST_INSERT_ID();");
			int pId = 0;
			while (rs.next()) {
				pId = Integer.parseInt(rs.getString("LAST_INSERT_ID()"));
			}

			preparedStatement = conn.prepareStatement("insert into prod.Location(description) values (?);");
			preparedStatement.setString(1, trail.getLoc());
			preparedStatement.executeUpdate();

			preparedStatement = conn.prepareStatement("insert into prod.Trail(trailName, locationId, rating, length, elevationGain, hasCampsites, policyId) values " + "(?, LAST_INSERT_ID(), ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, trail.getName());
			preparedStatement.setString(2, "" + trail.getRating());
			preparedStatement.setString(3, "" + trail.getLen());
			preparedStatement.setString(4, "" + trail.getElev());
			preparedStatement.setString(5, "" + trail.getCamp());
			preparedStatement.setString(6, "" + pId);
			preparedStatement.executeUpdate();

			stmt = conn.createStatement();
			rs = stmt.executeQuery("select LAST_INSERT_ID();");
			int tId = 0;
			while (rs.next()) {
				tId = Integer.parseInt(rs.getString("LAST_INSERT_ID()"));
			}

			preparedStatement = conn.prepareStatement("insert into prod.PolicyMapping(trailId, policyId) values (" + tId + ", " + pId +");");
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void deleteTrail(String name) {
		String sql = "delete from prod.Trail where trailName = ?; " ;

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "" + name);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void modifyTrail(String columnName, String update, String trailName) {

		String sql = "update prod.Trail set " + columnName + " = ?  where trailName = ?";
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
		String location = trail.getLoc();
		String sql = "UPDATE prod.trail SET " + columnName + " = ?  WHERE trail_name = ? AND trail_location = ? ";
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

	public List<Trail> getTrailByLocation(String location) {
		List<Trail> filterList = new ArrayList<>();
		list = getTrail();
		for (Trail trail : list) {
			try {
				if (trail.getLoc().toLowerCase().contains(location.toLowerCase())) {
					filterList.add(trail);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return filterList;
	}

	public List<Trail> getTrailByName(String name) {
		List<Trail> filterList = new ArrayList<>();
		list = getTrail();
		for (Trail trail : list) {
			try {
				System.out.println(trail.getName());
				System.out.println(name);
				if (trail.getName().toLowerCase().contains(name.toLowerCase())) {
					filterList.add(trail);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return filterList;
	}
}