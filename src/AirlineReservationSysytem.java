
import java.util.concurrent.Semaphore;

/**
 * Methods
 */
public class AirlineReservationSysytem implements Runnable{

    private Semaphore writerSemaphore;
    private Semaphore readerSemaphore;
    
    public int userID;
    public int requestSead; // istenen kultuk numarsı.
    public String seatStatus;
    public int[] seats;

    public AirlineReservationSysytem(Semaphore writerSemaphore , Semaphore readerSemaphore, int userID, int requestSead, int[] seats ){
        this.writerSemaphore = writerSemaphore;
        this.readerSemaphore = readerSemaphore;
        this.userID = userID;
        this.requestSead = requestSead;
        this.seats = seats;
    }
    
    // Writer
    private void makeReservation(){   
        try{
            writerSemaphore.acquire(); // Yazar semaforunu elde et.
            System.out.println("Time: "+ java.time.LocalTime.now());
            System.out.println("Writer "+ userID + " tire to book the seat " + requestSead);

            if(seats[requestSead] == 0){ // Kolduk durumu 0 ise (boş) satın al ve durumu 1'e çak.
                seats[requestSead] = 1;  // o kultuğu durumu 1 olduğundan başka bir writer tarafından alınamaz durumdadır.
                System.out.println("Writer "+ userID + " booked sead number " + requestSead + " successfully");

            }else if(seats[requestSead] == 1){
                seatStatus = "bookedUp";
                System.out.println("Writer "+ userID + " could not booked seat number" + requestSead + " since it has been already booked"); 
            }
            System.out.println("-------------------------------------------");
            ReaderThread(); // Her write işlemden sora Reader son durumu görmek için Reader() fonksiyonu burda yerleştridim.

        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
          writerSemaphore.release(); // Semaforu serbest bırak.
        }

    }

    // Reader
    private void ReaderThread() {
        try{  
            readerSemaphore.acquire(); // Okuyucu semaforunu elde et
            System.out.println("Time: "+ java.time.LocalTime.now());
            System.out.println("Reader "+ userID + " looks for available seats. State of the seat are: ");
            for(int i = 0; i < seats.length; i++){
                System.out.println("seat No " + i + " : " + seats[i]);
            }
            System.out.println("\n"+"-------------------------------------");

        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally{
            readerSemaphore.release();   // Semaforu serbest bırak.
        }
    }

    @Override
    public void run() {
      try {
          makeReservation(); // Koltuğu al ve ardından bilet durumunu göster
          Thread.sleep(500);
      }catch (Exception e) {
          e.printStackTrace();
      }
    }


}