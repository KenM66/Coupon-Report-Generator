
public class Guest {
	private String firstName;
	private String lastName;
	private int roomNumber;
	private String ratePlan;
	private String company;
	private char honorsTier;
	private int numAdults;
	private int numKids;
	private String myWaySelection;
	private boolean guestFound=false;
	
	private String breakfastPackageName;
	private String breakfastPackageDescription;
	
	Guest(int roomNumber, String lastName, String firstName,String ratePlan, int numAdults, int numKids){
		this.roomNumber=roomNumber;
		this.lastName= lastName;
		this.firstName= firstName;
		this.ratePlan= ratePlan;
		this.numAdults= numAdults;
		this.numKids= numKids;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRatePlan() {
		return ratePlan;
	}

	public void setRatePlan(String ratePlan) {
		this.ratePlan = ratePlan;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public char getHonorsTier() {
		return honorsTier;
	}

	public void setHonorsTier(char honorsTier) {
		this.honorsTier = honorsTier;
	}

	public int getNumAdults() {
		return numAdults;
	}

	public void setNumAdults(int numGuests) {
		this.numAdults = numGuests;
	}

	public String getMyWaySelection() {
		return myWaySelection;
	}

	public void setMyWaySelection(String myWaySelection) {
		this.myWaySelection = myWaySelection;
	}

	public int getNumKids() {
		return numKids;
	}

	public void setNumKids(int numKids) {
		this.numKids = numKids;
	}


	public String getBreakfastPackageName() {
		return breakfastPackageName;
	}

	public void setBreakfastPackageName(String breakfastPackageName) {
		this.breakfastPackageName = breakfastPackageName;
	}

	public String getBreakfastPackageDescription() {
		return breakfastPackageDescription;
	}

	public void setBreakfastPackageDescription(String breakfastPackageDescription) {
		this.breakfastPackageDescription = breakfastPackageDescription;
	}

	public boolean isGuestFound() {
		return guestFound;
	}

	public void setGuestFound(boolean guestFound) {
		this.guestFound = guestFound;
	}
	

}
