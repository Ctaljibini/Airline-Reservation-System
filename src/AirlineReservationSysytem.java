import java.nio.channels.WritableByteChannel;
import java.util.concurrent.Semaphore;

/**
 * Methods
 */
public class AirlineReservationSysytem implements Runnable{
    private static final int MAX_CONCURRENT_WRITERS = 1;  // Maksimum aynı anda yazan sayısı.  
    private static final int MAX_CONCURRENT_READERS = 1;  // Maksimum aynı anda okuyna sayısı.

    private Semaphore writerSemaphore;
    private Semaphore readerSemaphore;
    
    public int userID;
    public int requestSead; // istenen kultuk numaraı.
    public int orderOfOperation; // işlem sırası.
    public String seatStatus;
    public int[] seats;

    public AirlineReservationSysytem(Semaphore writerSemaphore , Semaphore readerSemaphore, int userID, int requestSead, int orderOfOperation, int[] seats ){
        this.writerSemaphore = writerSemaphore;
        this.readerSemaphore = readerSemaphore;
        this.userID = userID;
        this.requestSead = requestSead;
        this.orderOfOperation = orderOfOperation;
        this.seats = seats;
    }
    
    // Writer
    private void makeReservation(){   
        try{
            writerSemaphore.acquire(); // Yazar semaforunu elde et.
            System.out.println("Time: "+ java.time.LocalTime.now());
            System.out.println("Writer "+ userID + " tire to book the seat" + requestSead);

            if(seats[requestSead] == 0){ // Kolduk durumu 0 ise (boş) satın al ve durumu 1'e çak.
                seats[requestSead] = 1;  // o kultuğu durumu 1 olduğundan başka bir writer tarafından alınamaz durumdadır.
                System.out.println("Writer "+ userID + " booked sead number " + requestSead + " successfully");

            }else if(seats[requestSead] == 1){
                seatStatus = "bookedUp";
                System.out.println("Writer "+ userID + "could not booked seat number" + requestSead + " since it has been already booked"); 
            }
            System.out.println("-------------------------------------------");

        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
          writerSemaphore.release(); // Semaforu serbest bırak.
        }
    }

    @Override
    public void run() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'run'");
    }


}