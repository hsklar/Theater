package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFeeNoDiscounts() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(17,0));
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);
    }
}
