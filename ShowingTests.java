package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import project.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {

    @BeforeAll
    public void setUpProvider(){
        LocalDateProvider provider = LocalDateProvider.singleton()
    }

    @Test
    void specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),20, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(10, 0)));
        assertEquals(16, showing.calculateTicketPrice());
    }

    void specialMovieWith25PercentDiscountReTiming() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),20, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 0)));
        assertEquals(15, showing.calculateTicketPrice());
    }

    void specialMovieWith3DollarDiscountFirstOfDay() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0)));
        assertEquals(9.5, showing.calculateTicketPrice());
    }
}
