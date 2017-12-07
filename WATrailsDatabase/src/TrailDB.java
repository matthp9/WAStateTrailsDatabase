package WATrailsDatabase.src;

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
	private static String userName = "bsands2"; // CHANGE TO YOURS
	private static String password = "password"; // CHANGE TO YOURS
	private static String serverName = "localhost:3306";
	private static Connection conn;
	private List<Trail> list;

	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/prod", connectionProps);

		System.out.println("Connected to database");
	}


	public List<Trail> getTrail() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select trailName, location.description AS \"location\", rating, length,\n" +
				"elevationGain, hasCampsites from Trail LEFT JOIN location " +
				"ON trail.locationId = location.locationId";

		list = new ArrayList<Trail>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String trailName = rs.getString("trailName");
				String trailLocation = rs.getString("location");
				float rating = Float.parseFloat(rs.getString("rating"));
				float length = Float.parseFloat(rs.getString("length"));
				int elevationGain = Integer.parseInt(rs.getString("elevationGain"));
				int hasCampsites = Integer.parseInt(rs.getString("hasCampsites"));
				Trail trail = new Trail(trailName, trailLocation, length, rating,
						elevationGain, hasCampsites, 0, 0);
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
		/*
		String sql = "insert into trails.Trail values " + "(?, ?, ?, ?, ?, ?, ?); ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, trail.getName());
			preparedStatement.setString(2, trail.getLoc());
			preparedStatement.setString(3, trail.getLen());
			preparedStatement.setString(4, trail.getElev());
			preparedStatement.setString(5, trail.getDog());
			preparedStatement.setString(6, trail.getKid());
			preparedStatement.setString(7, trail.getCamp());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		*/

		String trailInsert = "insert into trails.Trail(trailName, locationId, rating, length, elevationGain, hasCampsites) values " + "(?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(trailInsert);
			preparedStatement.setString(1, trail.getName());
			preparedStatement.setString(2, trail.getLoc());
			preparedStatement.setString(3, "" + trail.getRating());
			preparedStatement.setString(4, "" + trail.getLen());
			preparedStatement.setString(5, "" + trail.getElev());
			preparedStatement.setString(6, "" + trail.getCamp());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void deleteTrail(String name) {
		String sql = "delete from trails.Trail where trail_name = ? " ;

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

		String sql = "update trails.Trail set " + columnName + " = ?  where trail_name = ?";
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
		String sql = "UPDATE trails.trail SET " + columnName + " = ?  WHERE trail_name = ? AND trail_location = ? ";
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
        List<Trail> filterList = new ArrayList<Trail>();
        try {
            list = getTrail();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Trail trail : list) {
            try {
                System.out.println(trail.getLoc());
                if (trail.getLoc().toLowerCase().contains(location.toLowerCase())) {
                    filterList.add(trail);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return filterList;
    }
}