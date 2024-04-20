import java.util.Scanner;

public class AppointmentsApplication {

    public static void main(String[] args) {
        // create a scanner to take input
        Scanner input = new Scanner(System.in);

        //create a weekly appointment
        WeeklyApptSchedule weeklyApptSchedule = new WeeklyApptSchedule();

        boolean quit = false;
        while (!quit){
            String menu = """
                Welcome to appointment booking with FI/course Faculty
                To book an appointment press 1.
                To cancel an appointment press 2.
                To reschedule an appointment press 3.
                To display the list of appointments press 4.
                To quit press 0.
                """;
            System.out.println(menu);

            int userChoice = input.nextInt();
            if (userChoice >4 || userChoice <0){
                userChoice = input.nextInt();
            }

            input.nextLine();
            if (userChoice == 1){
                System.out.print("Enter name: ");
                String name = input.nextLine();
                System.out.print("Enter location: ");
                String location = input.nextLine();
                System.out.print("Enter purpose: ");
                String purpose = input.nextLine();

                Appointment appointment = new Appointment(name,location,purpose);

                System.out.println("1:Monday 2:Tuesday 3:Wednesday 4:Thursday 5:Friday");
                System.out.print("Enter day by selecting number: ");
                int dayIndex = input.nextInt();
                input.nextLine();
                WeeklyApptSchedule.Day day = WeeklyApptSchedule.getDay(dayIndex);

                System.out.println("Timeslots: 8am 9am 10am 11am 12noon 1pm 2pm 3pm 4pm");
                System.out.print("Enter timeslot: ");
                String timeslotInput = input.nextLine();
                int timeslot = DailyApptSchedule.getTimeSlot(timeslotInput);

                weeklyApptSchedule.addAppointment(appointment,day,timeslot);
            }
            else if (userChoice == 2) {
                System.out.println("1:Monday 2:Tuesday 3:Wednesday 4:Thursday 5:Friday");
                System.out.print("Enter day of appointment to cancel: ");
                int dayIndex = input.nextInt();
                WeeklyApptSchedule.Day day = WeeklyApptSchedule.getDay(dayIndex);

                System.out.println("Timeslots: 8am 9am 10am 11am 12noon 1pm 2pm 3pm 4pm");
                System.out.print("Enter time of appointment to cancel: ");
                String timeslotInput = input.nextLine();
                int timeslot = DailyApptSchedule.getTimeSlot(timeslotInput);

                weeklyApptSchedule.cancelAppointment(day,timeslot);
            }
            else if (userChoice == 3) {
                System.out.println("To reschedule your appointment...");
                System.out.println("1:Monday 2:Tuesday 3:Wednesday 4:Thursday 5:Friday");
                System.out.print("Enter the old day: ");
                int olddayIndex = input.nextInt();
                input.nextLine();
                WeeklyApptSchedule.Day oldday = WeeklyApptSchedule.getDay(olddayIndex);

                System.out.println("Timeslots: 8am 9am 10am 11am 12noon 1pm 2pm 3pm 4pm");
                System.out.println("Enter the old time slot");
                String oldtimeslotInput = input.nextLine();
                int oldtimeslot = DailyApptSchedule.getTimeSlot(oldtimeslotInput);

                System.out.println("1:Monday 2:Tuesday 3:Wednesday 4:Thursday 5:Friday");
                System.out.print("Enter the new day: ");
                int newdayIndex = input.nextInt();
                input.nextLine();
                WeeklyApptSchedule.Day newday = WeeklyApptSchedule.getDay(newdayIndex);

                System.out.println("Timeslots: 8am 9am 10am 11am 12noon 1pm 2pm 3pm 4pm");
                System.out.println("Enter the new time slot");
                String newtimeslotInput = input.nextLine();
                int newtimeslot = DailyApptSchedule.getTimeSlot(newtimeslotInput);
                weeklyApptSchedule.rescheduleAppointment(oldday,oldtimeslot,newday,newtimeslot);
            }
            else if (userChoice == 4) {
                weeklyApptSchedule.displayWeeklyAppointments();
            }
            else if (userChoice == 0) {
                quit = true;
            }
            System.out.println();
        }
    }

}