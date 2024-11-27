package classes;

import io.Logger;

public class Car extends Thread {
    private final int carId;
    private final int arrivalTime;
    private final int parkingDuration;
    private final Parking parkingLot;
    private int gateId;

    public Car(int carId, int arrivalTime, int parkingDuration, Parking parkingLot) {
        this.carId = carId;
        this.arrivalTime = arrivalTime;
        this.parkingDuration = parkingDuration;
        this.parkingLot = parkingLot;
    }

    public int getCarId() {
        return carId;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    // In Car.java
    @Override
    public void run() {
        try {
            // Simulate arrival delay
            Thread.sleep(arrivalTime * 1000L);
            Logger.log("Car " + carId + " from Gate " + gateId + " arrived at time " + arrivalTime);

            // Attempt to park, logging if waiting or parked
            boolean parked = parkingLot.parkCar(this);
            if (!parked) {
                Logger.log("Car " + carId + " from Gate " + gateId + " waiting for a spot.");
                parkingLot.waitForSpot(this);
            }

            // Simulate parking duration
            Thread.sleep(parkingDuration * 1000L);

            // Leave the parking lot and log the departure
            parkingLot.leaveCar(this, parkingDuration);
        } catch (InterruptedException e) {
            Logger.log("Car " + carId + " was interrupted.");
        }
    }

}