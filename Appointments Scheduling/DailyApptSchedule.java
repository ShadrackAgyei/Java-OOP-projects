
/**
 * A class to represent the appointment schedule for a given day.
 * Each day is assumed to have hour-long timeslots starting at 8am and
 * with the final timeslot starting at 4pm
 **/
public class DailyApptSchedule {

  private Appointment[] apptsForTheDay;

  public static final int NUM_SLOTS = 9;
  public static final String[] TIMES = {"8am", "9am", "10am", "11am", 
                                        "12noon", "1pm", "2pm", "3pm", "4pm"};

  // No-arg onstructor creates an empty schedule for a day
  public DailyApptSchedule() {
    apptsForTheDay = new Appointment[NUM_SLOTS];
  }

  /**
   * A method to display the list of appointments for the day
   * In the format:
   *    time: appointment_info
   *    time: appointment_info ... etc
   * Any empty slots (null Appointments in the array) should be listed as Free
   */
  public void displayAppointments() {
    for (int i = 0; i < NUM_SLOTS; i++){
      if (apptsForTheDay[i] != null){
        System.out.println(TIMES[i] + ": " + apptsForTheDay[i].toString());
      }else {
        System.out.println(TIMES[i] + ": Free");
      }
    }
    System.out.println();
   /* int counter = 0;
    for (Appointment appts: apptsForTheDay){
      if (appts == null){counter++;}
    }
    if (counter == 9){
      System.out.println("No appointments scheduled for the day");
    }*/

  }

  /**
   * A method to add the given appointment to the schedule in the specified timeslot
   * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
   * @param appt represents the appointment object
   * @return true if it was successful and false if not successful
   */
  public boolean addAppointment(int whichSlot, Appointment appt) {
    boolean success;
    if (apptsForTheDay[whichSlot] == null){
      apptsForTheDay[whichSlot] = appt;
      success = true;
    }else {
      success = false;
    }
    return success;
  }

  /**
   * A method to add an appointment for the given person, venue and purpose to the schedule in the specified timeslot.
   * @param whichSlot represents the index of the timeslot (Eg. 0 -> 8am, 1 -> 9am, ...)
   * @param n represents the name of the student
   * @param v represents the location of the appointment
   * @param p represents the reason for the meeting
   * @return true if it was successful and false if not successful (i.e. if the given slot is invalid or taken)
   */
  public boolean addAppointment(int whichSlot, String n, String v, String p) {
    Appointment appt = new Appointment(n,v,p);
    boolean success = true;
    if (apptsForTheDay[whichSlot] == null){
      apptsForTheDay[whichSlot] = appt;
      success = true;
    }else {
      success = false;
    }
    return success;
  }

  /**
   * A method to add the given appointment to the schedule in any free timeslot.
   * @param appt represents the appointment object
   * @return the index of the chosen timeslot or -1 if no free timeslot exists.
   */
  public int addAppointment(Appointment appt) {
    int index = -1;
    for (int i = 0; i<NUM_SLOTS; i++){
      if (apptsForTheDay[i] == null){
        apptsForTheDay[i] = appt;
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * A method to add an appointment for the given person, venue and purpose
   * @param n represents the name of the student
   * @param v represents the location of the appointment
   * @param p represents the reason for the meeting
   * @return the index of the chosen timeslot or -1 if no free timeslot exists.
   */
  public int addAppointment(String n, String v, String p) {
    Appointment appt = new Appointment(n,v,p);
    int index = -1;
    for (int i = 0; i<NUM_SLOTS; i++){
      if (apptsForTheDay[i] == null){
        apptsForTheDay[i] = appt;
        index = i;
        break;
      }
    }
    return index;
  }

  /**
   * Remove the appointment in the given slot
   * @param slot the index of the timeslot
   * @return true if operation was successful and false if not
   */
  public boolean removeAppointment(int slot){
    if (apptsForTheDay[slot] == null){
      return false;
    } else {
      apptsForTheDay[slot] = null;
      return true;
    }
  }

  /**
   * Remove the appointment corresponding to the given student name
   * @param n represents the name of the student
   * @return true if operation was successful and false if not
   */
  public void removeAppointment(String n){
    for (int i =0; i < NUM_SLOTS; i++){
      if (apptsForTheDay[i] == null) continue;
      if (apptsForTheDay[i].getStudentName().equals(n)){
        apptsForTheDay[i] = null;
      }
    }
  }

  /**
   * Reschedule the appointment using the previous slot and the new preferred slot
   * @param oldSlot represents the slot for the appointment to be rescheduled
   * @param newSlot represents the new slot to schedule appointment
   * @return true if operation was successful and false if not
   */
  public boolean rescheduleAppointment(int oldSlot,int newSlot){
    Appointment appt = apptsForTheDay[oldSlot];
    if (apptsForTheDay[newSlot] != null) return false;
    if (apptsForTheDay[oldSlot] == null) return false;
    removeAppointment(oldSlot);
    addAppointment(newSlot,appt);
    return true;
  }

  /**
   * returns an appointment
   * @param slot slot for the appointment
   * @return appointment
   */
  public Appointment getAppointment(int slot){
    return apptsForTheDay[slot];
  }
  public static int getTimeSlot(String input){
    int index = 0;
    for (int i = 0; i< NUM_SLOTS; i++){
      if (TIMES[i].equals(input)){
        index = i;
      }
    }
    return index;
  }
   
}