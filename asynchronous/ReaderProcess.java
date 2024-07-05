import java.util.concurrent.CompletableFuture;

class ReaderProcess {
  private final int userID;
  private final int[] seats;

  public ReaderProcess(int userID, int[] seats) {
      this.userID = userID;
      this.seats = seats;
  }

  public CompletableFuture<Void> read() {
      return CompletableFuture.runAsync(() -> {
          System.out.println("Time: " + java.time.LocalTime.now());
          System.out.println("Reader " + userID + " looks for available seats. State of the seats are: ");
          for (int i = 0; i < seats.length; i++) {
              System.out.println("Seat No " + i + " : " + seats[i]);
          }
          System.out.println("-------------------------------------------\n");
      });
  }
}