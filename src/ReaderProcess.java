import java.util.concurrent.Semaphore;

class ReaderProcess implements Runnable {

    private final Semaphore readerSemaphore;
    private final Semaphore writerSemaphore;
    private final int userID;
    private final int[] seats;

    public ReaderProcess(Semaphore readerSemaphore, Semaphore writerSemaphore, int userID, int[] seats) {
        this.readerSemaphore = readerSemaphore;
        this.writerSemaphore = writerSemaphore;
        this.userID = userID;
        this.seats = seats;
    }

    @Override
    public void run() {
        try {
            readerSemaphore.acquire();  //To ensure that only one reader enters the critical saction.
            writerSemaphore.acquire();  //Lock the writer while reading so that we can read correct information

            // Reading
            System.out.println("Time: " + java.time.LocalTime.now());
            System.out.println("Reader " + userID + " looks for available seats. State of the seats are: ");
            for (int i = 0; i < seats.length; i++) {
                System.out.println("Seat No " + i + " : " + seats[i]);
            }
            System.out.println("-------------------------------------------\n");

            // Exit the critical section.
            writerSemaphore.release();
            readerSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
