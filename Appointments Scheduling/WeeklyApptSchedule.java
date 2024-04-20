import java.io.FileReader;

public class WeeklyApptSchedule {
   
   private DailyApptSchedule[] apptsForTheWeek;
   
   public static final int NUM_DAYS = 5;
   public static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", 
     "Thursday", "Friday"};
   public enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
   
   // No-arg constructor creates an array of DailyApptSchedule 
   // for the week
   public WeeklyApptSchedule() {
      apptsForTheWeek = new DailyApptSchedule[NUM_DAYS];
   }
   
   // display weekly appointments
   // Display the set of appointments for the week by showing
   // the schedule for each day.
   public void displayWeeklyAppointments() {
      for (int i = 0; i < NUM_DAYS; i++){
         if (apptsForTheWeek[i] != null){
            System.out.println(DAYS[i] + ":");
            apptsForTheWeek[i].displayAppointments();
            System.out.println();
         } else {
            System.out.println(DAYS[i] + ":");
            System.out.println("No appointments scheduled");
            System.out.println();
         }
      }
   }
   
   // Add an appointment for a given timeslot on a given day.
   // Note: You may find it helpful to invoke day.ordinal()
   // ordinal() is a method automatically defined for enumerations 
   // it gives an "index" of a particular value in the enumeration.
   public boolean addAppointment(Appointment appt, Day day, int timeSlot){
      int index = day.ordinal();

      if (apptsForTheWeek[index] == null){
         apptsForTheWeek[index] = new DailyApptSchedule();
         apptsForTheWeek[index].addAppointment(timeSlot,appt);
      return true;
      } else if (apptsForTheWeek[index] != null) {
         apptsForTheWeek[index].addAppointment(timeSlot,appt);
      }
      return false;
   }
   
   // Cancels (removes) the appointment in a given timeslot on
   // a given day
   public void cancelAppointment(Day day, int timeSlot) {
      int index = day.ordinal();
      if (apptsForTheWeek[index] == null){return;}

      if (!apptsForTheWeek[index].removeAppointment(timeSlot)){ // if you cannot remove an appointment that means there is no appointment present
         System.out.println("There is no appointment here to cancel.");
      }else {
         apptsForTheWeek[index].removeAppointment(timeSlot);
      }
   }

   /**
    * Reschedule the appointment using the previous slot and the new preferred slot
    * @param oldDay represents the current day for the appointment
    * @param oldSlot represents the slot for the appointment to be rescheduled
    * @param newDay represents the new day to reschedule appointment
    * @param newSlot represents the new slot to schedule appointment
    */
   public void rescheduleAppointment(Day oldDay,int oldSlot,Day newDay, int newSlot){
      int oldIndex = oldDay.ordinal();

      // Store the old appointment temporarily
      DailyApptSchedule d1 = apptsForTheWeek[oldIndex];
      Appointment oldAppt = d1.getAppointment(oldSlot);

      cancelAppointment(oldDay,oldSlot);
      addAppointment(oldAppt,newDay,newSlot);
   }

   // returns the Day using an index
   public static Day getDay(int index){
      Day day = Day.MONDAY;
      switch (index){
         case 1:
            day = Day.MONDAY;
            break;
         case 2:
            day = Day.TUESDAY;
            break;
         case 3:
            day = Day.WEDNESDAY;
            break;
         case 4:
            day = Day.THURSDAY;
            break;
         case 5:
            day = Day.FRIDAY;
            break;
      }
      return day;
   }


}