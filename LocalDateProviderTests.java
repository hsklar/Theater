package com.jpmc.theater;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {

    @After
    public void destroyDate(){
        provider.destroy()
    }

    @Test
    void makeSureCurrentTime() {
        LocalDateProvider provider = LocalDateProvider.singleton()
        assertEquals(LocalDate.now(), provider.currentDate());
    }

    void makeSureSetTime(){
        LocalDateProvider provider = LocalDateProvider.singleton(LocalDate.of(2022, 11, 3));
        assertEquals(LocalDate.of(2022, 11, 3), provider.currentDate());
    }



}
