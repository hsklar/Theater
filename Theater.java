
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    private LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
schedule = List.of(                new Showing(turningRed, 1, generateStartTime(9, 0)),
                new Showing(spiderMan, 2, generateStartTime(11, 0)),
                new Showing(theBatMan, 3, generateStartTime(12, 50)),
                new Showing(turningRed, 4, generateStartTime(14, 30)),
                new Showing(spiderMan, 5, generateStartTime(16, 10)),
                new Showing(theBatMan, 6, generateStartTime(17, 50)),
                new Showing(turningRed, 7, generateStartTime(19, 30)),
                new Showing(spiderMan, 8, generateStartTime(21, 10)),
                new Showing(theBatMan, 9, generateStartTime(23, 0))
        );
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public LocalDateTime generateStartTime(int hour, int minute){
        return LocalDateTime.of(provider.currentDate(), LocalTime.of(hour, minute));
    }


    public void printSchedule() {
        System.out.println(LocalDate.now());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovie().getTicketPrice())
        );
        System.out.println("===================================================");
    }
  
  	public void printScheduleJson() {
        System.out.println(LocalDate.now());
      	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(this));
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }
    public static void main(String[] args) {
        Theater theater = new Theater(new LocalDateProvider().singleton());
        theater.printSchedule();
        theater.printScheduleJson();
    }
}