import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }


    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }
    
    public double calculateTicketPrice() {
        return movie.getTicketPrice() - getDiscount();
    }

    private double getDiscount() {
        double pcntDiscount = 0;
        if (showStartTime.getHour() >= 11 && showStartTime.getHour() <= 14){
            pcntDiscount = movie.getTicketPrice() * 0.25;
        } else if (movie.isSpecial()) {
            pcntDiscount = movie.getTicketPrice() * 0.2;
        }

        double dollarDiscount = 0;
        if (sequenceOfTheDay == 1) {
            dollarDiscount = 3;
        } else if (sequenceOfTheDay == 2) {
            dollarDiscount = 2;
        } else if (showStartTime.getDayOfMonth() == 7) {
            dollarDiscount = 1;
        }
        
        // biggest discount wins
        return pcntDiscount > dollarDiscount ? pcntDiscount : dollarDiscount;
    }

}