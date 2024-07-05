import java.util.concurrent.Semaphore;

public class AirlineReservationSysytem {
    private static final int MAX_CONCURRENT_WRITERS = 1;  // Maximum number of simultaneous wirters.  
    private static final int MAX_CONCURRENT_READERS = 1;  // Maximum number of simultaneous readers.
    public static void main(String[] args) throws InterruptedException {
        Semaphore writerProcess = new Semaphore(MAX_CONCURRENT_WRITERS);
        Semaphore readerProcess = new Semaphore(MAX_CONCURRENT_READERS);
        
        int[] seats = new int[5];  // Number of seats. 

                
        WriterProcess writer1 = new WriterProcess(writerProcess, 1, 1, seats); //The writer1 wants to reserve seat 0.
        WriterProcess writer2 = new WriterProcess(writerProcess, 2, 2, seats); //The writer5 wants to reserve seat 4.
        WriterProcess writer3 = new WriterProcess(writerProcess, 3, 2, seats); // writer3 trying to reserve the seat reserved by wrider5.

        ReaderProcess reader1 = new ReaderProcess(readerProcess, writerProcess, 1, seats); // The reader1 wants to see the condition of the seats
        ReaderProcess reader2 = new ReaderProcess(readerProcess, writerProcess, 2, seats); // The reader3 wants to see the condition of the seats
        
        Thread t1 = new Thread(writer1); 
        Thread t2 = new Thread(reader2);
        Thread t3 = new Thread(writer2);
        Thread t4 = new Thread(writer3);
        Thread t5 = new Thread(reader1);
        
        
        t1.start(); 
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        
    }
  
}
