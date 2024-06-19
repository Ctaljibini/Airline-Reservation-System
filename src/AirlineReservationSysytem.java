
import java.util.concurrent.Semaphore;

/**
 * AirlineReservationSysytem
 */
public class AirlineReservationSysytem implements Runnable{

    private Semaphore writerSemaphore;
    private Semaphore readerSemaphore;
    
    public int userID;
    public int requestSeat; // istenen kultuk numarsı.
    public String seatStatus;
    public int[] seats;

    public AirlineReservationSysytem(Semaphore writerSemaphore , Semaphore readerSemaphore, int userID, int requestSeat, int[] seats ){
        this.writerSemaphore = writerSemaphore;
        this.readerSemaphore = readerSemaphore;
        this.userID = userID;
        this.requestSeat = requestSeat;
        this.seats = seats;
    }
    
    // Writer
    private synchronized void makeReservation(){   
        try{
            writerSemaphore.acquire(); // Yazar semaforunu elde et.
            System.out.println("Time: "+ java.time.LocalTime.now());
            System.out.println("Writer "+ userID + " tries to book the seat " + requestSeat + " ...");

            if(seats[requestSeat] == 0){ // Kolduk durumu 0 ise (boş) satın al ve durumu 1'e çak.
                seats[requestSeat] = 1;  // o kultuğu durumu 1 olduğundan başka bir writer tarafından alınamaz durumdadır.
                System.out.println("Writer "+ userID + " booked seat number " + requestSeat + " successfully.");

            }else if(seats[requestSeat] == 1){
                seatStatus = "bookedUp";
                System.out.println("Writer "+ userID + " could not booked seat number" + requestSeat + " since it has been already booked."); 
            }
            System.out.println("*******************************************");
            ReaderThread(); // Her write işlemden sora Reader son durumu görmek için Reader() fonksiyonu burda yerleştridim.

        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
          writerSemaphore.release(); // Semaforu serbest bırak.
        }

    }

    // Reader
    private synchronized void ReaderThread() {
        try{  
            readerSemaphore.acquire(); // Okuyucu semaforunu elde et
            System.out.println("Time: "+ java.time.LocalTime.now());
            System.out.println("Reader "+ userID + " looks for available seats. State of the seat are: ");
            for(int i = 0; i < seats.length; i++){
                System.out.println("seat No " + i + " : " + seats[i]);
            }
            System.out.println("-------------------------------------------\n");

        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally{
            readerSemaphore.release();   // Semaforu serbest bırak.
        }
    }

    @Override
    public synchronized void run() {
      try {
          makeReservation(); // Koltuğu al ve ardından bilet durumunu göster
          //Thread.sleep(500);
      }catch (Exception e) {
          e.printStackTrace();
      }
    }
}



