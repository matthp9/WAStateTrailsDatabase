/**
 * Representative object for our database entity.
 * @author Jin Byoun
 */
public class Trail {

	private String name;
	private String loc;
	private String policy;
	private float len, rating;
	private int elev, camp;

	public Trail(String addTrailNameLabel, String addTrailLocationLabel,
				 float addTrailLengthLabel, float addTrailRatingLabel,
				 int addTrailElevationLabel, int addTrailCampsitesLabel,
				 String addPolicyNameLabel) {

		name = addTrailNameLabel;
		loc = addTrailLocationLabel;
		policy = addPolicyNameLabel;
		camp = addTrailCampsitesLabel;
		len = addTrailLengthLabel;
		rating = addTrailRatingLabel;
		elev = addTrailElevationLabel;
	}

	public String getName() {
		return name;
	}

	public String getLoc() {
		return loc;
	}

	public float getLen() {
		return len;
	}

	public float getRating() {
		return rating;
	}

	public int getElev() {
		return elev;
	}

	public int getCamp() {
		return camp;
	}

	public String getPolicy() { return policy; }
}