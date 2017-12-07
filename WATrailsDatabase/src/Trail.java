package WATrailsDatabase.src;

/**
 * Represents a movie with a title, year, length, genre, and studio name.
 * @author mmuppa
 *
 */
public class Trail {

	private String name, loc;
	private float len, rating;
	private int elev, kid, dog, camp;

	public Trail(String addTrailNameLabel, String addTrailLocationLabel,
				 float addTrailLengthLabel, float addTrailRatingLabel,
				 int addTrailElevationLabel, int addTrailCampsitesLabel,
				 int addTrailKidLabel, int addTrailDogLabel) {

		name = addTrailNameLabel;
		loc = addTrailLocationLabel;
		kid = addTrailKidLabel;
		dog = addTrailDogLabel;
		camp = addTrailCampsitesLabel;
		len = addTrailLengthLabel;
		rating = addTrailRatingLabel;
		elev = addTrailElevationLabel;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public float getLen() {
		return len;
	}

	public void setLen(float len) {
		this.len = len;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getElev() {
		return elev;
	}

	public void setElev(int elev) {
		this.elev = elev;
	}

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public int getDog() {
		return dog;
	}

	public void setDog(int dog) {
		this.dog = dog;
	}

	public int getCamp() {
		return camp;
	}

	public void setCamp(int camp) {
		this.camp = camp;
	}
}