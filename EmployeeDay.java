import java.util.ArrayList;
import java.util.List;

/** 
 * @author Daniel Pisarek 
 * */

/** Klasa przedstawiajaca dzien osoby */
public class EmployeeDay {

	private String startHour;

	public String getStart_hour() {
		return startHour;
	}

	private String endHour;

	ArrayList<PlannedMeeting> listPlannedMeeting = new ArrayList<PlannedMeeting>();
	ArrayList<FreeHours> freeTime = new ArrayList<FreeHours>();

	public EmployeeDay(String startHour, String endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
	}

	public void addPlannedMeeting(PlannedMeeting pm) {
		listPlannedMeeting.add(pm);
	}

	public void printPlannedMeeting(EmployeeDay e) {
		System.out.println("\n Working_hours: ");
		System.out.println("   Start: " + startHour);
		System.out.println("   End: " + endHour + "\n");
		for (int i = 0; i < e.listPlannedMeeting.size(); i++) {

			e.listPlannedMeeting.get(i).PrintPlannedMeeting(i);
		}
	}

	public void printFreeHours(EmployeeDay e) {
		System.out.println("\n Free hours: ");
		for (int i = 0; i < e.freeTime.size(); i++) {

			e.freeTime.get(i).PrintFreeHours(i);
		}
		System.out.println("###############################");
	}

	/**
	 * Metoda odpowiedzialna za znalezienie czasu w ktorych dana osoba nie odbywa
	 * spotkan Zwraca liste obiektow FreeHours
	 */
	public List<FreeHours> findFreeTime(EmployeeDay e) {
		if (!e.listPlannedMeeting.isEmpty()) {
			for (int i = 0; i < e.listPlannedMeeting.size(); i++) {
				if (i == 0) {
					FreeHours fh = new FreeHours(e.listPlannedMeeting.get(i).hours[1],
							e.listPlannedMeeting.get(i + 1).hours[0]);
					e.freeTime.add(fh);
				} else {
					if (i + 1 == e.listPlannedMeeting.size()) {
						FreeHours fh = new FreeHours(e.listPlannedMeeting.get(i).hours[1], e.endHour);
						e.freeTime.add(fh);
					} else {
						if (e.listPlannedMeeting.get(i).hours[1] != e.listPlannedMeeting.get(i + 1).hours[0]) {
							FreeHours fh = new FreeHours(e.listPlannedMeeting.get(i).hours[1],
									e.listPlannedMeeting.get(i + 1).hours[0]);
							e.freeTime.add(fh);
						}
					}
				}
			}

		} else
			System.out.println("The list of scheduled meetings is empty.");

		return e.freeTime;
	}

}
