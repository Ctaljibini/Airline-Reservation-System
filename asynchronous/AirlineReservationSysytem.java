import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirlineReservationSysytem {
        public static void main(String[] args) {
            int[] seats = new int[5];
    
            ReaderProcess reader1 = new ReaderProcess(1, seats);
            ReaderProcess reader2 = new ReaderProcess(2, seats);
    
            WriterProcess writer1 = new WriterProcess(1, 0, seats);
            WriterProcess writer2 = new WriterProcess(2, 1, seats);
    
            ExecutorService executor = Executors.newFixedThreadPool(4);
    
            CompletableFuture<Void> combinedFuture = writer1.write()
                .thenCompose(aVoid -> reader1.read())
                .thenCompose(aVoid -> writer2.write())
                .thenCompose(aVoid -> reader2.read());
    
            combinedFuture.join();
            executor.shutdown();
        }
    }