package classes;

import io.Logger;
import io.Statistics;
import java.util.concurrent.Semaphore;

public class Parking {
    private final Semaphore parkingSpots;
    private static Parking instance;
    private int occupiedSpots = 0;

    private Parking(int totalSpots) {
        this.parkingSpots = new Semaphore(totalSpots);
    }

    public static synchronized Parking getInstance() {

        return instance;
    }

    public boolean parkCar(Car car) throws InterruptedException {
        return false;
    }

    public void waitForSpot(Car car) throws InterruptedException {

    }

    public synchronized void leaveCar(Car car, int parkingDuration) {

    }

    public synchronized int getOccupiedSpots() {
        return occupiedSpots;
    }
}
