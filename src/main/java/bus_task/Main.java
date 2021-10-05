package bus_task;

import bus_task.EndWorkException;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    private static String answer = "";
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        Scanner scanner = new Scanner(System.in);
        String travelTime;
        String relaxTime;

        while (!answer.equals("no")){
            try {
                System.out.println("Enter travelTime (format HH:MM): ");
                travelTime = scanner.nextLine();

                System.out.println("Enter relaxTime (format HH:MM): ");
                relaxTime = scanner.nextLine();

                schedule.addFlight(parseTime(travelTime), parseTime(relaxTime));

                System.out.println("Continue entering data? (yes / no) ");
                answer = scanner.nextLine();
            } catch (EndWorkException e) {
                System.err.println(e.getMessage());
                schedule.print();
                System.exit(0);
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());

            }
        }
        schedule.print();
    }

    public static LocalTime parseTime(String time) throws NumberFormatException{
        if (time.matches("\\d{2}:\\d{2}")){
            return LocalTime.parse(time);
        }

        throw new NumberFormatException("Incorrect time format entered. Correct format: HH:MM");
    }
}
