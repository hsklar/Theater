import java.time.LocalDate;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;
    private static LocalDate localDate = null;

    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
        return instance;
    }
    // constructor to be used in testing
    public static LocalDateProvider singleton(LocalDate localDate) {
        if (instance == null) {
            instance = new LocalDateProvider();
            localDate = localDate;
        }
        return instance;
    }

    public LocalDate currentDate() {
        return localDate == null ?  LocalDate.now() : localDate;
    }

    public void destroy(){
        this.instance = null;
    }
}