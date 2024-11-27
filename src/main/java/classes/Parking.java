package classes;

import io.Logger;
import io.Statistics;
import java.util.concurrent.Semaphore;

public class Parking {
    private final semaphore parkingSpots;
    private static Parking instance;
    private int occupiedSpots = 0;

    public Parking(int totalSpots) {
        this.parkingSpots = new semaphore(totalSpots);
    }

    public static synchronized Parking getInstance() {
        if (instance == null) {
            instance = new Parking(4); // Initialize with 4 spots
        }
        return instance;
    }

    // In Parking.java
    public boolean parkCar(Car car) throws InterruptedException {
        if (parkingSpots.tryacquire()) {
            synchronized (this) {
                occupiedSpots++;
                Statistics.carEntered(car.getGateId(), car.getCarId(), 0); // No wait time if acquired immediately
                Logger.log("Car " + car.getCarId() + " from Gate " + car.getGateId() + " parked. (Parking Status: "
                        + occupiedSpots + " spots occupied)");
            }
            return true;
        }
        return false;
    }

    public void waitForSpot(Car car) throws InterruptedException {
        parkingSpots.Acquire(occupiedSpots);
        ; // Wait until a spot is available
        synchronized (this) {
            occupiedSpots++;
            long waitTime = System.currentTimeMillis();
            Statistics.carEntered(car.getGateId(), car.getCarId(), waitTime);
            Logger.log("Car " + car.getCarId() + " from Gate " + car.getGateId()
                    + " parked after waiting. (Parking Status: " + occupiedSpots + " spots occupied)");
        }
    }

    public synchronized void leaveCar(Car car, int parkingDuration) {
        parkingSpots.Release();
        synchronized (this) {
            occupiedSpots--;
            Logger.log("Car " + car.getCarId() + " from Gate " + car.getGateId()
                    + " left after " + parkingDuration + " units of time. (Parking Status: " + occupiedSpots
                    + " spots occupied)");
            Statistics.carExited(car.getCarId());
        }
    }

    public synchronized int getOccupiedSpots() {
        return occupiedSpots;
    }
}