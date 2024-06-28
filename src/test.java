import java.util.concurrent.Semaphore;

class SharedResource {
    // Paylasılan kaynak
    public void use() {
        System.out.println(Thread.currentThread().getName() + " kaynagi kullaiyor...");
        try {
            Thread.sleep(2000); // Kaynağın kullanımını simüle etme
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " kaynagi kullanmayi bitirdi.");
    }
}

class Worker extends Thread {
    private Semaphore semaphore;
    private SharedResource resource;

    public Worker(Semaphore semaphore, SharedResource resource) {
        this.semaphore = semaphore;
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            // Semafor bekleme (wait)
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " erisim izni wait()");

            // cretical sectione 
            resource.use();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Semafor signal
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " erisim izni signal() .");
        }
    }
}

public class test {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        SharedResource resource = new SharedResource();


        Worker worker1 = new Worker(semaphore, resource);
        Worker worker2 = new Worker(semaphore, resource);
        Worker worker3 = new Worker(semaphore, resource);

        
        worker1.start();
        worker2.start();
        worker3.start();
    }
}
