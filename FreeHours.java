/** 
 * @author Daniel Pisarek 
 * */

/**
 * Klasa przedstawiajaca wolny czas osoby, w kotrym nie odbywa zaplanowanych
 * wczesniej spotkan
 */
public class FreeHours {
	private String startHour;
	private String endHour;
	public String freeHours[] = new String[2];

	public String getStartHour() {
		return startHour;
	}

	public FreeHours(String startHour, String endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
		freeHours[0] = startHour;
		freeHours[1] = endHour;
	}

	public void PrintFreeHours(int i) {
		System.out.println(IntToOrdinal(i + 1) + " free time window: ");
		System.out.println("  Start: " + freeHours[0]);
		System.out.println("  End: " + freeHours[1]);
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
