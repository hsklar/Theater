package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {

    @Before
    void setUp() {
        Customer john = new Customer("John Doe", "id-12345");
    }

    @After
    void destroyDate(){
        provider.destroy()
    }

    @Test
    void totalFeeForCustomer11AMShowing() {
        LocalDateProvider provider = LocalDateProvider.singleton(LocalDate.of(2022, 11, 3));
        Theater theater = new Theater(provider);
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 37.5);
    }

    @Test
    void totalFeeForCustomer7thOfMonthShowing() {
        LocalDateProvider provider = LocalDateProvider.singleton(LocalDate.of(2022, 11, 7));
        Theater theater = new Theater(provider);
        Reservation reservation = theater.reserve(john, 9, 4);
        assertEquals(reservation.totalFee(), 32);
    }

    @Test
    void throwIllegalArgumentException(){
        LocalDateProvider provider = LocalDateProvider.singleton();
        Theater theater = new Theater(provider);
        Reservation reservation = theater.reserve(john, 11, 4);
        assertThrows(IllegalStateException.class);
    }


}
