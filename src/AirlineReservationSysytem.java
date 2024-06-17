import java.util.concurrent.Semaphore;

/**
 * Methods
 */
public class AirlineReservationSysytem implements Runnable{
    private static final int MAX_CONCURRENT_WRITERS = 1;  // Maksimum aynı anda yazan sayısı.  
    private static final int MAX_CONCURRENT_READERS = 1;  // Maksimum aynı anda okuyna sayısı.

    private Semaphore wirtSemaphore;
    private Semaphore readSemaphore;
    
    public int userID;
    public int requestSead; // istek
    public int orderOfOperation; // işlem sırası.
    public String seatStatus;
    public int[] seat;


    @Override
    public void run() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'run'");
    }


}