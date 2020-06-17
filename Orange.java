import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

/**
 * @author Daniel Pisarek
 */

public class Orange {

	/**
	 * Klasa glowna, w której tworzone s¹ dwa kalandarze oraz znajduje siê w niej
	 * metoda w ktorej zaimplementowany jest algorytm odpowiadajacy za znalezenie
	 * wspolnego czasu na spotkanie o okreslonym czasie trwania
	 */
	public static void main(String[] args) {
		/**
		 * Tworzenie dwoch obiektow klasy EmployeeDay odpowidajacych za godziny pracuje
		 * danej osoby oraz przechowujacej zaplanowane spotkania i godziny wolne, czyli
		 * te w ktorych spotkanie moze sie odbyc
		 */
		EmployeeDay e1 = new EmployeeDay("09:00", "20:00");
		EmployeeDay e2 = new EmployeeDay("10:00", "18:30");

		/**
		 * Tworzenie obiektow klasy PlannedMeeting odpowiadajacych zaplanowanym
		 * spotkaniom pierwszej osoby
		 */
		PlannedMeeting pm1 = new PlannedMeeting("09:00", "10:30");
		PlannedMeeting pm2 = new PlannedMeeting("12:00", "13:00");
		PlannedMeeting pm3 = new PlannedMeeting("16:00", "18:30");
		/** Dodanie do dnia pracowanika zaplanowanych spotkan */
		e1.addPlannedMeeting(pm1);
		e1.addPlannedMeeting(pm2);
		e1.addPlannedMeeting(pm3);

		/**
		 * Tworzenie obiektow klasy PlannedMeeting odpowiadajacych zaplanowanym
		 * spotkaniom pierwszej osoby
		 */
		PlannedMeeting pm4 = new PlannedMeeting("10:00", "11:30");
		PlannedMeeting pm5 = new PlannedMeeting("12:30", "14:30");
		PlannedMeeting pm6 = new PlannedMeeting("14:30", "15:00");
		PlannedMeeting pm7 = new PlannedMeeting("16:00", "17:00");
		/** Dodanie do dnia pracowanika zaplanowanych spotkan */
		e2.addPlannedMeeting(pm4);
		e2.addPlannedMeeting(pm5);
		e2.addPlannedMeeting(pm6);
		e2.addPlannedMeeting(pm7);

		/**
		 * Utworzenie list, ktore przechowuja obiekty klasy FreeHours oraz wykonanie
		 * metody findFreeTime zwracajacej liste wolnych godzin danej osoby
		 */
		List<FreeHours> freeTime1 = e1.findFreeTime(e1);
		List<FreeHours> freeTime2 = e2.findFreeTime(e2);

		/**
		 * Po odkomentowaniu pokaza sie godziny w ktorych dana osoba pracuje, oraz
		 * zaplanowane spotkania wraz z godzinami w ktorych maja sie odbywac
		 */
		// e1.printPlannedMeeting(e1);
		// e2.printPlannedMeeting(e2);

		/** Po odkomentowaniu pokaza sie godziny w ktorych dana osoba nie ma spotkan */
		// e1.printFreeHours(e1);
		// e2.printFreeHours(e2);

		/**
		 * Lista obiektow SuggestedMeeting, w ktorej znajduja sie godziny w ktorych dwie
		 * osoby moga miec spotkanie
		 */
		ArrayList<SuggestedMeeting> finalTimeForMeeting = findHoursForMeeting(freeTime1, freeTime2, "00:30");

		for (int i = 0; i < finalTimeForMeeting.size(); i++) {
			/** Wypisanie mozlwiych godzin na spotkanie */
			finalTimeForMeeting.get(i).PrintSuggestedMeeting(i);
		}

	}

	/**
	 * Metoda zwracajaca liste obiektow SuggestedMeeting, czyli godzin mozliwych na
	 * spotkanie Jako argumenty przyjmuje dwie list obiektow FreeHours, ktore
	 * przechowuja godziny w ktorych dana osoba nie ma zaplanowanych spotkan.
	 * Trzecim argumentem funkcji jest czas spotkania.
	 */
	public static ArrayList<SuggestedMeeting> findHoursForMeeting(List<FreeHours> ft1, List<FreeHours> ft2,
			String meetingDuration) {
		ArrayList<SuggestedMeeting> suggestedMeetingTime = new ArrayList<SuggestedMeeting>();
		if (ft1.size() == ft2.size()) {
			for (int i = 0; i < ft1.size(); i++) {
				String tempHours[] = new String[2];
				String reference1 = ft1.get(i).freeHours[0];
				String[] parts1 = reference1.split(":");
				Calendar date1 = Calendar.getInstance();
				date1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts1[0]));
				date1.set(Calendar.MINUTE, Integer.parseInt(parts1[1]));

				String reference2 = ft2.get(i).freeHours[0];
				String[] parts2 = reference2.split(":");
				Calendar date2 = Calendar.getInstance();
				date2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts2[0]));
				date2.set(Calendar.MINUTE, Integer.parseInt(parts2[1]));

				String reference3 = ft1.get(i).freeHours[1];
				String[] parts3 = reference3.split(":");
				Calendar date3 = Calendar.getInstance();
				date3.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts3[0]));
				date3.set(Calendar.MINUTE, Integer.parseInt(parts3[1]));

				String reference4 = ft2.get(i).freeHours[1];
				String[] parts4 = reference4.split(":");
				Calendar date4 = Calendar.getInstance();
				date4.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts4[0]));
				date4.set(Calendar.MINUTE, Integer.parseInt(parts4[1]));

				// jezeli takie same (start) godziny wolne
				if (ft1.get(i).freeHours[0] == ft2.get(i).freeHours[0]) {
					tempHours[0] = ft1.get(i).freeHours[0];
					// jezeli takie same (end) godziny wolne
					if (ft1.get(i).freeHours[1] == ft2.get(i).freeHours[1]) {
						tempHours[1] = ft1.get(i).freeHours[1];
						String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
						SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
						suggestedMeetingTime.add(sm);
					}
					// jezeli rozne (end) godziny wolne
					else {
						if (date1.before(date2)) {
							tempHours[1] = ft1.get(i).freeHours[1];
							if (checkPossibility(tempHours, meetingDuration) != null) {
								String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
								SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
								suggestedMeetingTime.add(sm);
							} else {
								continue;
							}

						} else {
							tempHours[1] = ft2.get(i).freeHours[1];
							if (checkPossibility(tempHours, meetingDuration) != null) {
								String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
								SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
								suggestedMeetingTime.add(sm);
							} else {
								continue;
							}
						}

					}
				}

				// jezeli rozne (start) godziny wolne
				else if (ft1.get(i).freeHours[0] != ft2.get(i).freeHours[0]) {

					if (date1.before(date2)) {
						tempHours[0] = ft2.get(i).freeHours[0];
						// jezeli takie same (end) godziny wolne
						if (ft1.get(i).freeHours[1] == ft2.get(i).freeHours[1]) {
							tempHours[1] = ft2.get(i).freeHours[1];
							if (checkPossibility(tempHours, meetingDuration) != null) {
								String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
								SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
								suggestedMeetingTime.add(sm);
							} else {
								continue;
							}
						}
						// jezeli rozne (end) godziny wolne
						else {

							if (date3.before(date4)) {
								tempHours[1] = ft1.get(i).freeHours[1];
								if (checkPossibility(tempHours, meetingDuration) != null) {
									String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
									SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
									suggestedMeetingTime.add(sm);
									if (tempHours[0] == tempHours[1]) {
										suggestedMeetingTime.remove(sm);
									}
								} else {
									continue;
								}

							} else {
								tempHours[1] = ft2.get(i).freeHours[1];

								if (checkPossibility(tempHours, meetingDuration) != null) {
									String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
									SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
									suggestedMeetingTime.add(sm);
									if (tempHours[0] == tempHours[1]) {
										continue;
									}
								} else {
									continue;
								}

							}
						}
					}

					else {
						tempHours[0] = ft1.get(i).freeHours[0];
						// jezeli takie same (end) godziny wolne
						if (ft1.get(i).freeHours[1] == ft2.get(i).freeHours[1]) {
							tempHours[1] = ft1.get(i).freeHours[1];
							if (checkPossibility(tempHours, meetingDuration) != null) {
								String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
								SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
								suggestedMeetingTime.add(sm);
								if (tempHours[0] == tempHours[1]) {
									suggestedMeetingTime.remove(sm);
								}
							} else {
								continue;
							}
						}
						// jezeli rozne (end) godziny wolne
						else {

							if (date4.before(date3)) {
								tempHours[1] = ft2.get(i).freeHours[1];
								if (checkPossibility(tempHours, meetingDuration) != null) {
									String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
									SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
									suggestedMeetingTime.add(sm);
									if (tempHours[0] == tempHours[1]) {
										suggestedMeetingTime.remove(sm);
									}
								} else {
									continue;
								}

							}

							else {
								tempHours[1] = ft2.get(i).freeHours[1];
								if (checkPossibility(tempHours, meetingDuration) != null) {
									String confirmedHours[] = checkPossibility(tempHours, meetingDuration);
									SuggestedMeeting sm = new SuggestedMeeting(confirmedHours[0], confirmedHours[1]);
									suggestedMeetingTime.add(sm);
									if (tempHours[0] == tempHours[1]) {
										suggestedMeetingTime.remove(sm);
									}
								} else {
									continue;
								}
							}
						}
					}

				}

			}
		}

		return suggestedMeetingTime;

	}

	/**
	 * Metoda sprawdzajaca czy w danym oknie czasowym moze odbyc sie spotkanie o
	 * podanej dlugosci meetingDuration
	 */
	private static String[] checkPossibility(String[] tempHours, String meetingDuration) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(tempHours[0]);
			d2 = format.parse(tempHours[1]);

			String reference1 = meetingDuration;
			String[] parts1 = reference1.split(":");
			int meetingDurationMinutes = Integer.parseInt(parts1[1]);

			long diff = d2.getTime() - d1.getTime();

			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;

			if (diffHours >= 1) {
				diffMinutes += 60;
				if (diffMinutes > 0 && diffMinutes >= meetingDurationMinutes) {
					return tempHours;
				} else {
					return null;
				}
			} else if (diffHours < 1) {
				if (diffMinutes > 0 && diffMinutes >= meetingDurationMinutes) {
					return tempHours;
				} else {
					return null;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
