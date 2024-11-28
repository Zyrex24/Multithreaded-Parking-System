package classes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import classes.semaphore;
import io.Logger;

public class Parking {
    private final semaphore parkingSpots;
    private int occupiedSpots = 0;
    private final Queue<Integer> carsServedByGate = new LinkedList<>();
    private int totalCarsServed = 0;
    private static Parking instance;

    private Parking(int totalSpots) {
        this.parkingSpots = new semaphore(totalSpots);
    }

    public static synchronized Parking getInstance() {
        if (instance == null) {
            instance = new Parking(4); // Initialize with 4 spots
        }
        return instance;
    }

    public boolean parkCar(Car car) {
        if (parkingSpots.tryacquire()) { // Try to acquire a spot
            synchronized (this) {
                occupiedSpots++;
                totalCarsServed++;
                carsServedByGate.add(car.getGateId());
                Logger.log("Car " + car.getCarId() + " from Gate " + car.getGateId()
                        + " parked. (Parking Status: " + occupiedSpots + " spots occupied)");
            }
            return true;
        }
        return false;
    }

    public void waitForSpot(Car car) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        parkingSpots.Acquire(totalCarsServed);
        ; // Block until a spot is available
        synchronized (this) {
            occupiedSpots++;
            totalCarsServed++;
            carsServedByGate.add(car.getGateId());
            long waitTime = System.currentTimeMillis() - startTime;
            Logger.log("Car " + car.getCarId() + " from Gate " + car.getGateId()
                    + " parked after waiting. (Parking Status: " + occupiedSpots + " spots occupied)");
        }
    }

    public void leaveCar(Car car, int parkingDuration) {
        synchronized (this) {
            occupiedSpots--;
            parkingSpots.Release();
            Logger.log("Car " + car.getCarId() + " from Gate " + car.getGateId()
                    + " left after " + parkingDuration + " units of time. (Parking Status: " + occupiedSpots
                    + " spots occupied)");
        }
    }

    public synchronized void printSimulationDetails() {
        System.out.println("\nSimulation Details:");
        System.out.println("Total Cars Served: " + totalCarsServed);
        System.out.println("Current Cars in Parking: " + occupiedSpots);
        Map<Integer, Integer> gateCounts = new HashMap<>();
        for (int gateId : carsServedByGate) {
            gateCounts.put(gateId, gateCounts.getOrDefault(gateId, 0) + 1);
        }
        System.out.println("Details:");
        for (Map.Entry<Integer, Integer> entry : gateCounts.entrySet()) {
            System.out.println("- Gate " + entry.getKey() + " served " + entry.getValue() + " cars.");
        }
    }
}