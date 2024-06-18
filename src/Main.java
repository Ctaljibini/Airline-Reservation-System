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


        AirlineReservationSysytem request0 = new AirlineReservationSysytem(writerSemaphore, readerSemaphore, 0, 0, seats);
        AirlineReservationSysytem request1 = new AirlineReservationSysytem(writerSemaphore, readerSemaphore, 1, 1, seats);
        AirlineReservationSysytem request2 = new AirlineReservationSysytem(writerSemaphore, readerSemaphore, 2, 1, seats);

        Thread thread0 = new Thread(request0);
        Thread thread1 = new Thread(request1);
        Thread thread2 = new Thread(request2);

        thread0.start();
        thread1.start();
        thread2.start(); 

        thread0.join();
        thread1.join();
        thread2.join(); 


    }
}