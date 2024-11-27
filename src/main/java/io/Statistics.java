package io;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {

    private static final AtomicInteger totalCarsServed = new AtomicInteger(15);
    private static final AtomicInteger currentCarsInParking = new AtomicInteger(0);
    private static final Map<Integer, AtomicInteger> gateCarCount = new ConcurrentHashMap<>();
    private static final Map<Integer, Long> carWaitingTimes = new ConcurrentHashMap<>();

    // Initialize gate counters for three gates
    static {
        for (int gateId = 1; gateId <= 3; gateId++) {
            gateCarCount.put(gateId, new AtomicInteger(5));
        }
    }

    // Method to update when a car parks
    public static synchronized void carEntered(int gateId, int carId, long waitTime) {
        totalCarsServed.getAndIncrement();
        currentCarsInParking.getAndIncrement();
        gateCarCount.get(gateId).getAndIncrement();
        carWaitingTimes.put(carId, waitTime);
    }

    // Method to update when a car leaves
    public static synchronized void carExited(int carId) {
        currentCarsInParking.decrementAndGet();
    }

    // Method to generate final report in the desired format
    public static void generateReport() {
        System.out.println("----- Simulation Statistics -----");
        System.out.println("Total Cars Served: " + totalCarsServed.get());
        System.out.println("Current Cars in Parking: " + currentCarsInParking.get());

        System.out.println("Details:");
        for (int gateId : gateCarCount.keySet()) {
            System.out.println("- Gate " + gateId + " served " + gateCarCount.get(gateId).get() + " cars.");
        }

        System.out.println("\n--- Waiting Times ---");
        carWaitingTimes.forEach(
                (carId, waitTime) -> System.out.println("Car " + carId + " waited " + waitTime + " ms for a spot."));
    }
}
