/** 
 * @author Daniel Pisarek 
 * */

/**
 * Klasa przedstawiajaca godziny, w ktorych spotkanie moze sie odbyc na
 * podstawie kalendarza dwoch osob
 */
public class SuggestedMeeting {
	private String startHour;
	private String endHour;
	public String timeForMeeting[] = new String[2];

	public String getStartHour() {
		return startHour;
	}

	public SuggestedMeeting(String startHour, String endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
		timeForMeeting[0] = startHour;
		timeForMeeting[1] = endHour;
	}

	public void PrintSuggestedMeeting(int i) {
		System.out.println("\n" + IntToOrdinal(i + 1) + " meeting suggestion: ");
		System.out.println("  Start: " + timeForMeeting[0]);
		System.out.println("  End: " + timeForMeeting[1]);

	}

	public static String IntToOrdinal(int i) {
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
