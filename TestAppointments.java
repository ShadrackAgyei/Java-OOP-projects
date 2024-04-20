public class TestAppointments {
    public static void main (String[] args){
        // Testing Appointments
        Appointment a0 = new Appointment("Shad","Arabs","Lab");
        a0.displayDetails();
        System.out.println();
        a0.setStudentName("Khad");
        a0.setVenue("Apt 216");
        a0.setPurpose("Study");
        a0.displayDetails();
        System.out.println();


        // Testing DailyapptSchedule
        DailyApptSchedule d1 = new DailyApptSchedule();
        Appointment a1 = new Appointment("Shad","Arabs","Lab");
        Appointment a2 = new Appointment("Dhad","Arabs","Lab");
        d1.displayAppointments();

        //testing overloaded addAppointment method
        d1.addAppointment(0, a1);
        d1.addAppointment(1,"Bhad","Sci lab", "Assignment");
        d1.addAppointment(a2);
        d1.addAppointment("Chad","Fci lab", "Assignment2");
        d1.displayAppointments();

        //testing overloaded removeAppointment method
        d1.removeAppointment(1);
        d1.removeAppointment("Chad");
        d1.displayAppointments();

        //testing reschedule method
        d1.rescheduleAppointment(0,7);
        d1.rescheduleAppointment(0,2);
        d1.displayAppointments();

        //End of DailyApptSchedule testing

        //Testing WeeklyApptSchedule
        WeeklyApptSchedule w1 = new WeeklyApptSchedule();
        w1.displayWeeklyAppointments();

        // Testing addAppointments method
        w1.addAppointment(a1, WeeklyApptSchedule.Day.MONDAY,0);
        w1.addAppointment(a2, WeeklyApptSchedule.Day.MONDAY,1);
        w1.displayWeeklyAppointments();

        //Testing cancelAppointments method
        w1.cancelAppointment(WeeklyApptSchedule.Day.MONDAY,5);
        w1.cancelAppointment(WeeklyApptSchedule.Day.MONDAY,1);
        w1.displayWeeklyAppointments();

        // Testing rescheduleAppointments method
        w1.rescheduleAppointment(WeeklyApptSchedule.Day.MONDAY,0, WeeklyApptSchedule.Day.FRIDAY,3);
        w1.displayWeeklyAppointments();

        // End of testing

    }
}