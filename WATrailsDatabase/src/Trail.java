/**
 * Represents a movie with a title, year, length, genre, and studio name.
 * @author mmuppa
 *
 */
public class Trail {

	
	private String trail_name, trail_location, 
				   trail_length, trail_elevation, 
				   dog_friendly, kid_friendly, established_campsites;
	
	
	/**
	 * Initialize the movie parameters.
	 * @param title
	 * @param year
	 * @param length
	 * @param genre
	 * @param studioName
	 * @throws IllegalArgumentException if title or genre or studio name are null or empty,
	 * length <= 0, year < 1920.
	 */
	public Trail(String trail_name, String location, 
				 String trail_length, String trail_elevation, 
				 String dog_friendly, String kid_friendly, String established_campsites) {
		
		setName(trail_name);
		setLocation(location);
		setLength(trail_length);
		setElevation(trail_elevation);
		setDog(dog_friendly);
		setKid(kid_friendly);
		setCamp(established_campsites);
	}
	
	@Override
	public String toString()
	{
		return "Trail [Trail Name = " + trail_name + ", Trail Location = " + trail_location + ", Trail Length = "
				+ trail_length + ", Trail Elevation = " + trail_elevation + ", Dog Friendly = " + dog_friendly
				+ ", Kid Friendly = " + kid_friendly + ", Established Campsites = " + established_campsites +  "]";
	}


	public String getName()
	{
		return trail_name;
	}
	

	public void setName(String trail_name)
	{
		if (trail_name == null || trail_name.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid title.");
		this.trail_name = trail_name;
	}

	public String getLocation()
	{
		return trail_location;
	}
	

	public void setLocation(String trail_location)
	{
//		if (trail_location == null || trail_location.length() == 0 )
//			throw new IllegalArgumentException("Please supply a valid title.");
		this.trail_location = trail_location;
	}
	

	public String getLength()
	{
		return trail_length;
	}
	

	public void setLength(String trail_length)
	{
		if (trail_length == null || trail_length.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid title.");
		this.trail_length = trail_length;
	}
	

	public String getElevation()
	{
		return trail_elevation;
	}
	

	public void setElevation(String trail_elevation)
	{
		if (trail_elevation == null || trail_elevation.length() == 0)
			throw new IllegalArgumentException("Please supply a valid genre.");
		
		this.trail_elevation = trail_elevation;
	}
	

	public String getDog()
	{
		return dog_friendly;
	}
	

	public void setDog(String dog_friendly)
	{
		if (dog_friendly == null || dog_friendly.length() == 0)
			throw new IllegalArgumentException("Please supply a valid studio name.");
		
		this.dog_friendly = dog_friendly;
	}
	

	public String getKid()
	{
		return kid_friendly;
	}
	

	public void setKid(String kid_friendly)
	{
		if (kid_friendly == null || kid_friendly.length() == 0)
			throw new IllegalArgumentException("Please supply a valid studio name.");
		
		this.kid_friendly = kid_friendly;
	}

	public String getCamp()
	{
		return established_campsites;
	}

	public void setCamp(String established_campsites)
	{
		if (established_campsites == null || established_campsites.length() == 0)
			throw new IllegalArgumentException("Please supply a valid studio name.");
		
		this.established_campsites = established_campsites;
	}
	
	
}
