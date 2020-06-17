/** 
 * @author Daniel Pisarek 
 * */

/** Klasa przedstawiajaca zaplanowane spotkania osoby */
public class PlannedMeeting {
	private String startHour;
	private String endHour;
	public String hours[] = new String[2];

	public String getStartHour() {
		return startHour;
	}

	public PlannedMeeting(String startHour, String endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
		hours[0] = startHour;
		hours[1] = endHour;
	}

	public void PrintPlannedMeeting(int i) {
		System.out.println(intToOrdinal(i + 1) + " meeting: ");
		System.out.println("  Start: " + hours[0]);
		System.out.println("  End: " + hours[1]);

	}

	public static String intToOrdinal(int i) {
		String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
		switch (i % 100) {
		case 11:
		case 12:
		case 13:
			return i + "th";
		default:
			return i + sufixes[i % 10];
		}
	}

}
