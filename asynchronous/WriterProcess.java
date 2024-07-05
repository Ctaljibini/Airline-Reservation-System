import java.util.concurrent.CompletableFuture;

class WriterProcess {
  private final int userID;
  private final int requestSeat;
  private final int[] seats;

  public WriterProcess(int userID, int requestSeat, int[] seats) {
      this.userID = userID;
      this.requestSeat = requestSeat;
      this.seats = seats;
  }

  public CompletableFuture<Void> write() {
      return CompletableFuture.runAsync(() -> {
          System.out.println("Time: " + java.time.LocalTime.now());
          System.out.println("Writer " + userID + " tries to book the seat " + requestSeat + " ...");

          if (seats[requestSeat] == 0) {
              seats[requestSeat] = 1;
              System.out.println("Writer " + userID + " booked seat number " + requestSeat + " successfully.");
          } else {
              System.out.println("Writer " + userID + " could not book seat number " + requestSeat + " since it has been already booked.");
          }
          System.out.println("*******************************************");
      });
  }
}
