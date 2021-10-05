package bus_task;

import bus_task.EndWorkException;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private static final LocalTime START_WORK = LocalTime.parse("07:00");
    private static final LocalTime END_WORK = LocalTime.parse("19:00");
    private Map<Integer, TimeOfFlight> schedule = new HashMap<>();
    private LocalTime haveTime = START_WORK;
    private int flightNumber = 1;

    public void addFlight(LocalTime travelTime, LocalTime relaxTime) throws EndWorkException {
      TimeOfFlight timeOfFlight = new TimeOfFlight();

      timeOfFlight.setStartTime(haveTime);
      plusTime(relaxTime);
      plusTime(travelTime);
      if (flightNumber != 1){
          plusTime(relaxTime);
      }
      timeOfFlight.setEndTime(haveTime);

        System.out.println(haveTime);
        if (haveTime.compareTo(END_WORK) >= 0){
            minusTime(relaxTime);
            minusTime(travelTime);
            if (flightNumber != 1){
                minusTime(relaxTime);
            }

            throw new EndWorkException("Sorry, bus driver is overworking! End of working day at 19:00");

        } else {
            schedule.put(flightNumber, timeOfFlight);
            flightNumber++;
        }

    }

    private void plusTime(LocalTime time){
        haveTime = haveTime.plusHours(time.getHour());
        haveTime = haveTime.plusMinutes(time.getMinute());
    }

    private void minusTime(LocalTime time){
        haveTime = haveTime.minusHours(time.getHour());
        haveTime = haveTime.minusMinutes(time.getMinute());
    }

    public Map<Integer, TimeOfFlight> getSchedule() {
        return schedule;
    }

    public void print(){
        TimeOfFlight timeOfFlight;
        for (int flight : schedule.keySet()){
            timeOfFlight = schedule.get(flight);
            System.out.printf("Рейс № %s %s - %s %n", flight, timeOfFlight.getStartTime(), timeOfFlight.getEndTime());
        }
    }
}
