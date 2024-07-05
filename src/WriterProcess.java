
import java.util.concurrent.Semaphore;

/**
 * WriterProcess
 */
public class WriterProcess implements Runnable{

    Semaphore writerSemaphore;
    Semaphore readerSemaphore;
    private int userID;
    private int requestSeat;
    private int[] seats;

    public WriterProcess(Semaphore writerSemaphore,  int userID, int requestSeat, int[] seats){
        this.writerSemaphore = writerSemaphore;
        this.userID = userID;
        this.requestSeat = requestSeat;
        this.seats = seats;
    }  
    @Override
    public void run(){
        try{
            writerSemaphore.acquire(); // To ensure that one writer enters the critical section.
            
            System.out.println("Time: "+ java.time.LocalTime.now());
            System.out.println("Writer "+ userID + " tries to book the seat " + requestSeat + " ...");

            if(seats[requestSeat] == 0){ // If the seat status is 0 (empty), buy it and set the status to 1.
                seats[requestSeat] = 1;  // Since that seat status is 1, it cannot be retrieved by another writer.
                System.out.println("Writer "+ userID + " booked seat number " + requestSeat + " successfully.");

            }else if(seats[requestSeat] == 1){
                //seatStatus = "bookedUp";
                System.out.println("Writer "+ userID + " could not booked seat number " + requestSeat + " since it has been already booked."); 
            }
            System.out.println("*******************************************");
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            writerSemaphore.release(); // Exit the critical section. 
        }
    } 
}