import java.util.concurrent.Semaphore;
/**
 * Main
 */
public class Main {
    private static final int MAX_CONCURRENT_WRITERS = 1;  // Maksimum aynı anda yazan sayısı.  
    private static final int MAX_CONCURRENT_READERS = 1;  // Maksimum aynı anda okuyna sayısı.

    public static void main(String[] args) throws InterruptedException {
        
        int[] seats = new int[5];
        Semaphore writerSemaphore = new Semaphore(MAX_CONCURRENT_WRITERS);
        Semaphore readerSemaphore = new Semaphore(MAX_CONCURRENT_READERS);

        AirlineReservationSysytem request1 = new AirlineReservationSysytem(writerSemaphore, readerSemaphore, 0, 0, seats);
        AirlineReservationSysytem request2 = new AirlineReservationSysytem(writerSemaphore, readerSemaphore, 1, 1, seats);
        AirlineReservationSysytem request3 = new AirlineReservationSysytem(writerSemaphore, readerSemaphore, 1, 1, seats);

        Thread thread1 = new Thread(request1);
        Thread thread2 = new Thread(request2);
        Thread thread3 = new Thread(request3);

        thread1.start();
        thread2.start();
        thread3.start();


    }
}