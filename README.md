# Airline Reservation System

## Overview

This project demonstrates a classic reader-writer synchronization problem using Java's `Semaphore` class. The aim is to ensure that multiple readers and writers can access shared resources (in this case, an array representing seats) without causing data inconsistency. The readers and writers are designed to operate such that no two readers or writers can perform their operations simultaneously, ensuring synchronized access.

## Features

- Synchronized access for multiple readers.
- Synchronized access for multiple writers.
- Prevention of read-write and write-read conflicts.
- Demonstrates the use of Java concurrency utilities.


## Project Structure

The project consists of three main classes:

1. **ReaderProcess**: Represents a reader that attempts to read the state of the seats.
2. **WriterProcess**: Represents a writer that attempts to book a seat.
3. **AirlineReservationSysytem**: The entry point of the program, which initializes and starts reader and writer threads.

## Classes and Methods

- The main goal is to protect the critical area (the part that modifies the shared data) from the entry of more than one process.

```java
public class classes implements Runabale{
    @overrid
    public run(){
        readerSemaphore.acquire();
        writerSemaphore.acquire();

        // critical section.

        writerSemaphore.release();
        readerSemaphore.release();
    }
}
```
## Run
 - The method of running the program is manual, by creating an object from the writer (or reader) and then running it using Threads.
 ```java
 public class AirlineReservationSysytem{
    public void main(String[] args){
        // Maximum number of simultaneous readters and writers
        Semapfore writerProcess = new Semaphore(1);  
        Semaphore readerProcess = new Semaphore(1);

        int[] seats = new int[5];  // Number of seats.

        // Create objects from the writerProcess and send the wrider id and information about the seat to be reserved
        WriterProcess writer1 = new WriterProcess(writerProcess, 1, 0, seats); //The writer1 wants to reserve seat 0.

        // Create objects from the readerProcess and send the reader id and writer status (To ensure that it does not enter the critical section while reading)
        ReaderProcess reader1 = new ReaderProcess(readerProcess, writerProcess, 1, seats); // The reader1 wants to see the condition of the seats

        // Assign the running task to Thread.
        Thread t1 = new Thread(writer1); 
        Thread t2 = new Thread(reader1);


        // Startup by Thread.
        t1.start();
        t2.start();

        t1.join();
        t2.joim();

    }
}
```

## Output
- The result of operating a writer1 and reader1
```
Time: 22:59:17.690750400
Writer 1 tries to book the seat 0 ...
Writer 1 booked seat number 0 successfully.
*******************************************
Time: 22:59:17.707750
Reader 1 looks for available seats. State of the seats are:
Seat No 0 : 1
Seat No 1 : 0
Seat No 2 : 0
Seat No 3 : 0
Seat No 4 : 0
-------------------------------------------
```
- The result of operating more than one writer who wants to reserve the same seat.
```
Time: 23:04:33.373481100
Writer 1 tries to book the seat 1 ...
Writer 1 booked seat number 1 successfully.
*******************************************
Time: 23:04:33.391479500
Writer 2 tries to book the seat 1 ...
Writer 2 could not booked seat number 1 since it has been already booked.
*******************************************
Time: 23:04:33.393483100
Reader 1 looks for available seats. State of the seats are:
Seat No 0 : 0
Seat No 1 : 1
Seat No 2 : 0
Seat No 3 : 0
Seat No 4 : 0
-------------------------------------------
```
-The result of employing more than one writer and more than one reader.
```
Time: 23:07:36.005196
Writer 1 tries to book the seat 1 ...
Writer 1 booked seat number 1 successfully.
*******************************************
Time: 23:07:36.024195500
Writer 2 tries to book the seat 2 ...
Writer 2 booked seat number 2 successfully.
*******************************************
Time: 23:07:36.027198100
Reader 2 looks for available seats. State of the seats are:
Seat No 0 : 0
Seat No 1 : 1
Seat No 2 : 1
Seat No 3 : 0
Seat No 4 : 0
-------------------------------------------

Time: 23:07:36.038196100
Writer 3 tries to book the seat 2 ...
Writer 3 could not booked seat number 2 since it has been already booked.
*******************************************
Time: 23:07:36.041195600
Reader 1 looks for available seats. State of the seats are:
Seat No 0 : 0
Seat No 1 : 1
Seat No 2 : 1
Seat No 3 : 0
Seat No 4 : 0
-------------------------------------------
```